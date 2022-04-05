package by.epam.silina.information_handling;

import by.epam.silina.information_handling.exception.FileUtilException;
import by.epam.silina.information_handling.exception.TextUtilException;
import by.epam.silina.information_handling.model.Component;
import by.epam.silina.information_handling.util.text.impl.TextUtilImpl;
import lombok.extern.slf4j.Slf4j;

import java.io.File;

import static by.epam.silina.information_handling.config.Constant.TEXT1_TXT_PATH;

@Slf4j
public class App {
    public static void main(String[] args) {
        Component textComponent = null;
        try {
            textComponent = TextUtilImpl.getInstance().getTextCompositeFromFile(new File(TEXT1_TXT_PATH));
        } catch (TextUtilException e) {
            log.error("", e);
        }
        try {
            TextUtilImpl.getInstance().mapComponentToJSONFormatString(textComponent);
        } catch (TextUtilException e) {
            log.error("", e);
        }
        try {
            TextUtilImpl.getInstance().findSentencesWithTheLargestWordsFromText(textComponent);
        } catch (FileUtilException e) {
            log.error("", e);
        }
        try {
            TextUtilImpl.getInstance().sortParagraphsByNumberOfSentencesFromMostToLeast(textComponent);
        } catch (FileUtilException e) {
            log.error("", e);
        }
        try {
            TextUtilImpl.getInstance().sortParagraphsByNumberOfSentencesFromLeastToMost(textComponent);
        } catch (FileUtilException e) {
            log.error("", e);
        }
        try {
            TextUtilImpl.getInstance().findAllWordsFromTextAndCountTheirNumber(textComponent);
        } catch (FileUtilException e) {
            log.error("", e);
        }
        try {
            TextUtilImpl.getInstance().showNumberOfConsonantsAndVowelsInAllSentencesOfText(textComponent);
        } catch (FileUtilException e) {
            log.error("", e);
        }
    }
}
