package Backend.FrontendCommandProcessing;

import java.util.regex.*;

import Backend.Runtime.Environment; 

//TODO Changes at patterns have to occur at all methods!

/**
 * Class to decode commandstring.
 */
public class CommandDecoder {

    private String sActualCommand = "";

    public CommandDecoder(String sActualCommand) {
        this.sActualCommand = sActualCommand;
    }

    /**
     * Get type of command.
     * @return int representation of pattern, -1 if no pattern matches
     */
    public int getCommandType() {

        int iPatternMatched = 0;

        //Read file and save line to string
        //sActualCommand = input.getActualCommand();

        //External oparations
        Pattern loadFile = Pattern.compile("^F_\\S*$");//0
        Pattern saveProgrammState = Pattern.compile("^S_\\S*$");//1
        Pattern setBreakpoint = Pattern.compile("^BS_\\d*$");//2
        Pattern togglePortA = Pattern.compile("^PA_\\d\\s(1|0)$");//3
        Pattern togglePortB = Pattern.compile("^PB_\\d$");//4
        Pattern changeQuarz = Pattern.compile("^Q_[\\d]{1,2}$");//5
        Pattern toggleWatchdog = Pattern.compile("W_[tf]$");//6
        Pattern reset = Pattern.compile("^R_$");//7
        Pattern step = Pattern.compile("^S_$");//8
        Pattern startProgramm = Pattern.compile("^RP_$");//9
        Pattern stopProgramm = Pattern.compile("^SP_$");//10
        Pattern loadProgrammState = Pattern.compile("^L_\\S*$");//11
        Pattern removeBreakpoint = Pattern.compile("^BR_\\d*$");//12
        Pattern removeAllBreakpoint = Pattern.compile("^BRA$");//13

        //Internal operations
        Pattern errorFileRead = Pattern.compile("ERROR FILEREAD");//14

        Pattern[] patterns = {loadFile, saveProgrammState, setBreakpoint, togglePortA, togglePortB, changeQuarz, toggleWatchdog, reset, step,
                              startProgramm, stopProgramm, loadProgrammState, removeBreakpoint, removeAllBreakpoint, errorFileRead};
        
        int iNumberOfPatterns = patterns.length;
        
        //Iterate through patterns and check if pattern matches to command.
        for (int i = 0; i < iNumberOfPatterns; i++) {
            Matcher matcher = patterns[i].matcher(sActualCommand);
            boolean bMatchFound = matcher.find();
            if (bMatchFound) {
                iPatternMatched = i;
                i = iNumberOfPatterns; // break loop for performance
            } else {
                iPatternMatched = -1;
            }
        }

        return iPatternMatched;
    }

    /**
     * Get value of command
     * @return
     */
    public String getCommandValue(int iCommandType) {

        int iStringLen = sActualCommand.length();

        String sValue = "";

        switch (iCommandType) {
            //0 F_<Path>
            //1 S_<Path>
            //5 Q_<IndexNr>
            //6 W_<Boolean>
            case 0:
            case 1:
            case 5:
            case 6:
            case 11: {
                sValue = sActualCommand.substring(2, iStringLen);
            }break;

            //2 BS_<RowNr>
            //3 PA_<PortNr>
            //4 PB_<PortNr>
            //12 BR_<RowNr>
            case 2:
            case 3:
            case 4:
            case 12: {
                sValue = sActualCommand.substring(3, iStringLen);
            }break;

            case 13: {
                //No Value needed
            }break;

            default: {
                sValue = "undefined";
            }break;
        }

        return sValue;
    }

    public int executeAction(Environment env) {
        int iAction = getCommandType();
        String sValue = getCommandValue(iAction);
        CommandAction action = new CommandAction(iAction, sValue, env);
        return action.execute();
    }
}