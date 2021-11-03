import java.awt.*;
import javax.swing.*;

public class Game implements Runnable {

    public void run() {

        final Font helvetica = new Font("Helvetica", Font.BOLD, 15);
        final JFrame window = new JFrame("Snake: Michael Sun Edition");
        window.setLocation(500, 500);

        // Display green status and control panel
        final JPanel statusPanel = new JPanel();
        statusPanel.setSize(500, 200);
        statusPanel.setBackground((new Color(16, 181, 16)));
        window.add(statusPanel, BorderLayout.NORTH);
        final JLabel status = new JLabel();
        status.setFont(helvetica);
        statusPanel.add(status);

        // Display game board
        final GamePlay board = new GamePlay(status);
        window.add(board, BorderLayout.CENTER);

        // Display blue Instructions button
        final String instructions = ("Control the snake using the arrow keys" +
                ".\n\nDon't " +
                "let the snake hit a wall or itself.\n\nEat the APPLES to " +
                "grow " +
                "and avoid the LANDMINES.\n\nFor a challenge map, click " +
                "\"CHAL\" \n\nGood " +
                "Luck! " +
                "\n\nTo continue, " +
                "click " +
                "\"OK\" and click \"RESTART\" at the top. The game will " +
                "start once you press an arrow key.");
        final JButton instruction = new JButton("INSTRUCTIONS");
        instruction.setBackground(Color.BLUE);
        instruction.setFont(helvetica);
        instruction.setForeground(Color.WHITE);
        instruction.addActionListener(
            e -> JOptionPane.showMessageDialog(
                        window, instructions,
                        "Instructions", JOptionPane.PLAIN_MESSAGE
                )
        );

        // Display red Restart button
        final JButton restart = new JButton("RESTART");
        restart.setBackground(Color.RED);
        restart.setFont(helvetica);
        restart.setForeground(Color.WHITE);
        restart.addActionListener(e -> board.restart());

        // Display Load Challenge Map button
        final JButton challenge = new JButton("CHAL");
        challenge.setBackground(Color.MAGENTA);
        challenge.setFont(helvetica);
        challenge.setForeground(Color.WHITE);
        challenge.addActionListener(e -> board.importChallenge());

        statusPanel.add(Box.createRigidArea(new Dimension(10, 0)));
        statusPanel.add(instruction);
        statusPanel.add(restart);
        statusPanel.add(challenge);

        window.pack();
        window.setVisible(true);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        board.restart();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Game());
    }
}