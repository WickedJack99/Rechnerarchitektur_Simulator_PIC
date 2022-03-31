package Model.Runtime;

import Control.MyControlModelPIC;
import Control.MyControlModelRuntime;
import Control.MyControlView;
import Model.MyModel;
import View.MyView;

public class Main {
    public static void main(String[] args) {
        MyModel oModel = new MyModel();
        MyView oView = new MyView(oModel);
        MyControlView oControlView = new MyControlView(oModel, oView);
        MyControlModelRuntime oControlModelRuntime = new MyControlModelRuntime(oModel, oView);
        MyControlModelPIC oControlModelPIC = new MyControlModelPIC(oModel, oView);
    }
}