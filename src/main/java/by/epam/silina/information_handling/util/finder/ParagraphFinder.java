package by.epam.silina.information_handling.util.finder;

import by.epam.silina.information_handling.model.Component;
import by.epam.silina.information_handling.model.SymbolLeaf;
import by.epam.silina.information_handling.model.TextPartComposite;
import by.epam.silina.information_handling.model.TextPartTypeEnum;
import org.apache.commons.lang3.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static by.epam.silina.information_handling.config.Constant.*;

public class ParagraphFinder extends TextComponentFinder {

    @Override
    public void parseContentToComposite(String wholeText, Component textWithListOfParagraphs) {
        Matcher matcher = Pattern.compile(PARAGRAPH_FINDER_REGEX).matcher(wholeText);
        while (matcher.find()) {
            SymbolFinder symbolFinder = new SymbolFinder();
            String paragraphMark = matcher.group(PARAGRAPH_MARK);
            if (!StringUtils.isEmpty(paragraphMark)) {
                symbolFinder.parseStringToCharacters(paragraphMark)
                        .forEach(symbol -> textWithListOfParagraphs.add(new SymbolLeaf(symbol)));
            }

            String textOfParagraph = matcher.group(TEXT_OF_PARAGRAPH);
            if (!StringUtils.isEmpty(textOfParagraph)) {
                textWithListOfParagraphs.add(find(textOfParagraph, new TextPartComposite(TextPartTypeEnum.PARAGRAPH)));
            }

            String newLine = matcher.group(NEW_LINE);
            if (!StringUtils.isEmpty(newLine)) {
                symbolFinder.parseStringToCharacters(newLine)
                        .forEach(symbol -> textWithListOfParagraphs.add(new SymbolLeaf(symbol)));
            }
        }
    }
}
