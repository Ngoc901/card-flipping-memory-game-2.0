package org.example;

public class Configs {
    public static String[] cardList;
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
        this.rows = 4;
        this.columns = 3;
        this.cardWidth = 90;
        this.cardHeight = 90;
    }
}
