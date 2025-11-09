// Java Program to reverse a List using Stack

import java.util.*;

class Reverse{

    static void myReverse(List<Integer> list)
    {
        Stack<Integer> s = new Stack<Integer>();

        // Push all the elements in the list into the stack.
        for(Integer x: list)
            s.push(x);

        // Pop the stack and insert back into the list.
        for(int i=0; i<list.size(); i++)
        {
            list.set(i, s.pop());
        }

    }

    public static void main (String[] args) {

        List<Integer> list = new ArrayList<Integer>();

        list.add(10);
        list.add(20);
        list.add(30);

        myReverse(list);

        System.out.println(list);
    }
}