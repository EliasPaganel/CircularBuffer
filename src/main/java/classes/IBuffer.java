package classes;

public interface IBuffer {

    int getLengthBuffer();
    void writeInBuffer(byte[] newData);
    byte [] readOfBuffer(int desiredQuantity);

}
