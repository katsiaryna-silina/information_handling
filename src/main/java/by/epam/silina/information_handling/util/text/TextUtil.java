package by.epam.silina.information_handling.util.text;

import by.epam.silina.information_handling.exception.FileUtilException;
import by.epam.silina.information_handling.exception.TextUtilException;
import by.epam.silina.information_handling.model.Component;

import java.io.File;
import java.util.List;
import java.util.Map;

public interface TextUtil {
    Component getTextCompositeFromFile(File file) throws TextUtilException;

    String mapComponentToJSONFormatString(Component component) throws TextUtilException;

    Component sortParagraphsByNumberOfSentencesFromLeastToMost(Component textComponent) throws FileUtilException;

    Component sortParagraphsByNumberOfSentencesFromMostToLeast(Component textComponent) throws FileUtilException;

    List<Component> findSentencesWithTheLargestWordsFromText(Component textComponent) throws FileUtilException;

    Map<CharSequence, Integer> findAllWordsFromTextAndCountTheirNumber(Component textComponent) throws FileUtilException;

    Integer countNumberOfConsonantsInSentence(Component sentenceComponent) throws FileUtilException;

    Integer countNumberOfVowelsInSentence(Component sentenceComponent) throws FileUtilException;

    void showNumberOfConsonantsAndVowelsInAllSentencesOfText(Component textComponent) throws FileUtilException;
}
