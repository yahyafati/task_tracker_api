package com.yahya.task.tracker.tasktracker.security.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yahya.task.tracker.tasktracker.model.security.Authority;
import io.jsonwebtoken.*;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.crypto.SecretKey;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class JwtTokenVerifier extends OncePerRequestFilter {

    private final SecretKey secretKey;
    private final JwtConfig jwtConfig;

    @Autowired
    public JwtTokenVerifier(SecretKey secretKey, JwtConfig jwtConfig) {
        this.secretKey = secretKey;
        this.jwtConfig = jwtConfig;
    }


    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    @NonNull HttpServletResponse response,
                                    @NonNull FilterChain filterChain) throws ServletException, IOException {

        String authorization = request.getHeader(jwtConfig.getAuthorizationHeader());
        if (authorization == null || authorization.isEmpty() || !authorization.startsWith(jwtConfig.getTokenPrefix())) {
            filterChain.doFilter(request, response);
//            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
//            throw new IllegalStateException("No Token has been given.");
            return;
        }

        String token = authorization.replace(jwtConfig.getTokenPrefix(), "");
        try {

            final JwtParser parser = Jwts.parserBuilder()
                    .setSigningKey(secretKey)
                    .build();

            final Jws<Claims> claimsJws = parser.parseClaimsJws(token);

            final Claims body = claimsJws.getBody();
            final String username = body.getSubject();

            List<Map<String, Object>> authoritiesMapList = (List<Map<String, Object>>) body.get("authorities");


            final List<Authority> authorities = authoritiesMapList
                    .stream()
                    .map(stringObjectMap -> new ObjectMapper().convertValue(stringObjectMap, Authority.class))
                    .collect(Collectors.toList());

            final Authentication authentication = new UsernamePasswordAuthenticationToken(
                    username, null, authorities
            );

            SecurityContextHolder.getContext().setAuthentication(authentication);
        } catch (JwtException ex) {
            throw new IllegalStateException(String.format("Token %s can not be truest", token));
        }
        filterChain.doFilter(request, response);
    }
}
