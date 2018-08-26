import buffer.IBuffer;
import buffer.RingBuffer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import threads.ThreadRead;
import threads.ThreadWrite;
import workWithFiles.WorkWithFiles;

public class Main {

    private static final Logger log = LoggerFactory.getLogger(Main.class);
    public static void main(String[] args) {

        String filePath = "E://lol.txt";

        IBuffer<Byte> iBuffer = new RingBuffer<>(Byte.class, 5, false);
        //сделать потоки обощенными
        ThreadWrite threadWrite1 = new ThreadWrite("Поток записи 1", iBuffer, 1);
        ThreadWrite threadWrite2 = new ThreadWrite("Поток записи 2", iBuffer, 2);
        ThreadRead threadRead =
                new ThreadRead("Поток чтения из буфера 1", iBuffer, 12, filePath, false);


        threadWrite1.start();
        threadWrite2.start();
        threadRead.start();

        try {
            threadWrite1.join();
            threadWrite2.join();
            threadRead.join();
        } catch (InterruptedException e) {
            log.debug(e.getMessage());
        }

        log.info("Данные из файла: ");
        for(byte b : WorkWithFiles.readFromFile(filePath)) {
            log.info("{} ", b);
        }
        System.out.println();

        ThreadRead threadRead2 =
                new ThreadRead("Поток чтения из буфера 2", iBuffer, 5, filePath, true);
        ThreadWrite threadWrite3 = new ThreadWrite("Поток записи 3", iBuffer, 3);
        threadRead2.start();
        threadWrite3.start();

        try {
            threadWrite3.join();
            threadRead2.join();
        } catch (InterruptedException e) {
            log.debug(e.getMessage());
        }
        System.out.println("Данные из файла: ");
        for(byte b : WorkWithFiles.readFromFile(filePath)){
            log.info("{} ", b);
        }

    }
}
