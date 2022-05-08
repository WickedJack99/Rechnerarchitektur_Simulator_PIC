/**
 * @author Aaron Moser
 */
package View;

import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;

public class GUIHelp extends JFrame {
    public GUIHelp() {
        // sets title of frame
        this.setTitle("PIC-Simulator GUI - Help");

        //prevent frame from beeing resized
        this.setResizable(false);

        //sets x and y dimension of frame
        this.setSize(650, 420);

        //Set Icon
        ImageIcon guiLogo = new ImageIcon("./pictures/gui_logo.png"); // create an ImageIcon
        this.setIconImage(guiLogo.getImage()); // change icon of frame

        this.setVisible(true);


        JScrollPane oJScrollPane = new JScrollPane();

        JPanel oHelpPanel = new JPanel();

        JTextArea oHelpText = new JTextArea();
        oHelpText.setLineWrap(true);
        oHelpText.setPreferredSize(new Dimension(600, 400));
        oHelpText.setEditable(false);

        oHelpPanel.add(oHelpText);

        oHelpText.setText(  "\n" + 
                            "\n" + 
                            "\n" + 
                            "\n" + 
                            "\n");

        oJScrollPane.setViewportView(oHelpPanel);
        oJScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        oJScrollPane.setWheelScrollingEnabled(true);
        oJScrollPane.getVerticalScrollBar().setUnitIncrement(16);

        this.add(oJScrollPane);
    }
}
