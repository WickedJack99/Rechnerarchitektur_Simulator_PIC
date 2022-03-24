package Model.Backend;

import Model.Backend.Microcontroller.PIC;
import Model.Backend.Runtime.Environment;

public class MyModel {
    PIC oPIC;
    Environment oEnvironment;
    
    public MyModel(PIC pic, Environment env) {
        oPIC = pic;
        oEnvironment = env;
    }

    public PIC getPIC() {
        return this.oPIC;
    }

    public Environment getEnvironment() {
        return this.oEnvironment;
    }
}
