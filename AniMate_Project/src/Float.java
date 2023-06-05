/***************************************
 Animation: Float across screen
 Working: A component follows a smooth motion to a new specified location

 Parameters passed to the function:
 1) JComponent component - The component to which the animation will be applied
 2) Point new_location - The new coordinates of the component that it will shift to
 3) int numIterationsLimit - The no. of iterations it takes for the animation to complete
 4) int sec - The duration the timer runs for

 Animation Principles: Motion Blur, Arcs
 ***************************************/
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Float {
    public static int seconds = 100;
    public static int numIterations = 0;

    public static Timer timer;

    public static void FloatComponent(JComponent component, Point new_location, int numIterationsLimit, int sec)
    {
        //Set the start and end positions of the component
        int startX = (int) component.getX();
        int startY = (int) component.getY();
        int endX = (int) new_location.getX();
        int endY = (int) new_location.getY();

        numIterations=0;
        seconds=sec;

        timer = new Timer(seconds, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Timer stops when number of iterations reaches the iteration limit specified by the user
                if (numIterations++ >= numIterationsLimit) {
                    timer.stop();
                } else {
                    //Updating the position of the component
                    component.setLocation(startX + numIterations * (endX - startX)/numIterationsLimit,
                            startY + numIterations * (endY - startY)/numIterationsLimit);
                }
            }
        });
        timer.start();
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

        JFrame frame = new JFrame("Float");

        JButton test = new JButton("Test Me");
        test.setPreferredSize(new Dimension(150,150));
        test.setSize(new Dimension(150,150));
        //test.setBounds(50,50,50,50);

        JTextField text = new JTextField(20);
        text.setPreferredSize(new Dimension(50,50));
        text.setSize(new Dimension(50,50));
        //test.setBounds(50,50,50,50);

        test.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Point new_location = new Point(10,250);
                Float.FloatComponent(test,new_location,15,20);
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });

        text.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Point new_location = new Point(150,50);
                Float.FloatComponent(text,new_location,5,50);
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });

        frame.getContentPane().add(test);
        frame.getContentPane().add(text);
        frame.pack();
        frame.setVisible(true);
        frame.setSize(new Dimension(500,500));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }*/
}
