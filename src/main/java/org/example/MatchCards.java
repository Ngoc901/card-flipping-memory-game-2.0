package org.example;
import javax.sound.sampled.*;
import java.io.IOException;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;
import java.util.Random;


public class MatchCards {
    ArrayList<Card> cardSet;
    ArrayList<JButton> board;
    ImageIcon backCardImageIcon;
    Timer hideCardTimer;
    Timer gameTimer;
    Configs config = new Configs();

    User user;
    boolean gameReady = false;

    // board
    int score = 0;
    int lifetimeScore = 0;
    int errorCount = 0;
    int secondsElapsed = 0;
    int boardWidth = config.columns * config.cardWidth;
    int boardHeight = config.rows * config.cardHeight;

    // frame, error panel, restart button, card1 and card2
    JFrame frame = new JFrame("Memory Game");

    JMenuBar menuBar = new JMenuBar();
    JMenu gameMenu = new JMenu("Game");
    JMenuItem restartItem = new JMenuItem("Restart");
    JMenuItem exitItem = new JMenuItem("Exit");
    JMenu optionsMenu = new JMenu("Options");
    JMenuItem themeToggleItem = new JMenuItem("\uD83C\uDF11 Light Theme");
    JLabel userInfoLabel = new JLabel();
    JPanel rightPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 5, 0)); // 5px gap

    JLabel textLabel = new JLabel();
    JLabel scoreLabel = new JLabel();
    JLabel timeLabel = new JLabel();
    JLabel lifetimeScoreLabel = new JLabel();
    JPanel textPanel = new JPanel();
    JPanel boardPanel = new JPanel();
    JPanel restartGamePanel = new JPanel();
    JPanel changeThemesPanel = new JPanel();
    JButton restartButton = new JButton("Restart");
    JButton changeLightThemeButton = new JButton("Change Light Themes");
    JButton changeDarkThemeButton = new JButton("Change Dark Themes");
    JButton card1Selected;
    JButton card2Selected;

    Clip backgroundClip;

    MatchCards(User user){
        this.user = user;

        setupCard("light_theme", config.cardList);
        shuffleCard();


        restartItem.addActionListener(this::handleRestartClick);
        gameMenu.add(restartItem);
        exitItem.addActionListener(e -> {
            frame.dispose(); // Directly close this game's window
            SwingUtilities.invokeLater(() -> new MainPage(new UserManager()).createUI());
        });
        gameMenu.add(exitItem);

        themeToggleItem.setFont(new Font("Arial", Font.PLAIN, 14));
        themeToggleItem.addActionListener(e -> {
            if (themeToggleItem.getText().contains("\uD83C\uDF11")) {
                themeToggleItem.setText("\uD83C\uDF15 Dark Theme"); // 🌕
                setupCard("dark_theme", config.darkThemeCardList);
            } else {
                themeToggleItem.setText("\uD83C\uDF11 Light Theme"); // 🌙
                setupCard("light_theme", config.cardList);
            }
            handleRestartClick(null); // Apply theme
        });
        optionsMenu.add(themeToggleItem);

        userInfoLabel.setText(String.format("👤 %s  |  🏆 Score: %d", user.getName(), user.getScore()));
        userInfoLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        userInfoLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0)); // Remove padding
        userInfoLabel.setFocusable(false);
        userInfoLabel.setEnabled(false);
        userInfoLabel.setOpaque(false);
        rightPanel.setOpaque(false);
        rightPanel.add(userInfoLabel);

        menuBar.add(gameMenu);
        menuBar.add(optionsMenu);
        menuBar.add(Box.createHorizontalGlue());
        menuBar.add(rightPanel);
        frame.setJMenuBar(menuBar);


        // frame customizing
        frame.setLayout(new BorderLayout());
        frame.setSize(boardWidth, boardHeight);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // time label
        timeLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        timeLabel.setHorizontalAlignment(JLabel.CENTER);
        timeLabel.setText("Time: 0s");
        textPanel.add(timeLabel);

        // matched score label
        scoreLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        scoreLabel.setHorizontalAlignment(JLabel.CENTER);
        scoreLabel.setText("Matched: " + score);
        textPanel.add(scoreLabel);

        // lifetime score
        lifetimeScoreLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        lifetimeScoreLabel.setHorizontalAlignment(JLabel.CENTER);
        lifetimeScoreLabel.setText("Life Time Score: " + lifetimeScore);
        textPanel.add(lifetimeScoreLabel);

        // error text
        textLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        textLabel.setHorizontalAlignment(JLabel.CENTER);
        textLabel.setText("Misses: " + Integer.toString(errorCount));

        textPanel.setPreferredSize(new Dimension(boardWidth, 30));
        textPanel.add(textLabel);
        frame.add(textPanel, BorderLayout.NORTH);

        //add buttons to panel
        board = new ArrayList<JButton>();
        boardPanel.setLayout(new GridLayout(config.rows, config.columns));
        for (int i = 0; i < cardSet.size(); i++){
            JButton tile = new JButton();
            tile.setPreferredSize(new Dimension(config.cardWidth, config.cardHeight));
            tile.setOpaque(true);
            tile.setIcon(cardSet.get(i).cardImageIcon);
            tile.setFocusable(false);
            tile.addActionListener(this::handleCardClick);
            board.add(tile);
            boardPanel.add(tile);
        }

        frame.add(boardPanel, BorderLayout.CENTER);

        //restart game button
        restartButton.setFont(new Font("Arial", Font.PLAIN, 16));
        restartButton.setText("Restart");
        restartButton.setPreferredSize(new Dimension(boardWidth, 30));
        restartButton.setFocusable(false);
        restartButton.setEnabled(false);
        restartButton.addActionListener(this::handleRestartClick);
        restartGamePanel.add(restartButton);
        frame.add(restartGamePanel, BorderLayout.SOUTH);

        //change themes button
        changeLightThemeButton.setFont(new Font("Arial", Font.PLAIN, 16));
        changeLightThemeButton.setText("☆");
        changeLightThemeButton.setPreferredSize(new Dimension(30, 30));
        changeLightThemeButton.setFocusable(false);
        changeLightThemeButton.setEnabled(false);
        changeLightThemeButton.addActionListener(this::handleLightThemeChangeClick);

        changeDarkThemeButton.setFont(new Font("Arial", Font.PLAIN, 16));
        changeDarkThemeButton.setText("★");
        changeDarkThemeButton.setPreferredSize(new Dimension(30, 30));
        changeDarkThemeButton.setFocusable(false);
        changeDarkThemeButton.setEnabled(false);
        changeDarkThemeButton.addActionListener(this::handleDarkThemeChangeClick);

        changeThemesPanel.setLayout(new BoxLayout(changeThemesPanel, BoxLayout.Y_AXIS));
        changeThemesPanel.add(changeLightThemeButton);
        changeThemesPanel.add(changeDarkThemeButton);
