package View;

import java.awt.Color;
import java.awt.Insets;
import java.util.ArrayList;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

import Model.Backend.MyModel;
import Model.Backend.Runtime.Environment;

public class GUIMainFrame extends JFrame {

    /**
     * Color oWhite = new Color(255, 253, 250);
     * Color oDarkGray = new Color(76, 78, 82);
     * Color oDarkGrayB = new Color(47, 47, 47);
     * Color oLightBlue = new Color(173, 216, 230);
     * Color oOrangeDM = new Color(255, 170, 0);
     * Color oLightBlueDM = new Color(0, 213, 255);
     * Color oOrangeDMB = new Color(255, 85, 0);
     * First Color == TextColor
     * Second Color == BackgroundColor
     * Third Color == BorderColor
     * Fourth Color == TextColor Marked
     * Fifth Color == BackgroundColor Marked
     * Sixth Color == BorderColor Marked
     */
    Color[] aoDarkTheme = {new Color(255, 253, 250), new Color(76, 78, 82), new Color(47, 47, 47), new Color(0, 213, 255), new Color(255, 170, 0), new Color(255, 85, 0)};
    Color[] aoLightTheme = {new Color(76, 78, 82), new Color(255, 253, 250), new Color(173, 216, 230), new Color(0, 213, 255), new Color(255, 170, 0), new Color(255, 85, 0)};

    //Components of gui-main-frame
    GUITestFileTable oGUITestFileTable = new GUITestFileTable();
    GUIMenuBar oGUIMenuBar;
    GUIRegister oGUIRegister = new GUIRegister();
    GUIRegistersDetailed oGUIRegistersDetailed = new GUIRegistersDetailed();
    GUIRamTable oGUIRamTable = new GUIRamTable();
    GUIPorts oGUIPorts = new GUIPorts();
    GUIStack oGUIStack = new GUIStack();
    GUIMCMenu oGUIMCMenu = new GUIMCMenu();
    GUITime oGUITime = new GUITime();

    //Panels of gui-main-frame
    JPanel oMainPanel = new JPanel();
    JPanel oPanel0 = new JPanel();
    JPanel oPanel1 = new JPanel();
    JPanel oPanel2 = new JPanel();
    JPanel oPanel3 = new JPanel();

    /**
     * Object for storing all components, will be overhanded to menubar, to set theme.
     * 
     */
    MyView oMyView;

    MyModel oMyModel;

    ArrayList<JPanel> oPanels = new ArrayList<JPanel>();

    /**
     * Constructor
     */
    public GUIMainFrame(Environment oEnvironment) {

        this.setTitle("PIC-Simulator GUI"); // sets title of frame
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // if x is pressed, exit application (HIDE_ON_CLOSE-hides application, DO_NOTHING_ON_CLOSE-prevents user from closing application)
        this.setResizable(false); // prevent frame from beeing resized
        this.setSize(1400, 800); //sets x and y dimension of frame
        //Set Icon
        ImageIcon guiLogo = new ImageIcon("./pictures/gui_logo.png"); // create an ImageIcon
        this.setIconImage(guiLogo.getImage()); // change icon of frame

        //Adds components to frame, sets layouts,...
        buildGUIMainFrame();

        //Init view object with components of main frame.
        oMyView = new MyView(this, oGUIMCMenu, oGUIPorts, oGUIRamTable, oGUIRegister, oGUIRegistersDetailed, oGUIStack, oGUITestFileTable, oGUITime, oMainPanel, oEnvironment);

        //Set menubar
        oGUIMenuBar = new GUIMenuBar(oMyModel, oMyView);
        this.setJMenuBar(oGUIMenuBar);
        
        setTheme(0);
        updateWindow();
        this.setVisible(true); //make frame visible
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
        oPanel0.add(oGUITestFileTable, oConstraints);

        //Build 2nd Panel from left
        oConstraints.gridx = 0;
        oConstraints.gridy = 0;
        oConstraints.insets = new Insets(10,10,0,0);
        oPanel1.add(oGUIRegister, oConstraints);
        oConstraints.gridy = 1;
        oConstraints.insets = new Insets(36,10,0,0);
        oPanel1.add(oGUIRegistersDetailed, oConstraints);
        oConstraints.gridy = 2;
        oConstraints.insets = new Insets(47,10,0,0);
        oPanel1.add(oGUIRamTable, oConstraints);

        //Build 3rd Panel from left
        oConstraints.gridx = 0;
        oConstraints.gridy = 0;
        oConstraints.insets = new Insets(10,10,0,0);
        oPanel2.add(oGUIPorts, oConstraints);
        oConstraints.gridy = 1;
        oPanel2.add(oPanel3, oConstraints);
        oConstraints.gridy = 2;
        oConstraints.insets = new Insets(45,90,0,0);
        oPanel2.add(oGUIMCMenu, oConstraints);

        //Build lower panel of 3rd panel
        oConstraints.gridx = 0;
        oConstraints.gridy = 0;
        oConstraints.insets = new Insets(0,0,0,0);
        oConstraints.weightx = 1;
        oConstraints.weighty = 1;
        oPanel3.add(oGUIStack, oConstraints);
        oConstraints.insets = new Insets(0,40,0,0);
        oConstraints.gridx = 1;
        oPanel3.add(oGUITime, oConstraints);

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
        this.revalidate();
        this.repaint();
    }

    public void setTheme(int iThemeNr) {
        switch (iThemeNr) {
            case 0: {
                for (JPanel oPanel : oPanels) {
                    oPanel.setForeground(aoLightTheme[0]);
                    oPanel.setBackground(aoLightTheme[1]);
                }
                this.setForeground(aoLightTheme[0]);
                this.setBackground(aoLightTheme[1]);
                oGUIMCMenu.setTheme(0);
                oGUIStack.setTheme(0);
                oGUIPorts.setTheme(0);
                oGUIRamTable.setTheme(0);
                oGUITime.setTheme(0);
            }break;
            case 1: {
                for (JPanel oPanel : oPanels) {
                    oPanel.setForeground(aoDarkTheme[0]);
                    oPanel.setBackground(aoDarkTheme[1]);
                }
                this.setForeground(aoDarkTheme[0]);
                this.setBackground(aoDarkTheme[1]);
                oGUIMCMenu.setTheme(1);
                oGUIStack.setTheme(1);
                oGUIPorts.setTheme(1);
                oGUIRamTable.setTheme(1);
                oGUITime.setTheme(1);
            }break;
        }
    }
}