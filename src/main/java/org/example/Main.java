package org.example;


import javax.swing.*;

public class Main {
    public static void main(String[] args) {
//        MatchCards matchCards = new MatchCards();
        UserManager userManager = new UserManager();
        SwingUtilities.invokeLater(() -> new MainPage(userManager).createUI());

    }

}
