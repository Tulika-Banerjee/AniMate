/***************************************
 Animation: Animate transitions between tabs
 Working: When transitioning between two tabs of a tabbed pane, the tabs
 fade out and fade in through the use of horizontal or vertical blinds

 Animation Principles: Solid Drawing, Motion Blur, Dissolves
 Code referred from Swing Hacks by Joshua Marinacci and Chris Adamson Hack#8 with modifications for multiple components
 ***************************************/

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.image.BufferedImage;

public class TabTransitions {

    public static class TransitionTabbedPane extends JTabbedPane
            implements ChangeListener, Runnable {

        protected int step;
        protected BufferedImage buf = null;
        protected int previous_tab = -1;
        protected int animation_length = 20;

        public TransitionTabbedPane() {
            super();
            this.addChangeListener(this);
        }

        public void paintChildren(Graphics g) {
            super.paintChildren(g);

            if(step != -1) {
                Rectangle size = this.getComponentAt(0).getBounds();
                Graphics2D g2 = (Graphics2D)g;
                paintTransition(g2, step, size, buf);
            }
        }

        public int getAnimationLength() {
            return this.animation_length;
        }

        public void setAnimationLength(int length) {
            this.animation_length = length;
        }

        public void paintTransition(Graphics2D g2, int step,
                                    Rectangle size, Image prev) {
        }


        // threading code
        public void stateChanged(ChangeEvent evt) {
            new Thread(this).start();
        }


        public void run() {
            step = 0;

            // save the previous tab
            if(previous_tab != -1) {
                Component comp = this.getComponentAt(previous_tab);
                buf = new BufferedImage(comp.getWidth(),
                        comp.getHeight(),
                        BufferedImage.TYPE_4BYTE_ABGR);
                comp.paint(buf.getGraphics());
            }

            for(int i=0; i<animation_length; i++) {
                step = i;
                repaint();
                try {
                    Thread.currentThread().sleep(100);
                } catch (Exception ex) {
                    p("ex: " + ex);
                }
            }

            step = -1;
            previous_tab = this.getSelectedIndex();
            repaint();
        }


        public static void p(String s) {
            System.out.println(s);
        }
    }

    public static class InOutPane extends TransitionTabbedPane {
        public void paintTransition(Graphics2D g2, int state,
                                    Rectangle size, Image prev) {

            int length = getAnimationLength();
            int half = length/2;

            double scale = size.getHeight()/length;
            int offset = 0;
            // calculate the fade out part
            if(state >= 0 && state < half) {
                // draw the saved version of the old tab component
                if(prev != null) {
                    g2.drawImage(prev,(int)size.getX(),(int)size.getY(),null);
                }
                offset = (int)((10-state)*scale);
            }

            // calculate the fade in part
            if(state >= half && state < length) {
                g2.setColor(Color.white);
                offset = (int)((state-10)*scale);
            }


            // do the drawing
            g2.setColor(Color.white);
            Rectangle area = new Rectangle((int)(size.getX()+offset),
                    (int)(size.getY()+offset),
                    (int)(size.getWidth()-offset*2),
                    (int)(size.getHeight()-offset*2));
            g2.fill(area);
        }
    }

    public static class VenetianPane extends TransitionTabbedPane {
        public VenetianPane() {
            super();
            this.setAnimationLength(10);
        }
        public void paintTransition(Graphics2D g2, int step,
                                    Rectangle size, Image prev) {

            int length = getAnimationLength();
            int half = length/2;

            // create a blind
            Rectangle blind = new Rectangle();

            // calculate the fade out part
            if(step >= 0 && step < half) {
                // draw the saved version of the old tab component
                if(prev != null) {
                    g2.drawImage(prev,(int)size.getX(),(int)size.getY(),null);
                }
                // calculate the growing blind
                blind = new Rectangle(
                        (int)size.getX(),
                        (int)size.getY(),
                        step,
                        (int)size.getHeight());
            }


            // calculate the fade in part
            if(step >= half && step < length) {
                // calculate the shrinking blind
                blind = new Rectangle(
                        (int)size.getX(),
                        (int)size.getY(),
                        length-step,
                        (int)size.getHeight());
                blind.translate(step-half,0);
            }


            // draw the blinds
            for(int i=0; i<size.getWidth()/half; i++) {
                g2.setColor(Color.white);
                g2.fill(blind);
                blind.translate(half,0);
            }

        }
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

        JFrame frame = new JFrame("Fade Tabs");

        //JTabbedPane tab = new InOutPane();
        JTabbedPane tab = new VenetianPane();
        tab.addTab("t1",new JButton("Test Button 1"));
        tab.addTab("t2",new JButton("Test Button 2"));

        frame.getContentPane().add(tab);
        frame.pack();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }*/
}
