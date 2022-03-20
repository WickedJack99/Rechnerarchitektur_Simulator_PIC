package Frontend.PIC_SIMULATOR_GUI_JAVA;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;


import Backend.EepromLoader.ReadEepromFile;
import Backend.Runtime.Environment;

public class GUIMenuBar extends JMenuBar implements ActionListener {
    Environment oEnv;
    GUIMainFrame oGUIMainFrame;
    GUITestFileTable oGUITestFileTable;
    GUIRegisters oGUIRegisters;
    ArrayList<JCheckBox> oCheckBoxes;
    ReadEepromFile oRef;
    boolean[] bBreakpointSet;
    int iTestFileLoaded = 0;

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
    String[] sGermanLang = {"Datei", "Testdatei laden", "Programmzustand laden", "Programmzustand speichern", "Simulation beenden",
                            "Ansicht", "Thema", "Dunkler Modus", "Heller Modus",
                            "Microcontroller", "Programm starten", "Programm pausieren", "Programm zuruecksetzen", "Schritt fuer Schritt", "Bearbeitungsintervall", "Sofort", "1 Sekunde", "2 Sekunden", 
                            "Hilfe", "Sprache", "Deutsch", "Englisch", "Anleitung", "Ueber"};
    
    //English language array
    String[] sEnglishLang = {"File", "Load Testfile", "Load Programstate", "Save Programstate", "Exit simulation",
                             "View", "Theme", "Dark Theme", "Light Theme",
                             "Microcontroller", "Start program", "Pause program", "Reset program", "Step by Step", "Workinterval", "Instant", "1 second", "2 seconds", 
                             "Help", "Language", "German", "English", "Manual", "About"};

    /**
     * Color oWhite = new Color(255, 253, 250);
     * Color oDarkGray = new Color(76, 78, 82);
     * First Color == TextColor
     * Second Color == BackgroundColor
     */
    Color[] aoDarkTheme = {new Color(255, 253, 250), new Color(76, 78, 82)};
    Color[] aoLightTheme = {new Color(76, 78, 82), new Color(255, 253, 250)};

    //Color for separators is always the same.
    Color oColorSeparators = new Color(47, 47, 47);

    /**
     * Constructor initializes menubar.
     * @param frame
     */
    public GUIMenuBar(Environment env, GUIMainFrame mainframe, GUITestFileTable guitft, GUIRegisters guiregs) { //TODO maybe single components, with methods, of frame to set theme

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
        oEnv = env;
        oGUIMainFrame = mainframe;
        oGUITestFileTable = guitft;
        oGUIRegisters = guiregs;

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
        setGerMnemonics();
        buildMenubar();
        setTheme(aoLightTheme[0], aoLightTheme[1]);
    }

