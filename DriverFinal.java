import javax.swing.JFrame;


public class DriverFinal
{
   public static void main(String[] args)
   { 
      JFrame frame = new JFrame("Rubiks Cube");
      frame.setLocation(0, 0);
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.add(new MainMenuPanel(frame));
      frame.pack();
      frame.setLocationRelativeTo(null);
      frame.setVisible(true);
   }
}