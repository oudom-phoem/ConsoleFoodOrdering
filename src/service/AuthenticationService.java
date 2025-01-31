package service;

import model.User;
import exceptions.InvalidLoginException;

import java.util.ArrayList;
import java.util.List;

public class AuthenticationService {
    private List<User> users = new ArrayList<>();

    public AuthenticationService() {
        // Add default users
        users.add(new User("koko1", "password1"));
        users.add(new User("koko2", "password2"));
    }

    public boolean login(String username, String password) throws InvalidLoginException {
        for (User user : users) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                return true;
            }
        }
        throw new InvalidLoginException("Invalid username or password");
    }
}
