package com.yahya.task.tracker.tasktracker.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.yahya.task.tracker.tasktracker.model.helper.UserMeta;
import com.yahya.task.tracker.tasktracker.model.security.Role;
import lombok.*;
import org.checkerframework.common.value.qual.StringVal;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Getter @Setter @ToString
@Builder @NoArgsConstructor @AllArgsConstructor
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    @JsonIgnore @ToString.Exclude
    private String password;

    private boolean isAccountNonExpired = true;
    private boolean isAccountNonLocked = true;
    private boolean isCredentialsNonExpired = true;
    private boolean isEnabled = true;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore @ToString.Exclude
    private UserProfile userProfile;

    @ManyToOne
    private Role role;

    @Override
    @JsonIgnore
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return role.getAuthorities();
    }

    public void activate() {
        setAccountNonExpired(true);
        setAccountNonLocked(true);
        setEnabled(true);
        setCredentialsNonExpired(true);
    }

    public User(UserMeta userMeta) {
        this.username = userMeta.getUsername();
        this.password = userMeta.getPassword();
        this.userProfile = new UserProfile(userMeta);
    }

}
