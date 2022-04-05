package by.epam.silina.information_handling.util.file.impl;

import by.epam.silina.information_handling.exception.FileUtilException;
import by.epam.silina.information_handling.util.file.FileUtil;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import static by.epam.silina.information_handling.config.Constant.*;

@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class FileUtilImpl implements FileUtil {
    private static final FileUtil instance = new FileUtilImpl();

    public static FileUtil getInstance() {
        return instance;
    }

    @Override
    public boolean isFileExists(File file) {
        if (file.exists()) {
            log.info(FILE_IS_EXISTS, file.getName());
            return true;
        } else {
            log.info(FILE_DOES_NOT_EXIST, file.getName());
            return false;
        }
    }

    @Override
    public String readTextFromFile(File file) throws FileUtilException {
        String wholeText = null;
        if (isFileExists(file)) {
            try (Scanner scanner = new Scanner(file)) {
                scanner.useDelimiter(BEGINNING_OF_THE_FILE_DELIMITER);
                wholeText = scanner.next();
                log.info(SCANNED_TEXT_FROM_FILE, file.getName(), wholeText);
            } catch (FileNotFoundException e) {
                throw new FileUtilException("", e);
            }
        }
        return wholeText;
    }
}
