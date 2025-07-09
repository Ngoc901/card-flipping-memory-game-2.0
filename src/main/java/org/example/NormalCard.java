package org.example;

import javax.swing.*;

public class NormalCard extends Card{
    NormalCard(String cardName, ImageIcon cardImageIcon) {
        super(cardName, cardImageIcon);
    }

    @Override
    public int getMismatchPenalty(){
        return 30;
    }

    @Override
    public int getMatchScore(){
        return 10;
    }
}
