import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.util.Random;
import edu.fcps.karel2.Display;

public class RubiksCubePanel extends JPanel
{
   //define six sides
   private static final Color BACKGROUND = Color.WHITE;
   private BufferedImage myImage;
   private Graphics2D myBuffer;
   
   private int show[] = {1, 2, 3};
   private int Sides[][][] = {{{1, 1, 1}, {1, 1, 1}, {1, 1, 1}},
                              {{2, 2, 2}, {2, 2, 2}, {2, 2, 2}},
                              {{3, 3, 3}, {3, 3, 3}, {3, 3, 3}},
                              {{4, 4, 4}, {4, 4, 4}, {4, 4, 4}},
                              {{5, 5, 5}, {5, 5, 5}, {5, 5, 5}},
                              {{6, 6, 6}, {6, 6, 6}, {6, 6, 6}}};
   private final Color colors[] = {Color.WHITE, Color.ORANGE, Color.GREEN, Color.RED, Color.BLUE, Color.YELLOW};
   private Timer timer;
   
   public RubiksCubePanel()
   {
      //System.out.println(Sides[5][1][1]);
      //repaint();
      addKeyListener(new KeyAdapter(){
            public void keyPressed(KeyEvent e){
                 switch (e.getKeyCode()){
                     case KeyEvent.VK_RIGHT:
                         turnLeft();
                         break;
                     case KeyEvent.VK_UP:
                         turnAround();
                         break;
                   /*case KeyEvent.VK_E:
                         upperLeft();
                         break;
                     case KeyEvent.VK_D:
                         middleLeft();
                         break;
                     case KeyEvent.VK_C:
                         lowerLeft();
                         break;
                     case KeyEvent.VK_Q:
                         rightDown();
                         break;
                     case KeyEvent.VK_A:
                         middleDown();
                         break;
                     case KeyEvent.VK_Z:
                         leftDown();
                         break;*/
                 }
                 repaint();
            }
        });
         setFocusable(true);
   }
   
   public void paint(Graphics g) 
   {
     super.paint(g);
     Graphics2D g2d = (Graphics2D) g;

     //game region
     g2d.setColor(BACKGROUND);
     g2d.fillRect(0, 0, 1600, 900);
     
     //fill color
     for(int i = 0;i < 3;i++)//all three sides
     {
         if(i == 0)//top side
         {
            int x[] = {500, 600, 700};
            int y[] = {100,  50, 100};
            for(int j = 0;j < 3;j++)//nine blocks
            {
               for(int k = 0;k < 3;k++)
               {
                  g2d.setColor(colors[Sides[show[0]-1][j][k]-1]);// first row
                  g2d.fillPolygon(x, y, 3);
                  y[1] += 100;
                  g2d.fillPolygon(x, y, 3);
                  y[1] -= 100;
                  for(int p = 0;p < 3; p++)
                  {
                     x[p] += 100;
                     y[p] += 50;
                  }
               }
               for(int l = 0;l < 3;l++)
               {
                  x[l] -= 400;
                  y[l] -= 100;
               }
            }
         }
         else if(i == 1)//left side
         {
            int x1[] = {300, 300, 400};
            int y1[] = {300, 200, 250};
            for(int j = 0;j < 3;j++)//nine blocks
            {
               for(int k = 0;k < 3;k++)
               {
                  g2d.setColor(colors[Sides[show[1]-1][j][k]-1]);// first row
                  g2d.fillPolygon(x1, y1, 3);
                  x1[1] += 100;
                  y1[1] += 150;
                  g2d.fillPolygon(x1, y1, 3);
                  x1[1] -= 100;
                  y1[1] -= 150;
                  for(int p = 0;p < 3; p++)
                  {
                     x1[p] += 100;
                     y1[p] += 50;
                  }
               }
               for(int l = 0;l < 3;l++)
               {
                  x1[l] -= 300;
                  y1[l] -= 50;
               }
            }
         }
         else  //right side
         {
            int x2[] = {600, 600, 700};
            int y2[] = {450, 350, 300};
            for(int j = 0;j < 3;j++)//nine blocks
            {
               for(int k = 0;k < 3;k++)
               {
                  g2d.setColor(colors[Sides[show[2]-1][j][k]-1]);// first row
                  g2d.fillPolygon(x2, y2, 3);
                  x2[1] += 100;
                  y2[1] += 50;
                  g2d.fillPolygon(x2, y2, 3);
                  x2[1] -= 100;
                  y2[1] -= 50;
                  for(int p = 0;p < 3; p++)
                  {
                     x2[p] += 100;
                     y2[p] -= 50;
                  }
               }
               for(int l = 0;l < 3;l++)
               {
                  x2[l] -= 300;
                  y2[l] += 250;
               }
            }
         }
     }
     
     //outframe of cubes
     g2d.setColor(Color.BLACK);
     g2d.setStroke(new BasicStroke(3)); //set line width to 3
     
     //draw from the most top point outline
     g2d.drawLine(600, 50, 900, 200);// top\
     g2d.drawLine(600, 50, 300, 200);// top/
     g2d.drawLine(900, 200, 900, 500);// right|
     g2d.drawLine(300, 200, 300, 500);// left|
     g2d.drawLine(300, 500, 600, 650);// below\
     g2d.drawLine(900, 500, 600, 650);// below/ 
     g2d.drawLine(300, 200, 600, 350);// mid\
     g2d.drawLine(900, 200, 600, 350);// mid/
     g2d.drawLine(600, 350, 600, 650);// mid|
     
     //crossLines
     g2d.drawLine(500, 100, 800, 250);
     g2d.drawLine(800, 250, 800, 550);
     g2d.drawLine(400, 150, 700, 300);
     g2d.drawLine(700, 300, 700, 600);
     g2d.drawLine(300, 300, 600, 450);
     g2d.drawLine(300, 400, 600, 550);
     g2d.drawLine(600, 450, 900, 300);
     g2d.drawLine(600, 550, 900, 400);
     g2d.drawLine(700, 100, 400, 250);
     g2d.drawLine(800, 150, 500, 300);
     g2d.drawLine(400, 250, 400, 550);
     g2d.drawLine(500, 300, 500, 600);
   }
   
   private void turnLeft() //turn left for a side
   {
      for(int i = 1;i < 3;i++)
      {
         if(show[0] == 1)
         {
            if(show[i] == 5)
            {
               show[i] = 2;
            }
            else
            {
               show[i] = show[i] + 1;
            }
         }
         else if(show[0] == 6)
         {
            if(show[i] == 2)
            {
               show[i] = 5;
            }
            else
            {
               show[i] = show[i] - 1;
            }
         }
      }
   }
   
   private void turnAround()  //upside down
   {
      if(show[0] == 1)
      {
         if(show[1] == 2)
         {
            show[1] = 5;
         }
         else
         {
            show[1] = show[1] - 1;
         }
         if(show[2] == 5)
         {
            show[2] = 2;
         }
         else
         {
            show[2] = show[2] + 1;
         }
      }
      else if(show[0] == 6)
      {
         if(show[1] == 5)
         {
            show[1] = 2;
         }
         else
         {
            show[1] = show[1] + 1;
         }
         if(show[2] == 2)
         {
            show[2] = 5;
         }
         else
         {
            show[2] = show[2] - 1;
         }
      }
      if(show[0] == 1)// up and bottom
      {
         show[0] = 6;
      }
      else
      {
         show[0] = 1;
      }
   }
   
   
}