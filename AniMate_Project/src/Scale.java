/***************************************
 Animation: Smooth Scaling of components
 Working: The selected component scales to new size smoothly

 Parameters passed to the function:
 1) JComponent component - The component to which the animation will be applied
 2) Dimension size - The dimensions of the component
 3) Dimension new_size - The new dimensions for the component that it will be scaled to
 4) int numIterationsLimit - The no. of iterations it takes for the animation to complete
 5) int sec - The duration the timer runs for

 Animation Principles: Motion Blur
 ***************************************/
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Scale {

    public static int seconds = 100;
    public static int numIterations = 0;

    public static Timer timer;


    public static void Scale(JComponent component, Dimension size, Dimension new_size, int numIterationsLimit, int sec)
    {
        //Assigning the current size of the component for future reference
        int width = (int) size.getWidth();
        int height = (int) size.getHeight();

        seconds=sec;
        numIterations=0;

        //Deriving the new dimensions for the component
        int end_width = (int) new_size.getWidth();
        int end_height = (int) new_size.getHeight();
        timer = new Timer(seconds, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Timer stops when number of iterations reaches the iteration limit specified by the user
                if (numIterations++ >= numIterationsLimit) {
                    timer.stop();
                } else {
                    //Updating the size of the component
                    component.setSize(width + numIterations * (end_width - width)/numIterationsLimit,
                            height + numIterations * (end_height - height)/numIterationsLimit);
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

        JFrame frame = new JFrame("Scale");

        JButton test = new JButton("Test Me");
        test.setPreferredSize(new Dimension(50,50));
        test.setSize(new Dimension(50,50));
        test.setBounds(50,50,50,50);
        test.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Dimension size = test.getSize();
                Dimension new_size = new Dimension(150,20);
                Scale.Scale(test,size,new_size,10,80);
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
        frame.pack();
        frame.setVisible(true);
        frame.setSize(new Dimension(500,500));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }*/
}
