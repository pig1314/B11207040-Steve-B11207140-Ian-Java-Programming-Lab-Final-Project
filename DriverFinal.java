import javax.swing.JFrame;


public class DriverFinal
{
   public static void main(String[] args)
   { 
      JFrame frame = new JFrame("Rubik's Cube");
      frame.setLocation(0, 0);
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.getContentPane().removeAll();
      MainMenuPanel MainPanel = new MainMenuPanel(frame);
      frame.add(MainPanel);
      frame.pack();
      MainPanel.requestFocusInWindow();
      frame.revalidate();
      frame.repaint();
      frame.setVisible(true);
   }
}