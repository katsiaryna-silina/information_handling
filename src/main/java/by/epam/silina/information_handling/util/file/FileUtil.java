package by.epam.silina.information_handling.util.file;

import by.epam.silina.information_handling.exception.FileUtilException;

import java.io.File;

public interface FileUtil {
    boolean isFileExists(File file);

    String readTextFromFile(File file) throws FileUtilException;
}
