package workWithFiles;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.Util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class WorkWithFiles {
    private static final Logger log = LoggerFactory.getLogger(WorkWithFiles.class);

    public static void writeToFile(Byte[] tempMass, String filePath, boolean append) //пишем в файл
    {
        if(tempMass == null || tempMass.length == 0 || filePath.isEmpty())
            throw new IllegalArgumentException();

        try(FileOutputStream fos = new FileOutputStream(new File(filePath), append)) {
            fos.write(Util.arrayUnWrapper(tempMass), 0, tempMass.length);
        }
        catch(IOException ex) {
            log.debug(ex.getMessage());
        }
        log.info("Запись в файл '"+filePath+"' произведена успешно");
    }

    public static byte[] readFromFile(String filePath) // читаем из файла
    {
        if(filePath.isEmpty())
            throw new IllegalArgumentException();

        byte[] bytes = null;
        try(FileInputStream fin = new FileInputStream(filePath)) {

            bytes = new byte[fin.available()];
            // считаем файл в массив
            if(fin.read(bytes, 0, fin.available()) == 0) {
                log.debug("File is empty");
            }
        }
        catch(IOException ex){
            log.debug(ex.getMessage());
        }
        log.info("Чтение из файла '"+filePath+"' произведено успешно");
        return bytes;
    }

}
