package classes;

public interface IBuffer<T> {

    int getLengthBuffer();
    void writeInBuffer(T[] newData);
    T [] readOfBuffer(int desiredQuantity);

}
