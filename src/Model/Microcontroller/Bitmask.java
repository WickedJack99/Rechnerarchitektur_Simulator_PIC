package Model.Microcontroller;

public class Bitmask {
    /**
     * Decides which PIC-command-method to call.
     * @param iCommandAsIntToMask
     * @param oPIC
     */
    public int bitMaskDecoderAndExecuteCommand(int iCommandAsIntToMask, PIC oPIC) {
        //System.out.println("Command " + Integer.toHexString(iCommandAsIntToMask));
        //Return-value will be -1 if command can't be read.
        int iDecodedCommand = -1;

        //Command that was overhanded is written into "command"
        int iCommand = iCommandAsIntToMask;

        //Bitmask to get the destination-bit
        int iDestinationBitBitmask = 0b00000010000000;
        //Destination-bit
        int iDestinationBit = (iDestinationBitBitmask & iCommand) >> 7;

        //Bitmask to get the register-file-adress
        int iRegisterFileAddressBitmask = 0b00000001111111;
        //Register-file-adress
        int iRegisterFileAddress = (iRegisterFileAddressBitmask & iCommand);

        //Bitmask to get bitaddress
        int iBitaddressBitmask = 0b00001110000000;
        //Bitaddress
        int iBitaddress = (iBitaddressBitmask & iCommand) >> 7;

        //Bitmask to get eight last k's
        int iEightKBitmask = 0b00000011111111;
        int iEightK = (iEightKBitmask & iCommand);

        //Bitmask to get eleven last k's
        int iElevenKBitmask = 0b00011111111111;
        int iElevenK = (iElevenKBitmask & iCommand);

        //Bitmask to get TRIS-register TRIS instruction not used
        //int tRISRegisterBitmask = 0b000000000111;
        //int tRISRegisterAddress = (tRISRegisterBitmask & command);

        if ((0b10000000000000 & iCommand) == 0b10000000000000) {
            //1x xxxx xxxx xxxx
            if ((0b01000000000000 & iCommand) == 0b01000000000000) {
                //11 xxxx xxxx xxxx
                if ((0b00100000000000 & iCommand) == 0b00100000000000) {
                    //11 1xxx xxxx xxxx
                    if ((0b00010000000000 & iCommand) == 0b00010000000000) {
                        //11 11xx xxxx xxxx
                        if ((0b00001000000000 & iCommand) == 0b00001000000000) {
                            //11 111x xxxx xxxx
                            iDecodedCommand = 22; //ADDLW
                        } else {
                            //11 110x xxxx xxxx
                            iDecodedCommand = 33; //SUBLW
                        }
                    } else { 
                        //11 10xx xxxx xxxx
                        if ((0b00001000000000 & iCommand) == 0b00001000000000) {
                            //11 101x xxxx xxxx
                            if ((0b00000100000000 & iCommand) == 0b00000100000000) {
                                //11 1011 xxxx xxxx
                            } else {
                                //11 1010 xxxx xxxx
                                iDecodedCommand = 34; //XORLW
                            }
                        } else {
                            //11 100x xxxx xxxx
                            if ((0b00000100000000 & iCommand) == 0b00000100000000) {
                                //11 1001 xxxx xxxx
                                iDecodedCommand = 23; //ANDLW
                            } else {
                                //11 1000 xxxx xxxx
                                iDecodedCommand = 27; //IORLW
                            }
                        }
                    }
                } else {
                    //11 0xxx xxxx xxxx
                    if ((0b00010000000000 & iCommand) == 0b00010000000000) {
                        //11 01xx xxxx xxxx
                        iDecodedCommand = 30; //RETLW
                    } else {
                        //11 00xx xxxx xxxx
                        iDecodedCommand = 28; //MOVLW
                    }
                }
            } else {
                //10 xxxx xxxx xxxx
                if ((0b00100000000000 & iCommand) == 0b00100000000000) {
                    //10 1xxx xxxx xxxx
                    iDecodedCommand = 26; //GOTO
                } else {
                    //10 0xxx xxxx xxxx
                    iDecodedCommand = 24; //CALL
                }
            }
        } else if ((0b01000000000000 & iCommand) == 0b01000000000000) {
            //01 xxxx xxxx xxxx
            if ((0b00100000000000 & iCommand) == 0b00100000000000) {
                //01 1xxx xxxx xxxx
                if ((0b00010000000000 & iCommand) == 0b00010000000000) {
                    //01 11xx xxxx xxxx
                    iDecodedCommand = 21; //BTFSS
                } else {
                    //01 10xx xxxx xxxx
                    iDecodedCommand = 20; //BTFSC
                }
            } else {
                //01 0xxx xxxx xxxx
                if ((0b00010000000000 & iCommand) == 0b00010000000000) {
                    //01 01xx xxxx xxxx
                    iDecodedCommand = 19; //BSF
                } else {
                    //01 00xx xxxx xxxx
                    iDecodedCommand = 18; //BCF
                }
            }
        } else if ((0b00100000000000 & iCommand) == 0b00100000000000) {
            //00 1xxx xxxx xxxx
            if ((0b00010000000000 & iCommand) == 0b00010000000000) {
                //00 11xx xxxx xxxx
                if ((0b00001000000000 & iCommand) == 0b00001000000000) {
                    //00 111x xxxx xxxx
                    if ((0b00000100000000 & iCommand) == 0b00000100000000) {
                        //00 1111 xxxx xxxx
                        iDecodedCommand = 8; //INCFSZ
                    } else {
                        //00 1110 xxxx xxxx
                        iDecodedCommand = 16; //SWAPF
                    }
                } else {
                    //00 110x xxxx xxxx
                    if ((0b00000100000000 & iCommand) == 0b00000100000000) {
                        //00 1101 xxxx xxxx
                        iDecodedCommand = 13; //RLF
                    } else {
                        //00 1100 xxxx xxxx
                        iDecodedCommand = 14; //RRF
                    }
                }
            } else {
                //00 10xx xxxx xxxx
                if ((0b00001000000000 & iCommand) == 0b00001000000000) {
                    //00 101x xxxx xxxx
                    if ((0b00000100000000 & iCommand) == 0b00000100000000) {
                        //00 1011 xxxx xxxx
                        iDecodedCommand = 6; //DECFSZ
                    } else {
                        //00 1010 xxxx xxxx
                        iDecodedCommand = 7; //INCF
                    }
                } else {
                    //00 100x xxxx xxxx
                    if ((0b00000100000000 & iCommand) == 0b00000100000000) {
                        //00 1001 xxxx xxxx
                        iDecodedCommand = 4; //COMF
                    } else {
                        //00 1000 xxxx xxxx
                        iDecodedCommand = 10; //MOVF
                    }
                }
            }
        } else if ((0b00010000000000 & iCommand) == 0b00010000000000) {
            //00 01xx xxxx xxxx
            if ((0b00001000000000 & iCommand) == 0b00001000000000) {
                //00 011x xxxx xxxx
                if ((0b00000100000000 & iCommand) == 0b00000100000000) {
                    //00 0111 xxxx xxxx
                    iDecodedCommand = 0; //ADDWF
                } else {
                    //00 0110 xxxx xxxx
                    iDecodedCommand = 17; //XORWF
                }
            } else {
                //00 010x xxxx xxxx
                if ((0b00000100000000 & iCommand) == 0b00000100000000) {
                    //00 0101 xxxx xxxx
                    iDecodedCommand = 1; //ANDWF
                } else {
                    //00 0100 xxxx xxxx
                    iDecodedCommand = 9; //IORWF
                }
            }
        } else if ((0b00001000000000 & iCommand) == 0b00001000000000) {
            //00 001x xxxx xxxx
            if ((0b00000100000000 & iCommand) == 0b00000100000000) {
                //00 0011 xxxx xxxx
                iDecodedCommand = 5; //DECF
            } else {
                //00 0010 xxxx xxxx
                iDecodedCommand = 15; //SUBWF
            }
        } else if ((0b00000100000000 & iCommand) == 0b00000100000000) {
            //00 0001 xxxx xxxx
            if ((0b00000010000000 & iCommand) == 0b00000010000000) {
                //00 0001 1xxx xxxx
                iDecodedCommand = 2; //CLRF
            } else {
                //00 0001 0xxx xxxx
                iDecodedCommand = 3; //CLRW
            }
        } else if ((0b00000001000000 & iCommand) == 0b00000001000000) {
            //00 0000 01xx xxxx
            if ((0b00000000100000 & iCommand) == 0b00000000100000) {
                //00 0000 011x xxxx
                if ((0b00000000010000 & iCommand) == 0b00000000010000) {
                    //00 0000 0111 xxxx
                } else {
                    //00 0000 0110 xxxx
                    if ((0b00000000001000 & iCommand) == 0b00000000001000) {
                        //00 0000 0110 1xxx
                    } else {
                        //00 0000 0110 0xxx
                        if ((0b00000000000100 & iCommand) == 0b00000000000100) {
                            //00 0000 0110 01xx
                            if ((0b00000000000010 & iCommand) == 0b00000000000010) {
                                //00 0000 0110 011x
                            } else {
                                //00 0000 0110 010x
                                if ((0b00000000000001 & iCommand) == 0b00000000000001) {
                                    //00 0000 0110 0101
                                } else {
                                    //00 0000 0110 0100
                                    iDecodedCommand = 25; //CLRWDT
                                }
                            }
                        } else {
                            //00 0000 0110 00xx
                            if ((0b00000000000010 & iCommand) == 0b00000000000010) {
                                //00 0000 0110 001x
                                if ((0b00000000000001 & iCommand) == 0b00000000000001) {
                                    //00 0000 0110 0011
                                    iDecodedCommand = 32; //SLEEP
                                } else {
                                    //00 0000 0110 0010
                                    iDecodedCommand = 35; //OPTION
                                }
                            }
                        }
                    }
                }
            }
        } else if ((0b00000010000000 & iCommand) == 0b00000010000000) {
            //00 0000 1xxx xxxx
            iDecodedCommand = 11; //MOVWF
        } else if ((0b00000000001000 & iCommand) == 0b00000000001000) {
            //00 0000 0000 1xxx
            if ((0b00000000000100 & iCommand) == 0b00000000000100) {
                //00 0000 0000 11xx
            } else {
                //00 0000 0000 10xx
                if ((0b00000000000010 & iCommand) == 0b00000000000010) {
                    //00 0000 0000 101x
                } else {
                    //00 0000 0000 100x
                    if ((0b00000000000001 & iCommand) == 0b00000000000001) {
                        //00 0000 0000 1001
                        iDecodedCommand = 29; //RETFIE
                    } else {
                        //00 0000 0000 1000
                        iDecodedCommand = 31; //RETURN
                    }
                }
            }
        } else if ((0b00000001100000 & iCommand) == iCommand) {
            //00 0000 0xx0 0000
            iDecodedCommand = 12; //NOP
        }
        iDecodedCommandDecissionMaker(iDecodedCommand, oPIC, iDestinationBit, iRegisterFileAddress, iBitaddress, iEightK, iElevenK);
        
        return iDecodedCommand;
    }

