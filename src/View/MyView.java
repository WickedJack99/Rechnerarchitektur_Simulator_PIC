package View;

import javax.swing.JPanel;

import Model.Backend.Runtime.Environment;

public class MyView implements IMyView {

    GUIMainFrame oGUIMainFrame;
    GUIMCMenu oGUIMCMenu;
    GUIPorts oGUIPorts;
    GUIRamTable oGUIRamTable;
    GUIRegister oGUIRegister;
    GUIRegistersDetailed oGUIRegistersDetailed;
    GUIStack oGUIStack;
    GUITestFileTable oGUITestFileTable;
    GUITime oGUITime;

    JPanel oGUIMainPanel;

    Environment oEnvironment;

    public MyView(GUIMainFrame oGUIMainFrame, GUIMCMenu oGUIMCMenu, GUIPorts oGUIPorts, GUIRamTable oGUIRamTable, GUIRegister oGUIRegister, GUIRegistersDetailed oGUIRegistersDetailed, GUIStack oGUIStack, GUITestFileTable oGUITestFileTable, GUITime oGUITime, JPanel oGUIMainPanel, Environment oEnvironment) {
        this.oGUIMainFrame = oGUIMainFrame;
        this.oGUIMCMenu = oGUIMCMenu;
        this.oGUIPorts = oGUIPorts;
        this.oGUIRamTable = oGUIRamTable;
        this.oGUIRegister = oGUIRegister;
        this.oGUIRegistersDetailed = oGUIRegistersDetailed;
        this.oGUIStack = oGUIStack;
        this.oGUITestFileTable = oGUITestFileTable;
        this.oGUITime = oGUITime;
        this.oGUIMainPanel = oGUIMainPanel;
        this.oEnvironment = oEnvironment;
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

    public GUITestFileTable getGUITestFileTable() {
        return this.oGUITestFileTable;
    }

    public JPanel getGUIMainPanel() {
        return this.oGUIMainPanel;
    }

    public Environment getEnvironment() {
        return this.oEnvironment;
    }
}