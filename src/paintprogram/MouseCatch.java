/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package paintprogram;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import static java.lang.System.out;
import paintprogram.PaintProgram.DrawPlane.Line;
import static paintprogram.PaintProgram.linedraw;
import paintprogram.PaintProgram.DrawPlane.Rect;
import static paintprogram.PaintProgram.rectdraw;
import static paintprogram.PaintProgram.startX;
import static paintprogram.PaintProgram.startY;
import static paintprogram.PaintProgram.prevX;
import static paintprogram.PaintProgram.prevY;
import static paintprogram.PaintProgram.tempX;
import static paintprogram.PaintProgram.tempY;

/**
 *
 * @author Administrator
 */
class MouseCatch extends PaintProgram implements MouseListener{
    
    
    
         MouseCatch(){
           
         }
        
         @Override
         public void mousePressed(MouseEvent e) {             
             //int x = e.getX();
             //int y = e.getY();
             
             //startX = x;
             //startY = y;
             
             //linedraw=true;
             
             out.println("The result is " + linedraw);
             out.println("Pressed Coordinates: " + prevX + " " + prevY);
             
             int x = e.getX();
             int y = e.getY();
             
             tempX = x;
             tempY = y;
             
             
         }

         @Override
         public void mouseReleased(MouseEvent e) {
             
             //linedraw=false;
             
             out.println("The result is " + linedraw);
             out.println("Released Coordinates: " + startX + " " + startY);
            
             int x = e.getX();
             int y = e.getY();
             
             prevX = tempX;
             prevY = tempY;
             
             startX = x;
             startY = y;
             
             
             lineslist.add(new Line());
         }

         @Override
         public void mouseEntered(MouseEvent e) {

         }

         @Override
         public void mouseExited(MouseEvent e) {

         }

         @Override
         public void mouseClicked(MouseEvent e) {
            
         }
         
         void saySomething(String eventDescription, MouseEvent e) {
            out.println(eventDescription + " detected on " + e.getComponent().getClass().getName());
         }
}
