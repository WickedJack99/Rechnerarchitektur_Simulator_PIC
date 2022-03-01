package Frontend.PIC_SIMULATOR_GUI_JAVA;

import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.border.Border;

public class GUIMainFrame extends JFrame {

    /**
     * Constructor
     */
    public GUIMainFrame() {

        this.setTitle("PIC-Simulator GUI"); // sets title of frame
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // if x is pressed, exit application (HIDE_ON_CLOSE-hides application, DO_NOTHING_ON_CLOSE-prevents user from closing application)
        //this.setResizable(false); // prevent frame from beeing resized
        this.setSize(420, 420); //sets x and y dimension of frame
        this.setVisible(true); //make frame visible

        ImageIcon guiLogo = new ImageIcon("./images/gui_logo.png"); // create an ImageIcon
        this.setIconImage(guiLogo.getImage()); // change icon of frame

        Color guiBackgroundColor = new Color(255, 255, 255); // 0xFFFFFF || 0, 0, 0
        //this.getContentPane().setBackground(guiBackgroundColor); //change color of background

        JLabel text = new JLabel(); // create label, passing of text at constructor possible
        text.setText("Bro do you even code?"); // set text of label
        text.setIcon(guiLogo);
        text.setHorizontalTextPosition(JLabel.CENTER); //JLabel.LEFT, JLabel.CENTER, JLabel.RIGHT
        text.setVerticalTextPosition(JLabel.TOP); //JLabel.TOP, JLabel.CENTER, JLabel.BOTTOM
        text.setForeground(Color.green); // set fontcolor of text
        text.setFont(new Font("Arial", Font.ITALIC, 20)); // set fontstyle, fontformat (PLAIN, BOLD, ITALIC), and size
        text.setIconTextGap(-100); // set gap of text to image
        text.setBackground(Color.BLUE); // set background color
        text.setOpaque(true); // display background color

        Border border = BorderFactory.createLineBorder(Color.green, 3); // creates border for label, color and size of border

        text.setBorder(border); // sets border of label to "border"
        text.setVerticalAlignment(JLabel.TOP); // vertically alligns label "text" (JLabel.TOP, JLabel.CENTER, JLabel.BOTTOM)
        text.setHorizontalAlignment(JLabel.CENTER); // horizontally aligns label "text" (JLabel.LEFT, JLabel.CENTER, JLabel.RIGHT)

        this.add(text); // add label to frame
    }
}