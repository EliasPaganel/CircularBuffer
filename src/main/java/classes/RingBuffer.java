package classes;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

public class RingBuffer<T> implements IBuffer<T> {

    private static final Logger log = LoggerFactory.getLogger(RingBuffer.class);

    //fields
    private int lengthBuffer;
    private T[] buffer;

    //Индекс записи
    private int writingIndex;
    //Индекс чтения
    private int readingIndex;

    private boolean mashingData = false;

    private LastOperation lastOperation;

    //constructor
    public RingBuffer(int lengthBuffer, boolean mashingData) {
        log.debug("При создании буфера заданы следующие параметры: длина буфера - {}, " +
                "а способ записи данных затирание - {}",lengthBuffer, mashingData);

        if(lengthBuffer < 2)
            throw new IllegalArgumentException("Длина буфера не может быть меньше 2 элементов");
        this.lengthBuffer = lengthBuffer;
        buffer = (T[])new Object[this.lengthBuffer];
        this.mashingData = mashingData;
        writingIndex = readingIndex = -1; //Индексы будут отражать где последний раз была запись или чтение
        lastOperation = LastOperation.UNDEFINED;
    }

    //Getters and setters
    public int getLengthBuffer() {
        return lengthBuffer;
    }

    public boolean isMashingData() {
        return mashingData;
    }

    public void setMashingData(boolean mashingData) {
        this.mashingData = mashingData;
    }

    //functions
    @Override
    public void writeInBuffer(T[] newData) {
        log.debug("Данные, ктр нужно записать в буфер:");
        showContent(newData);

        int tempIndex, lastWritingIndex = -1;

        for (int i = 0, length = newData.length; i < length; i++) {
            tempIndex = getNextWritingIndex();
            if(tempIndex >= 0){
                buffer[tempIndex] = newData[i];
                lastOperation = LastOperation.WRITING;
                lastWritingIndex = i;
            }
            else {
                break;
            }
        }
        // какой поток работал с этим
//        log.debug();
        log.debug("Удалось записать {} эл", ++lastWritingIndex);
        log.debug("Состояние буфера после записи:");
        showContent(buffer);
    }

    @Override
    public T[] readOfBuffer(int desiredQuantity) {

        log.info("Обращение к буферу на прочтение {} элем.", desiredQuantity);
        T[] tempArray = (T[])new Object[desiredQuantity];
        int tempIndex;
        int lastReadingIndex = -1;

        for (int i = 0; i < desiredQuantity; i++) {
            tempIndex = getNextReadingIndex();
            if(tempIndex >= 0){
                tempArray[i] = buffer[tempIndex];
                lastOperation = LastOperation.READING;
                lastReadingIndex = i;
            }
            else{
                break;
            }
        }
        // Логгируем какой поток работал с этим
        log.debug("Удалось прочитать {} элем.", ++lastReadingIndex);

        if(lastReadingIndex == 0)
            return (T[])new Object[0];

        if(lastReadingIndex < desiredQuantity)
            tempArray = Arrays.copyOfRange(tempArray,0, lastReadingIndex);

        log.debug("Прочитанные данные: ");
        showContent(tempArray);

        return tempArray;
    }

    private int getNextWritingIndex() {

        if(canWriting()) {
            if(writingIndex + 1 < lengthBuffer)
                return ++writingIndex;
            else
                return writingIndex = 0;
        }
        return -1;
    }

    private int getNextReadingIndex() {

        if(canReading()) {
            if(readingIndex + 1 < lengthBuffer)
                return ++readingIndex;
            else
                return readingIndex = 0;
        }
        return -1;
    }

    private boolean canWriting() {

        if(mashingData && (writingIndex < lengthBuffer))
            return true;

        if(writingIndex == -1)
            return true;

        if(writingIndex + 1 < lengthBuffer) {
            return writingIndex != readingIndex || lastOperation.equals(LastOperation.READING);
        }
        else if(writingIndex + 1 == lengthBuffer) {
            if(writingIndex == readingIndex)
                return lastOperation.equals(LastOperation.READING);
            else
                return readingIndex >= 0;

        }
        else
            throw new IndexOutOfBoundsException("Индекс записи за пределами буфера");
    }

    private boolean canReading(){

        if(writingIndex == -1)
            return false;

        if(readingIndex + 1 < lengthBuffer){
            return readingIndex != writingIndex || lastOperation.equals(LastOperation.WRITING);
        }
        else if(readingIndex + 1 == lengthBuffer){
            if(readingIndex == writingIndex)
                return lastOperation.equals(LastOperation.WRITING);
            else
                return writingIndex >= 0;
        }
        else
            throw new IndexOutOfBoundsException("Индекс чтения за пределами буфера");
    }

    public void showContent(T[] content) {
        if(content.length == 0){
            log.debug("Массив пуст.");
            return;
        }
        StringBuilder s = new StringBuilder("[");
        for (T elem : content) {
            s.append(elem).append(", ");
        }
        s.replace(s.length() - 2, s.length() ,"]");
        log.debug("{} - длина массива: {}", String.valueOf(s), content.length);
    }
}
