package Runtime;

public class TODO {

    /**
     * Returns the actual line which represents the actual programstep.
     * @return
     
    public static int iGetLine()
    {
        int iActualLine = 1;

        if (sLoadedSimulationFile != ("Uninitialized"))
        {
            String[] asDataLines = {};
            try
            {
                //Filereader reads data out of destination.
                FileReader fR = new FileReader(new File(sLoadedSimulationFile));
    
                //Creates String-array with whole data.
                asDataLines = getData(fR);
            }
            catch (Exception e) {}

            for (int i = 0; i < asDataLines.length; i++)
            {
                //Substring 0-4 from hex to dec. after that dec value has to be equal to progcounter minus 1 and at substring 20-25 is line.

                String substringHexStep = "0000";
                int hexStepAsInt = 0;

                try
                {
                    if (!((asDataLines[i].substring(0, 4)).equals("    ")))
                    {
                        substringHexStep = asDataLines[i].substring(0, 4);
                        hexStepAsInt = Integer.parseInt(substringHexStep, 16);
                    }
                }catch(NullPointerException npe){}

                if (hexStepAsInt == (oPIC.getRam().get_Programcounter()))
                {
                    iActualLine = i+1;
                }
            }
        }

        return iActualLine;
    }

    
     * Returns the last line which represents the last programstep.
     * @return
     
    public static int getLastLine()
    {
        int actualLine = 1;

        if (sLoadedSimulationFile != ("Uninitialized"))
        {
            String[] dataLines = {};
            try
            {
                //Filereader reads data out of destination.
                FileReader fR = new FileReader(new File(sLoadedSimulationFile));
    
                //Creates String-array with whole data.
                dataLines = getData(fR);
            }
            catch (Exception e) {}

            for (int i = 0; i < dataLines.length; i++)
            {
                //Substring 0-4 from hex to dec. after that dec value has to be equal to progcounter minus 1 and at substring 20-25 is line.

                String substringHexStep = "0000";
                int hexStepAsInt = 0;

                try
                {
                    if (!((dataLines[i].substring(0, 4)).equals("    ")))
                    {
                        substringHexStep = dataLines[i].substring(0, 4);
                        hexStepAsInt = Integer.parseInt(substringHexStep, 16);
                    }
                }catch(NullPointerException npe){}

                if (hexStepAsInt == (oPIC.getRam().get_LastProgramcounter()))
                {
                    actualLine = i+1;
                }
            }
        }

        return actualLine;
    }*/
}
