package com.yahya.task.tracker.tasktracker.model.helper;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.yahya.task.tracker.tasktracker.model.User;
import com.yahya.task.tracker.tasktracker.model.Profile;
import com.yahya.task.tracker.tasktracker.model.security.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class UserMeta {

    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private Role role;

    public UserMeta(User user) {
        this.username = user.getUsername();
        this.role = user.getRole();
        final Profile profile = user.getProfile();
        if (profile != null) {
            this.firstName = profile.getFirstName();
            this.lastName = profile.getLastName();
            this.email = profile.getEmail();
            this.phone = profile.getPhone();
        }
    }

    public String getFullName() {
        return String.format("%s %s", firstName, lastName);
    }

}
