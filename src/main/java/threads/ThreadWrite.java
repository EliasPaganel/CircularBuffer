package threads;


import buffer.IBuffer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import randomData.DataGeneration;

import java.util.Calendar;
import java.util.Random;

/**
 * Класс, ктр инкапсулирует в себе поток записи в буфер, данных ктр сгенерированы случайным образом*/
public class ThreadWrite extends Thread{

    private static final Logger log = LoggerFactory.getLogger(ThreadWrite.class);

    private String threadName;
    private IBuffer<Byte> buffer;
    private int howMany;

    /**
     * @param threadName имя потока
     * @param buffer ссылка на буфер, из которого будет производится чтение
     * @param howMany кол-во элементов, ктр мы хотим прочесть из буфера*/
    public ThreadWrite(String threadName, IBuffer<Byte> buffer, int howMany) {
        //Создать новый поток исполнения
        super(threadName);
        this.threadName = threadName;
        this.buffer = buffer;
        this.howMany = howMany;

        log.debug("Поток создан: " + this);
    }

    @Override
    public void run() {
        log.debug("Поток запущен: " + this);
        try {
            Thread.sleep(new Random(Calendar.getInstance().getTimeInMillis()).nextInt()%1000+1000);
        } catch (InterruptedException e) {
            log.debug(e.getMessage());
        }
        buffer.writeInBuffer(new DataGeneration().getBytes(howMany));
    }
}
