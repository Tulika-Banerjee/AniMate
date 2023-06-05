/***************************************
 Animation: Cyclic Rotation of Components
 Working: When multiple instances of the same component
 are to be displayed in a limited space, it allows the user to
 rotate through the components. The components slide left or
 right to make space for the next component.

 Parameters passed to the function:
 1) Arraylist<JComponent> components - An array containing all the components that the cyclic rotation should be applied to
 2) int numPanes - The number of Panes to be displayed at a time
 3) int numIterationsLimit - The no. of iterations it takes for the animation to complete
 4) int sec - The duration that the timer runs for

 Animation Principles: Slow in and slow out, Motion Blur
 ***************************************/
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.ArrayList;

public class CyclicRotation {

    public static int seconds = 100;
    public static int numIterations = 0;

    public static Timer timer;

    public static ArrayList<Integer> starts;

    public static void CyclicRotation(ArrayList<JComponent> components, int numPanes, int numIterationsLimit, int sec)
    {
        int totalPanes = components.size();
        //Total Width of the displayed region is calculated by multiplying the number of panes that can be visible at a time with the width of each component
        int totalWidth = numPanes*components.get(0).getWidth();

        starts = new ArrayList<Integer>();
        ArrayList<Integer> ends = new ArrayList<Integer>();

        //Initializing the start position for each component in the list
        for(int i=0;i<totalPanes;i++)
        {
            Integer startX = components.get(i).getX();
            starts.add(startX);
        }

        numIterations=0;
        seconds=sec;

        timer = new Timer(seconds, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Timer stops when number of iterations reaches the iteration limit specified by the user
                if (numIterations++ >= numIterationsLimit) {
                    timer.stop();
                } else {

                    for(int i=0;i<totalPanes;i++)
                    {
                        //Calculating the end location based on the start location and width of the component
                        Integer startX = starts.get(i);
                        int startY = components.get(i).getY();
                        Integer endX = startX+components.get(i).getWidth();

                        //Updating the location of each component
                        components.get(i).setLocation(startX + numIterations * (endX - startX)/numIterationsLimit,
                                startY);
                        Component[] comps = components.get(i).getComponents();
                        if(endX>=totalWidth)
                        {
                            startX = 0 - components.get(i).getWidth();
                            endX=0;
                            components.get(i).setLocation(startX + numIterations * (endX - startX)/numIterationsLimit,
                                    startY);
                        }

                    }
                }

            }
        });

        timer.start();
        //Updating the start positions with the updated start positions for each component
        for(int i=0;i<totalPanes;i++)
        {
            if(components.get(i).getX()>=totalWidth)
                starts.set(i,0);
            else starts.set(i,components.get(i).getX());
        }
    }

    //Demo code to test functionality - not part of the final package
    /*public static void main(String[] args) throws IOException {

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

        JFrame frame = new JFrame("Cyclic Rotation");
        JPanel mainPane = new JPanel(new GridLayout());
        JPanel panel1 = new JPanel();
        JPanel panel2 = new JPanel();
        JPanel panel3 = new JPanel();
        JPanel panel4 = new JPanel();
        Image pane1Image = ImageIO.read(CyclicRotation.class.getResource("/images/LandingPage.png"));
        Image new_img1 = pane1Image.getScaledInstance(100,220, Image.SCALE_SMOOTH);
        JLabel pic1 = new JLabel(new ImageIcon(new_img1));
        Image pane2Image = ImageIO.read(CyclicRotation.class.getResource("/images/LoginPage.png"));
        Image new_img2 = pane2Image.getScaledInstance(100,220, Image.SCALE_SMOOTH);
        JLabel pic2 = new JLabel(new ImageIcon(new_img2));
        Image pane3Image = ImageIO.read(CyclicRotation.class.getResource("/images/RegistrationPage.png"));
        Image new_img3 = pane3Image.getScaledInstance(100,220, Image.SCALE_SMOOTH);
        JLabel pic3 = new JLabel(new ImageIcon(new_img3));
        Image pane4Image = ImageIO.read(CyclicRotation.class.getResource("/images/ProfileSetupPage.png"));
        Image new_img4 = pane4Image.getScaledInstance(100,220, Image.SCALE_SMOOTH);
        JLabel pic4 = new JLabel(new ImageIcon(new_img4));
        panel1.add(pic1);
        panel2.add(pic2);
        panel3.add(pic3);
        panel4.add(pic4);
        mainPane.add(panel1);
        mainPane.add(panel2);
        mainPane.add(panel3);
        mainPane.add(panel4);

        JButton rotate = new JButton("Rotate");

        rotate.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                ArrayList<JComponent> panes = new ArrayList<>();
                panes.add(panel1);
                panes.add(panel2);
                panes.add(panel3);
                panes.add(panel4);
                CyclicRotation.CyclicRotation(panes,4,10,50);
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

        frame.getContentPane().setLayout(new BorderLayout());
        frame.getContentPane().add(mainPane,BorderLayout.CENTER);
        frame.getContentPane().add(rotate,BorderLayout.SOUTH);
        frame.pack();
        frame.setVisible(true);
        frame.setSize(new Dimension(500,500));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }*/
}
