package org.example;

public class Configs {
    public static String[] cardList;
    public static String[] darkThemeCardList;
    public int rows;
    public int columns;
    public int cardWidth;
    public int cardHeight;

    Configs(){
        this.cardList = new String[]{
                "balloondog",
                "bear",
                "cake",
                "catto",
                "fish",
                "flower",
                "letter",
                "milk",
                "panda",
                "strawberry"
        };
        this.darkThemeCardList = new String[]{
                "bear",
                "bunny",
                "capybara",
                "cat",
                "catepillar",
                "dog",
                "shark",
                "sheep",
                "shrimp",
                "snail"
        };
        this.rows = 4;
        this.columns = 3;
        this.cardWidth = 95;
        this.cardHeight = 95;
    }
}
