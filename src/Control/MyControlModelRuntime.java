package Control;

import Model.MyModel;
import View.MyView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JCheckBox;

public class MyControlModelRuntime implements ActionListener {

    MyModel oMyModel;

    ArrayList<JCheckBox> oBreakpoints;
    ArrayList<JButton> oControlButtons;

    public MyControlModelRuntime(MyModel model, MyView view) {
        oMyModel = model;
        oControlButtons = view.getGUIMCMenu().getControlButtons();
        oBreakpoints = view.getGUITestFileTable().getCheckboxes();
        addActionListeners();
    }   

    private void startProgramEnvironment() {
        oMyModel.start();
    }

    private void stepProgramEnvironment() {
        oMyModel.step();
    }

    private void pauseProgramEnvironment() {
        oMyModel.pause();
    }

    private void resetProgramEnvironment() {

    }

    public void controlWDTEnvironment(boolean bEnabled) {

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int i = 0;
        for (JCheckBox oBreakpoint : oBreakpoints) {
            if (e.getSource() == oBreakpoint) {
                oMyModel.controlBreakpoint(i);
            }
            i++;
        }
        if (e.getSource() == oControlButtons.get(0)) {
            //startProgramEnvironment();
            System.out.println("Test");
        }
        if (e.getSource() == oControlButtons.get(1)) {

        }
        if (e.getSource() == oControlButtons.get(2)) {

        }
        
        // TODO Auto-generated method stub
        
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
    }
}