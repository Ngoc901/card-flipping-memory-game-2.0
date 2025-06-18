package org.example;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class UserManager {
    private final List<User> users = new ArrayList<>();
    private final File userFile = new File("data/users.json");
    private final ObjectMapper objectMapper = new ObjectMapper();

    public UserManager() {
        loadUsersFromFile();
    }

    private void loadUsersFromFile() {
        System.out.println("Looking for file at: " + userFile.getAbsolutePath());
        if (userFile.exists()) {
            try {
                List<User> loadedUsers = objectMapper.readValue(userFile, new TypeReference<List<User>>() {});
                users.addAll(loadedUsers);
                System.out.println("Loaded users: " + users);
            } catch (IOException e) {
                System.err.println("Failed to read users.json: " + e.getMessage());
            }
        } else {
            System.out.println("users.json not found!");
        }
    }

    private void saveUsersToFile() {
        try {
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(userFile, users);
        } catch (IOException e) {
            System.err.println("Failed to save users.json: " + e.getMessage());
        }
    }

    public List<User> getUsers() {
        List<User> sortedUsers = new ArrayList<>(users);
        sortedUsers.sort((u1, u2) -> Integer.compare(u2.getScore(), u1.getScore())); // descending
        return sortedUsers;
    }

    public User getOrCreateUser(String name) {
        for (User user : users) {
            if (user.getName().equalsIgnoreCase(name)) {
                return user;
            }
        }
        User newUser = new User();
        newUser.setName(name);
        newUser.setScore(0);
        users.add(newUser);
        return newUser;
    }

    public static void updateUser(User updatedUser) {
        ObjectMapper mapper = new ObjectMapper();
        File file = new File("data/users.json");

        try {
            // Load current users from file
            List<User> userList = mapper.readValue(file, new TypeReference<List<User>>() {});

            // Search and update
            boolean found = false;
            for (int i = 0; i < userList.size(); i++) {
                if (userList.get(i).getName().equalsIgnoreCase(updatedUser.getName())) {
                    userList.set(i, updatedUser);
                    found = true;
                    break;
                }
            }

            // if not found, add new user
            if (!found) {
                userList.add(updatedUser);
            }

            // Save updated list back to file
            mapper.writerWithDefaultPrettyPrinter().writeValue(file, userList);
        } catch (IOException e) {
            System.err.println("Error updating user: " + e.getMessage());
        }
    }

}
