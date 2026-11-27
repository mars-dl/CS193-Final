import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import java.awt.BorderLayout;
import java.awt.GridLayout;

/**
 * HW11 Basic Paint
 *
 * A program to allow a user to draw on a canvas with their mouse
 *
 * @author Ethan Ma, CRN SectionId: 10687-BLK
 * @version 11/20/26
 */

public class Paint {
    private static int startX;
    private static int startY;
    private static int endX;
    private static int endY;
    private static boolean clear = false;
    private static boolean fill = false;

    public static void main(String[] args) {

        Components.currentColor = Color.BLACK;
        Components.backgroundColor = Color.WHITE;

        SwingUtilities.invokeLater(() -> {
            Components.canvas = new JPanel() {
                @Override
                protected void paintComponent(Graphics g) {
                    Graphics2D g2d = (Graphics2D) g;

                    if (clear) {
                        super.paintComponent(g);
                        Components.backgroundColor = Color.WHITE;
                        Components.currentColor = Color.BLACK;

                        g2d.setColor(Components.backgroundColor);
                        g2d.fillRect(0, 0, getWidth(), getHeight());
                        g2d.setColor(Components.currentColor);

                        clear = false;

                    } else if (fill) {
                        super.paintComponent(g);
                        Components.backgroundColor = new Color(Components.currentColor.getRed(),
                                Components.currentColor.getGreen(),
                                Components.currentColor.getBlue());
                        Components.currentColor = Color.BLACK;

                        g2d.setColor(Components.backgroundColor);
                        g2d.fillRect(0, 0, getWidth(), getHeight());
                        g2d.setColor(Components.currentColor);

                        fill = false;

                    } else {
                        g2d.drawLine(startX, startY, endX, endY);
                        startX = endX;
                        startY = endY;
                    }
                }
            };
            Components.canvas.addMouseListener(new MouseListener() {
                public void mouseClicked(MouseEvent e) {
                    startX = e.getX();
                    startY = e.getY();
                }

                public void mousePressed(MouseEvent e) {
                    endX = e.getX();
                    endY = e.getY();
                    Components.canvas.repaint();
                }

                public void mouseReleased(MouseEvent e) {

                }

                public void mouseEntered(MouseEvent e) {

                }

                public void mouseExited(MouseEvent e) {

                }
            });

            Components.clrButton = new JButton("Clear");
            Components.clrButton.addActionListener(e -> {
                Components.hexText.setText("#");
                Components.rText.setText("");
                Components.gText.setText("");
                Components.bText.setText("");
                clear = true;
                Components.canvas.repaint();
            });

            Components.fillButton = new JButton("Fill");
            Components.fillButton.addActionListener(e -> {
                fill = true;
                Components.hexText.setText("#");
                Components.rText.setText("");
                Components.gText.setText("");
                Components.bText.setText("");
                Components.canvas.repaint();
            });

            Components.eraseButton = new JButton("Erase");
            Components.eraseButton.addActionListener(e -> {
                Components.currentColor = Components.backgroundColor;
            });

            Components.hexText = new JTextField(10);
            Components.hexText.setText("#");
            Components.rText = new JTextField(5);
            Components.rText.setText("");
            Components.gText = new JTextField(5);
            Components.gText.setText("");
            Components.bText = new JTextField(5);
            Components.bText.setText("");

            Components.hexButton = new JButton("Use Hex");
            Components.hexButton.addActionListener(e -> {
                try {
                    Components.currentColor = Color.decode(Components.hexText.getText());
                    Components.rText.setText("" + Components.currentColor.getRed());
                    Components.gText.setText("" + Components.currentColor.getGreen());
                    Components.bText.setText("" + Components.currentColor.getBlue());
                } catch (Exception ex) {
                    Components.currentColor = Color.BLACK;
                }
            });

            Components.rgbButton = new JButton("Use RGB");
            Components.rgbButton.addActionListener(e -> {
                try {
                    if (Components.rText.getText().equals("")) {
                        Components.rText.setText("0");
                    }
                    if (Components.gText.getText().equals("")) {
                        Components.gText.setText("0");
                    }
                    if (Components.bText.getText().equals("")) {
                        Components.bText.setText("0");
                    }

                    int r;
                    int g;
                    int b;

                    r = Integer.parseInt(Components.rText.getText());
                    g = Integer.parseInt(Components.gText.getText());
                    b = Integer.parseInt(Components.bText.getText());

                    Components.currentColor = new Color(r, g, b);

                    Components.hexText.setText(String.format("#%02x%02x%02x", r, g, b));

                } catch (Exception ex) {
                    Components.currentColor = Color.BLACK;
                }
            });

            JPanel top = new JPanel();
            top.setLayout(new GridLayout(1, 0));
            top.add(Components.clrButton);
            top.add(Components.fillButton);
            top.add(Components.eraseButton);

            JPanel bottom = new JPanel();
            bottom.setLayout(new GridLayout(1, 0));
            bottom.add(Components.hexText);
            bottom.add(Components.hexButton);
            bottom.add(Components.rText);
            bottom.add(Components.gText);
            bottom.add(Components.bText);
            bottom.add(Components.rgbButton);

            Components.frame = new JFrame("Paint");
            Components.frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            Components.frame.setSize(800, 400);

            Components.frame.setLayout(new BorderLayout());
            Components.frame.add(top, BorderLayout.NORTH);
            Components.frame.add(Components.canvas, BorderLayout.CENTER);
            Components.frame.add(bottom, BorderLayout.SOUTH);
            Components.frame.setVisible(true);
        });
    }
}