package View;

import javax.swing.JPanel;

import Model.MyModel;

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

    public MyView(MyModel model) {
        oGUIMenuBar = new GUIMenuBar(model, this);
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
        oGUIMCMenu.setLanguage(iLangNr);
        oGUIRegister.setLanguage(iLangNr);
        oGUITestFileTable.setLanguage(iLangNr);
        oGUITime.setLanguage(iLangNr);
    }

    public GUIMainFrame getGUIMainFrame() {
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
}