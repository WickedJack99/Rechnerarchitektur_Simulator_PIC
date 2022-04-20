package Model.Microcontroller;

import java.util.EmptyStackException;

/**
 * @author Aaron Moser
 * @date 31.03.2021
 */

/**
 * Datasheet Page 18
 */
public class STACK
{
    private int stackpointer;
    private int[] stack;

    /**
     * Constructor of STACK.
     */
    public STACK() //
    {
        stackpointer = 0;
        //Initialize stack with -1 to decide wether the stack is empty or not, because 0 could be a return address.
        stack = new int[]{-1, -1, -1, -1, -1, -1, -1, -1};
    }

    /**
     * Pushs returnAddress on stack. If stack is full, throws StackOverflowError.
     * @param return_Adress
     */
    public void pushReturnAdressOnStack(int return_Adress)
    {
        if (stackpointer < 8)
        {
            stack[stackpointer] = return_Adress;
            stackpointer++;
        }
        
        if (stackpointer == 8)
        {
            //throw new StackOverflowError();
            stackpointer = 0;
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
            adressToReturn = stack[stackpointer];
            stack[stackpointer] = -1;
        }

        if (stackpointer == -1)
            stackpointer = 7;

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
}