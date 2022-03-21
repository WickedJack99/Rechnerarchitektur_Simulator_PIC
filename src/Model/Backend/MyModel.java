package Model.Backend;

import Model.Backend.Microcontroller.PIC;

public class MyModel {
    PIC oPIC;
    
    public MyModel(PIC pic) {
        oPIC = pic;
    }

    public PIC getPIC() {
        return this.oPIC;
    }
}
