/***************************************
 Animation: Wiggle in place
 Working: The text-field wiggles/shakes along the horizontal
 axis to show invalid input e.g incorrect password

 Parameters passed to the function:
 1) JComponent component - The component to which the animation will be applied
 2) Point location - The current coordinates of the component
 3) int numIterationsLimit - The no. of iterations it takes for the animation to complete
 4) int sec - The duration the timer runs for

 Animation Principles: Solid Drawing, Follow Through
 ***************************************/

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Wiggle {

    public static int seconds = 100;
    public static int numIterations = 0;

    public static Timer timer;

    public static void Wiggle(JComponent component, Point location, int numIterationsLimit, int sec)
    {
        //The component's start coordinates are derived from the passed location parameter and saved for reference
        int startX = (int) location.getX();
        int startY = (int) location.getY();
        //The end position is calculated based on startY since motion takes place in the vertical direction
        int endX = startX+20;

        numIterations=0;
        seconds=sec;

        timer = new Timer(seconds, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Timer stops when number of iterations reaches the iteration limit specified by the user
                if (numIterations++ >= numIterationsLimit) {
                    timer.stop();
                } else {
                    //The location of the component is updated to show a wiggle/shake
                    component.setLocation(startX + numIterations * (endX - startX)/numIterationsLimit,
                            startY);
                    //The location of the component is reverted to its original position at the end of wiggle/shake
                    component.setLocation(endX - numIterations * (endX - startX)/numIterationsLimit,
                            startY);
                }
            }
        });
        timer.start();
    }

    //Demo Code to check the functionality - not part of the final package
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

        JFrame frame = new JFrame("Wiggle");
        JTextField text = new JTextField(20);
        text.setPreferredSize(new Dimension(50,50));
        text.setSize(new Dimension(50,50));

        JButton submit = new JButton("Submit");

        String passPhrase = "password";

        text.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode()==KeyEvent.VK_ENTER)
                {
                    String password = text.getText();
                    if(!password.equals(passPhrase)){
                        Point location = text.getLocation();
                        Wiggle.Wiggle(text,location,5,50);
                    }
                    else System.out.println("Passwords Match!");
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });

        submit.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String password = text.getText();
                if(!password.equals(passPhrase)){
                    Point location = text.getLocation();
                    Wiggle.Wiggle(text,location,5,50);
                }
                else System.out.println("Passwords Match!");
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                Dimension size = submit.getSize();
                Highlight.HighlightButton(submit,size,2,20,e);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                Dimension size = submit.getSize();
                Highlight.HighlightButton(submit,size,2,20,e);

            }
        });

        frame.getContentPane().setLayout(new BorderLayout());
        frame.getContentPane().add(text,BorderLayout.CENTER);
        frame.getContentPane().add(submit,BorderLayout.SOUTH);
        submit.setMargin(new Insets(20,20,20,20));
        frame.pack();
        frame.setVisible(true);
        frame.setSize(new Dimension(500,500));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }*/
}
