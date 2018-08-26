package randomData;

import buffer.LastOperation;
import javafx.beans.binding.NumberBinding;
import util.Util;

import java.lang.reflect.Array;
import java.util.Calendar;
import java.util.Random;


public class DataGeneration {

    private Random rnd;

    public DataGeneration() {
        rnd = new Random();
    }

    public Byte[] getBytes(int length) {
        if(length < 1)
            throw new IllegalArgumentException();

        byte[] bytes = new byte[length];
        rnd.setSeed(Calendar.getInstance().getTimeInMillis());
        rnd.nextBytes(bytes);

        return Util.arrayWrapper(bytes);
    }

    public Double[] getDoubles(int length) {
        if(length < 1)
            throw new IllegalArgumentException();

        rnd.setSeed(Calendar.getInstance().getTimeInMillis());
        double[] doubles = rnd.doubles(length, Double.MIN_VALUE, Double.MAX_VALUE).toArray();

        return Util.arrayWrapper(doubles);
    }

    public Integer[] getInts(int length) {
        if(length < 1)
            throw new IllegalArgumentException();

        rnd.setSeed(Calendar.getInstance().getTimeInMillis());
        int[] ints = rnd.ints(length).toArray();

        return Util.arrayWrapper(ints);
    }

    public Long[] getLongs(int length) {
        if(length < 1)
            throw new IllegalArgumentException();

        rnd.setSeed(Calendar.getInstance().getTimeInMillis());
        long[] longs = rnd.longs(length).toArray();

        return Util.arrayWrapper(longs);
    }

}
