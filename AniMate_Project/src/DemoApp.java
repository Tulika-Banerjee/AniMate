import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.util.ArrayList;

import static java.awt.GridBagConstraints.NONE;

public class DemoApp implements PropertyChangeListener {

    public final static String passPhrase = "password";
    public static String listMode = "Folding";
    public static String menuMode = "Folding";

    public static String listState = "Expanded";

    public static void createAndShowMainGUI() throws IOException {
        //JFrame frame = new JFrame("DemoMain");
        JFrame frame = new AniSheetableJFrame("DemoMain");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        JPanel centrePane = new JPanel(new BorderLayout());
        JPanel mainPane = new JPanel(new GridLayout());
        JPanel panel1 = new JPanel();
        JPanel panel2 = new JPanel();
        JPanel panel3 = new JPanel();
        JPanel panel4 = new JPanel();
        Image pane1Image = ImageIO.read(DemoApp.class.getResource("/images/LandingPage.png"));
        Image new_img1 = pane1Image.getScaledInstance(100,220, Image.SCALE_SMOOTH);
        JLabel pic1 = new JLabel(new ImageIcon(new_img1));
        Image pane2Image = ImageIO.read(DemoApp.class.getResource("/images/LoginPage.png"));
        Image new_img2 = pane2Image.getScaledInstance(100,220, Image.SCALE_SMOOTH);
        JLabel pic2 = new JLabel(new ImageIcon(new_img2));
        Image pane3Image = ImageIO.read(DemoApp.class.getResource("/images/RegistrationPage.png"));
        Image new_img3 = pane3Image.getScaledInstance(100,220, Image.SCALE_SMOOTH);
        JLabel pic3 = new JLabel(new ImageIcon(new_img3));
        Image pane4Image = ImageIO.read(DemoApp.class.getResource("/images/ProfileSetupPage.png"));
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
        rotate.setFocusPainted(false);
        rotate.setBackground(Color.decode("#FF725E"));
        rotate.setOpaque(true);
        rotate.setBorderPainted(false);
        rotate.setForeground(Color.white);

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

        JPanel centreMainPane = new JPanel();
        JPanel centreBottomPane = new JPanel();

        centreBottomPane.setLayout(new FlowLayout());

        centreMainPane.setLayout(new BorderLayout());

        centrePane.add(mainPane,BorderLayout.CENTER);
        centrePane.add(rotate,BorderLayout.SOUTH);
        centrePane.setBorder(new EmptyBorder(50,150,50,150));
        centreMainPane.add(centrePane,BorderLayout.NORTH);
        Container contentPane = frame.getContentPane();
        contentPane.add(centreMainPane,BorderLayout.CENTER);

        JTabbedPane tab = new TabTransitions.VenetianPane();
        //tab.addTab("Gradient Button",new ButtonGradient());
        tab.addTab("Button",new JButton("This is a Button"));
        tab.addTab("Label",new JLabel("This is a JLabel",SwingConstants.CENTER));
        tab.addTab("Text Field",new JTextField());

        JTabbedPane tab2 = new TabTransitions.InOutPane();
        //tab.addTab("Gradient Button",new ButtonGradient());
        tab2.addTab("Button",new JButton("This is a Button"));
        tab2.addTab("Label",new JLabel("This is a JLabel",SwingConstants.CENTER));
        tab2.addTab("Text Field",new JTextField());

        JPanel topPane = new JPanel(new GridLayout(2,1));


        JPanel menu = new JPanel();
        menu.setPreferredSize(new Dimension(1520,50));
        menu.setBackground(Color.decode("#FF725E"));

        menu.setLayout(new BorderLayout());
        //GridBagConstraints gbc = new GridBagConstraints();
        //gbc.anchor=GridBagConstraints.EAST;
        //gbc.fill=NONE;

        Image profileImage = ImageIO.read(DemoApp.class.getResource("/images/profile_icon.png"));
        Image profileImg = profileImage.getScaledInstance(30,30, Image.SCALE_SMOOTH);
        JButton profile = new JButton(new ImageIcon(profileImg));
        profile.setBackground(Color.decode("#FF725E"));
        profile.setOpaque(true);
        profile.setBorderPainted(false);
        profile.setFocusPainted(false);
        //profilePic.setBounds(new Rectangle(100,15,30,30));
        profile.setAlignmentX(Component.RIGHT_ALIGNMENT);
        JPanel profileGroup = new JPanel();
        profileGroup.setLayout(new FlowLayout());

        //profileGroup.setLayout(new BoxLayout(profileGroup,BoxLayout.Y_AXIS));
        profileGroup.add(profile);


        Image downArrowImage = ImageIO.read(DemoApp.class.getResource("/images/down-arrow.png"));
        Image downImg = downArrowImage.getScaledInstance(15,15, Image.SCALE_SMOOTH);
        ImageIcon dImg = new ImageIcon(downImg);

        Image upArrowImage = ImageIO.read(DemoApp.class.getResource("/images/up-arrow.png"));
        Image upImg = upArrowImage.getScaledInstance(15,15, Image.SCALE_SMOOTH);
        ImageIcon uImg = new ImageIcon(upImg);

        JButton toggle = new JButton(uImg);
        toggle.setBackground(Color.decode("#FF725E"));
        toggle.setOpaque(true);
        toggle.setBorderPainted(false);
        toggle.setFocusPainted(false);

        profileGroup.add(toggle);

        JPanel slidingMenu = new JPanel();
        slidingMenu.setLayout(new BoxLayout(slidingMenu,BoxLayout.Y_AXIS));
        JLabel settings = new JLabel("Settings");
        settings.setForeground(Color.white);
        settings.setFont(new Font("Calibri",Font.BOLD,14));
        JLabel privacy = new JLabel("Privacy");
        privacy.setForeground(Color.white);
        privacy.setFont(new Font("Calibri",Font.BOLD,14));
        JLabel profileLabel = new JLabel("Profile");
        profileLabel.setForeground(Color.white);
        profileLabel.setFont(new Font("Calibri",Font.BOLD,14));
        JLabel logout = new JLabel("Logout");
        logout.setForeground(Color.white);
        logout.setFont(new Font("Calibri",Font.BOLD,14));
        slidingMenu.add(settings);
        slidingMenu.add(privacy);
        slidingMenu.add(profileLabel);
        slidingMenu.add(logout);
        slidingMenu.setBorder(new EmptyBorder(20,20,20,20));

        slidingMenu.setBackground(Color.decode("#FF725E"));
        slidingMenu.setForeground(Color.white);

        //profileGroup.add(slidingMenu);
        profileGroup.setBackground(Color.decode("#FF725E"));
        menu.add(profileGroup,BorderLayout.EAST);
        //slidingMenu.setVisible(false);

        topPane.add(menu);
        JPanel tabPanes = new JPanel(new GridLayout(1,2));
        tabPanes.add(tab);
        tabPanes.add(tab2);

        topPane.add(tabPanes);


        contentPane.add(topPane,BorderLayout.NORTH);

        toggle.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                slidingMenu.setVisible(true);
                SlidingMenu.Slide(slidingMenu,"Vertical",menuMode,15,20);
                if(menuMode=="Folding") {
                    menuMode = "Expanding";
                    toggle.setIcon(dImg);
                }
                else if(menuMode=="Expanding") {
                    menuMode = "Folding";
                    toggle.setIcon(uImg);
                }
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

        JPanel leftPane = new JPanel();
        leftPane.setLayout(new BoxLayout(leftPane,BoxLayout.Y_AXIS));

        DefaultListModel listModel = new DefaultListModel();

        /*for (int i = 0; i < 10; i++) {
            listModel.addElement("List Item " + i);
        }*/
        listModel.addElement("Highlight on Hover");
        listModel.addElement("Animated Sheet Dialogs");
        listModel.addElement("Sliding Menu");
        listModel.addElement("Cyclic Rotation of components");
        listModel.addElement("Wiggle");
        listModel.addElement("Bounce");
        listModel.addElement("Smooth Scaling");
        listModel.addElement("Morphing");
        listModel.addElement("Drag and Drop");
        listModel.addElement("Button Gradient");
        listModel.addElement("Float Component");
        listModel.addElement("Animated JList");
        listModel.addElement("Transparent Frame");


        JList list = new AnimatedJList();
        list.setModel(listModel);
        list.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        list.setDropMode(DropMode.INSERT);
        JScrollPane scrollPane = new JScrollPane(list);
        scrollPane.setPreferredSize(new Dimension(250,600));

        list.setDragEnabled(true);
        list.setTransferHandler(new ListTransferHandler(scrollPane));

        Image rightArrow = ImageIO.read(DemoApp.class.getResource("/images/right-arrow.png"));
        Image rArrowImg = rightArrow.getScaledInstance(10,10, Image.SCALE_SMOOTH);
        ImageIcon rImg = new ImageIcon(rArrowImg);

        Image leftArrow = ImageIO.read(DemoApp.class.getResource("/images/left-arrow.png"));
        Image lArrowImg = leftArrow.getScaledInstance(10,10, Image.SCALE_SMOOTH);
        ImageIcon lImg = new ImageIcon(lArrowImg);

        JButton rArrow = new JButton(lImg);
        rArrow.setBackground(Color.decode("#FF725E"));
        rArrow.setOpaque(true);
        rArrow.setBorderPainted(false);
        rArrow.setFocusPainted(false);

        leftPane.add(scrollPane);
        JPanel ArrowPane = new JPanel();
        ArrowPane.setLayout(new BorderLayout());
        ArrowPane.add(rArrow,BorderLayout.WEST);
        leftPane.add(ArrowPane);

        JPanel areaPane = new JPanel();

        JTextArea area = new JTextArea();
        String text = "Dissolve Frame Page Turning Genie Effect";
        area.setText(text);
        area.setDragEnabled(true);
        JScrollPane scroll = new JScrollPane(area);
        scroll.setPreferredSize(new Dimension(400,100));
        areaPane.add(scroll);
        areaPane.setBorder(new EmptyBorder(0,50,0,50));

        centreMainPane.add(areaPane,BorderLayout.CENTER);

        rArrow.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(listState=="Folded")
                    listMode="Expanding";
                else listMode="Folding";
                DragAndDrop.DragAndDrop(scrollPane,"Horizontal",listMode,15,20);
                if(listMode=="Folding")
                {listMode="Expanding";
                    listState="Folded";
                    rArrow.setIcon(rImg);
                }
                else if(listMode=="Expanding")
                {listMode="Folding";
                    listState="Expanded";
                rArrow.setIcon(lImg);}
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

        area.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {

                if(!listState.equals("Expanded")) {
                    DragAndDrop.DragAndDrop(scrollPane, "Horizontal", "Expanding", 15, 20);
                    listState = "Expanded";
                    rArrow.setIcon(lImg);
                }
                System.out.println("Press registered");

            }

            @Override
            public void mouseReleased(MouseEvent e) {
                /*DragAndDrop.DragAndDrop(scrollPane,"Horizontal","Folding",15,20);
                System.out.println("Release registered");*/
                /*if(mode=="Folding")
                    mode="Expanding";
                else if(mode=="Expanding")
                    mode="Folding";*/
            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {
                listState="Folded";
                rArrow.setIcon(rImg);

            }
        });

