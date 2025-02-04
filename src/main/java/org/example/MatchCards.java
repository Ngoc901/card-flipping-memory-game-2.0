package org.example;
import java.util.ArrayList;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.Image;


public class MatchCards {
        class Card {
            String cardName;
            ImageIcon cardImageIcon;

            Card(String cardName, ImageIcon cardImage) {
                this.cardName = cardName;
                this.cardImageIcon = cardImageIcon;
            }

            @Override
            public String toString() {
                return cardName;
            }
        }

        String[] cardList = {
                "darkness",
                "lightning",
                "double",
                "fairy",
                "metal",
                "psychic",
                "water",
                "fire",
                "fighting"
        };

        int rows = 4;
        int columns = 3;
        int cardWidth = 90;
        int cardHeight = 128;

        ArrayList<Card> cardSet; //create a deck of cards with cardNames and cardImageIcons

        ImageIcon backCardImageIcon;

        MatchCards(){
            setupCard();
            shuffleCard();
        }

        void setupCard(){
            cardSet = new ArrayList<Card>();
            for (String cardName : cardList) {
                String path = "assets/images/" + cardName + ".jpg";
                Image cardImg = new ImageIcon(getClass().getResource(path).getImage());
            }
        }

}