//        frame.add(changeThemesPanel, BorderLayout.WEST);

        frame.pack();
        frame.setVisible(true);

        // game timer
        gameTimer = new Timer(1000, e -> {
            secondsElapsed++;
            timeLabel.setText("Time: " + secondsElapsed + "s");
            // NEW: Update final score display every second
        });

        // start game
        hideCardTimer = new Timer(1500, this::timerAction);
        hideCardTimer.setRepeats(false);
        hideCardTimer.start();
    }

    void setupCard(String pathTheme, String[] cardList){
        String path;
        cardSet = new ArrayList<Card>();
        for (String cardName : cardList) {
            path = "/images/"+ pathTheme +"/" + cardName + ".jpg";
            Image cardImg = new ImageIcon(getClass().getResource(path)).getImage();
            ImageIcon cardImageIcon = new ImageIcon(cardImg.getScaledInstance(config.cardWidth, config.cardHeight, java.awt.Image.SCALE_SMOOTH));

            // 50% chance for BonusCard, otherwise NormalCard
            Card card;
            Random random = new Random();
            if (random.nextBoolean()) {
                card = new BonusCard(cardName, cardImageIcon);
            } else {
                card = new NormalCard(cardName, cardImageIcon);
            }

            cardSet.add(card);
        }
        cardSet.addAll(cardSet);

        String pathBackImage = "/images/" + pathTheme + "/back.jpg";
        Image cardBackImg = new ImageIcon(getClass().getResource(pathBackImage)).getImage();
        backCardImageIcon = new ImageIcon(cardBackImg.getScaledInstance(config.cardWidth, config.cardHeight, java.awt.Image.SCALE_SMOOTH));
    }

    void shuffleCard(){
        for (int i = 0; i < cardSet.size(); i++) {
            int j = (int) (Math.random() * cardSet.size());
            Card temp = cardSet.get(i);
            cardSet.set(i, cardSet.get(j));
            cardSet.set(j, temp);
        }
    }

    void hideCards(){
        if (gameReady && card1Selected != null && card2Selected != null){
            card1Selected.setIcon(backCardImageIcon);
            card1Selected = null;
            card2Selected.setIcon(backCardImageIcon);
            card2Selected = null;
        }else{
            for (int i = 0; i < board.size(); i++){
                board.get(i).setIcon(backCardImageIcon);
            }
            gameReady = true;
            restartButton.setEnabled(true);
            changeDarkThemeButton.setEnabled(true);
            changeLightThemeButton.setEnabled(true);

            secondsElapsed = 0;
            timeLabel.setText("Time: 0s");
            gameTimer.start();
        }
    }

    public void handleCardClick(ActionEvent e) {
        if (!gameReady) {
            return;
        }
        JButton tile = (JButton) e.getSource();
        if (tile.getIcon() == backCardImageIcon) {
            if (card1Selected == null) {
                card1Selected = tile;
                int index = board.indexOf(card1Selected);
                card1Selected.setIcon(cardSet.get(index).cardImageIcon);
            } else if (card2Selected == null) {
                card2Selected = tile;
                int index = board.indexOf(card2Selected);
                card2Selected.setIcon(cardSet.get(index).cardImageIcon);

                int indexForScore = board.indexOf(card1Selected);
                Card CardForScore = cardSet.get(indexForScore);
                int Penalty = CardForScore.getMismatchPenalty();
                int Score = CardForScore.getMatchScore();
                System.out.println(Penalty);
                System.out.println(Score);


                // Check if cards match
                if (card1Selected.getIcon() != card2Selected.getIcon()) {
                    errorCount++;
                    textLabel.setText("Misses: " + errorCount);
                    //Deduct points from lifetime score for mistake

                    lifetimeScore = Math.max(0, lifetimeScore - Penalty);
                    lifetimeScoreLabel.setText("Life Time Score: " + lifetimeScore);
                    hideCardTimer.start();
                } else {
                    score++;
                    scoreLabel.setText("Matched: " + score);


                    int matchPoints = 0; // Start at 0
                    matchPoints += Score;
                    // Add time-based bonus (e.g., 200 points max, decreasing by 2 per second)
                    int timeBonus = Math.max(0, 200 - (secondsElapsed * 2));
                    matchPoints += timeBonus;
                    lifetimeScore += matchPoints;
                    lifetimeScoreLabel.setText("Life Time Score: " + lifetimeScore);

                    card1Selected = null;
                    card2Selected = null;
                }


                // check for game completion
                if (score == config.cardList.length) {
                    gameTimer.stop();
                    if (lifetimeScore > user.getScore()){
                        user.setScore(lifetimeScore);
                        UserManager.updateUser(user);
                    }

                    JOptionPane.showMessageDialog(frame,
                            "🎉 You won!\n⏱ Time: " + secondsElapsed + "s\n❌ Misses: " + errorCount +
                                    "\n💯 Lifetime Score: " + lifetimeScore + "\n💯 Higher Score: " + user.getScore());
                }
            }
        }
    }

    public void handleRestartClick(ActionEvent e){
        if(!gameReady){
            return;
        }
        gameReady = false;
        restartButton.setEnabled(false);
        card1Selected = null;
        card2Selected = null;
        shuffleCard();

        for(int i = 0; i < board.size(); i++){
            board.get(i).setIcon(cardSet.get(i).cardImageIcon);
        }

        gameTimer.stop();
        secondsElapsed = 0;
        timeLabel.setText("Time: 0s");

        errorCount = 0;
        textLabel.setText("Misses: " + 0);

        score = 0;
        scoreLabel.setText("Score: " + 0);

        lifetimeScore = 0;
        lifetimeScoreLabel.setText("Life Time Score: " + 0);

        hideCardTimer.start();
    }

    public void handleDarkThemeChangeClick(ActionEvent e){
        setupCard("dark_theme",  config.darkThemeCardList);
        handleRestartClick(e);
    }

    public void handleLightThemeChangeClick(ActionEvent e){
        setupCard("light_theme", config.cardList);
        handleRestartClick(e);
    }

    public void timerAction(ActionEvent e) {
        hideCards();
    }

    void backgroundMusicSound(){
        try {
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(getClass().getResource("/audios/elevator_music_crazy.wav"));
            backgroundClip = AudioSystem.getClip();
            backgroundClip.open(audioStream);
            backgroundClip.loop(Clip.LOOP_CONTINUOUSLY);
            backgroundClip.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }
}