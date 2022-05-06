package Model.Microcontroller;

import java.util.EmptyStackException;

/**
 * @author Aaron Moser
 * @date 31.03.2021
 */

/**
 * Datasheet Page 18
 */
public class Stack
{
    private int stackpointer;
    private int[] stack;

    private boolean bStackOverflow = false;
    private boolean bStackUnderflow = false;

    /**
     * Constructor of STACK.
     */
    public Stack() //
    {
        stackpointer = 0;
        //Initialize stack with -1 to decide wether the stack is empty or not, because 0 could be a return address.
        stack = new int[8];
    }

    /**
     * Pushs returnAddress on stack. If stack is full, throws StackOverflowError.
     * @param return_Adress
     */
    public void pushReturnAdressOnStack(int return_Adress) {
        if (stackpointer < 8) {
            stack[stackpointer] = return_Adress;
            stackpointer++;
        }
        
        if (stackpointer == 8) {
            stackpointer = 0;
            bStackOverflow = true;
        }
    }

    /**
     * Pops returnAddress from stack. If stack is empty, throws EmptyStackException.
     * @return
     */
    public int popReturnAdressFromStack() {
        int adressToReturn = -1;

        if (noObjects(stack)) {
            throw new EmptyStackException();
        } else {
            stackpointer--;
            if (stackpointer == -1) {
                stackpointer = 7;
                bStackUnderflow = true;
            }
            adressToReturn = stack[stackpointer];
            stack[stackpointer] = -1;
        }

        

        return adressToReturn;
    }

    /**
     * Returns stack-array.
     */
    public int[] getSTACK()
    {
        return this.stack;
    }

    /**
     * Returns stackpointer.
     */
    public int getStackpointer()
    {
        return stackpointer;
    }

    /**
     * Checks if there are any objects in stack.
     * @param array to check.
     * @return true if empty, false if not empty.
     */
    private boolean noObjects(int[] array)
    {
        boolean noObjects = true;

        for (int i = 0; i < 8; i++)
        {
            if (stack[i] != (-1))
            {
                noObjects = false;
            }
        }

        return noObjects;
    }

    public boolean getStackOverflow() {
        return bStackOverflow;
    }

    public void resetStackOverflow() {
        bStackOverflow = false;
    }

    public boolean getStackUnderflow() {
        return bStackUnderflow;
    }

    public void resetStackUnderflow() {
        bStackUnderflow = false;
    }
}