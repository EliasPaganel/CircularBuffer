package util;

/**
 * Класс хранящий в себе служебные методы:
 * Упаковка массива примитивов в массив оберток;
 * распаковка массива оберток в массив примитивов.*/
public class Util {

    public static Byte[] arrayWrapper(byte[] bytes) {

        Byte[] byteObjects = new Byte[bytes.length];
        int i = 0;
        for (byte b: bytes) {
            byteObjects[i++] = b;
        }
        return byteObjects;
    }

    public static Integer[] arrayWrapper(int[] ints) {

        Integer[] integerObjects = new Integer[ints.length];
        int i = 0;
        for (int in: ints) {
            integerObjects[i++] = in;
        }
        return integerObjects;
    }

    public static Double[] arrayWrapper(double[] doubles) {

        Double[] doubleObjects = new Double[doubles.length];
        int i = 0;
        for (double d: doubles) {
            doubleObjects[i++] = d;
        }
        return doubleObjects;
    }

    public static Long[] arrayWrapper(long[] longs) {

        Long[] longObjects = new Long[longs.length];
        int i = 0;
        for (long lon: longs) {
            longObjects[i++] = lon;
        }
        return longObjects;
    }

    public static byte[] arrayUnWrapper(Byte[] byteObjects) {

        byte[] bytes = new byte[byteObjects.length];
        int i = 0;
        for (Byte b: byteObjects) {
            bytes[i++] = b;
        }
        return bytes;
    }

    public static int[] arrayUnWrapper(Integer[] integerObjects) {

        int[] ints = new int[integerObjects.length];
        int i = 0;
        for (Integer in: integerObjects) {
            ints[i++] = in;
        }
        return ints;
    }

    public static double[] arrayUnWrapper(Double[] doubleObjects) {

        double[] doubles = new double[doubleObjects.length];
        int i = 0;
        for (Double d: doubleObjects) {
            doubles[i++] = d;
        }
        return doubles;
    }

    public static long[] arrayUnWrapper(Long[] longObjects) {

        long[] longs = new long[longObjects.length];
        int i = 0;
        for (Long lon: longObjects) {
            longs[i++] = lon;
        }
        return longs;
    }
}
