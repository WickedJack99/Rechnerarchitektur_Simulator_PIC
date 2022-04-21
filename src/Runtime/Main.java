package Runtime;

import java.util.concurrent.ConcurrentLinkedQueue;

import Control.MyControlModel;
import Control.MyControlView;
import Model.MyModel;
import Model.MyModelData;
import Model.Microcontroller.PIC;
import View.MyView;

public class Main {

    public static void main(String[] args) {
        PIC oPIC = new PIC();

        ConcurrentLinkedQueue<Integer> qCommandsToModel = new ConcurrentLinkedQueue<Integer>();
        ConcurrentLinkedQueue<PIC> qDataToView = new ConcurrentLinkedQueue<PIC>();
        ConcurrentLinkedQueue<MyModelData> qDataToModel = new ConcurrentLinkedQueue<MyModelData>();

        MyModel oModel = new MyModel(qCommandsToModel, qDataToView, qDataToModel);
        oModel.start();

        MyView oView = new MyView();

        MyControlView oControlView = new MyControlView(oPIC, oView, qCommandsToModel);

        new MyControlModel(oView, qCommandsToModel, qDataToModel);

        //Check if model sent a new pic-element through queue and update view.
        while (true) {
            while (!qDataToView.isEmpty()) {
                PIC pic = qDataToView.poll();
                oControlView.setPIC(pic);
                oControlView.updateView();
            }
        }
    }
}