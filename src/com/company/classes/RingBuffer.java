package com.company.classes;

public class RingBuffer implements IBuffer {

    private int lengthBuffer;
    private byte[] buffer;

    //Индекс записи
    private int writingIndex;
    //Индекс чтения
    private int readingIndex;

    private boolean mashingData = false;

    public RingBuffer(int lengthBuffer) {
        this.lengthBuffer = lengthBuffer;
        buffer = new byte[this.lengthBuffer];
        writingIndex = readingIndex = 0;
    }

    public int getLengthBuffer() {
        return lengthBuffer;
    }

    public boolean isMashingData() {
        return mashingData;
    }

    public void setMashingData(boolean mashingData) {
        this.mashingData = mashingData;
    }

    //Функция управления индексами
    private void operateIndexes(boolean isWriting) {

    }

    public void writeInBuffer(byte element){

    }

    public byte readOfBuffer(){

        return 0;
    }

}
