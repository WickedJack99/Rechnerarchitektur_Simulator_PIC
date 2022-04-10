package Control;

import Model.MyModelData;
import Model.EepromLoader.ReadEepromFile;
import Model.Microcontroller.PIC;
import View.GUIAbout;
import View.GUIHelp;
import View.MyView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.concurrent.ConcurrentLinkedQueue;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JMenuItem;

public class MyControlModel implements ActionListener {

    MyView oMyView;

    ArrayList<JCheckBox> oBreakpoints;
    ArrayList<JButton> oControlButtons;
    JCheckBox oWDTEnabled;
    JComboBox<String> oQuarzIntervals;
    ArrayList<JMenuItem> oMenuItems;

    ConcurrentLinkedQueue<Integer> qCommandsForModel;
    ConcurrentLinkedQueue<MyModelData> qDataForModel;

    ReadEepromFile oRef;
    int iTestFileLoaded = 0;
    boolean[] abBreakpoints;
    PIC oPIC;

    MyModelData oMyModelData = new MyModelData();

    public MyControlModel(MyView view, ConcurrentLinkedQueue<Integer> qCommandsForModel, ConcurrentLinkedQueue<MyModelData> qDataForModel) {
        oMyView = view;
        this.oPIC = new PIC();
        this.qCommandsForModel = qCommandsForModel;
        this.qDataForModel = qDataForModel;
        oControlButtons = oMyView.getGUIMCMenu().getControlButtons();
        oBreakpoints = oMyView.getGUITestFileTable().getCheckboxes();
        oWDTEnabled = oMyView.getGUITime().getWDTEnableCheckbox();
        oQuarzIntervals = oMyView.getGUITime().getQuarzComboBox();
        oMenuItems = oMyView.getGUIMenuBar().getMenuItems();
        addActionListeners();
    }   

    private void startProgramModel() {
        qCommandsForModel.add(1); // 1 == start
    }

    private void pauseProgramModel() {
        qCommandsForModel.add(2); // 2 == pause
    }

    private void resetProgramModel() {
        qCommandsForModel.add(3); // 3 == reset
    }

    private void stepProgramModel() {
        qCommandsForModel.add(4); // 4 == step
    }

