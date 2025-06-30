package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;

public class MainPage {
    UserManager userManager;

    public MainPage(UserManager userManager) {
        this.userManager = userManager;
    }

    public void createUI() {
        JFrame frame = new JFrame("Memory Game - Welcome");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(350, 300);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BorderLayout());

        // Title
        JLabel title = new JLabel("Welcome to Memory Game", JLabel.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 18));
        frame.add(title, BorderLayout.NORTH);

        // Center panel (vertical stack)
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Name input panel (no extra spacing)
        JPanel inputPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 0)); // vertical gap set to 0
        JLabel nameLabel = new JLabel("Enter your name:");
        JTextField nameField = new JTextField(20);
        inputPanel.add(nameLabel);
        inputPanel.add(nameField);
        centerPanel.add(inputPanel);

        // Leaderboard title
        JLabel titleLeaderBoard = new JLabel("Leaderboard", JLabel.CENTER);
        titleLeaderBoard.setFont(new Font("Arial", Font.BOLD, 16));
        titleLeaderBoard.setAlignmentX(Component.CENTER_ALIGNMENT);
        centerPanel.add(titleLeaderBoard);

        // User list display
        List<User> sortedUsers = userManager.getUsers();
        DefaultListModel<String> listModel = new DefaultListModel<>();
        for (User user : sortedUsers) {
            listModel.addElement(String.format("%-15s : %d", user.getName(), user.getScore()));
        }

        JList<String> userList = new JList<>(listModel);
        userList.setFont(new Font("Monospaced", Font.PLAIN, 14));
        userList.setVisibleRowCount(5);
        userList.setEnabled(false);

        JScrollPane scrollPane = new JScrollPane(userList);
        scrollPane.setPreferredSize(new Dimension(300, 100));
        centerPanel.add(scrollPane);
        frame.add(centerPanel, BorderLayout.CENTER);

        JButton startButton = new JButton("Start Game");
        startButton.addActionListener((ActionEvent e) -> {
            String playerName = nameField.getText().trim();
            User user = userManager.getOrCreateUser(playerName);
            if (playerName.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Please enter your name.");
            } else {
                frame.dispose();
                new MatchCards(user);
            }
        });
        frame.add(startButton, BorderLayout.SOUTH);

        frame.setVisible(true);
    }
}
