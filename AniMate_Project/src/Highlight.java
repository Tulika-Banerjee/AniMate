/***************************************
 Animation: Highlight a button on hover
 Working: The selected component grows in size when the
 mouse pointer hovers on it

 Parameters passed to the function:
 1) JButton button - The button to which the animation will be applied
 2) Dimension size - The dimensions of the button
 3) int numIterationsLimit - The no. of iterations it takes for the animation to complete
 4) int sec - The duration the timer runs for
 5) MouseEvent e - The event that calls Highlight i.e. Mouse_Entered or Mouse_Exited

 Animation Principles: Solid Drawing, Anticipation
 ***************************************/
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import static java.awt.event.MouseEvent.MOUSE_ENTERED;
import static java.awt.event.MouseEvent.MOUSE_EXITED;

public class Highlight {
    public static int seconds = 100;
    public static int numIterations = 0;

    public static Timer timer;


    public static void HighlightButton(JButton button, Dimension size, int numIterationsLimit, int sec, MouseEvent e)
    {
        //Assigning the current size of the button for future reference
        int width = (int) size.getWidth();
        int height = (int) size.getHeight();

        seconds=sec;
        numIterations=0;

        //Define functionality for the Mouse_Entered event
        if(e.getID()==MOUSE_ENTERED){
            //Specify the new dimensions for the button
            int end_width = width+5;
            int end_height = height+5;
            timer = new Timer(seconds, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    //Timer stops when number of iterations reaches the iteration limit specified by the user
                    if (numIterations++ >= numIterationsLimit) {
                        timer.stop();
                    } else {
                        //Updating the size of the component
                        button.setSize(width + numIterations * (end_width - width)/numIterationsLimit,
                                height + numIterations * (end_height - height)/numIterationsLimit);
                    }
                }
            });
            timer.start();
        }

        //Define functionality for the Mouse_Exited event
        if(e.getID()==MOUSE_EXITED){
            //Specify the new dimensions for the button
            int end_width = width-5;
            int end_height = height-5;
            timer = new Timer(seconds, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    //Timer stops when number of iterations reaches the iteration limit specified by the user
                    if (numIterations++ >= numIterationsLimit) {
                        timer.stop();
                    } else {
                        //Updating the size of the component
                        button.setSize(width - numIterations * (width - end_width)/numIterationsLimit,
                                height - numIterations * (height - end_height)/numIterationsLimit);
                    }
                }
            });
            timer.start();
        }
    }

    //Demo code to check functionality - not part of the final package
    /*public static void main(String[] args) {

        try {
            // Set System L&F
            UIManager.setLookAndFeel(
                    UIManager.getSystemLookAndFeelClassName());
        }
        catch (UnsupportedLookAndFeelException e) {

        }
        catch (InstantiationException e) {

        }
        catch (IllegalAccessException e) {

        }catch (ClassNotFoundException e) {

        }

        JFrame frame = new JFrame("Highlight");

        JButton test = new JButton("Test Me");
        test.setPreferredSize(new Dimension(50,50));
        test.setSize(new Dimension(50,50));
        test.setBounds(50,50,50,50);
        test.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                Dimension size = test.getSize();
                Highlight.HighlightButton(test,size,2,20,e);
                //System.out.println(e.getID());
            }

            @Override
            public void mouseExited(MouseEvent e) {
                //System.out.println(e.getButton());
                Dimension size = test.getSize();
                Highlight.HighlightButton(test,size,2,20,e);

            }
        });
        frame.getContentPane().add(test);
        frame.pack();
        frame.setVisible(true);
        frame.setSize(new Dimension(500,500));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }*/
}


