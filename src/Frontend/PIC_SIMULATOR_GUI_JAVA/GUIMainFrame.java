package Frontend.PIC_SIMULATOR_GUI_JAVA;

import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.border.Border;

import Backend.EepromLoader.ReadEepromFile;
import Backend.Runtime.Environment;

public class GUIMainFrame extends JFrame {

    GUITestFileTable oGUITestFileTable;
    GUIMenuBar oGUIMenuBar;
    /**
     * Constructor
     */
    public GUIMainFrame(Environment env) {

        this.setTitle("PIC-Simulator GUI"); // sets title of frame
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // if x is pressed, exit application (HIDE_ON_CLOSE-hides application, DO_NOTHING_ON_CLOSE-prevents user from closing application)
        //this.setResizable(false); // prevent frame from beeing resized
        this.setSize(1200, 840); //sets x and y dimension of frame
        this.setLayout(new GridBagLayout());

        oGUITestFileTable = new GUITestFileTable();

        this.setJMenuBar(new GUIMenuBar(env, this, oGUITestFileTable));

        this.setVisible(true); //make frame visible
        this.setBackground(new Color(76, 78, 82));
        ImageIcon guiLogo = new ImageIcon("./images/gui_logo.png"); // create an ImageIcon
        this.setIconImage(guiLogo.getImage()); // change icon of frame
        
        Color guiBackgroundColor = new Color(255, 255, 255); // 0xFFFFFF || 0, 0, 0
        //this.getContentPane().setBackground(Color.green); //change color of background

        //JLabel text = new JLabel(); // create label, passing of text at constructor possible
        //text.setText("Bro do you even code?"); // set text of label
        //text.setIcon(guiLogo);
        //text.setHorizontalTextPosition(JLabel.CENTER); //JLabel.LEFT, JLabel.CENTER, JLabel.RIGHT
        //text.setVerticalTextPosition(JLabel.TOP); //JLabel.TOP, JLabel.CENTER, JLabel.BOTTOM
        //text.setForeground(Color.green); // set fontcolor of text
        //text.setFont(new Font("Arial", Font.ITALIC, 20)); // set fontstyle, fontformat (PLAIN, BOLD, ITALIC), and size
        //text.setIconTextGap(-100); // set gap of text to image
        //text.setBackground(Color.BLUE); // set background color
        //text.setOpaque(true); // display background color

        Border border = BorderFactory.createLineBorder(Color.green, 3); // creates border for label, color and size of border

        //text.setBorder(border); // sets border of label to "border"
        //text.setVerticalAlignment(JLabel.TOP); // vertically alligns label "text" (JLabel.TOP, JLabel.CENTER, JLabel.BOTTOM)
        //text.setHorizontalAlignment(JLabel.CENTER); // horizontally aligns label "text" (JLabel.LEFT, JLabel.CENTER, JLabel.RIGHT)

        //this.add(text); // add label to frame

        //getContentPane().setLayout(new BorderLayout());
        //this.add(new GUITestFileTable("./testfiles/TPicSim1.LST"));
        GridBagConstraints oGridBagConstraints = new GridBagConstraints();
        oGridBagConstraints.gridx = 10;
        oGridBagConstraints.gridy = 10;
        oGridBagConstraints.gridheight = 10;
        oGridBagConstraints.gridwidth = 500;
        oGridBagConstraints.anchor = GridBagConstraints.PAGE_START;
        this.add(oGUITestFileTable, oGridBagConstraints);
        updateWindow();
    }

    public void updateWindow() {
        this.revalidate();
        this.repaint();
    }
}