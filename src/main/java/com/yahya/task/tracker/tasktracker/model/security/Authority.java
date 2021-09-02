package com.yahya.task.tracker.tasktracker.model.security;

import com.yahya.task.tracker.tasktracker.security.Permission;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Authority implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false, unique = true)
    private String authority;

    @Override
    public String getAuthority() {
        return authority;
    }

    public Authority(String authority) {
        this.authority = authority;
    }

    public Authority(Permission permission) {
        this.authority = permission.getPermission();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Authority authority = (Authority) o;
        return Objects.equals(this.authority, authority.authority);
    }

    @Override
    public int hashCode() {
        return Objects.hash(authority);
    }
}
