import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.ThreadLocalRandom;
import javax.imageio.ImageIO;

public class LandMine extends Item {
    public static final int INITIAL_X_POS = ThreadLocalRandom.current().nextInt(10, 490);
    public static final int INITIAL_Y_POS = ThreadLocalRandom.current().nextInt(50, 450);

    private static BufferedImage bufferedImage;

    public LandMine(int fieldWidth, int fieldHeight) {
        super(INITIAL_X_POS, INITIAL_Y_POS, fieldWidth, fieldHeight);

        try {
            if (bufferedImage == null) {
                bufferedImage = ImageIO.read(new File("landmine.png"));
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    /**
     * Adds up to 3 landmines to the board (exact number chosen randomly)
     *
     * Unlike Apples, there is no cap on the number of landmines that can be
     * on the board.
     */
    @Override
    public void add() {
        for (int i = 0; i < ThreadLocalRandom.current().nextInt(0, 4); i++) {
            getGameObjects().add(
                    new Point(
                            (int) (Math.random() * getMaxX()),
                            (int) (Math.random() * getMaxY())
                    )
            );
        }
    }

    @Override
    public void addSpecific(int px, int py) {
        getGameObjects().add(new Point(px, py));
    }

    // Draws landmines onto the board
    @Override
    public void draw(Graphics g) {
        for (Point p : getGameObjects()) {
            g.drawImage(bufferedImage, p.x, p.y, getWidth(), getHeight(), null);
        }
    }
}