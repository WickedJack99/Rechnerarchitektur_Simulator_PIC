package View;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JCheckBox;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import Model.EepromLoader.ReadEepromFile;

public class GUIMenuBar extends JMenuBar {

    MyView oMyView;

    ArrayList<JCheckBox> oBreakpoints;
    ReadEepromFile oRef;
    boolean[] bBreakpointSet;

    ArrayList<JMenuItem> oMenuItems = new ArrayList<JMenuItem>();
    //Custom separators because addSeparator(default) looks not nice.
    JMenuItem oSeparator0;
    JMenuItem oSeparator1;
    JMenuItem oSeparator2;
    JMenuItem oSeparator3;

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

    //German language array
    String[][] sLanguages = {
                            {"Datei", "Testdatei laden", "Programmzustand laden", "Programmzustand speichern", "Simulation beenden",
                            "Ansicht", "Thema", "Dunkler Modus", "Heller Modus",
                            "Microcontroller", "Programm starten", "Programm pausieren", "Programm zuruecksetzen", "Schritt fuer Schritt", "Bearbeitungsintervall", "Sofort", "1 Sekunde", "2 Sekunden", 
                            "Hilfe", "Sprache", "Deutsch", "Englisch", "Anleitung", "Ueber"}, 
                            
                            {"File", "Load Testfile", "Load Programstate", "Save Programstate", "Exit simulation",
                            "View", "Theme", "Dark Theme", "Light Theme",
                            "Microcontroller", "Start program", "Pause program", "Reset program", "Step by Step", "Workinterval", "Instant", "1 second", "2 seconds", 
                            "Help", "Language", "German", "English", "Manual", "About"}};

    //Color for separators is always the same.
    Color oColorSeparators = new Color(47, 47, 47);

    /**
     * Constructor initializes menubar.
     * @param frame
     */
    public GUIMenuBar(MyView view) {

        //Custom Separators since default is not able to change background.
        oSeparator0 = new JMenuItem();
        oSeparator0.setEnabled(false);
        oSeparator0.setPreferredSize(new Dimension(0,1));
        oSeparator1 = new JMenuItem();
        oSeparator1.setEnabled(false);
        oSeparator1.setPreferredSize(new Dimension(0,1));
        oSeparator2 = new JMenuItem();
        oSeparator2.setEnabled(false);
        oSeparator2.setPreferredSize(new Dimension(0,1));
        oSeparator3 = new JMenuItem();
        oSeparator3.setEnabled(false);
        oSeparator3.setPreferredSize(new Dimension(0,1));

        //Referrence to change different parts of gui for theme.
        oMyView = view;

        //File
        oFileMenu = new JMenu(sLanguages[0][0]);
        oLoadTestFile = new JMenuItem(sLanguages[0][1]);
        oLoadProgStateItem  = new JMenuItem(sLanguages[0][2]);
        oSaveProgStateItem = new JMenuItem(sLanguages[0][3]);
        oExitItem = new JMenuItem(sLanguages[0][4]);

        //View
        oViewMenu = new JMenu(sLanguages[0][5]);
        oChangeColors = new JMenu(sLanguages[0][6]);
        oDarkTheme = new JMenuItem(sLanguages[0][7]);
        oLightTheme = new JMenuItem(sLanguages[0][8]);

        //Microcontroller
        oMicrocontroller = new JMenu(sLanguages[0][9]);
        oStartProg = new JMenuItem(sLanguages[0][10]);
        oPauseProg = new JMenuItem(sLanguages[0][11]);
        oResetProg = new JMenuItem(sLanguages[0][12]);
        oStepProg = new JMenuItem(sLanguages[0][13]);
        oChangeWorkInterval = new JMenu(sLanguages[0][14]);
        oIntervalASAP = new JMenuItem(sLanguages[0][15]);
        oInterval1Sec = new JMenuItem(sLanguages[0][16]);
        oInterval2Sec = new JMenuItem(sLanguages[0][17]);

        //Help
        oHelpMenu = new JMenu(sLanguages[0][18]);
        oChangeLanguageMenu = new JMenu(sLanguages[0][19]);
        oGerLangItem = new JMenuItem(sLanguages[0][20]);
        oEngLangItem = new JMenuItem(sLanguages[0][21]);
        oManual = new JMenuItem(sLanguages[0][22]);
        oAbout = new JMenuItem(sLanguages[0][23]);

        fillList();
        setGerMnemonics();
        buildMenubar();
        setTheme(0);
    }

