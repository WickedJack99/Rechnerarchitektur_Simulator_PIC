package View;

import java.awt.Color;
import java.awt.Insets;
import java.util.ArrayList;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class GUIMainFrame extends JFrame {

    //Panels of gui-main-frame
    JPanel oMainPanel = new JPanel();
    JPanel oPanel0 = new JPanel();
    JPanel oPanel1 = new JPanel();
    JPanel oPanel2 = new JPanel();
    JPanel oPanel3 = new JPanel();

    ArrayList<JPanel> oPanels = new ArrayList<JPanel>();

    /**
     * Object for storing all components, will be overhanded to menubar, to set theme.
     */
    MyView oMyView;

    /**
     * Constructor
     */
    public GUIMainFrame(GUIMenuBar oGUIMenuBar, MyView view) {
        oMyView = view;

        // sets title of frame
        this.setTitle("PIC-Simulator GUI");

        // if x is pressed, exit application (HIDE_ON_CLOSE-hides application, DO_NOTHING_ON_CLOSE-prevents user from closing application)
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 

        //prevent frame from beeing resized
        this.setResizable(false);

        //sets x and y dimension of frame
        this.setSize(1400, 800);

        //Set Icon
        ImageIcon guiLogo = new ImageIcon("./pictures/gui_logo.png"); // create an ImageIcon
        this.setIconImage(guiLogo.getImage()); // change icon of frame

        //Adds components to frame, sets layouts,...
        buildGUIMainFrame();

        //Set menubar
        this.setJMenuBar(oGUIMenuBar);
        
        setTheme(0);
        this.setVisible(true); //make frame visible
        updateWindow();
    }

    public JPanel getMainPanel() {
        return oMainPanel;
    }

    private void buildGUIMainFrame() {
        //Set layouts of panels
        oMainPanel.setLayout(new GridBagLayout());
        oPanel0.setLayout(new GridBagLayout());
        oPanel1.setLayout(new GridBagLayout());
        oPanel2.setLayout(new GridBagLayout());
        oPanel3.setLayout(new GridBagLayout());

        //Create constraints for positioning
        GridBagConstraints oConstraints = new GridBagConstraints();
        oConstraints.anchor = GridBagConstraints.NORTHWEST;
        oConstraints.weightx = 1;
        oConstraints.weighty = 1;

        //Build first Panel from left
        oConstraints.gridx = 0;
        oConstraints.gridy = 0;
        oConstraints.insets = new Insets(10,10,0,0);
        oPanel0.add(oMyView.getGUITestFileTable(), oConstraints);

        //Build 2nd Panel from left
        oConstraints.gridx = 0;
        oConstraints.gridy = 0;
        oConstraints.insets = new Insets(10,10,0,0);
        oPanel1.add(oMyView.getGUIRegister(), oConstraints);
        oConstraints.gridy = 1;
        oConstraints.insets = new Insets(36,10,0,0);
        oPanel1.add(oMyView.getGUIRegistersDetailed(), oConstraints);
        oConstraints.gridy = 2;
        oConstraints.insets = new Insets(47,10,0,0);
        oPanel1.add(oMyView.getGUIRamTable(), oConstraints);

        //Build 3rd Panel from left
        oConstraints.gridx = 0;
        oConstraints.gridy = 0;
        oConstraints.insets = new Insets(10,10,0,0);
        oPanel2.add(oMyView.getGUIPorts(), oConstraints);
        oConstraints.gridy = 1;
        oPanel2.add(oPanel3, oConstraints);
        oConstraints.gridy = 2;
        oConstraints.insets = new Insets(45,90,0,0);
        oPanel2.add(oMyView.getGUIMCMenu(), oConstraints);

        //Build lower panel of 3rd panel
        oConstraints.gridx = 0;
        oConstraints.gridy = 0;
        oConstraints.insets = new Insets(0,0,0,0);
        oConstraints.weightx = 1;
        oConstraints.weighty = 1;
        oPanel3.add(oMyView.getGUIStack(), oConstraints);
        oConstraints.insets = new Insets(0,40,0,0);
        oConstraints.gridx = 1;
        oPanel3.add(oMyView.getGUITime(), oConstraints);

        //Build MainPanel
        oConstraints.gridx = 0;
        oConstraints.gridy = 0;
        oConstraints.weightx = 1;
        oConstraints.weighty = 1;
        oConstraints.insets = new Insets(0,0,0,0);
        oMainPanel.add(oPanel0, oConstraints);
        oConstraints.gridx = 1;
        oMainPanel.add(oPanel1, oConstraints);
        oConstraints.gridx = 2;
        oMainPanel.add(oPanel2, oConstraints);

        //Add panels to list
        oPanels.add(oPanel0);
        oPanels.add(oPanel1);
        oPanels.add(oPanel2);
        oPanels.add(oPanel3);
        oPanels.add(oMainPanel);

        //Build this frame
        this.add(oMainPanel);
    }

    public void updateWindow() {
        //update tables
        this.revalidate();
        this.repaint();
    }

    public void setTheme(int iThemeNr) {
        switch (iThemeNr) {
            case 0: {
                Color[] aoLightTheme = MyColors.getTheme(0);
                for (JPanel oPanel : oPanels) {
                    oPanel.setForeground(aoLightTheme[0]);
                    oPanel.setBackground(aoLightTheme[1]);
                }
                this.setForeground(aoLightTheme[0]);
                this.setBackground(aoLightTheme[1]);
            }break;
            case 1: {
                Color[] aoDarkTheme = MyColors.getTheme(1);
                for (JPanel oPanel : oPanels) {
                    oPanel.setForeground(aoDarkTheme[0]);
                    oPanel.setBackground(aoDarkTheme[1]);
                }
                this.setForeground(aoDarkTheme[0]);
                this.setBackground(aoDarkTheme[1]);
            }break;
        }
    }
}