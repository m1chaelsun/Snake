import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.ThreadLocalRandom;
import javax.imageio.ImageIO;

public class Apple extends Item {
    public static final int INITIAL_X_POS = ThreadLocalRandom.current().nextInt(10, 490);
    public static final int INITIAL_Y_POS = ThreadLocalRandom.current().nextInt(50, 450);

    private static BufferedImage bufferedImage;

    public Apple(int fieldWidth, int fieldHeight) {
        super(INITIAL_X_POS, INITIAL_Y_POS, fieldWidth, fieldHeight);

        try {
            if (bufferedImage == null) {
                bufferedImage = ImageIO.read(new File("apple.png"));
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    /**
     * Adds up to 2 apples to the board (exact number chosen randomly)
     *
     * Only adds if there are fewer than 3 total objects on the board as to
     * not overpopulate the board with apples.
     */
    @Override
    public void add() {
        int howMany = ThreadLocalRandom.current().nextInt(1, 3);

        if (getGameObjects().size() <= 3) {
            int i = 0;
            while (i < howMany) {
                getGameObjects().add(
                        new Point(
                                (int) (Math.random() * getMaxX()),
                                (int) (Math.random() * getMaxY())
                        )
                );
                i++;
            }
        }
    }

    @Override
    public void addSpecific(int px, int py) {
        getGameObjects().add(new Point(px, py));
    }

    // Checks for intersection with Snake
    @Override
    public boolean intersects(GameObj gameObj) {
        int i = 0;
        while (i < getGameObjects().size()) {
            if (getGameObjects().get(i).x + getWidth() >= gameObj.getPx()
                    && getGameObjects().get(i).y + getHeight() >= gameObj.getPy()
                    && gameObj.getPx() + gameObj.getWidth() >= getGameObjects().get(i).x
                    && gameObj.getPy() + gameObj.getHeight() >= getGameObjects().get(i).y) {
                remove(i);
                return true;
            }
            i++;
        }
        return false;
    }

    // Draws apples onto the board
    @Override
    public void draw(Graphics gx) {
        for (Point p : getGameObjects()) {
            gx.drawImage(bufferedImage, p.x, p.y, getWidth(), getHeight(), null);
        }
    }
}