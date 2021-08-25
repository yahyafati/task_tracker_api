package com.yahya.task.tracker.tasktracker.model.helper;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.yahya.task.tracker.tasktracker.model.User;
import com.yahya.task.tracker.tasktracker.model.UserProfile;
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

        final UserProfile userProfile = user.getUserProfile();
        if (userProfile != null) {
            this.firstName = userProfile.getFirstName();
            this.lastName = userProfile.getLastName();
            this.email = userProfile.getEmail();
            this.phone = userProfile.getPhone();
        }
    }

}
