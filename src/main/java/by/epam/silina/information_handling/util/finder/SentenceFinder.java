package by.epam.silina.information_handling.util.finder;

import by.epam.silina.information_handling.model.Component;
import by.epam.silina.information_handling.model.SymbolLeaf;
import by.epam.silina.information_handling.model.TextPartComposite;
import by.epam.silina.information_handling.model.TextPartTypeEnum;
import org.apache.commons.lang3.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static by.epam.silina.information_handling.config.Constant.*;

public class SentenceFinder extends TextComponentFinder {

    @Override
    public void parseContentToComposite(String paragraph, Component paragraphWithSentencesAndSeparators) {
        Matcher matcher = Pattern.compile(SENTENCE_FINDER_REGEX).matcher(paragraph);
        while (matcher.find()) {
            String sentence = matcher.group(SENTENCE);
            if (!StringUtils.isEmpty(sentence)) {
                paragraphWithSentencesAndSeparators.add(find(sentence, new TextPartComposite(TextPartTypeEnum.SENTENCE)));
            }

            SymbolFinder symbolFinder = new SymbolFinder();
            String separator = matcher.group(SEPARATOR);
            if (!StringUtils.isEmpty(separator)) {
                symbolFinder.parseStringToCharacters(separator)
                        .forEach(symbol -> paragraphWithSentencesAndSeparators.add(new SymbolLeaf(symbol)));
            }
        }
    }
}
