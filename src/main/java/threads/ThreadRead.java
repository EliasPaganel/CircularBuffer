package threads;

import buffer.IBuffer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import workWithFiles.WorkWithFiles;

import java.util.Calendar;
import java.util.Random;

/**
 * Класс, ктр инкапсулирует в себе поток чтения из буфера, и запись в файл*/
public class ThreadRead extends Thread {

    private static final Logger log = LoggerFactory.getLogger(ThreadRead.class);

    private String threadName;
    private String pathFile;

    private IBuffer<Byte> buffer;
    private int howMany;
    private boolean append;

    /**
     * @param threadName имя потока
     * @param buffer ссылка на буфер, из которого будет производится чтение
     * @param howMany кол-во элементов, ктр мы хотим прочесть из буфера
     * @param pathFile путь файла, в ктр мы будем помещать прочитанные из буфера данные
     * @param append логическое значение отражающее, добавлять ли в конец файла прочитанные данные, в случае false - файл будет перезаписываться*/
    public ThreadRead(String threadName, IBuffer<Byte> buffer, int howMany, String pathFile, boolean append) {
        //Создать новый поток исполнения
        super(threadName);
        this.threadName = threadName;
        this.buffer = buffer;
        this.howMany = howMany;
        this.pathFile = pathFile;
        this.append = append;

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
        WorkWithFiles.writeToFile(buffer.readOfBuffer(howMany), pathFile, append);
    }
}
