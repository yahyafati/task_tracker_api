package com.yahya.task.tracker.tasktracker.model.helper;

import com.yahya.task.tracker.tasktracker.model.User;
import com.yahya.task.tracker.tasktracker.model.Profile;
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

    public UserMeta(User user) {
        this.username = user.getUsername();

        final Profile profile = user.getProfile();
        if (profile != null) {
            this.firstName = profile.getFirstName();
            this.lastName = profile.getLastName();
            this.email = profile.getEmail();
            this.phone = profile.getPhone();
        }
    }

}
