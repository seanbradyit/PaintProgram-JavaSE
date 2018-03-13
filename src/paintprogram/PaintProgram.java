/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package paintprogram;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
//import java.util.LinkedList;

import static java.lang.System.out;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author Administrator
 */
public class PaintProgram{
    
    public static LinkedList<DrawPlane.Line> lineslist = new LinkedList<DrawPlane.Line>();
    
    static int startX = 0, startY = 0, prevX = 0, prevY = 0, tempX = 0, tempY = 0;
    static boolean linedraw = true;
    
    static boolean export = false;
    
    public static class DrawPlane extends JPanel{
        
        DrawPlane(){
           
        }
        
        public DrawPlane getP(){
                return this;
        }
        
        /*public void Line(Graphics g, int lastX, int lastY, int nextX, int nextY){
            g.drawLine(lastX, lastY, nextX, nextY);
            repaint();
        }*/
        
        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            
            Color WHITE = new Color(255,255,255);
            g.setColor(WHITE);
            
            int x = this.getWidth();
            int y = this.getHeight();
            
            g.fillRect(0,0,x,y);
            
            Color RED = new Color(255,0,0);
            g.setColor(RED);
            
            g.drawLine(0, 513, 513, 513);
            g.drawLine(513, 0, 513, 513);

            
            Color BLACK = new Color(0,0,0);
            g.setColor(BLACK);
            
            if(true==linedraw){
                for (Line line : lineslist) {
                    g.drawLine(line.lastX, line.lastY, line.nextX, line.nextY);
                    repaint();
                }
                //g.drawLine(prevX, prevY, startX, startY);
            }
            
            
             
            repaint();
        }
        
        public static class Line{
            int lastX, lastY, nextX, nextY;

            public Line(){
                this.lastX = prevX;
                this.lastY = prevY;
                this.nextX = startX;
                this.nextY = startY;
            }

            public void add(Line n) {
                lineslist.add(n);        
            }
        }
        
    }
    
    private static class BrushSelect extends JPanel {
        
            BrushSelect(){
                
                // ADDING EXTRA SPACE WHERE NEEDED
                //add(Box.createRigidArea(new Dimension(0,10)));
                
                // ADDING IMAGE ICON
                ImageIcon icon = new ImageIcon(this.getClass().getResource("/resources/editortop.gif"));
                JLabel tooltype = new JLabel(icon);
                //tooltype.setVerticalTextPosition(JLabel.BOTTOM);
                //tooltype.setHorizontalTextPosition(JLabel.CENTER);
                add(tooltype);
                
                
                JButton choice1 = new JButton("Pencil");
                JButton choice2 = new JButton("Pen");
                JButton choice3 = new JButton("Fill Color");
                JButton choice4 = new JButton("Clear Screen");
                JButton leave = new JButton("Exit Paint");
                
                PencilButton pencilchoice = new PencilButton();
                choice1.addActionListener(pencilchoice);
                add(choice1);

                PenButton penchoice = new PenButton();
                choice2.addActionListener(penchoice);
                add(choice2);
                
                FillButton fillchoice = new FillButton();
                choice3.addActionListener(fillchoice);
                add(choice3);
                
                ClearButton clearchoice = new ClearButton();
                choice4.addActionListener(clearchoice);
                add(choice4);
                
                ExitButton listener = new ExitButton();
                leave.addActionListener(listener);
                add(leave);
                
            }
        
            @Override
            public void paintComponent(Graphics g) {
                //super.paintComponent(g);
                //
            }
    }
    
    
    
    
    
    private static class PencilButton implements ActionListener {
        
            @Override
            public void actionPerformed(ActionEvent e) {
                out.println("This brush you have selected is the Pencil Brush");
                linedraw=!linedraw;
            }
    }
    
    private static class PenButton implements ActionListener {
        
            @Override
            public void actionPerformed(ActionEvent e) {
                out.println("This brush you have selected is the Pen Brush");  
            }
    }
    
    private static class FillButton implements ActionListener {
        
            @Override
            public void actionPerformed(ActionEvent e) {
                out.println("This brush you have selected is the Fill Brush");  
            }
    }
    
    private static class ClearButton implements ActionListener {
        
            @Override
            public void actionPerformed(ActionEvent e) {
                out.println("This brush you have selected is the Clear Brush");
                lineslist.clear();
            }
    }
    
    
    private static class ExitButton implements ActionListener {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
    }
    
    private static class ExportButton implements ActionListener {
        
            private JPanel base = new JPanel();
        
            ExportButton(JPanel pan){
                base = pan;
            }
        
            @Override
            public void actionPerformed(ActionEvent e) {
                out.println("This brush you have selected is the Export Button");

                BufferedImage bi;
                bi = createImage(base);
                String name = JOptionPane.showInputDialog(base, "Name of JPG file:");
                File outputfile = new File(name + ".jpg");
                try {
                    ImageIO.write(bi, "jpg", outputfile);
                } catch (IOException ex) {
                    Logger.getLogger(PaintProgram.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
    }
    
    
    public static BufferedImage createImage(JPanel pan) {

        int w = 512;
        int h = 512;
        BufferedImage bi = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = bi.createGraphics();
        pan.print(g);
        return bi;
        
    }
    
    public static void main(String[] args) throws IOException{
        // TODO code application logic here
        
            // Content creation
            MouseCatch click = new MouseCatch();
            DrawPlane displayPanel = new DrawPlane();
            displayPanel.addMouseListener(click);
        
            // Creating pane for content and adding content defined above
            JPanel content = new JPanel();
            content.setLayout(new BorderLayout());
            content.add(displayPanel, BorderLayout.CENTER);
            
            //Creating Listener
            BrushSelect bselect = new BrushSelect();
            bselect.setLayout(new BoxLayout(bselect, BoxLayout.PAGE_AXIS));
            content.add(bselect, BorderLayout.EAST);
            
            JButton createimg = new JButton("Export JPG");
            ExportButton exportpic = new ExportButton(displayPanel);
            createimg.addActionListener(exportpic);
            content.add(createimg, BorderLayout.SOUTH);
            
            
            // Creating window for panel with content and adding it
            JFrame window = new JFrame("Sean's Paint");
            window.setContentPane(content);
            window.setSize(800,600);
            window.setLocation(100,100);
            window.setVisible(true);

    }
}