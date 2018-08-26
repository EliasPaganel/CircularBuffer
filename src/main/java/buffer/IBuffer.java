package buffer;

public interface IBuffer<T extends Number> {

    int getLengthBuffer();
    void writeInBuffer(T[] newData);
    T [] readOfBuffer(int desiredQuantity);

}
