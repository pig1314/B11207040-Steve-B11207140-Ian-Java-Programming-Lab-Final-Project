import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MainMenuPanel extends JPanel {
    private static final int PANEL_WIDTH = 1600;
    private static final int PANEL_HEIGHT = 900;

    public MainMenuPanel(JFrame frame) {
        setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
        setBackground(Color.BLACK);
        setLayout(null); // 使用絕對佈局以精確定位

        // 標題
        JLabel titleLabel = new JLabel("Rubik's Cube", SwingConstants.CENTER);
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 50));
        titleLabel.setBounds(0, 150, PANEL_WIDTH-200, 60);
        add(titleLabel);

        // Start 按鈕
        JButton CasualButton = new JButton("Practice");
        CasualButton.setForeground(Color.WHITE);
        CasualButton.setBackground(Color.GREEN);
        CasualButton.setFont(new Font("Arial", Font.BOLD, 30));
        CasualButton.setBounds(550, 250, 300, 80);
        CasualButton.setFocusPainted(false);
        CasualButton.addActionListener(e -> {
            frame.getContentPane().removeAll();
            RubiksCubePanel CasualPanel = new RubiksCubePanel(frame);
            frame.add(CasualPanel);
            frame.pack();
            CasualPanel.requestFocusInWindow();
            frame.revalidate();
            frame.repaint();
        });
        add(CasualButton);
        
        JButton ChallengeButton = new JButton("Challenge");
        ChallengeButton.setForeground(Color.WHITE);
        ChallengeButton.setBackground(Color.RED);
        ChallengeButton.setFont(new Font("Arial", Font.BOLD, 30));
        ChallengeButton.setBounds(550, 400, 300, 80);
        ChallengeButton.setFocusPainted(false);
        ChallengeButton.addActionListener(e -> {
            frame.getContentPane().removeAll();
            ChallengeCubePanel ChallengePanel = new ChallengeCubePanel(frame);
            frame.add(ChallengePanel);
            frame.pack();
            ChallengePanel.requestFocusInWindow();
            frame.revalidate();
            frame.repaint();
        });
        add(ChallengeButton);
        
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, PANEL_WIDTH, PANEL_HEIGHT);
    }
}