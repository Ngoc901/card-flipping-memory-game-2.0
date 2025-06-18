package org.example;

public class User {
    private String name;
    private int score;

    public User() {
    }

    // Getters
    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }

    // Setters
    public void setName(String name) {
        this.name = name;
    }

    public void setScore(int score) {
        this.score = score;
    }

    // Optional: toString, equals, hashCode...
    @Override
    public String toString() {
        return "User{name='" + name + "', score=" + score + "}";
    }
}
