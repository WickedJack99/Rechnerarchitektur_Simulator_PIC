package Model;

import Model.Microcontroller.PIC;
import Model.Runtime.Environment;

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
