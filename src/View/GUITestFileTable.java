/**
 * @author Aaron Moser
 */
package View;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Color;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

public class GUITestFileTable extends JScrollPane {
    String sLine;
    ArrayList<JTextField> oLineInformation = new ArrayList<JTextField>();
    ArrayList<JCheckBox> oCheckboxes = new ArrayList<JCheckBox>();
    ArrayList<JPanel> oPanels = new ArrayList<JPanel>();
    JPanel oTable = new JPanel();
    int iTheme = 0;
    boolean bFileLoaded = false;
    JTextField oFill;

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

    /**
     * Constructor which initializes a filler.
     */
    public GUITestFileTable() {
        
        oFill = new JTextField();
        oFill.setEditable(false);
        oLineInformation.add(oFill);

        JPanel oTestPanel = new JPanel();
        oTestPanel.setLayout(new GridLayout(1, 3));
        oTestPanel.add(oFill);

        this.setPreferredSize(new Dimension(600, 700));
        this.setViewportView(oTestPanel);
        setTheme(0);
        setLanguage(0);
    }

    /**
     * Creates scrollable panel which contains information of testfile.
     * @param data arraylist which contains the data of the testfile as strings. (testfile has x lines, data has x entries)
     */
    public void setData(ArrayList<String> data) {
        oTable.removeAll(); //Clear table.
        oCheckboxes.clear(); //Clear arraylist containing checkboxes.
        oLineInformation.clear(); //clear arraylist containing references to informationfields.

        //Component which will include numbers, breakpoints and lines from testfile.
        JPanel oLines = new JPanel();
        oPanels.add(oLines);
        oLines.setLayout(new GridBagLayout());

        //Constraint for position of components at oLines.
        GridBagConstraints l = new GridBagConstraints();
        l.gridx = 0; 

        int iMaxLength = getMaxLen(data); //MaxLen for displaying line.
        int iNumberOfLines = data.size() + 1;
        int iVerticalCount = 0; //Variable for adding components to right line.
        for (int i = 1; i < iNumberOfLines; i++) {
            //Component which will be filled with three components (represents one line).
            JPanel oLine = new JPanel();
            oPanels.add(oLine);
            oLine.setLayout(new GridBagLayout());

            //Constraint for position of components at oLine.
            GridBagConstraints c = new GridBagConstraints();
            c.gridy = 0; //row 0

            c.gridx = 0; //collumn 0
            //Component displays number of testfileline
            JTextField oNumber = new JTextField(i + "", 3);
            oLineInformation.add(oNumber); //refernce for changing color mode
            oNumber.setForeground(aoLightTheme[0]);
            oNumber.setBackground(aoLightTheme[1]);
            oNumber.setEditable(false);
            oLine.add(oNumber, c);

            c.gridx = 1; //collumn 1
            //Component displays whether breakpoint is set or reset.
            JCheckBox oCheckbox = new JCheckBox();
            oCheckbox.setEnabled(false);      
            oCheckboxes.add(oCheckbox);
            oLine.add(oCheckbox, c);

            c.gridx = 2; //collumn 2
            //Component displays comment to specific line.
            JTextField oTestLine = new JTextField(data.get(i - 1), (int)(iMaxLength * 0.6));
            oLineInformation.add(oTestLine); //reference for changing color mode 
            oTestLine.setEditable(false);
            oLine.add(oTestLine, c);
            
            l.gridy = iVerticalCount; //row iVerticalcount
            l.anchor = GridBagConstraints.WEST; //Set orientation of elements to left (west)
            oLines.add(oLine, l);
            iVerticalCount++; //increment for putting next entry at next line.
        }

        oTable.add(oLines);

        this.setPreferredSize(new Dimension(600, 700));
        this.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        this.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        this.setWheelScrollingEnabled(true);
        this.getVerticalScrollBar().setUnitIncrement(16);
        this.setViewportView(oTable);
        setTheme(iTheme);
    }

    /**
     * @returns ArrayList containing all checkboxes which represent the breakpoints at testfile.
     */
    public ArrayList<JCheckBox> getCheckboxes() {
        return oCheckboxes;
    }