    /**
     * Sets english mnemonics.
     */
    private void setEngMnemonics() {
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

    /**
     * Sets german mnemonics.
     */
    private void setGerMnemonics() {
        //File
        oFileMenu.setMnemonic(KeyEvent.VK_D); //alt + d for datei
        oLoadTestFile.setMnemonic(KeyEvent.VK_T); //t for testdatei
        oLoadProgStateItem.setMnemonic(KeyEvent.VK_L); //l for laden
        oSaveProgStateItem.setMnemonic(KeyEvent.VK_S); //s for speichern
        oExitItem.setMnemonic(KeyEvent.VK_B); //b for beenden 

        //View
        oViewMenu.setMnemonic(KeyEvent.VK_A); //alt + a for ansicht
        oChangeColors.setMnemonic(KeyEvent.VK_T); //t for thema
        oDarkTheme.setMnemonic(KeyEvent.VK_D); //d for dunkel
        oLightTheme.setMnemonic(KeyEvent.VK_L); //l for hell

        //Microcontroller
        oMicrocontroller.setMnemonic(KeyEvent.VK_M); //alt + m for microcontroller
        oStartProg.setMnemonic(KeyEvent.VK_S); //s for starten
        oPauseProg.setMnemonic(KeyEvent.VK_P); //p for pausieren
        oResetProg.setMnemonic(KeyEvent.VK_R); //r for zuruecksetzen
        oStepProg.setMnemonic(KeyEvent.VK_T); // t for schritt
        oChangeWorkInterval.setMnemonic(KeyEvent.VK_I); //i for intervall
        oIntervalASAP.setMnemonic(KeyEvent.VK_0); //0 for sofort
        oInterval1Sec.setMnemonic(KeyEvent.VK_1); //1 for 1 sekunde
        oInterval2Sec.setMnemonic(KeyEvent.VK_2); //2 for 2 sekunden

        //Help
        oHelpMenu.setMnemonic(KeyEvent.VK_H); //alt + h for hilfe
        oChangeLanguageMenu.setMnemonic(KeyEvent.VK_L); //s for sprache
        oGerLangItem.setMnemonic(KeyEvent.VK_D); //d for deutsch
        oEngLangItem.setMnemonic(KeyEvent.VK_E); //e for englisch
        oManual.setMnemonic(KeyEvent.VK_A); //a for anleitung
        oAbout.setMnemonic(KeyEvent.VK_U); //u for ueber
    }

    /**
     * Builds menubar.
     */
    private void buildMenubar() {
        //File
        oFileMenu.add(oLoadTestFile);
        oFileMenu.add(oSeparator0);
        oFileMenu.add(oLoadProgStateItem);
        oFileMenu.add(oSaveProgStateItem);
        oFileMenu.add(oSeparator1);
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
        oMicrocontroller.add(oSeparator2);
        oChangeWorkInterval.add(oIntervalASAP);
        oChangeWorkInterval.add(oInterval1Sec);
        oChangeWorkInterval.add(oInterval2Sec);
        oMicrocontroller.add(oChangeWorkInterval);

        //Help
        oChangeLanguageMenu.add(oGerLangItem);
        oChangeLanguageMenu.add(oEngLangItem);
        oHelpMenu.add(oChangeLanguageMenu);
        oHelpMenu.add(oSeparator3);
        oHelpMenu.add(oManual);
        oHelpMenu.add(oAbout);

        //Menubar (this)
        this.add(oFileMenu);
        this.add(oViewMenu);
        this.add(oMicrocontroller);
        this.add(oHelpMenu);
    }

    /**
     * Change language to overhanded language array.
     * @param lang = language to change to.
     */
    public void changeLangMenuBar(int iLang) {
        switch (iLang) {
            case 0: {
                setGerMnemonics();
            }break;
            case 1: {
                setEngMnemonics();
            }break;
        }

        //File
        oFileMenu.setText(sLanguages[iLang][0]);
        oLoadTestFile.setText(sLanguages[iLang][1]);
        oLoadProgStateItem.setText(sLanguages[iLang][2]);
        oSaveProgStateItem.setText(sLanguages[iLang][3]);
        oExitItem.setText(sLanguages[iLang][4]);

        //View
        oViewMenu.setText(sLanguages[iLang][5]);
        oChangeColors.setText(sLanguages[iLang][6]);
        oDarkTheme.setText(sLanguages[iLang][7]);
        oLightTheme.setText(sLanguages[iLang][8]);

        //Microcontroller
        oMicrocontroller.setText(sLanguages[iLang][9]);
        oStartProg.setText(sLanguages[iLang][10]);
        oPauseProg.setText(sLanguages[iLang][11]);
        oResetProg.setText(sLanguages[iLang][12]);
        oStepProg.setText(sLanguages[iLang][13]);
        oChangeWorkInterval.setText(sLanguages[iLang][14]);
        oIntervalASAP.setText(sLanguages[iLang][15]);
        oInterval1Sec.setText(sLanguages[iLang][16]);
        oInterval2Sec.setText(sLanguages[iLang][17]);

        //Help
        oHelpMenu.setText(sLanguages[iLang][18]);
        oChangeLanguageMenu.setText(sLanguages[iLang][19]);
        oGerLangItem.setText(sLanguages[iLang][20]);
        oEngLangItem.setText(sLanguages[iLang][21]);
        oManual.setText(sLanguages[iLang][22]);
        oAbout.setText(sLanguages[iLang][23]);
    }

    private void fillList() {
        //Fill list oMenuitems
        oMenuItems.add(oLoadTestFile);
        oMenuItems.add(oLoadProgStateItem);
        oMenuItems.add(oSaveProgStateItem);
        oMenuItems.add(oExitItem);
        oMenuItems.add(oDarkTheme);
        oMenuItems.add(oLightTheme);
        oMenuItems.add(oStartProg);
        oMenuItems.add(oPauseProg);
        oMenuItems.add(oResetProg);
        oMenuItems.add(oStepProg);
        oMenuItems.add(oIntervalASAP);
        oMenuItems.add(oInterval1Sec);
        oMenuItems.add(oInterval2Sec);
        oMenuItems.add(oGerLangItem);
        oMenuItems.add(oEngLangItem);
        oMenuItems.add(oManual);
        oMenuItems.add(oAbout);
    }

    public void setTheme(int iThemeNr) {
        switch (iThemeNr) {
            case 0: {
                Color[] aoLightTheme = MyColors.getTheme(0);
                this.setBackground(aoLightTheme[1]);
                this.setBorder(BorderFactory.createLineBorder(aoLightTheme[1], 2));
                this.setOpaque(true);

                //File
                oFileMenu.setForeground(aoLightTheme[0]);
                oLoadTestFile.setForeground(aoLightTheme[0]);
                oLoadProgStateItem.setForeground(aoLightTheme[0]);
                oSaveProgStateItem.setForeground(aoLightTheme[0]);
                oExitItem.setForeground(aoLightTheme[0]);

                //View
                oViewMenu.setForeground(aoLightTheme[0]);
                oChangeColors.setForeground(aoLightTheme[0]);
                oDarkTheme.setForeground(aoLightTheme[0]);
                oLightTheme.setForeground(aoLightTheme[0]);

                //Microcontroller
                oMicrocontroller.setForeground(aoLightTheme[0]);
                oStartProg.setForeground(aoLightTheme[0]);
                oPauseProg.setForeground(aoLightTheme[0]);
                oResetProg.setForeground(aoLightTheme[0]);
                oStepProg.setForeground(aoLightTheme[0]);
                oChangeWorkInterval.setForeground(aoLightTheme[0]);
                oIntervalASAP.setForeground(aoLightTheme[0]);
                oInterval1Sec.setForeground(aoLightTheme[0]);
                oInterval2Sec.setForeground(aoLightTheme[0]);

                //Help
                oHelpMenu.setForeground(aoLightTheme[0]);
                oChangeLanguageMenu.setForeground(aoLightTheme[0]);
                oGerLangItem.setForeground(aoLightTheme[0]);
                oEngLangItem.setForeground(aoLightTheme[0]);
                oManual.setForeground(aoLightTheme[0]);
                oAbout.setForeground(aoLightTheme[0]);

                //File
                oFileMenu.setBackground(aoLightTheme[1]);
                oLoadTestFile.setBackground(aoLightTheme[1]);
                oLoadProgStateItem.setBackground(aoLightTheme[1]);
                oSaveProgStateItem.setBackground(aoLightTheme[1]);
                oExitItem.setBackground(aoLightTheme[1]);

                //View
                oViewMenu.setBackground(aoLightTheme[1]);
                oChangeColors.setBackground(aoLightTheme[1]);
                oDarkTheme.setBackground(aoLightTheme[1]);
                oLightTheme.setBackground(aoLightTheme[1]);

                //Microcontroller
                oMicrocontroller.setBackground(aoLightTheme[1]);
                oStartProg.setBackground(aoLightTheme[1]);
                oPauseProg.setBackground(aoLightTheme[1]);
                oResetProg.setBackground(aoLightTheme[1]);
                oStepProg.setBackground(aoLightTheme[1]);
                oChangeWorkInterval.setBackground(aoLightTheme[1]);
                oIntervalASAP.setBackground(aoLightTheme[1]);
                oInterval1Sec.setBackground(aoLightTheme[1]);
                oInterval2Sec.setBackground(aoLightTheme[1]);

                //Help
                oHelpMenu.setBackground(aoLightTheme[1]);
                oChangeLanguageMenu.setBackground(aoLightTheme[1]);
                oGerLangItem.setBackground(aoLightTheme[1]);
                oEngLangItem.setBackground(aoLightTheme[1]);
                oManual.setBackground(aoLightTheme[1]);
                oAbout.setBackground(aoLightTheme[1]);

                //File
                oFileMenu.getPopupMenu().setBorder(BorderFactory.createLineBorder(aoLightTheme[1]));
                oFileMenu.getPopupMenu().setOpaque(true);

                //View
                oViewMenu.getPopupMenu().setBorder(BorderFactory.createLineBorder(aoLightTheme[1]));
                oChangeColors.getPopupMenu().setBorder(BorderFactory.createLineBorder(aoLightTheme[1]));
                oChangeColors.setOpaque(true);

                //Microcontroller
                oMicrocontroller.getPopupMenu().setBorder(BorderFactory.createLineBorder(aoLightTheme[1]));
                oChangeWorkInterval.getPopupMenu().setBorder(BorderFactory.createLineBorder(aoLightTheme[1]));
                oChangeWorkInterval.setOpaque(true);

                //Help
                oHelpMenu.getPopupMenu().setBorder(BorderFactory.createLineBorder(aoLightTheme[1]));
                oChangeLanguageMenu.getPopupMenu().setBorder(BorderFactory.createLineBorder(aoLightTheme[1]));
                oChangeLanguageMenu.setOpaque(true);

                //Separators
                oSeparator0.setBackground(oColorSeparators);
                oSeparator0.setBorder(BorderFactory.createLineBorder(oColorSeparators, 2));
                oSeparator1.setBackground(oColorSeparators);
                oSeparator1.setBorder(BorderFactory.createLineBorder(oColorSeparators, 2));
                oSeparator2.setBackground(oColorSeparators);
                oSeparator2.setBorder(BorderFactory.createLineBorder(oColorSeparators, 2));
                oSeparator3.setBackground(oColorSeparators);
                oSeparator3.setBorder(BorderFactory.createLineBorder(oColorSeparators, 2));
            }break;
            case 1: {
                Color[] aoDarkTheme = MyColors.getTheme(1);
                this.setBackground(aoDarkTheme[1]);
                this.setBorder(BorderFactory.createLineBorder(aoDarkTheme[1], 2));
                this.setOpaque(true);

                //File
                oFileMenu.setForeground(aoDarkTheme[0]);
                oLoadTestFile.setForeground(aoDarkTheme[0]);
                oLoadProgStateItem.setForeground(aoDarkTheme[0]);
                oSaveProgStateItem.setForeground(aoDarkTheme[0]);
                oExitItem.setForeground(aoDarkTheme[0]);

                //View
                oViewMenu.setForeground(aoDarkTheme[0]);
                oChangeColors.setForeground(aoDarkTheme[0]);
                oDarkTheme.setForeground(aoDarkTheme[0]);
                oLightTheme.setForeground(aoDarkTheme[0]);

                //Microcontroller
                oMicrocontroller.setForeground(aoDarkTheme[0]);
                oStartProg.setForeground(aoDarkTheme[0]);
                oPauseProg.setForeground(aoDarkTheme[0]);
                oResetProg.setForeground(aoDarkTheme[0]);
                oStepProg.setForeground(aoDarkTheme[0]);
                oChangeWorkInterval.setForeground(aoDarkTheme[0]);
                oIntervalASAP.setForeground(aoDarkTheme[0]);
                oInterval1Sec.setForeground(aoDarkTheme[0]);
                oInterval2Sec.setForeground(aoDarkTheme[0]);

                //Help
                oHelpMenu.setForeground(aoDarkTheme[0]);
                oChangeLanguageMenu.setForeground(aoDarkTheme[0]);
                oGerLangItem.setForeground(aoDarkTheme[0]);
                oEngLangItem.setForeground(aoDarkTheme[0]);
                oManual.setForeground(aoDarkTheme[0]);
                oAbout.setForeground(aoDarkTheme[0]);

                //File
                oFileMenu.setBackground(aoDarkTheme[1]);
                oLoadTestFile.setBackground(aoDarkTheme[1]);
                oLoadProgStateItem.setBackground(aoDarkTheme[1]);
                oSaveProgStateItem.setBackground(aoDarkTheme[1]);
                oExitItem.setBackground(aoDarkTheme[1]);

                //View
                oViewMenu.setBackground(aoDarkTheme[1]);
                oChangeColors.setBackground(aoDarkTheme[1]);
                oDarkTheme.setBackground(aoDarkTheme[1]);
                oLightTheme.setBackground(aoDarkTheme[1]);

                //Microcontroller
                oMicrocontroller.setBackground(aoDarkTheme[1]);
                oStartProg.setBackground(aoDarkTheme[1]);
                oPauseProg.setBackground(aoDarkTheme[1]);
                oResetProg.setBackground(aoDarkTheme[1]);
                oStepProg.setBackground(aoDarkTheme[1]);
                oChangeWorkInterval.setBackground(aoDarkTheme[1]);
                oIntervalASAP.setBackground(aoDarkTheme[1]);
                oInterval1Sec.setBackground(aoDarkTheme[1]);
                oInterval2Sec.setBackground(aoDarkTheme[1]);

                //Help
                oHelpMenu.setBackground(aoDarkTheme[1]);
                oChangeLanguageMenu.setBackground(aoDarkTheme[1]);
                oGerLangItem.setBackground(aoDarkTheme[1]);
                oEngLangItem.setBackground(aoDarkTheme[1]);
                oManual.setBackground(aoDarkTheme[1]);
                oAbout.setBackground(aoDarkTheme[1]);

                //File
                oFileMenu.getPopupMenu().setBorder(BorderFactory.createLineBorder(aoDarkTheme[1]));
                oFileMenu.getPopupMenu().setOpaque(true);

                //View
                oViewMenu.getPopupMenu().setBorder(BorderFactory.createLineBorder(aoDarkTheme[1]));
                oChangeColors.getPopupMenu().setBorder(BorderFactory.createLineBorder(aoDarkTheme[1]));
                oChangeColors.setOpaque(true);

                //Microcontroller
                oMicrocontroller.getPopupMenu().setBorder(BorderFactory.createLineBorder(aoDarkTheme[1]));
                oChangeWorkInterval.getPopupMenu().setBorder(BorderFactory.createLineBorder(aoDarkTheme[1]));
                oChangeWorkInterval.setOpaque(true);

                //Help
                oHelpMenu.getPopupMenu().setBorder(BorderFactory.createLineBorder(aoDarkTheme[1]));
                oChangeLanguageMenu.getPopupMenu().setBorder(BorderFactory.createLineBorder(aoDarkTheme[1]));
                oChangeLanguageMenu.setOpaque(true);

                //Separators
                oSeparator0.setBackground(oColorSeparators);
                oSeparator0.setBorder(BorderFactory.createLineBorder(oColorSeparators, 2));
                oSeparator1.setBackground(oColorSeparators);
                oSeparator1.setBorder(BorderFactory.createLineBorder(oColorSeparators, 2));
                oSeparator2.setBackground(oColorSeparators);
                oSeparator2.setBorder(BorderFactory.createLineBorder(oColorSeparators, 2));
                oSeparator3.setBackground(oColorSeparators);
                oSeparator3.setBorder(BorderFactory.createLineBorder(oColorSeparators, 2));
            }break;
        }
    }

    public boolean[] getBreakpoints() {
        return bBreakpointSet;
    }

    public ArrayList<JMenuItem> getMenuItems() {
        return oMenuItems;
    }
}