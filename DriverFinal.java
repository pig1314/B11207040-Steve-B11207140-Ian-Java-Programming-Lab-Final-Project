   //Torbert, e-mail: smtorbert@fcps.edu
	//version 6.17.2003

   import javax.swing.JFrame;
    public class DriverFinal
   {
       public static void main(String[] args)
      { 
         JFrame frame = new JFrame("Rubiks Cube");
         frame.setSize(1600, 900);
         frame.setLocation(0, 0);
         frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       	frame.setContentPane(new RubiksCubePanel());
         frame.setVisible(true);
      }
   }