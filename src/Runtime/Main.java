package Runtime;

import java.util.concurrent.ConcurrentLinkedQueue;

import Control.MyControlModel;
import Control.MyControlView;
import Model.MyModel;
import Model.MyModelData;
import Model.Microcontroller.Pic;
import View.MyView;

public class Main {

    public static void main(String[] args) {
        Pic oPIC = new Pic();

        ConcurrentLinkedQueue<Integer> qCommandsToModel = new ConcurrentLinkedQueue<Integer>();
        ConcurrentLinkedQueue<Pic> qDataToView = new ConcurrentLinkedQueue<Pic>();
        ConcurrentLinkedQueue<MyModelData> qDataToModel = new ConcurrentLinkedQueue<MyModelData>();

        MyModel oModel = new MyModel(qCommandsToModel, qDataToView, qDataToModel);
        oModel.start();

        MyView oView = new MyView();

        MyControlView oControlView = new MyControlView(oPIC, oView, qCommandsToModel);

        new MyControlModel(oView, qCommandsToModel, qDataToModel);

        //Check if model sent a new pic-element through queue and update view.
        while (true) {
            while (!qDataToView.isEmpty()) {
                Pic pic = qDataToView.poll();
                oControlView.setPIC(pic);
                oControlView.updateView();
            }
        }
    }
}