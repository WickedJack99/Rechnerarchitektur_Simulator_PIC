package View;

import java.awt.Color;
import java.awt.Insets;
import java.util.ArrayList;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

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

    GUITestFileTable oGUITestFileTable;
    GUIMenuBar oGUIMenuBar;

    JPanel oMainPanel;
    JPanel oPanelRegisterInformation;

    ArrayList<JPanel> oPanels = new ArrayList<JPanel>();
    /**
     * Constructor
     */
    public GUIMainFrame(Environment env) {
        oMainPanel = new JPanel();
        this.setTitle("PIC-Simulator GUI"); // sets title of frame
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // if x is pressed, exit application (HIDE_ON_CLOSE-hides application, DO_NOTHING_ON_CLOSE-prevents user from closing application)
        //this.setResizable(false); // prevent frame from beeing resized
        this.setSize(1400, 800); //sets x and y dimension of frame
        //this.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        oMainPanel.setLayout(new GridBagLayout());
        oGUITestFileTable = new GUITestFileTable();

        oPanelRegisterInformation = new JPanel();
        oPanelRegisterInformation.setLayout(new GridBagLayout());
        GridBagConstraints oConstraintsRegisterInformation = new GridBagConstraints();
        int iPrescaler;
        if (env.getPIC().getRam().get_PSA())
            iPrescaler = env.getPIC().getRam().get_WDT_PrescalerRate();
        else
            iPrescaler = env.getPIC().getRam().get_TMR0_PrescalerRate();
        /*int[] aiRegisters = {env.getPIC().getRam().get_PCL(), env.getPIC().getRam().get_PCLATH(), 
                             env.getPIC().getRam().get_Programcounter(), env.getPIC().getRam().get_STATUS(),
                             env.getPIC().getRam().get_FSR(), env.getPIC().getRam().get_OPTION(),
                             iPrescaler, env.getPIC().getRam().get_TMR0(),
                             env.getPIC().get_WRegister()};*/
        GUIRegister oGUIRegister = new GUIRegister();
        //oGUIRegisters.setRegisters(aiRegisters);
        GUIRegistersDetailed oGUIRegistersDetailed = new GUIRegistersDetailed();
        oConstraintsRegisterInformation.anchor = GridBagConstraints.NORTHWEST;
        oConstraintsRegisterInformation.gridx = 0;
        oConstraintsRegisterInformation.gridy = 0;
        oConstraintsRegisterInformation.insets = new Insets(10, 0, 10, 0);
        oPanelRegisterInformation.add(oGUIRegister, oConstraintsRegisterInformation);
        oConstraintsRegisterInformation.gridy = 1;
        oConstraintsRegisterInformation.anchor = GridBagConstraints.WEST;
        oPanelRegisterInformation.add(oGUIRegistersDetailed, oConstraintsRegisterInformation);

        this.setJMenuBar(new GUIMenuBar(env, this, oGUITestFileTable, oGUIRegister, oGUIRegistersDetailed));
        this.setVisible(true); //make frame visible

        ImageIcon guiLogo = new ImageIcon("./pictures/gui_logo.png"); // create an ImageIcon
        this.setIconImage(guiLogo.getImage()); // change icon of frame
        
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

        //Border border = BorderFactory.createLineBorder(Color.green, 3); // creates border for label, color and size of border

        //text.setBorder(border); // sets border of label to "border"
        //text.setVerticalAlignment(JLabel.TOP); // vertically alligns label "text" (JLabel.TOP, JLabel.CENTER, JLabel.BOTTOM)
        //text.setHorizontalAlignment(JLabel.CENTER); // horizontally aligns label "text" (JLabel.LEFT, JLabel.CENTER, JLabel.RIGHT)

        //this.add(text); // add label to frame

        //getContentPane().setLayout(new BorderLayout());
        //this.add(new GUITestFileTable("./testfiles/TPicSim1.LST"));
        GridBagConstraints c = new GridBagConstraints();
        c.anchor = GridBagConstraints.WEST;
        c.insets = new Insets(2,5,2,5);
        c.gridx = 0; //next added element will be in column 1
        c.gridy = 0; //next added element will be in row 1
        oMainPanel.add(oGUITestFileTable, c);
        c.gridx = 1;
        oMainPanel.add(oPanelRegisterInformation, c);
        oPanels.add(oPanelRegisterInformation);
        oPanels.add(oMainPanel);
        oMainPanel.add(new GUIPorts());
        this.add(oMainPanel);
        updateWindow();
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
            }break;
            case 1: {
                for (JPanel oPanel : oPanels) {
                    oPanel.setForeground(aoDarkTheme[0]);
                    oPanel.setBackground(aoDarkTheme[1]);
                }
            }break;
        }
    }
}