package Frontend.PIC_SIMULATOR_GUI_JAVA;

import java.awt.Color;
import java.awt.event.*;

import javax.swing.BorderFactory;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class GUIMenuBar extends JMenuBar  implements ActionListener {
    GUIMainFrame oMainFrame;

    //File
    JMenu oFileMenu;
    JMenuItem oLoadTestFile;
    JMenuItem oLoadProgStateItem;
    JMenuItem oSaveProgStateItem;
    JMenuItem oExitItem;

    //View
    JMenu oViewMenu;
    JMenu oChangeColors;
    JMenuItem oDarkTheme;
    JMenuItem oLightTheme;

    //Microcontroller
    JMenu oMicrocontroller;
    JMenuItem oStartProg;
    JMenuItem oPauseProg;
    JMenuItem oResetProg;
    JMenuItem oStepProg;
    JMenu oChangeWorkInterval;
    JMenuItem oIntervalASAP;
    JMenuItem oInterval1Sec;
    JMenuItem oInterval2Sec;

    //Help
    JMenu oHelpMenu;
    JMenu oChangeLanguageMenu;
    JMenuItem oGerLangItem;
    JMenuItem oEngLangItem;
    JMenuItem oManual;
    JMenuItem oAbout;

    String[] sGermanLang = {"Datei", "Testdatei laden", "Programmzustand laden", "Programmzustand speichern", "Simulation beenden",
                            "Ansicht", "Thema", "Dunkler Modus", "Heller Modus",
                            "Microcontroller", "Programm starten", "Programm stoppen", "Programm zuruecksetzen", "Schritt fuer Schritt", "Bearbeitungsintervall", "Sofort", "1 Sekunde", "2 Sekunden", 
                            "Hilfe", "Sprache", "Deutsch", "Englisch", "Anleitung", "Ueber"};
    
    String[] sEnglishLang = {"File", "Load Testfile", "Load Programstate", "Save Programstate", "Exit simulation",
                             "View", "Theme", "Dark Theme", "Light Theme",
                             "Microcontroller", "Start program", "Stop program", "Reset program", "Step by Step", "Workinterval", "Instant", "1 second", "2 seconds", 
                             "Help", "Language", "German", "English", "Manual", "About"};

    public GUIMenuBar(GUIMainFrame frame) {
        oMainFrame = frame;
        //File
        oFileMenu = new JMenu(sGermanLang[0]);
        oLoadTestFile = new JMenuItem(sGermanLang[1]);
        oLoadProgStateItem  = new JMenuItem(sGermanLang[2]);
        oSaveProgStateItem = new JMenuItem(sGermanLang[3]);
        oExitItem = new JMenuItem(sGermanLang[4]);

        //View
        oViewMenu = new JMenu(sGermanLang[5]);
        oChangeColors = new JMenu(sGermanLang[6]);
        oDarkTheme = new JMenuItem(sGermanLang[7]);
        oLightTheme = new JMenuItem(sGermanLang[8]);

        //Microcontroller
        oMicrocontroller = new JMenu(sGermanLang[9]);
        oStartProg = new JMenuItem(sGermanLang[10]);
        oPauseProg = new JMenuItem(sGermanLang[11]);
        oResetProg = new JMenuItem(sGermanLang[12]);
        oStepProg = new JMenuItem(sGermanLang[13]);
        oChangeWorkInterval = new JMenu(sGermanLang[14]);
        oIntervalASAP = new JMenuItem(sGermanLang[15]);
        oInterval1Sec = new JMenuItem(sGermanLang[16]);
        oInterval2Sec = new JMenuItem(sGermanLang[17]);

        //Help
        oHelpMenu = new JMenu(sGermanLang[18]);
        oChangeLanguageMenu = new JMenu(sGermanLang[19]);
        oGerLangItem = new JMenuItem(sGermanLang[20]);
        oEngLangItem = new JMenuItem(sGermanLang[21]);
        oManual = new JMenuItem(sGermanLang[22]);
        oAbout = new JMenuItem(sGermanLang[23]);

        setActionListeners();
        setMnemonics();
        buildMenubar();
    }

    private void setActionListeners() {
        //File
        oLoadTestFile.addActionListener(this);
        oLoadProgStateItem.addActionListener(this);
        oSaveProgStateItem.addActionListener(this);
        oExitItem.addActionListener(this);

        //View
        oDarkTheme.addActionListener(this);
        oLightTheme.addActionListener(this);

        //Microcontroller
        oStartProg.addActionListener(this);
        oPauseProg.addActionListener(this);
        oResetProg.addActionListener(this);
        oStepProg.addActionListener(this);
        oIntervalASAP.addActionListener(this);
        oInterval1Sec.addActionListener(this);
        oInterval2Sec.addActionListener(this);

        //Help
        oGerLangItem.addActionListener(this);
        oEngLangItem.addActionListener(this);
        oManual.addActionListener(this);
        oAbout.addActionListener(this);
    }

    private void setMnemonics() {
        //File
        oFileMenu.setMnemonic(KeyEvent.VK_F); //alt + f for file
        oLoadTestFile.setMnemonic(KeyEvent.VK_T); //t for test
        oLoadProgStateItem.setMnemonic(KeyEvent.VK_L); //l for load
        oSaveProgStateItem.setMnemonic(KeyEvent.VK_S); //s for save
        oExitItem.setMnemonic(KeyEvent.VK_E); //e for exit

        //View
        oViewMenu.setMnemonic(KeyEvent.VK_V); //alt + v for view
        oChangeColors.setMnemonic(KeyEvent.VK_C); //c for color
        oDarkTheme.setMnemonic(KeyEvent.VK_D); //d for dark
        oLightTheme.setMnemonic(KeyEvent.VK_L); //l for light

        //Microcontroller
        oMicrocontroller.setMnemonic(KeyEvent.VK_M); //alt + m for microcontroller
        oStartProg.setMnemonic(KeyEvent.VK_S); //s for start
        oPauseProg.setMnemonic(KeyEvent.VK_P); //p for pause
        oResetProg.setMnemonic(KeyEvent.VK_R); //r for reset
        oStepProg.setMnemonic(KeyEvent.VK_T); // t for step
        oChangeWorkInterval.setMnemonic(KeyEvent.VK_I); //i for interval
        oIntervalASAP.setMnemonic(KeyEvent.VK_0); //0 for as soon as possible
        oInterval1Sec.setMnemonic(KeyEvent.VK_1); //1 for 1 second
        oInterval2Sec.setMnemonic(KeyEvent.VK_2); //2 for 2 seconds

        //Help
        oHelpMenu.setMnemonic(KeyEvent.VK_H); //alt + h for help
        oChangeLanguageMenu.setMnemonic(KeyEvent.VK_L); //l for language
        oGerLangItem.setMnemonic(KeyEvent.VK_G); //g for german
        oEngLangItem.setMnemonic(KeyEvent.VK_E); //e for english
        oManual.setMnemonic(KeyEvent.VK_M); //m for manual
        oAbout.setMnemonic(KeyEvent.VK_A); //a for about
    }

    private void buildMenubar() {
        //File
        oFileMenu.add(oLoadTestFile);
        oFileMenu.addSeparator();
        oFileMenu.add(oLoadProgStateItem);
        oFileMenu.add(oSaveProgStateItem);
        oFileMenu.addSeparator();
        oFileMenu.add(oExitItem);

        //View
        oChangeColors.add(oDarkTheme);
        oChangeColors.add(oLightTheme);
        oViewMenu.add(oChangeColors);

        //Microcontroller
        oMicrocontroller.add(oStartProg);
        oMicrocontroller.add(oPauseProg);
        oMicrocontroller.add(oResetProg);
        oMicrocontroller.add(oStepProg);
        oMicrocontroller.addSeparator();
        oChangeWorkInterval.add(oIntervalASAP);
        oChangeWorkInterval.add(oInterval1Sec);
        oChangeWorkInterval.add(oInterval2Sec);
        oMicrocontroller.add(oChangeWorkInterval);

        //Help
        oChangeLanguageMenu.add(oGerLangItem);
        oChangeLanguageMenu.add(oEngLangItem);
        oHelpMenu.add(oChangeLanguageMenu);
        oHelpMenu.addSeparator();
        oHelpMenu.add(oManual);
        oHelpMenu.add(oAbout);

        //Menubar (this)
        this.add(oFileMenu);
        this.add(oViewMenu);
        this.add(oMicrocontroller);
        this.add(oHelpMenu);
    }

    private void changeLangMenuBar(String[] lang) {
        //File
        oFileMenu.setText(lang[0]);
        oLoadTestFile.setText(lang[1]);
        oLoadProgStateItem.setText(lang[2]);
        oSaveProgStateItem.setText(lang[3]);
        oExitItem.setText(lang[4]);

        //View
        oViewMenu.setText(lang[5]);
        oChangeColors.setText(lang[6]);
        oDarkTheme.setText(lang[7]);
        oLightTheme.setText(lang[8]);

        //Microcontroller
        oMicrocontroller.setText(lang[9]);
        oStartProg.setText(lang[10]);
        oPauseProg.setText(lang[11]);
        oResetProg.setText(lang[12]);
        oStepProg.setText(lang[13]);
        oChangeWorkInterval.setText(lang[14]);
        oIntervalASAP.setText(lang[15]);
        oInterval1Sec.setText(lang[16]);
        oInterval2Sec.setText(lang[17]);

        //Help
        oHelpMenu.setText(lang[18]);
        oChangeLanguageMenu.setText(lang[19]);
        oGerLangItem.setText(lang[20]);
        oEngLangItem.setText(lang[21]);
        oManual.setText(lang[22]);
        oAbout.setText(lang[23]);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //File
        if (e.getSource() == oLoadTestFile) {
            System.out.println("Bro do you even code? test");
            //TODO
        }
        if (e.getSource() == oLoadProgStateItem) {
            System.out.println("Bro do you even code? load");
            //TODO
        }
        if (e.getSource() == oSaveProgStateItem) {
            System.out.println("Bro do you even code? save");
            //TODO
            System.exit(0);
        }
        if (e.getSource() == oExitItem) {
            System.out.println("Bro do you even code? exit");
            //TODO
            System.exit(0);
        }

        //View
        //Change to dark theme
        if (e.getSource() == oDarkTheme) {
            System.out.println("It's gettin dark brooo"); //TODO
            setDarkTheme();
        }
        //Change to light theme
        if (e.getSource() == oLightTheme) {
            System.out.println("It burns, it burnnnnnnssssss"); //TODO
        }

        //Microcontroller
        if (e.getSource() == oStartProg) {
            System.out.println("Start prog"); //TODO
        }
        if (e.getSource() == oPauseProg) {
            System.out.println("oPauseProg"); //TODO
        }
        if (e.getSource() == oResetProg) {
            System.out.println("oResetProg"); //TODO
        }
        if (e.getSource() == oStepProg) {
            System.out.println("oStepProg"); //TODO
        }
        if (e.getSource() == oIntervalASAP) {
            System.out.println("oIntervalASAP"); //TODO
        }
        if (e.getSource() == oInterval1Sec) {
            System.out.println("oInterval1Sec"); //TODO
        }
        if (e.getSource() == oInterval2Sec) {
            System.out.println("oInterval2Sec"); //TODO
        }

        //Help
        //Change language at gui.
        if (e.getSource() == oGerLangItem) {
            changeLangMenuBar(sGermanLang);
            //TODO rest of gui
        }
        if (e.getSource() == oEngLangItem) {
            changeLangMenuBar(sEnglishLang);
            //TODO rest of gui
        }
        //Show manual
        if (e.getSource() == oManual) {
            //TODO
        }
        //Show about
        if (e.getSource() == oAbout) {
            //TODO
        }
    }

    private void setDarkTheme() {
        Color oWhite = new Color(255, 253, 250);
        Color oDarkGray = new Color(76, 78, 82);
        this.setBackground(oDarkGray);
        this.setOpaque(true);

        //File
        oFileMenu.setForeground(oWhite);
        oLoadTestFile.setForeground(oWhite);
        oLoadProgStateItem.setForeground(oWhite);
        oSaveProgStateItem.setForeground(oWhite);
        oExitItem.setForeground(oWhite);

        //View
        oViewMenu.setForeground(oWhite);
        oChangeColors.setForeground(oWhite);
        oDarkTheme.setForeground(oWhite);
        oLightTheme.setForeground(oWhite);

        //Microcontroller
        oMicrocontroller.setForeground(oWhite);
        oStartProg.setForeground(oWhite);
        oPauseProg.setForeground(oWhite);
        oResetProg.setForeground(oWhite);
        oStepProg.setForeground(oWhite);
        oChangeWorkInterval.setForeground(oWhite);
        oIntervalASAP.setForeground(oWhite);
        oInterval1Sec.setForeground(oWhite);
        oInterval2Sec.setForeground(oWhite);

        //Help
        oHelpMenu.setForeground(oWhite);
        oChangeLanguageMenu.setForeground(oWhite);
        oGerLangItem.setForeground(oWhite);
        oEngLangItem.setForeground(oWhite);
        oManual.setForeground(oWhite);
        oAbout.setForeground(oWhite);

        //File
        oFileMenu.setBackground(oDarkGray);
        oLoadTestFile.setBackground(oDarkGray);
        oLoadProgStateItem.setBackground(oDarkGray);
        oSaveProgStateItem.setBackground(oDarkGray);
        oExitItem.setBackground(oDarkGray);

        //View
        oViewMenu.setBackground(oDarkGray);
        oChangeColors.setBackground(oDarkGray);
        oDarkTheme.setBackground(oDarkGray);
        oLightTheme.setBackground(oDarkGray);

        //Microcontroller
        oMicrocontroller.setBackground(oDarkGray);
        oStartProg.setBackground(oDarkGray);
        oPauseProg.setBackground(oDarkGray);
        oResetProg.setBackground(oDarkGray);
        oStepProg.setBackground(oDarkGray);
        oChangeWorkInterval.setBackground(oDarkGray);
        oIntervalASAP.setBackground(oDarkGray);
        oInterval1Sec.setBackground(oDarkGray);
        oInterval2Sec.setBackground(oDarkGray);

        //Help
        oHelpMenu.setBackground(oDarkGray);
        oChangeLanguageMenu.setBackground(oDarkGray);
        oGerLangItem.setBackground(oDarkGray);
        oEngLangItem.setBackground(oDarkGray);
        oManual.setBackground(oDarkGray);
        oAbout.setBackground(oDarkGray);

        //File
        oFileMenu.getPopupMenu().setBorder(BorderFactory.createLineBorder(oDarkGray)); //TODO white lines at menus
        oFileMenu.getPopupMenu().setOpaque(true);
        //View
        oViewMenu.getPopupMenu().setBorder(BorderFactory.createLineBorder(oDarkGray));
        oChangeColors.getPopupMenu().setBorder(BorderFactory.createLineBorder(oDarkGray));
        oChangeColors.setOpaque(true);
        //Microcontroller
        oMicrocontroller.getPopupMenu().setBorder(BorderFactory.createLineBorder(oDarkGray));
        oChangeWorkInterval.getPopupMenu().setBorder(BorderFactory.createLineBorder(oDarkGray));
        oChangeWorkInterval.setOpaque(true);
        //Help
        oHelpMenu.getPopupMenu().setBorder(BorderFactory.createLineBorder(oDarkGray));
        oChangeLanguageMenu.getPopupMenu().setBorder(BorderFactory.createLineBorder(oDarkGray));
        oChangeLanguageMenu.setOpaque(true);

        this.setBorder(BorderFactory.createLineBorder(oDarkGray, 2));
    }
}