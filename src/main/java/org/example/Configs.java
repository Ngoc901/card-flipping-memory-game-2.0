package org.example;

public class Configs {
    public static String[] cardList;
    public int rows;
    public int columns;
    public int cardWidth;
    public int cardHeight;

    Configs(){
        this.cardList = new String[]{
                "darkness",
                "lightning",
                "double",
                "fairy",
                "metal",
                "psychic",
                "water",
                "fire",
                "fighting",
                "grass"
        };
        this.rows = 4;
        this.columns = 3;
        this.cardWidth = 90;
        this.cardHeight = 90;
    }
}
