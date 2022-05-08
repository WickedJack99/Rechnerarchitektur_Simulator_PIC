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

public class GUIAbout extends JFrame {
    public GUIAbout() {
        // sets title of frame
        this.setTitle("PIC-Simulator GUI - About");

        //prevent frame from beeing resized
        this.setResizable(false);

        //sets x and y dimension of frame
        this.setSize(650, 420);

        //Set Icon
        ImageIcon guiLogo = new ImageIcon("./pictures/gui_logo.png"); // create an ImageIcon
        this.setIconImage(guiLogo.getImage()); // change icon of frame

        this.setVisible(true);

        JScrollPane oJScrollPane = new JScrollPane();

        JPanel oAboutPanel = new JPanel();

        JTextArea oAboutText = new JTextArea();
        oAboutText.setLineWrap(true);
        oAboutText.setPreferredSize(new Dimension(600, 400));
        oAboutText.setEditable(false);

        oAboutPanel.add(oAboutText);

        oAboutText.setText( "Hochschule Offenburg SS2022\n" + 
                            "Projekt Rechnerarchitektur - Simulator Mikroprozessor 16F84\n" + 
                            "Entwickler: Aaron Moser\n" + 
                            "Programmiersprache: Java\n" + 
                            "Entwicklungsumgebung: Visual Studio Code\n");

        oJScrollPane.setViewportView(oAboutPanel);
        oJScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        oJScrollPane.setWheelScrollingEnabled(true);
        oJScrollPane.getVerticalScrollBar().setUnitIncrement(16);

        this.add(oJScrollPane);
    }
}