        list.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {
                /*DragAndDrop.DragAndDrop(scrollPane,"Horizontal","Folding",15,20);
                System.out.println("Release registered");
                listState="Folded";*/

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });

        contentPane.add(leftPane,BorderLayout.WEST);

        JPanel rightPane = new JPanel();
        rightPane.setLayout(new BoxLayout(rightPane,BoxLayout.Y_AXIS));

        JPanel slidingMenuPane = new JPanel();
        slidingMenuPane.setLayout(new BorderLayout());
        slidingMenuPane.add(slidingMenu,BorderLayout.EAST);

        slidingMenu.setPreferredSize(new Dimension(200,70));

        rightPane.add(slidingMenuPane);

        ButtonGradient buttonGradient1 = new ButtonGradient();
        ButtonGradient buttonGradient2 = new ButtonGradient();
        ButtonGradient buttonGradient3 = new ButtonGradient();

        buttonGradient1.setText("Slow Gradient");
        buttonGradient1.setFocusPainted(false);

        buttonGradient2.setText("Moderate Gradient");
        buttonGradient2.setColor1(new Color(24, 184, 10));
        buttonGradient2.setColor2(new Color(13, 93, 142));
        buttonGradient2.setSizeSpeed(2.0F);
        buttonGradient2.setFocusPainted(false);

        buttonGradient3.setText("Fast Gradient");
        buttonGradient3.setColor1(new Color(200, 50, 5));
        buttonGradient3.setColor2(new Color(100, 91, 50));
        buttonGradient3.setSizeSpeed(3.5F);
        buttonGradient3.setFocusPainted(false);

        JButton save = new JButton("Save");
        JButton floatButton = new JButton("Float");
        JButton scaleButton = new JButton("Scale");

        save.setFocusPainted(false);
        floatButton.setFocusPainted(false);
        scaleButton.setFocusPainted(false);

        //rightPane.add(buttonGradient1);
        //rightPane.add(buttonGradient2);
        //rightPane.add(save);

        centreBottomPane.setLayout(new GridLayout(2,2));
        centreBottomPane.setBorder(new EmptyBorder(50,150,50,150));

        centreBottomPane.add(buttonGradient1);
        centreBottomPane.add(buttonGradient2);
        centreBottomPane.add(buttonGradient3);



        //JPanel savePane = new JPanel();
        //savePane.setLayout(new GridLayout(1,1));
        //savePane.add(save);
        //centreBottomPane.add(savePane);
        //savePane.setBorder(new EmptyBorder(0,400,0,0));
        centreBottomPane.add(save);
        centreBottomPane.add(floatButton);
        centreBottomPane.add(scaleButton);
        centreMainPane.add(centreBottomPane,BorderLayout.SOUTH);
        rightPane.setPreferredSize(new Dimension(200,600));

        contentPane.add(rightPane,BorderLayout.EAST);

        JPanel bottomPane = new JPanel();
        bottomPane.setPreferredSize(new Dimension(1400,30));
        bottomPane.setBackground(Color.decode("#FF725E"));

        contentPane.add(bottomPane,BorderLayout.SOUTH);

        JOptionPane optionPane = new JOptionPane ("Do you want to save?",
                JOptionPane.QUESTION_MESSAGE,
                JOptionPane.YES_NO_OPTION);


        optionPane.addPropertyChangeListener(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent pce) {
                if (pce.getPropertyName().equals(JOptionPane.VALUE_PROPERTY)) {
                    System.out.println("Selected option " +
                            pce.getNewValue());
                    ((AniSheetableJFrame) frame).hideSheet();

                }
            }
        });

        floatButton.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Point new_location = new Point(floatButton.getX(), floatButton.getY()+30);
                Float.FloatComponent(floatButton,new_location,5,50);
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

        scaleButton.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Dimension size = scaleButton.getSize();
                Dimension new_size = new Dimension(150,20);
                Scale.Scale(scaleButton,size,new_size,10,80);
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

        save.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

                JDialog dialog =
                        optionPane.createDialog (frame, "Save");
                ((AniSheetableJFrame) frame).showJDialogAsSheet (dialog);

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


        frame.pack();
        frame.setVisible(true);
    }
    private static void createAndShowGUI() throws IOException {
        //Create and set up the window.
        JFrame frame = new JFrame("DemoLogin");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        frame.setLayout(new FlowLayout());
        JPanel leftPanel = new JPanel();
        JPanel rightPanel = new JPanel();
        Image bgImage = ImageIO.read(DemoApp.class.getResource("/images/BgImgRight.png"));
        //Image newBgImg = bgImage.getScaledInstance(750,760, Image.SCALE_SMOOTH);
        JLabel bgPic = new JLabel(new ImageIcon(bgImage));
        rightPanel.add(bgPic);
        Image bgImage2 = ImageIO.read(DemoApp.class.getResource("/images/BgImg.png"));
        Image newBgImg2 = bgImage2.getScaledInstance(750,760, Image.SCALE_SMOOTH);
        JLabel bgPic2 = new JLabel(new ImageIcon(newBgImg2));
        JPanel loginPane = new JPanel();
        leftPanel.add(loginPane);
        leftPanel.setBorder(new EmptyBorder(140,200,85,200));
        JPanel mainPane = new JPanel();
        mainPane.setLayout(new BorderLayout());
        //loginPane.setLayout(new BoxLayout(loginPane,BoxLayout.Y_AXIS));
        loginPane.setLayout(new GridLayout(4,1));
        //mainPane.setPreferredSize(new Dimension(400,550));
        //loginPane.setBorder(new EmptyBorder(50,70,0,70));
        loginPane.setBackground(Color.white);
        JPanel usernamePane = new JPanel();
        usernamePane.setLayout(new BoxLayout(usernamePane,BoxLayout.Y_AXIS));
        //usernamePane.setBorder(new EmptyBorder(20,0,150,0));
        JPanel passwordPane = new JPanel();
        passwordPane.setLayout(new BoxLayout(passwordPane,BoxLayout.Y_AXIS));
        //passwordPane.setBorder(new EmptyBorder(0,0,170,0));
        JLabel username = new JLabel("Username");
        username.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel password = new JLabel("Password");
        password.setAlignmentX(Component.LEFT_ALIGNMENT);
        JTextField uname = new JTextField(15);
        JTextField pword = new JTextField(15);
        username.setFont(new Font("Calibri",Font.BOLD,16));
        username.setForeground(Color.decode("#FF725E"));
        username.setBorder(new EmptyBorder(20,0,20,0));
        password.setFont(new Font("Calibri",Font.BOLD,16));
        password.setForeground(Color.decode("#FF725E"));
        password.setBorder(new EmptyBorder(20,0,20,0));
        usernamePane.add(username);
        usernamePane.add(uname);
        passwordPane.add(password);
        passwordPane.add(pword);
        usernamePane.setBackground(Color.white);
        passwordPane.setBackground(Color.white);
        //uname.setBorder(new EmptyBorder(-50,0,-50,0));
        JButton login = new JButton("Login");
        JButton signup = new JButton("Sign Up");
        login.setBackground(Color.decode("#FF725E"));
        login.setOpaque(true);
        login.setBorderPainted(false);
        login.setFocusPainted(false);
        login.setForeground(Color.white);
        login.setFont(new Font("Calibri",Font.BOLD,16));
        signup.setFont(new Font("Calibri",Font.BOLD,16));
        //login.setAlignmentX(Component.CENTER_ALIGNMENT);
        loginPane.add(usernamePane);
        //loginPane.add(username);
        //loginPane.add(uname);
        loginPane.add(passwordPane);
        loginPane.setBorder(new EmptyBorder(100,20,30,20));
        //mainPane.setBorder(new EmptyBorder());
        //loginPane.add(password);
        //loginPane.add(pword);
        JPanel buttonPane = new JPanel();
        buttonPane.add(login);
        buttonPane.add(signup);
        buttonPane.setBorder(new EmptyBorder(10,10,20,10));
        buttonPane.setBackground(Color.white);
        mainPane.add(loginPane,BorderLayout.CENTER);
        mainPane.add(buttonPane,BorderLayout.SOUTH);
        login.setBorder(new EmptyBorder(10,50,10,50));
        signup.setBorder(new EmptyBorder(10,50,10,50));
        signup.setForeground(Color.decode("#FF725E"));
        leftPanel.add(mainPane);
        //leftPanel.add(bgPic2);

        login.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

                String password = pword.getText();
                if(!password.equals(passPhrase)){
                    Point location = pword.getLocation();
                    Wiggle.Wiggle(pword,location,5,50);
                }
                else {
                    System.out.println("Passwords Match!");
                    try {
                        createAndShowMainGUI();
                        frame.dispose();
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }

                }
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {
                Point location = login.getLocation();
                Bounce.Bounce(login,location,5,50);
                Dimension size = login.getSize();
                Highlight.HighlightButton(login,size,2,20,e);

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                Dimension size = login.getSize();
                Highlight.HighlightButton(login,size,2,20,e);
                //System.out.println(e.getID());
            }

            @Override
            public void mouseExited(MouseEvent e) {
                //System.out.println(e.getButton());
                Dimension size = login.getSize();
                Highlight.HighlightButton(login,size,2,20,e);

            }
        });

        signup.addMouseListener(new MouseListener() {
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
                Dimension size = signup.getSize();
                Highlight.HighlightButton(signup,size,2,20,e);
                //System.out.println(e.getID());
            }

            @Override
            public void mouseExited(MouseEvent e) {
                //System.out.println(e.getButton());
                Dimension size = signup.getSize();
                Highlight.HighlightButton(signup,size,2,20,e);

            }
        });

        pword.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode()==KeyEvent.VK_ENTER)
                {
                    String password = pword.getText();
                    if(!password.equals(passPhrase)){
                        Point location = pword.getLocation();
                        Wiggle.Wiggle(pword,location,5,50);
                    }
                    else System.out.println("Passwords Match!");
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });

        //leftPanel.setSize(new Dimension(600,500));
        leftPanel.setBackground(Color.decode("#FF725E"));
        //rightPanel.setBackground(Color.white);

        frame.setSize(new Dimension(1280,800));
        Container contentPane = frame.getContentPane();
        contentPane.add(leftPanel);
        contentPane.add(rightPanel);


        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {

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

        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                //Turn off metal's use of bold fonts
                //UIManager.put("swing.boldMetal", Boolean.FALSE);
                try {
                    createAndShowGUI();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });


    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }
}
