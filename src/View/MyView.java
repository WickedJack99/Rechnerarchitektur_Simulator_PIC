/**
 * @author Aaron Moser
 */
package View;

import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JPanel;

public class MyView implements IMyView {

    private GUIMainFrame oGUIMainFrame;
    private GUIMCMenu oGUIMCMenu;
    private GUIPorts oGUIPorts;
    private GUIRamTable oGUIRamTable;
    private GUIRegister oGUIRegister;
    private GUIRegistersDetailed oGUIRegistersDetailed;
    private GUIStack oGUIStack;
    private GUITestFileTable oGUITestFileTable;
    private GUITime oGUITime;
    private GUIMenuBar oGUIMenuBar;

    private JPanel oGUIMainPanel;

    private int iLang;

    public MyView() {
        oGUIMenuBar = new GUIMenuBar(this);
        oGUIMCMenu = new GUIMCMenu();
        oGUIPorts = new GUIPorts();
        oGUIRamTable = new GUIRamTable();
        oGUIRegister = new GUIRegister();
        oGUIRegistersDetailed = new GUIRegistersDetailed();
        oGUIStack = new GUIStack();
        oGUITestFileTable = new GUITestFileTable();
        oGUITime = new GUITime();
        oGUIMainFrame = new GUIMainFrame(oGUIMenuBar, this);
        oGUIMainPanel = oGUIMainFrame.getMainPanel();
        setTheme(0);
    }

    public void setTheme(int iThemeNr) {
        oGUIMainFrame.setTheme(iThemeNr);
        oGUIMCMenu.setTheme(iThemeNr);
        oGUIPorts.setTheme(iThemeNr);
        oGUIRamTable.setTheme(iThemeNr);
        oGUIRegister.setTheme(iThemeNr);
        oGUIRegistersDetailed.setTheme(iThemeNr);
        oGUIStack.setTheme(iThemeNr);
        oGUITestFileTable.setTheme(iThemeNr);
        oGUITime.setTheme(iThemeNr);
        oGUIMenuBar.setTheme(iThemeNr);
    }

    public void setLanguage(int iLangNr) {
        iLang = iLangNr;
        oGUIMenuBar.changeLangMenuBar(iLangNr);
        oGUIMCMenu.setLanguage(iLangNr);
        oGUIRegister.setLanguage(iLangNr);
        oGUITestFileTable.setLanguage(iLangNr);
        oGUITime.setLanguage(iLangNr);
    }

    public int getLanguage() {
        return iLang;
    }    

    public void test() {
        this.getGUITime().getQuarzComboBox();
        this.getGUIMenuBar().getMenuItems();
    }

    public ArrayList<JButton> getControlButtons() {
        return this.getGUIMCMenu().getControlButtons();
    }

    public ArrayList<JCheckBox> getCheckboxes() {
        return this.getGUITestFileTable().getCheckboxes();
    }

    public JCheckBox getWDTEnableCheckbox() {
        return this.getGUITime().getWDTEnableCheckbox();
    }

    public void updateWindow() {
        this.getGUIMainFrame().updateWindow();
    }

    private GUIMainFrame getGUIMainFrame() {
        return this.oGUIMainFrame;
    }

    public GUIMCMenu getGUIMCMenu() {
        return this.oGUIMCMenu;
    }

    public GUIPorts getGUIPorts() {
        return this.oGUIPorts;
    }

    public GUIRamTable getGUIRamTable() {
        return this.oGUIRamTable;
    }

    public GUIRegister getGUIRegister() {
        return this.oGUIRegister;
    }

    public GUIRegistersDetailed getGUIRegistersDetailed() {
        return this.oGUIRegistersDetailed;
    }

    public GUIStack getGUIStack() {
        return this.oGUIStack;
    }

    public GUITime getGUITime() {
        return this.oGUITime;
    }

    public GUITestFileTable getGUITestFileTable() {
        return this.oGUITestFileTable;
    }

    public JPanel getGUIMainPanel() {
        return this.oGUIMainPanel;
    }

    public GUIMenuBar getGUIMenuBar() {
        return oGUIMenuBar;
    }
}