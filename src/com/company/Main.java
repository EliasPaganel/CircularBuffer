package com.company;

import com.company.classes.IBuffer;
import com.company.classes.RingBuffer;

public class Main {

    public static void main(String[] args) {
        byte c = 127;
        System.out.println(c);
	// write your code here
        IBuffer buffer = new RingBuffer(15);
        System.out.println(buffer.getLengthBuffer());
    }
}