    /**
     * Sets ActionListeners to all items.
     */
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
            loadTestFile();
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
            setTheme(aoDarkTheme[0], aoDarkTheme[1]);
            oGUITestFileTable.setTheme(1);
            oGUIRegisters.setTheme(1);
            oGUIMainFrame.setTheme(1);
        }
        //Change to light theme
        if (e.getSource() == oLightTheme) {
            System.out.println("Death to all vampires!"); //TODO
            setTheme(aoLightTheme[0], aoLightTheme[1]);
            oGUITestFileTable.setTheme(0);
            oGUIRegisters.setTheme(0);
            oGUIMainFrame.setTheme(0);
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
            oEnv.step();
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
            setGerMnemonics();
            //TODO rest of gui
        }
        if (e.getSource() == oEngLangItem) {
            changeLangMenuBar(sEnglishLang);
            setEngMnemonics();
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

        controlBreakpoints(e);
    }

    /**
     * Changes Color of text and background of menubar.
     * @param oText = Color of text
     * @param oBackground = Color of background
     */
    private void setTheme(Color oText, Color oBackground) {
        this.setBackground(oBackground);
        this.setBorder(BorderFactory.createLineBorder(oBackground, 2));
        this.setOpaque(true);

        //File
        oFileMenu.setForeground(oText);
        oLoadTestFile.setForeground(oText);
        oLoadProgStateItem.setForeground(oText);
        oSaveProgStateItem.setForeground(oText);
        oExitItem.setForeground(oText);

        //View
        oViewMenu.setForeground(oText);
        oChangeColors.setForeground(oText);
        oDarkTheme.setForeground(oText);
        oLightTheme.setForeground(oText);

        //Microcontroller
        oMicrocontroller.setForeground(oText);
        oStartProg.setForeground(oText);
        oPauseProg.setForeground(oText);
        oResetProg.setForeground(oText);
        oStepProg.setForeground(oText);
        oChangeWorkInterval.setForeground(oText);
        oIntervalASAP.setForeground(oText);
        oInterval1Sec.setForeground(oText);
        oInterval2Sec.setForeground(oText);

        //Help
        oHelpMenu.setForeground(oText);
        oChangeLanguageMenu.setForeground(oText);
        oGerLangItem.setForeground(oText);
        oEngLangItem.setForeground(oText);
        oManual.setForeground(oText);
        oAbout.setForeground(oText);

        //File
        oFileMenu.setBackground(oBackground);
        oLoadTestFile.setBackground(oBackground);
        oLoadProgStateItem.setBackground(oBackground);
        oSaveProgStateItem.setBackground(oBackground);
        oExitItem.setBackground(oBackground);

        //View
        oViewMenu.setBackground(oBackground);
        oChangeColors.setBackground(oBackground);
        oDarkTheme.setBackground(oBackground);
        oLightTheme.setBackground(oBackground);

        //Microcontroller
        oMicrocontroller.setBackground(oBackground);
        oStartProg.setBackground(oBackground);
        oPauseProg.setBackground(oBackground);
        oResetProg.setBackground(oBackground);
        oStepProg.setBackground(oBackground);
        oChangeWorkInterval.setBackground(oBackground);
        oIntervalASAP.setBackground(oBackground);
        oInterval1Sec.setBackground(oBackground);
        oInterval2Sec.setBackground(oBackground);

        //Help
        oHelpMenu.setBackground(oBackground);
        oChangeLanguageMenu.setBackground(oBackground);
        oGerLangItem.setBackground(oBackground);
        oEngLangItem.setBackground(oBackground);
        oManual.setBackground(oBackground);
        oAbout.setBackground(oBackground);

        //File
        oFileMenu.getPopupMenu().setBorder(BorderFactory.createLineBorder(oBackground)); //TODO white lines at menus
        oFileMenu.getPopupMenu().setOpaque(true);

        //View
        oViewMenu.getPopupMenu().setBorder(BorderFactory.createLineBorder(oBackground));
        oChangeColors.getPopupMenu().setBorder(BorderFactory.createLineBorder(oBackground));
        oChangeColors.setOpaque(true);

        //Microcontroller
        oMicrocontroller.getPopupMenu().setBorder(BorderFactory.createLineBorder(oBackground));
        oChangeWorkInterval.getPopupMenu().setBorder(BorderFactory.createLineBorder(oBackground));
        oChangeWorkInterval.setOpaque(true);

        //Help
        oHelpMenu.getPopupMenu().setBorder(BorderFactory.createLineBorder(oBackground));
        oChangeLanguageMenu.getPopupMenu().setBorder(BorderFactory.createLineBorder(oBackground));
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
    }

    private void loadTestFile() {
        File oFile;
        //select file to open
        JFileChooser oFileChooser = new JFileChooser();
        int iResponse = oFileChooser.showOpenDialog(null);
        if (iResponse == JFileChooser.APPROVE_OPTION) {
            oFile = new File(oFileChooser.getSelectedFile().getAbsolutePath());
            //System.out.println(oFile);
            oRef = new ReadEepromFile();
            oRef.setData(oFile);
            oRef.setOPCode(oRef.getData());
            oGUITestFileTable.setData(oRef.getData());
            ArrayList<String> data = oRef.getData();
            int iDataSize = data.size();
            ArrayList<String> opcode = oRef.getOPCode();
            int iOPCodeSize = opcode.size();
            if (iTestFileLoaded > 0) {
                oCheckBoxes = oGUITestFileTable.getCheckboxes();
                for (int i = 0; i < iDataSize; i++) {                    
                    oCheckBoxes.get(i).setEnabled(false);
                }
            }
            for (int i = 0; i < iDataSize; i++) {
                for (int j = 0; j < iOPCodeSize; j++) {
                    if (data.get(i).equals(opcode.get(j))) {
                        oCheckBoxes = oGUITestFileTable.getCheckboxes();
                        oCheckBoxes.get(i).setEnabled(true);
                        oCheckBoxes.get(i).addActionListener(this);
                    }
                }
            }
            bBreakpointSet = new boolean[iOPCodeSize];
            oRef.readFileAndWriteToEEPROM(oEnv.getPIC());
            oGUIMainFrame.updateWindow();
            iTestFileLoaded = 1;
        }
    }

    private void controlBreakpoints(ActionEvent e) {
        if (oRef != null) {
            int iOPCode = oRef.getOPCode().size();
            if (iOPCode > 0) {
                for (int i = 0; i < oCheckBoxes.size(); i++) {
                    if (e.getSource() == oCheckBoxes.get(i)) {
                        for (int j = 0; j < iOPCode; j++) {
                            if (oRef.getOPCode().get(j).equals(oRef.getData().get(i))) {                            
                                bBreakpointSet[j] = !bBreakpointSet[j];
                                if (bBreakpointSet[j])
                                    System.out.println("Breakpoint " + j + " got set.");
                                else
                                    System.out.println("Breakpoint " + j + " got reset.");
                            }
                        }
                    }
                }
            }
        }
    }

    public boolean[] getBreakpoints() {
        return bBreakpointSet;
    }
}