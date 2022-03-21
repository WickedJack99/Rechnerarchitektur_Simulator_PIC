package Control;

import java.util.ArrayList;

import Model.Backend.MyModel;
import View.MyView;

public class Control implements IActions {

    MyModel oModel;
    MyView oView;
    //put Objects to manipulate here

    public Control(MyModel model, MyView view) {
        oModel = model;
        oView = view;
    }

    public void getWRegister(int iValue) {
        
    }

    public void getPCintern(int iValue) {
        
    }

    public void getPCLATH(int iValue) {

    }

    public void getPCL(int iValue) {

    }

    public void getFSR(int iValue) {

    }

    public void getStatus(int iValue) {

    }

    public void getOption(int iValue) {

    }

    public void getPrescaler(int iValue) {

    }

    public void getTMR0(int iValue) {

    }



    public void setPortAPinXToY(int iPort, boolean bEnabled) {

    }

    public void setPortBPinXToY(int iPort, boolean bEnabled) {

    }

    public void setPortATrisXToY(int iPort, boolean bEnabled) {

    }

    public void setPortBTrisXToY(int iPort, boolean bEnabled) {

    }

    public void getRam(int[][] aiiRam) {

    }


    public void startProgram() {

    }

    public void stepProgram() {

    }

    public void pauseProgram() {

    }

    public void resetProgram() {

    }

    public void controlWDT(boolean bEnabled) {

    }

    public void setQuarz(int iElement) {

    }


    public void loadFileToEEPROM(ArrayList<String> oData) {

    }

    public void saveSimulatorState() {
        
    }

    public void loadSimulatorState() {

    }

    public void exitSimulator() {

    }
}
