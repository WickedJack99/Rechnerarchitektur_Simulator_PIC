package View;

import javax.swing.JPanel;

public class MyView implements IMyView {

    GUIMainFrame oGUIMainFrame;
    GUIMCMenu oGUIMCMenu;
    GUIMenuBar oGUIMenuBar;
    GUIPorts oGUIPorts;
    GUIRamTable oGUIRamTable;
    GUIRegister oGUIRegister;
    GUIRegistersDetailed oGUIRegistersDetailed;
    GUIStack oGUIStack;
    GUITestFileTable oGUITestFileTable;
    GUITime oGUITime;

    JPanel oGUIMainPanel;

    public MyView(GUIMainFrame oGUIMainFrame, GUIMCMenu oGUIMCMenu, GUIMenuBar oGUIMenuBar, GUIPorts oGUIPorts, GUIRamTable oGUIRamTable, GUIRegister oGUIRegister, GUIRegistersDetailed oGUIRegistersDetailed, GUIStack oGUIStack, GUITestFileTable oGUITestFileTable, GUITime oGUITime, JPanel oGUIMainPanel) {
        this.oGUIMainFrame = oGUIMainFrame;
        this.oGUIMCMenu = oGUIMCMenu;
        this.oGUIMenuBar = oGUIMenuBar;
        this.oGUIPorts = oGUIPorts;
        this.oGUIRamTable = oGUIRamTable;
        this.oGUIRegister = oGUIRegister;
        this.oGUIRegistersDetailed = oGUIRegistersDetailed;
        this.oGUIStack = oGUIStack;
        this.oGUITestFileTable = oGUITestFileTable;
        this.oGUITime = oGUITime;
        this.oGUIMainPanel = oGUIMainPanel;
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
}
