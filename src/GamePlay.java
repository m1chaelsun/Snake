import java.awt.*;
import java.awt.event.*;
import java.awt.Graphics;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.ThreadLocalRandom;
import javax.swing.*;

public class GamePlay extends JPanel {

    private Snake snake;
    private Apple apple;
    private LandMine landMine;

    private boolean playing = false;
    private JLabel status;

    private static final int BOARD_HEIGHT = 500;
    private static final int BOARD_WIDTH = 500;

    private static int xVelocity = 5;
    private static int yVelocity = 5;

    public GamePlay(JLabel status) {
        this.setBackground(Color.BLACK);

        new Timer(10, e -> {
            if (playing) {
                snake.move();
                if ((apple.intersects(snake))) {
                    if (ThreadLocalRandom.current().nextInt(0, 101) < 50) {
                        landMine.add();
                    }
                    snake.incrementApplesEaten();
                    snake.growSnake(15);
                    apple.add();
                    this.status.setText("Apples Eaten: " + snake.getApplesEaten());
                }
                if (((snake.getPx() + snake.getVx() < 0) ||
                        (snake.getPx() + snake.getVx() > snake.getMaxX())
                        || (snake.getPy() + snake.getVy() < 0) ||
                        (snake.getPy() + snake.getVy() > snake.getMaxY())
                        || (landMine.intersects(snake)
                                || (snake.selfIntersection())))) {
                    this.playing = false;
                }
                repaint();
            }
        }).start();
        setFocusable(true);

        addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent ev) {
                switch (ev.getKeyCode()) {
                    case KeyEvent.VK_UP:
                        snake.setVx(0);
                        snake.setVy(-yVelocity);
                        break;
                    case KeyEvent.VK_DOWN:
                        snake.setVx(0);
                        snake.setVy(yVelocity);
                        break;
                    case KeyEvent.VK_LEFT:
                        snake.setVx(-xVelocity);
                        snake.setVy(0);
                        break;
                    case KeyEvent.VK_RIGHT:
                        snake.setVx(xVelocity);
                        snake.setVy(0);
                        break;
                    default:
                        throw new IllegalStateException(
                                "Unexpected value: " + ev.getKeyCode()
                        );
                }
            }
        });

        this.status = status;
    }

    // Restores game state to initial
    public void restart() {
        this.setBackground(Color.BLACK);

        snake = new Snake(BOARD_WIDTH, BOARD_HEIGHT);
        apple = new Apple(BOARD_WIDTH, BOARD_HEIGHT);
        landMine = new LandMine(BOARD_WIDTH, BOARD_HEIGHT);

        playing = true;
        status.setText("Apples Eaten: " + snake.getApplesEaten());

        xVelocity = 5;
        yVelocity = 5;

        snake.setApplesEaten(0);

        requestFocusInWindow();
    }

    public void importChallenge() {
        this.setBackground(Color.BLACK);
        playing = true;
        status.setText("Apples Eaten: " + snake.getApplesEaten());

        xVelocity = 5;
        yVelocity = 5;

        snake.setApplesEaten(0);

        requestFocusInWindow();

        snake = new Snake(BOARD_WIDTH, BOARD_HEIGHT);
        snake.setPx(250);
        snake.setPy(250);

        try {
            BufferedReader gameReader = new BufferedReader(
                    new FileReader(
                            "presetMap.csv"
                    )
            );
            String line = "";
            while ((line = gameReader.readLine()) != null) {
                String[] gameList = line.split(",");
                if (gameList[0].equals("Apple")) {
                    apple.addSpecific(
                            Integer.parseInt(gameList[1]),
                            Integer.parseInt(gameList[2])
                    );
                } else {
                    landMine.addSpecific(
                            Integer.parseInt(gameList[1]),
                            Integer.parseInt(gameList[2])
                    );
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void paintComponent(Graphics gx) {
        super.paintComponent(gx);
        if (playing) {
            snake.draw(gx);
            apple.draw(gx);
            landMine.draw(gx);
        } else {
            this.setBackground(new Color(232, 61, 61));
            gx.setColor(Color.WHITE);
            gx.setFont((new Font("Helvetica", Font.BOLD, 40)));
            gx.drawString("Game Over!", 135, 250);
            gx.setFont((new Font("Helvetica", Font.BOLD, 20)));
            gx.drawString("Apples Eaten: " + snake.getApplesEaten(), 175, 300);
        }
    }

    // Getter methods for testing
    public int getApplesEaten() {
        return snake.getApplesEaten();
    }

    public int getXvel() {
        return xVelocity;
    }

    public int getYvel() {
        return yVelocity;
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(BOARD_WIDTH, BOARD_HEIGHT);
    }
}