    public void iDecodedCommandDecissionMaker(int iDecodedCommand, PIC oPIC, int iDestinationBit, int iRegisterFileAddress, int iBitaddress, int iEightK, int iElevenK) {
        switch (iDecodedCommand) {
            case -1: {
                System.out.println("Command doesn't exist.");
            }break;
            
            case 0: {
                oPIC.ADDWF(iDestinationBit, iRegisterFileAddress);
            }break;

            case 1: {
                oPIC.ANDWF(iDestinationBit, iRegisterFileAddress);
            }break;

            case 2: {
                oPIC.CLRF(iRegisterFileAddress);
            }break;

            case 3: {
                oPIC.CLRW();
            }break;

            case 4: {
                oPIC.COMF(iDestinationBit, iRegisterFileAddress);
            }break;

            case 5: {
                oPIC.DECF(iDestinationBit, iRegisterFileAddress);
            }break;

            case 6: {
                oPIC.DECFSZ(iDestinationBit, iRegisterFileAddress);
            }break;

            case 7: {
                oPIC.INCF(iDestinationBit, iRegisterFileAddress);
            }break;

            case 8: {
                oPIC.INCFSZ(iDestinationBit, iRegisterFileAddress);
            }break;

            case 9: {
                oPIC.IORWF(iDestinationBit, iRegisterFileAddress);
            }break;

            case 10: {
                oPIC.MOVF(iDestinationBit, iRegisterFileAddress);
            }break;

            case 11: {
                oPIC.MOVWF(iRegisterFileAddress);
            }break;

            case 12: {
                oPIC.NOP();
            }break;

            case 13: {
                oPIC.RLF(iDestinationBit, iRegisterFileAddress);
            }break;

            case 14: {
                oPIC.RRF(iDestinationBit, iRegisterFileAddress);
            }break;

            case 15: {
                oPIC.SUBWF(iDestinationBit, iRegisterFileAddress);
            }break;

            case 16: {
                oPIC.SWAPF(iDestinationBit, iRegisterFileAddress);
            }break;

            case 17: {
                oPIC.XORWF(iDestinationBit, iRegisterFileAddress);
            }break;

            case 18: {
                oPIC.BCF(iBitaddress, iRegisterFileAddress);
            }break;

            case 19: {
                oPIC.BSF(iBitaddress, iRegisterFileAddress);
            }break;

            case 20: {
                oPIC.BTFSC(iBitaddress, iRegisterFileAddress);
            }break;

            case 21: {
                oPIC.BTFSS(iBitaddress, iRegisterFileAddress);
            }break;

            case 22: {
                oPIC.ADDLW(iEightK);
            }break;

            case 23: {
                oPIC.ANDLW(iEightK);
            }break;

            case 24: {
                oPIC.CALL(iElevenK);
            }break;

            case 25: {
                //watchdogThread.setTime(0);
                oPIC.CLRWDT();
            }break;

            case 26: {
                oPIC.GOTO(iElevenK);
            }break;

            case 27: {
                oPIC.IORLW(iEightK);
            }break;

            case 28: {
                oPIC.MOVLW(iEightK);
            }break;

            case 29: {
                oPIC.RETFIE();
            }break;

            case 30: {
                oPIC.RETLW(iEightK);
            }break;

            case 31: {
                oPIC.RETURN();
            }break;

            case 32: {
                oPIC.SLEEP();
            }break;

            case 33: {
                oPIC.SUBLW(iEightK);
            }break;

            case 34: {
                oPIC.XORLW(iEightK);
            }break;

            case 35: {
                /**
                 * To maintain upward compatibility
                 * with future PIC16CXX products,
                 * do not use this instruction.
                 */
                oPIC.OPTION();
            }break;

            case 36: {
                /**
                 * To maintain upward compatibility
                 * with future PIC16CXX products,
                 * do not use this instruction.
                 */
                oPIC.TRIS();
            }break;
        }
    }
}