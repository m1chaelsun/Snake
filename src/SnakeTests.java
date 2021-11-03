import org.junit.jupiter.api.*;

import javax.swing.*;

import static org.junit.jupiter.api.Assertions.*;

public class SnakeTests {

    final GamePlay testInstance = new GamePlay(new JLabel());
    final Snake testSnake = new Snake(500, 500);
    final Apple testApple = new Apple(500, 500);

    @Test
    public void testRestart() {
        testInstance.restart();
        assertEquals(0, testInstance.getApplesEaten());
        assertEquals(5, testInstance.getXvel());
        assertEquals(5, testInstance.getYvel());
    }

    @Test
    public void addRandomApples() {
        testApple.add();
        assertTrue(
                testApple.getGameObjects().size() >= 1 &&
                        testApple.getGameObjects().size() <= 4
        );
    }

    @Test
    public void testValidInitialPosition() {
        assertTrue(
                testApple.getPx() >= 10 &&
                        testApple.getPx() <= 489
        );
        assertTrue(
                testApple.getPy() >= 10 &&
                        testApple.getPy() <= 489
        );
        assertTrue(
                testSnake.getPx() >= 10 &&
                        testSnake.getPx() <= 489
        );
        assertTrue(
                testSnake.getPy() >= 10 &&
                        testSnake.getPy() <= 489
        );
    }

    @Test
    public void growSnakeTest() {
        testSnake.growSnake(10);
        assertEquals(11, testSnake.getSize());
    }

    @Test
    public void moveSnake() {
        testSnake.setPx(100);
        testSnake.setPy(100);
        testSnake.setVx(5);
        testSnake.setVy(0);
        testSnake.move();
        assertEquals(105, testSnake.getPx());
        assertEquals(100, testSnake.getPy());
    }
}