    public void controlWDTModel(boolean bEnabled) {

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
            oMyView.getGUITestFileTable().setData(oRef.getData());
            setBreakpointsActionListeners();
            oRef.readFileAndWriteToEEPROM(oPIC);
            oMyView.updateWindow();
            iTestFileLoaded = 1;
            oMyModelData.setPIC(oPIC);
            oMyModelData.setBreakpoints(abBreakpoints);
            qDataForModel.add(oMyModelData);
        }
    }

    private void loadProgStateItem() {
        //TODO
    }

    private void saveProgStateItem() {
        //TODO
    }

    /**
     * Sets interval of visual running. (instant, 1 sec, 2 sec)
     * @param i
     */
    private void setIntervalProgramModel(int i) {
        oMyModelData.setVisualInterval(i);
        qDataForModel.add(oMyModelData);
    }

    /**
     * Sets ActionListeners for breakpoints.
     */
    private void setBreakpointsActionListeners() {
        ArrayList<String> data = oRef.getData();
        int iDataSize = data.size();
        ArrayList<String> opcode = oRef.getOPCode();

        int iOPCodeSize = opcode.size();
        //If testfile was loaded before, reset all checkboxes
        if (iTestFileLoaded > 0) {
            oBreakpoints = oMyView.getGUITestFileTable().getCheckboxes();
            for (int i = 0; i < iDataSize; i++) {                    
                oBreakpoints.get(i).setEnabled(false);
            }
        }
        //Enable only checkboxes which belong to real code
        int[] aiProgramLines = new int[iOPCodeSize];
        int k = 0;
        for (int i = 0; i < iDataSize; i++) {
            for (int j = 0; j < iOPCodeSize; j++) {
                if (data.get(i).equals(opcode.get(j))) {
                    aiProgramLines[k] = i;
                    k++;
                    oBreakpoints = oMyView.getGUITestFileTable().getCheckboxes();
                    oBreakpoints.get(i).setEnabled(true);
                    oBreakpoints.get(i).addActionListener(this);
                }
            }
        }
        oPIC.getEeprom().setProgramLines(aiProgramLines);
        abBreakpoints = new boolean[iOPCodeSize];
    }

    private void controlBreakpoints(ActionEvent e) {
        if (oRef != null) {
            int iOPCode = oRef.getOPCode().size();
            if (iOPCode > 0) {
                for (int i = 0; i < oBreakpoints.size(); i++) {
                    if (e.getSource() == oBreakpoints.get(i)) {
                        for (int j = 0; j < iOPCode; j++) {
                            if (oRef.getOPCode().get(j).equals(oRef.getData().get(i))) {
                                abBreakpoints[j] = !abBreakpoints[j];
                                oMyModelData.setBreakpoints(abBreakpoints);
                                qDataForModel.add(oMyModelData);
                            }
                        }
                    }
                }
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //Breakpoints set/reset
        controlBreakpoints(e);

        //Runtime control Buttons
        if (e.getSource() == oControlButtons.get(0)) {
            startProgramModel();
        }
        if (e.getSource() == oControlButtons.get(1)) {
            stepProgramModel();
        }
        if (e.getSource() == oControlButtons.get(2)) {
            pauseProgramModel();
        }
        if (e.getSource() == oControlButtons.get(3)) {
            resetProgramModel();
        }

        //WDT-Enabled Checkbox
        if (e.getSource() == oWDTEnabled) {
            //Enable/disable watchdog
            System.out.println("Watchdog got set to " + oWDTEnabled.isSelected());//TODO
        }

        //Quarzfrequency
        if (e.getSource() == oQuarzIntervals) {
            oMyModelData.setQuartzInterval(oQuarzIntervals.getSelectedIndex());
            qDataForModel.add(oMyModelData);
        }

        int i = 0;
        for (JMenuItem oMenuItem : oMenuItems) {
            if (e.getSource() == oMenuItem) {
                switch (i) {
                    case 0: {
                        //Load testfile
                        loadTestFile();
                    }break;
                    case 1: {
                        //Load Program State
                        loadProgStateItem();
                    }break;
                    case 2: {
                        //Save Program State
                        saveProgStateItem();
                    }break;
                    case 3: {
                        //Exit Program
                        qCommandsForModel.add(0);
                        System.exit(0);
                    }break;
                    case 4: {
                        //Dark theme
                        oMyView.setTheme(1);
                    }break;
                    case 5: {
                        //Light theme
                        oMyView.setTheme(0);
                    }break;
                    case 6: {
                        //Start pic
                        startProgramModel();
                    }break;
                    case 7: {
                        //Pause pic
                        pauseProgramModel();
                    }break;
                    case 8: {
                        //Reset pic
                        resetProgramModel();
                    }break;
                    case 9: {
                        //Step pic
                        stepProgramModel();
                    }break;
                    case 10: {
                        //Set interval to as soon as possible
                        setIntervalProgramModel(0);
                    }break;
                    case 11: {
                        //Set interval to 1 sec
                        setIntervalProgramModel(1);
                    }break;
                    case 12: {
                        //Set interval to 2 sec
                        setIntervalProgramModel(2);
                    }break;
                    case 13: {
                        //Change language to german
                        oMyView.setLanguage(0);
                    }break;
                    case 14: {
                        //Change language to english
                        oMyView.setLanguage(1);
                    }break;
                    case 15: {
                        new GUIHelp();
                        //Show manual
                    }break;
                    case 16: {
                        new GUIAbout();
                        //Show about
                    }break;
                }
            }
            i++;
        }
    }

    private void addActionListeners() {
        if (oBreakpoints != null) {
            for (JCheckBox oBreakpoint : oBreakpoints) {
                oBreakpoint.addActionListener(this);
            }
        }
        if (oControlButtons != null) {
            for (JButton oButton : oControlButtons) {
                oButton.addActionListener(this);
            }
        }
        if (oWDTEnabled != null) {
            oWDTEnabled.addActionListener(this);
        }
        if (oQuarzIntervals != null) {
            oQuarzIntervals.addActionListener(this);
        }
        if (oMenuItems != null) {
            for (JMenuItem oMenuItem : oMenuItems) {
                oMenuItem.addActionListener(this);
            }
        }
        
    }
}