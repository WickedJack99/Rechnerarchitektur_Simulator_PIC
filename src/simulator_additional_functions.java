import java.io.*;

//how to use In.java

public class simulator_additional_functions
{
    public static char menueSelectionChar = 'A';
    public static char secondMenueSelectionChar = 'A';
    public static String secondMenueSelectionString = "";
    public static File frontendToBackendFile = new File("../SIMULATOR_DAT/gui_change.dat");

    public static void main(String[] args)
    {
        int []array = new int[9];
        array[0] = 1;
        array[1] = 2;
        array[2] = 4;
        array[3] = 8;
        array[4] = 16;
        array[5] = 32;
        array[6] = 64;
        array[7] = 128;
        array[8] = 256;

        while ((menueSelectionChar != 'S') && (menueSelectionChar != 's'))
        {
            menueSelectionChar = In.readChar("What do you want to do?\n(B/b)reakpoint, (P/p)rescaler, (S/s)top:");

            switch (menueSelectionChar)
            {
                case 'B':
                case 'b':
                {
                    secondMenueSelectionString = In.readString("In which line do you want to set your breakpoint?:");

                    if (!frontendToBackendFile.isFile())
                    {
                        if (Integer.parseInt(secondMenueSelectionString) >= 0)
                        {
                            try
                            {
                                FileWriter fw = new FileWriter(frontendToBackendFile);
                                BufferedWriter bw = new BufferedWriter(fw);
                                bw.write("BREAKPOINT " + secondMenueSelectionString);
                                bw.close();
                            }
                            catch (IOException ioe)
                            {}
                        }

                        else
                        {
                            System.out.println("Please try again, value must be greater -1");
                        }
                    }

                    else
                    {
                        System.out.println("Error can't create new File, File already exists!");
                    }
                }break;
                
                case 'P':
                case 'p':
                {
                    secondMenueSelectionChar = In.readChar("Which Prescaler you want to set? (T/t)imer0, (W/w)atchdogtimer");
                    switch (secondMenueSelectionChar)
                    {
                        case 'T':
                        case 't':
                        {
                            if (!frontendToBackendFile.isFile())
                            {
                                secondMenueSelectionChar = In.readChar("What value you want to set the prescaler?\n0. 1, 1. 2, 2. 4, 3. 8, 4. 16, 5. 32, 6. 64, 7. 128, 8. 256:");
        
                                if ((Integer.parseInt("" + secondMenueSelectionChar) >= 0) && (Integer.parseInt("" + secondMenueSelectionChar) < 9))
                                {
                                    try
                                    {
                                        FileWriter fw = new FileWriter(frontendToBackendFile);
                                        BufferedWriter bw = new BufferedWriter(fw);
                                        bw.write("PRT " + Integer.parseInt("" + secondMenueSelectionChar));
                                        bw.close();
                                    }
                                    catch (IOException ioe)
                                    {}
                                }
                            }

                            else
                            {
                                System.out.println("Error can't create new File, File already exists!");
                            }
                        }break;
                    
                        case 'W':
                        case 'w':
                        {
                            if (!frontendToBackendFile.isFile())
                            {
                                secondMenueSelectionChar = In.readChar("What value you want to set the prescaler?\n0. 2, 1. 4, 2. 8, 3. 16, 4. 32, 5. 64, 6. 128, 7. 256:");
        
                                if ((Integer.parseInt("" + secondMenueSelectionChar) > 0) && (Integer.parseInt("" + secondMenueSelectionChar) < 9))
                                {
                                    try
                                    {
                                        FileWriter fw = new FileWriter(frontendToBackendFile);
                                        BufferedWriter bw = new BufferedWriter(fw);
                                        bw.write("PRW " + array[Integer.parseInt("" + secondMenueSelectionChar)]);
                                        bw.close();
                                    }
                                    catch (IOException ioe)
                                    {}
                                }
                            }

                            else
                            {
                                System.out.println("Error can't create new File, File already exists!");
                            }
                        }break;
                    }
                }break;
            }
        }
    }
}