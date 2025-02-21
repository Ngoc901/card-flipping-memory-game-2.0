package org.example;

import javax.swing.*;
import java.awt.*;

class VerticalButton extends JButton {
    VerticalButton(String text) {
        super(text);
        // Remove these lines to restore button appearance
        // setBorderPainted(false);
        // setFocusPainted(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        String text = getText();
        FontMetrics fm = g2.getFontMetrics();
        int letterHeight = fm.getHeight();

        // Starting y position from top with padding
        int y = fm.getAscent() + 5;  // Added padding from top

        // Center the text block horizontally
        int x = (getWidth() - fm.stringWidth("W")) / 2;

        // Draw each character vertically
        for (char c : text.toCharArray()) {
            if (c != ' ') {
                g2.drawString(String.valueOf(c), x, y);
            }
            y += letterHeight;
        }

        g2.dispose();
    }

    @Override
    public Dimension getPreferredSize() {
        FontMetrics fm = getFontMetrics(getFont());
        int width = fm.stringWidth("W") + 20;  // Added more padding
        int height = fm.getHeight() * getText().length() + 20;  // Added more padding
        return new Dimension(width, height);
    }
}