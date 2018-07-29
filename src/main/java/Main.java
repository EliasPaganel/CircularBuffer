import classes.IBuffer;
import classes.RingBuffer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        IBuffer buffer = new RingBuffer(5, false);
        buffer.readOfBuffer(5);
    }
}
