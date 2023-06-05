/***************************************
 Animation: Sliding Menu motion
 Working:  A Menu appears in a continuous motion from a
 start point and returns to the same point on close

 Parameters passed to the function:
 1) JComponent component - The menu component to which the animation will be applied
 2) String type - Specifies whether the animation is Horizontal or Vertical
 3) String direction - Specifies the direction in which the component needs to move (Folding or Expanding)
 4) int numIterationsLimit - The no. of iterations it takes for the animation to complete
 5) int sec - The duration that the timer runs for

 Animation Principles: Arcs, Motion Blur, Solid Drawing
 ***************************************/

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;

public class SlidingMenu extends JFrame {

    public static int seconds = 100;
    public static int numIterations = 0;

    public static Timer timer;
    //Default mode is set to 'Folding' i.e Outgoing animation
    public static String mode = "Folding";


    public static void Slide(JComponent component, String type, String direction, int numIterationsLimit, int sec)
    {
        int startX;
        int startY;
        int endX;
        int endY;

        numIterations=0;
        seconds=sec;

        //Functionality defined for Horizontal Expanding motion
        if(type.matches("Horizontal")&&direction.matches("Expanding"))
        {
            //Calculating the start and end positions for the component
            startX = component.getParent().getX()-component.getWidth();
            startY = component.getY();
            endX = component.getParent().getX();

            int finalStartY = startY;
            int finalStartX = startX;
            int finalEndX = endX;
            timer = new Timer(seconds, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    //Timer stops when number of iterations reaches the iteration limit specified by the user
                    if (numIterations++ >= numIterationsLimit) {
                        timer.stop();

                    } else {
                        //Updating the position of the component
                        component.setLocation(finalStartX + numIterations * (finalEndX - finalStartX)/numIterationsLimit,
                                finalStartY);
                    }
                }
            });

        }

        //Functionality defined for Vertical Expanding motion
        if(type.matches("Vertical")&&direction.matches("Expanding"))
        {
            //Calculating the start and end positions for the component
            startX = component.getX();
            startY = component.getParent().getY()-component.getHeight();
            endY = component.getParent().getY();

            int finalStartY1 = startY;
            int finalStartX1 = startX;

            int finalEndY1 = endY;
            timer = new Timer(seconds, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    //Timer stops when number of iterations reaches the iteration limit specified by the user
                    if (numIterations++ >= numIterationsLimit) {
                        timer.stop();
                    } else {
                        //Updating the position of the component
                        component.setLocation(finalStartX1,
                                finalStartY1 + numIterations * (finalEndY1 - finalStartY1)/numIterationsLimit);
                    }
                }
            });
        }

        //Functionality defined for Horizontal Folding motion
        if(type.matches("Horizontal")&&direction.matches("Folding"))
        {
            //Calculating the start and end positions for the component
            startX = component.getX();
            startY = component.getY();
            endX = startX-component.getWidth();

            int finalStartY = startY;
            int finalStartX = startX;
            int finalEndX = endX;
            timer = new Timer(seconds, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    //Timer stops when number of iterations reaches the iteration limit specified by the user
                    if (numIterations++ >= numIterationsLimit) {
                        timer.stop();

                    } else {
                        //Updating the position of the component
                        component.setLocation(finalStartX - numIterations * (finalStartX - finalEndX)/numIterationsLimit,
                                finalStartY);
                    }
                }
            });
        }

        //Functionality defined for Vertical Folding motion
        if(type.matches("Vertical")&&direction.matches("Folding"))
        {
            //Calculating the start and end positions for the component
            startX = component.getX();
            startY = component.getY();
            endY = component.getParent().getY()-component.getHeight();

            int finalStartY1 = startY;
            int finalStartX1 = startX;

            int finalEndY1 = endY;
            timer = new Timer(seconds, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    //Timer stops when number of iterations reaches the iteration limit specified by the user
                    if (numIterations++ >= numIterationsLimit) {
                        timer.stop();
                    } else {
                        //Updating the position of the component
                        component.setLocation(finalStartX1,
                                finalStartY1 - numIterations * (finalStartY1 - finalEndY1)/numIterationsLimit);
                    }
                }
            });
        }

        timer.start();

    }

    //Demo Code to check the functionality - not part of the final package
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

        JFrame frame = new JFrame("Slide");
        frame.setLayout(new BorderLayout());

        JButton test = new JButton("Test Me");
        test.setPreferredSize(new Dimension(150,150));
        test.setSize(new Dimension(150,150));
        JPanel mainPanel = new JPanel();
        JPanel menuPanel = new JPanel();
        Image pane1Image = ImageIO.read(SlidingMenu.class.getResource("/images/LandingPage.png"));
        Image new_img1 = pane1Image.getScaledInstance(100,220, Image.SCALE_SMOOTH);
        JLabel pic1 = new JLabel(new ImageIcon(new_img1));


        mainPanel.setLayout(new GridBagLayout());
        menuPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx=0;
        gbc.gridy=0;
        gbc.anchor=GridBagConstraints.FIRST_LINE_START;
        menuPanel.add(pic1,gbc);
        mainPanel.add(menuPanel,gbc);
        frame.add(mainPanel,BorderLayout.WEST);

        test.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                SlidingMenu.Slide(menuPanel,"Horizontal",mode,15,20);
                if(mode=="Folding")
                    mode="Expanding";
                else if(mode=="Expanding")
                    mode="Folding";
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

        frame.getContentPane().add(test,BorderLayout.SOUTH);
        JMenuBar mainMenu = new JMenuBar();
        JMenu menu1 = new JMenu("File");
        JMenu menu2 = new JMenu("Edit");
        mainMenu.add(menu1);
        mainMenu.add(menu2);
        frame.setJMenuBar(mainMenu);
        JPanel topPanel = new JPanel();
        frame.pack();
        frame.setVisible(true);
        frame.setSize(new Dimension(500,500));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }*/

}
