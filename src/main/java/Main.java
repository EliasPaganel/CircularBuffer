import classes.IBuffer;
import classes.RingBuffer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        IBuffer<Byte> buffer = new RingBuffer<>(7, false);
        buffer.writeInBuffer(new Byte[]{1,2,3,4,5});
        buffer.readOfBuffer(2);
        buffer.writeInBuffer(new Byte[]{6,7,8,9,10});
    }
}
