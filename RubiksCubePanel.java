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
                     case KeyEvent.VK_E:
                         upperLeft();
                         break;
                     case KeyEvent.VK_D:
                         middleLeft();
                         break;
                     case KeyEvent.VK_C:
                         lowerLeft();
                         break;
                     case KeyEvent.VK_Q:
                         rightUp();
                         break;
                     /*case KeyEvent.VK_A:
                         middleUp();
                         break;
                     case KeyEvent.VK_Z:
                         leftUp();
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
     int start = 0, stop = 0, differ = 0, j, k, p, l;
     if(show[0] == 1)
     {
         start = 0;
         stop = 3;
         differ = 1;
     }
     else if(show[0] == 6)
     {
         start = 2;
         stop = -1;
         differ = -1;
     }
     for(int i = 0;i < 3;i++)//all three sides
     {
         if(i == 0)//top side
         {
            int x[] = {500, 600, 700};
            int y[] = {100,  50, 100};
            for(j = start;j != stop;j+=differ)//nine blocks
            {
               for(k = start;k != stop;k+=differ)
               {
                  g2d.setColor(colors[Sides[show[0]-1][j][k]-1]);// first row
                  g2d.fillPolygon(x, y, 3);
                  y[1] += 100;
                  g2d.fillPolygon(x, y, 3);
                  y[1] -= 100;
                  for(p = start;p != stop; p+=differ)
                  {
                     x[p] += 100;
                     y[p] += 50;
                  }
               }
               for(l = start;l != stop;l+=differ)
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
            for(j = start;j != stop;j+=differ)//nine blocks
            {
               for(k = start;k != stop;k+=differ)
               {
                  g2d.setColor(colors[Sides[show[1]-1][j][k]-1]);// first row
                  g2d.fillPolygon(x1, y1, 3);
                  x1[1] += 100;
                  y1[1] += 150;
                  g2d.fillPolygon(x1, y1, 3);
                  x1[1] -= 100;
                  y1[1] -= 150;
                  for(p = start;p != stop; p+=differ)
                  {
                     x1[p] += 100;
                     y1[p] += 50;
                  }
               }
               for(l = start;l != stop;l+=differ)
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
            for(j = start;j != stop;j+=differ)//nine blocks
            {
               for(k = start;k != stop;k+=differ)
               {
                  g2d.setColor(colors[Sides[show[2]-1][j][k]-1]);// first row
                  g2d.fillPolygon(x2, y2, 3);
                  x2[1] += 100;
                  y2[1] += 50;
                  g2d.fillPolygon(x2, y2, 3);
                  x2[1] -= 100;
                  y2[1] -= 50;
                  for(p = start;p != stop; p+=differ)
                  {
                     x2[p] += 100;
                     y2[p] -= 50;
                  }
               }
               for(l = start;l != stop;l+=differ)
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
      int temp[] = {0, 0, 0}; 
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
      for(int j = 0; j < 2;j++)
      {
         temp[j]  = Sides[show[0]-1][0][j];
         Sides[show[0]-1][0][j] = Sides[show[0]-1][2 - j][0];
         Sides[show[0]-1][2 - j][0] = Sides[show[0]-1][2][2 - j];
         Sides[show[0]-1][2][2 - j] = Sides[show[0]-1][j][2];
         Sides[show[0]-1][j][2] = temp[j];   
      }
      for(int j = 0; j < 2;j++)
      {
         temp[j]  = Sides[opposite(show[0])-1][0][j];
         Sides[opposite(show[0])-1][0][j] = Sides[opposite(show[0])-1][j][2];
         Sides[opposite(show[0])-1][j][2] = Sides[opposite(show[0])-1][2][2 - j];
         Sides[opposite(show[0])-1][2][2 - j] = Sides[opposite(show[0])-1][2 - j][0];
         Sides[opposite(show[0])-1][2 - j][0] = temp[j]; 
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
   
   private void upperLeft()
   {
      int temp[] = {0, 0, 0};
      if(show[0] == 1)
      {
         for(int i = 0;i < 3;i++)
         {
            temp[i] = Sides[1][0][i];
            Sides[1][0][i] = Sides[2][0][i];
            Sides[2][0][i] = Sides[3][0][i];
            Sides[3][0][i] = Sides[4][0][i];
            Sides[4][0][i] = temp[i];
         }
         for(int j = 0; j < 2;j++)
         {
            temp[j]  = Sides[0][0][j];
            Sides[0][0][j] = Sides[0][2 - j][0];
            Sides[0][2 - j][0] = Sides[0][2][2 - j];
            Sides[0][2][2 - j] = Sides[0][j][2];
            Sides[0][j][2] = temp[j];   
         }
      }
      else
      {
         for(int i = 0;i < 3;i++)
         {
            temp[i] = Sides[4][2][i];
            Sides[4][2][i] = Sides[3][2][i];
            Sides[3][2][i] = Sides[2][2][i];
            Sides[2][2][i] = Sides[1][2][i];
            Sides[1][2][i] = temp[i];
         }
         for(int j = 0; j < 2;j++)
         {
            temp[j]  = Sides[5][0][j];
            Sides[5][0][j] = Sides[5][2 - j][0];
            Sides[5][2 - j][0] = Sides[5][2][2 - j];
            Sides[5][2][2 - j] = Sides[5][j][2];
            Sides[5][j][2] = temp[j];   
         }
      }
   }
   
   private void middleLeft()
   {
      int temp[] = {0, 0, 0};
      if(show[0] == 1)
      {
         for(int i = 0;i < 3;i++)
         {
            temp[i] = Sides[1][1][i];
            Sides[1][1][i] = Sides[2][1][i];
            Sides[2][1][i] = Sides[3][1][i];
            Sides[3][1][i] = Sides[4][1][i];
            Sides[4][1][i] = temp[i];
         }
      }
      else
      {
         for(int i = 0;i < 3;i++)
         {
            temp[i] = Sides[4][1][i];
            Sides[4][1][i] = Sides[3][1][i];
            Sides[3][1][i] = Sides[2][1][i];
            Sides[2][1][i] = Sides[1][1][i];
            Sides[1][1][i] = temp[i];
         }
      }
   }
   
   private void lowerLeft()
   {
      int temp[] = {0, 0, 0};
      if(show[0] == 1)
      {
         for(int i = 0;i < 3;i++)
         {
            temp[i] = Sides[1][2][i];
            Sides[1][2][i] = Sides[2][2][i];
            Sides[2][2][i] = Sides[3][2][i];
            Sides[3][2][i] = Sides[4][2][i];
            Sides[4][2][i] = temp[i];
         }
         for(int j = 0; j < 2;j++)
         {
            temp[j]  = Sides[5][0][j];
            Sides[5][0][j] = Sides[5][j][2];
            Sides[5][j][2] = Sides[5][2][2 - j];
            Sides[5][2][2 - j] = Sides[5][2 - j][0];
            Sides[5][2 - j][0] = temp[j];   
         }
      }
      else
      {
         for(int i = 0;i < 3;i++)
         {
            temp[i] = Sides[4][0][i];
            Sides[4][0][i] = Sides[3][0][i];
            Sides[3][0][i] = Sides[2][0][i];
            Sides[2][0][i] = Sides[1][0][i];
            Sides[1][0][i] = temp[i];
         }
         for(int j = 0; j < 2;j++)
         {
            temp[j]  = Sides[0][0][j];
            Sides[0][0][j] = Sides[0][j][2];
            Sides[0][j][2] = Sides[0][2][2 - j];
            Sides[0][2][2 - j] = Sides[0][2 - j][0];
            Sides[0][2 - j][0] = temp[j]; 
         }
      }
   }
   
   private void rightUp()
   {
      int temp[] = {0, 0, 0};
      if(show[0] == 1)
      {
         for(int q = 0; q < 3;q++)
         {
            temp[q] = Sides[0][q][2];
         }
         for(int i = 0;i < 3;i++)
         {
            Sides[0][i][2] = Sides[show[1]-1][i][2];
            Sides[show[1]-1][i][2] = Sides[5][2][2 - i];
            Sides[5][2][2 - i] = Sides[opposite(show[1]) - 1][2 - i][0];
            Sides[opposite(show[1]) - 1][2 - i][0] = temp[i];
            System.out.println("1."+Sides[5][2][0] +" 2."+Sides[5][2][1] +" 3."+Sides[5][2][2]);
         }
         for(int j = 0; j < 2;j++)
         {
            temp[j]  = Sides[rightAside(1, show[1])][0][j];
            Sides[rightAside(1, show[1])][0][j] = Sides[rightAside(1, show[1])][2 - j][0];
            Sides[rightAside(1, show[1])][2 - j][0] = Sides[rightAside(1, show[1])][2][2 - j];
            Sides[rightAside(1, show[1])][2][2 - j] = Sides[rightAside(1, show[1])][j][2];
            Sides[rightAside(1, show[1])][j][2] = temp[j];  
         }
      }
      else
      {
         for(int q = 0; q < 3;q++)
         {
            temp[q] = Sides[5][2 - q][0];
         }
         for(int i = 0;i < 3;i++)
         {
            Sides[5][2 - i][0] = Sides[show[1]-1][2 - i][0];
            Sides[show[1]-1][2 - i][0] = Sides[0][0][i];
            Sides[0][0][i] = Sides[opposite(show[1])-1][i][2];
            Sides[opposite(show[1])-1][i][2] = temp[i];
         }
         for(int j = 0; j < 2;j++)
         {
            temp[j]  = Sides[rightAside(6, show[1])][0][j];
            Sides[rightAside(6, show[1])][0][j] = Sides[rightAside(6, show[1])][2 - j][0];
            Sides[rightAside(6, show[1])][2 - j][0] = Sides[rightAside(6, show[1])][2][2 - j];
            Sides[rightAside(6, show[1])][2][2 - j] = Sides[rightAside(6, show[1])][j][2];
            Sides[rightAside(6, show[1])][j][2] = temp[j];   
         }
      }
   }
   
   /*private void middleUp() {
    int temp[] = {0, 0, 0};
    if (show[0] == 1) {
        // Middle slice: front (2) -> top (1) -> back (4) -> bottom (6) -> front (2)
        for (int i = 0; i < 3; i++) {
            temp[i] = Sides[1][i][1]; // Top (1) middle column
            Sides[1][i][1] = Sides[2-1][i][1]; // Front (2) -> Top (1)
            Sides[2-1][i][1] = Sides[6-1][2-i][1]; // Bottom (6) -> Front (2)
            Sides[6-1][2-i][1] = Sides[4-1][2-i][1]; // Back (4) -> Bottom (6)
            Sides[4-1][2-i][1] = temp[i]; // Top (1) -> Back (4)
        }
    } else {
        // Middle slice: bottom (6) -> back (4) -> top (1) -> front (2) -> bottom (6)
        for (int i = 0; i < 3; i++) {
            temp[i] = Sides[6-1][2-i][1]; // Bottom (6) middle column
            Sides[6-1][2-i][1] = Sides[4-1][2-i][1]; // Back (4) -> Bottom (6)
            Sides[4-1][2-i][1] = Sides[1][i][1]; // Top (1) -> Back (4)
            Sides[1][i][1] = Sides[2-1][i][1]; // Front (2) -> Top (1)
            Sides[2-1][i][1] = temp[i]; // Bottom (6) -> Front (2)
        }
    }
}

   private void leftUp() {
    int temp[] = {0, 0, 0};
    if (show[0] == 1) {
        // Left column: top (1) -> left (2) -> bottom (6) -> back (4) -> top (1)
        for (int i = 0; i < 3; i++) {
            temp[i] = Sides[1-1][i][0]; // Top (1) left column
            Sides[1-1][i][0] = Sides[show[1]-1][i][0]; // Left (2) -> Top (1)
            Sides[show[1]-1][i][0] = Sides[6-1][2-i][0]; // Bottom (6) -> Left (2)
            Sides[6-1][2-i][0] = Sides[4-1][2-i][2]; // Back (4), right column -> Bottom (6)
            Sides[4-1][2-i][2] = temp[i]; // Top (1) -> Back (4), right column
        }
        // Rotate left face (show[1]) clockwise
        int leftFace = show[1] - 1;
        for (int j = 0; j < 2; j++) {
            temp[j] = Sides[leftFace][0][j];
            Sides[leftFace][0][j] = Sides[leftFace][2-j][0];
            Sides[leftFace][2-j][0] = Sides[leftFace][2][2-j];
            Sides[leftFace][2][2-j] = Sides[leftFace][j][2];
            Sides[leftFace][j][2] = temp[j];
        }
    } else {
        // Left column: bottom (6) -> back (4) -> top (1) -> left (2) -> bottom (6)
        for (int i = 0; i < 3; i++) {
            temp[i] = Sides[6-1][2-i][0]; // Bottom (6) left column
            Sides[6-1][2-i][0] = Sides[4-1][2-i][2]; // Back (4), right column -> Bottom (6)
            Sides[4-1][2-i][2] = Sides[1-1][i][0]; // Top (1) -> Back (4), right column
            Sides[1-1][i][0] = Sides[show[1]-1][i][0]; // Left (2) -> Top (1)
            Sides[show[1]-1][i][0] = temp[i]; // Bottom (6) -> Left (2)
        }
        // Rotate left face (show[1]) clockwise
        int leftFace = show[1] - 1;
        for (int j = 0; j < 2; j++) {
            temp[j] = Sides[leftFace][0][j];
            Sides[leftFace][0][j] = Sides[leftFace][2-j][0];
            Sides[leftFace][2-j][0] = Sides[leftFace][2][2-j];
            Sides[leftFace][2][2-j] = Sides[leftFace][j][2];
            Sides[leftFace][j][2] = temp[j];
        }
    }
}*/
   
   private int rightAside(int top,int side)
   {
      int result = 0;
      if(top == 1)
      {
         if(side == 5)
         {
            result = 2;
         }
         else
         {
            result = side + 1;
         }
      }
      else
      {
         if(side == 2)
         {
            result = 5;
         }
         else
         {
            result = side - 1;
         }
      }
      return result-1;
   }
   
   private int leftAside(int top,int side)
   {
      int result = 0;
      if(top == 1)
      {
         if(side == 2)
         {
            result = 5;
         }
         else
         {
            result = side - 1;
         }
      }
      else
      {
         if(side == 5)
         {
            result = 2;
         }
         else
         {
            result = side + 1;
         }
      }
      return result-1;
   }

   
   private int opposite(int side)
   {
      int result = 0;
      switch(side)
      {
         case 1:
            result = 6;
            break;
         case 2:
            result = 4;
            break;
         case 3:
            result = 5;
            break;
         case 4:
            result = 2;
            break;
         case 5:
            result = 3;
            break;
         case 6:
            result = 1;
            break;
      }
      return result;
   }
}