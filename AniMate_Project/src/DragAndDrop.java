/***************************************
 Animation: Drag and Drop
 Working: Dragging a component prompts the destination
 component to appear on the screen

 Parameters passed to the function:
 1) JComponent component - The destination component to which the animation will be applied
 2) String type - Specifies whether the animation is Horizontal or Vertical
 3) String direction - Specifies the direction in which the component needs to move (Folding or Expanding)
 4) int numIterationsLimit - The no. of iterations it takes for the animation to complete
 5) int sec - The duration that the timer runs for

 Animation Principles: Slow in and slow out, Motion Blur, Solid Drawing
 ***************************************/
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

public class DragAndDrop {

    public static int seconds = 100;
    public static int numIterations = 0;

    public static Timer timer;

    //Default mode is set to 'Folding' i.e Outgoing animation
    public static String mode = "Folding";

    public static void DragAndDrop(JComponent component, String type, String direction, int numIterationsLimit, int sec)
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

        JFrame frame = new JFrame("Drag and Drop");
        frame.setLayout(new BorderLayout());

        JButton test = new JButton("Test Me");
        test.setPreferredSize(new Dimension(150,150));
        test.setSize(new Dimension(150,150));
        JPanel mainPanel = new JPanel();
        JPanel menuPanel = new JPanel();
        Image pane1Image = ImageIO.read(DragAndDrop.class.getResource("/images/LandingPage.png"));
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

        frame.getContentPane().add(test,BorderLayout.SOUTH);
        JMenuBar mainMenu = new JMenuBar();
        JMenu menu1 = new JMenu("File");
        JMenu menu2 = new JMenu("Edit");
        mainMenu.add(menu1);
        mainMenu.add(menu2);
        frame.setJMenuBar(mainMenu);
        JPanel rightPanel = new JPanel();
        JLabel label1 = new JLabel("Label1");
        JLabel label2 = new JLabel("Label2");
        JLabel label3 = new JLabel("Label3");
        rightPanel.add(label1);
        rightPanel.add(label2);
        rightPanel.add(label3);
        frame.getContentPane().add(rightPanel,BorderLayout.EAST);
        rightPanel.setLayout(new FlowLayout());
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BorderLayout());
        frame.getContentPane().add(centerPanel,BorderLayout.CENTER);

        label1.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {
                System.out.println("I'm selected");
                DragAndDrop.DragAndDrop(menuPanel,"Horizontal","Expanding",15,20);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                DragAndDrop.DragAndDrop(menuPanel,"Horizontal","Folding",15,20);
                System.out.println("I'm released");


            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });


        label2.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {
                System.out.println("I'm selected");
                DragAndDrop.DragAndDrop(menuPanel,"Horizontal","Expanding",15,20);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                DragAndDrop.DragAndDrop(menuPanel,"Horizontal","Folding",15,20);
                System.out.println("I'm released");


            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });

        label3.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {
                System.out.println("I'm selected");
                DragAndDrop.DragAndDrop(menuPanel,"Horizontal","Expanding",15,20);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                DragAndDrop.DragAndDrop(menuPanel,"Horizontal","Folding",15,20);
                System.out.println("I'm released");


            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });

        DefaultListModel listModel = new DefaultListModel();

        for (int i = 0; i < 10; i++) {
            listModel.addElement("List Item " + i);
        }

        JList list = new AnimatedJList();
        list.setModel(listModel);
        list.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        JScrollPane scrollPane = new JScrollPane(list);
        scrollPane.setPreferredSize(new Dimension(400,100));

        list.setDragEnabled(true);
        list.setTransferHandler(new ListTransferHandler());

        test.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                DragAndDrop.DragAndDrop(scrollPane,"Horizontal",mode,15,20);
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

        centerPanel.add(scrollPane,BorderLayout.WEST);

        frame.pack();
        frame.setVisible(true);
        frame.setSize(new Dimension(500,500));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }*/
}