    //Sets checkbox at position i to boolean at position i at overhanded list.
    public void setCheckboxes(ArrayList<Boolean> bEnabled) {
        int iCheckboxesSize = oCheckboxes.size();
        for (int i = 0; i < iCheckboxesSize; i++) {
            oCheckboxes.get(i).setEnabled(bEnabled.get(i));
        }
    }

    /**
     * @param data ArrayList to check.
     * @returns length of longest string at ArrayList.
     */
    private int getMaxLen(ArrayList<String> data) {
        int iSize = data.size();
        int iMaxLength = 0;
        for (int i = 0; i < iSize; i++) {
            if (data.get(i).length() > iMaxLength) {
                iMaxLength = data.get(i).length();
            }
        }
        return iMaxLength;
    }

    /**
     * Changes Foreground, Background and Border-Color of testfiletable compponents
     * @param iThemeNr 0 Light Theme, 1 Dark Theme
     */
    public void setTheme(int iThemeNr) {
        iTheme = iThemeNr;
        
        for (JTextField oTextfield : oLineInformation) {
            oTextfield.setForeground(getThemeColor()[0]);
            oTextfield.setBackground(getThemeColor()[1]);
            oTextfield.setBorder(BorderFactory.createLineBorder(getThemeColor()[2]));
        }
        for (JCheckBox oCheckbox : oCheckboxes) {
            oCheckbox.setForeground(getThemeColor()[0]);
            oCheckbox.setBackground(getThemeColor()[1]);
            oCheckbox.setBorder(BorderFactory.createLineBorder(getThemeColor()[2]));
        }
        for (JPanel oPanel : oPanels) {
            oPanel.setForeground(getThemeColor()[0]);
            oPanel.setBackground(getThemeColor()[1]);
        }
            
        oTable.setForeground(getThemeColor()[0]);
        oTable.setBackground(getThemeColor()[1]);
        this.setForeground(getThemeColor()[0]);
        this.setBackground(getThemeColor()[1]);
    }

    /**
     * Mark line at testfiletable.
     * @param iLineToMark
     */
    public void markLine(int iLineToMark) {
        if (oLineInformation.size() > (iLineToMark * 2 + 1)) {
            if (iLineToMark > -1) {
                oLineInformation.get(iLineToMark * 2).setForeground(getThemeColor()[3]);
                oLineInformation.get(iLineToMark * 2).setBorder(BorderFactory.createLineBorder(getThemeColor()[3]));
                oLineInformation.get(iLineToMark * 2 + 1).setForeground(getThemeColor()[3]);
                oLineInformation.get(iLineToMark * 2 + 1).setBorder(BorderFactory.createLineBorder(getThemeColor()[3]));
            }
        }
    }

    /**
     * Unmark line at testfiletable.
     * @param iLineToUnmark
     */
    public void unmarkLine(int iLineToUnmark) {
        if (oLineInformation.size() > (iLineToUnmark * 2 + 1)) {
            if (iLineToUnmark > -1) {
                oLineInformation.get(iLineToUnmark * 2).setForeground(getThemeColor()[0]);
                oLineInformation.get(iLineToUnmark * 2).setBorder(BorderFactory.createLineBorder(getThemeColor()[2]));
                oLineInformation.get(iLineToUnmark * 2 + 1).setForeground(getThemeColor()[0]);
                oLineInformation.get(iLineToUnmark * 2 + 1).setBorder(BorderFactory.createLineBorder(getThemeColor()[2]));
            }
        }
    }

    public void setLanguage(int iLangNr) {
        switch (iLangNr) {
            case 0: {
                oFill.setText("Bitte Testdatei laden!");
            }break;
            case 1: {
                oFill.setText("Please load testfile!");
            }break;
        }
    }

    /**
     * @return Color to set for testfiletable
     */
    private Color[] getThemeColor() {
        Color[] oReturnColor = aoLightTheme;
        switch (iTheme) {
            case 0: {
                oReturnColor = aoLightTheme;
            }break;
            case 1: {
                oReturnColor = aoDarkTheme;
            }break;
        }
        return oReturnColor;
    }
}