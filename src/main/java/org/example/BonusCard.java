package org.example;

import javax.swing.*;

public class BonusCard extends Card {
    BonusCard(String cardName, ImageIcon cardImageIcon) {
        super(cardName, cardImageIcon);
    }

    @Override
    public int getMismatchPenalty(){
        return 500;
    }

    @Override
    public int getMatchScore(){
        return 50;
    }
}
