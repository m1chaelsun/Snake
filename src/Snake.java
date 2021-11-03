import java.awt.*;
import java.util.concurrent.ThreadLocalRandom;

public class Snake extends GameObj {
    public static final int SIZE = 12;
    public static final int INITIAL_X_POS = ThreadLocalRandom.current().nextInt(200, 400);
    public static final int INITIAL_Y_POS = ThreadLocalRandom.current().nextInt(200, 400);
    private static int applesEaten;

    public Snake(int courtWidth, int courtHeight) {
        super(
                0, 0, INITIAL_X_POS, INITIAL_Y_POS, SIZE, SIZE,
                courtWidth, courtHeight
        );
        applesEaten = 0;
    }

    // Moves the snake by changing the position of the Snake's head
    @Override
    public void move() {
        int i = getGameObjects().size() - 1;
        while (i >= 1) {
            getGameObjects().get(i).setLocation(getGameObjects().get(i - 1));
            i--;
        }

        setPx(getPx() + getVx());
        setPy(getPy() + getVy());

        getGameObjects().set(0, new Point(getPx(), getPy()));
        clip();
    }

    // Checks to see if the Snake will intersect with itself. Returns true if
    // yes and false if not.
    public boolean selfIntersection() {
        if (getGameObjects().size() > 1) {
            int i = 1;
            while (i < getGameObjects().size()) {
                if (getGameObjects().getFirst().x + getVx() == getGameObjects().get(i).x) {
                    if (getGameObjects().getFirst().y + getVy() == getGameObjects().get(i).y) {
                        return true;
                    }
                }
                i++;
            }
        }
        return false;
    }

    @Override
    public boolean intersects(GameObj obj) {
        return (getGameObjects().getFirst().x + getWidth() >= obj.getPx()
                && getGameObjects().getFirst().y + getHeight() >= obj.getPy()
                && obj.getPx() + obj.getWidth() >= getGameObjects().getFirst().x
                && obj.getPy() + obj.getHeight() >= getGameObjects().getFirst().y);
    }

    // Adds num nodes to the Snake
    public void growSnake(int num) {
        while (num > 0) {
            getGameObjects().add(new Point(getGameObjects().getLast()));
            num--;
        }
    }

    // Returns current number of apples eaten
    public int getApplesEaten() {
        return applesEaten;
    }

    // Sets the number of apples eaten to num
    public void setApplesEaten(int num) {
        applesEaten = num;
    }

    // Increases the number of apples eaten by 1
    public void incrementApplesEaten() {
        applesEaten++;
    }

    // Returns current number of apples eaten
    public int getSize() {
        return getGameObjects().size();
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.WHITE);
        for (Point p : getGameObjects()) {
            g.fillRect(p.x, p.y, 15, 15);
        }
    }
}