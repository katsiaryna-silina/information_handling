package by.epam.silina.information_handling.util.finder;

import by.epam.silina.information_handling.model.Component;
import by.epam.silina.information_handling.model.SymbolLeaf;
import by.epam.silina.information_handling.model.TextPartComposite;
import by.epam.silina.information_handling.model.TextPartTypeEnum;
import org.apache.commons.lang3.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static by.epam.silina.information_handling.config.Constant.*;

public class LexemeFinder extends TextComponentFinder {

    @Override
    public void parseContentToComposite(String sentence, Component sentenceWithLexemes) {
        Matcher matcher = Pattern.compile(LEXEME_FINDER_REGEX).matcher(sentence);
        while (matcher.find()) {
            SymbolFinder symbolFinder = new SymbolFinder();
            String beginSpace = matcher.group(BEGIN_SPACE);
            if (!StringUtils.isEmpty(beginSpace)) {
                symbolFinder.parseStringToCharacters(beginSpace)
                        .forEach(symbol -> sentenceWithLexemes.add(new SymbolLeaf(symbol)));
            }

            String lexeme = matcher.group(LEXEME);
            if (!StringUtils.isEmpty(lexeme)) {
                sentenceWithLexemes.add(find(lexeme, new TextPartComposite(TextPartTypeEnum.LEXEME)));
            }

            String endSpace = matcher.group(END_SPACE);
            if (!StringUtils.isEmpty(endSpace)) {
                symbolFinder.parseStringToCharacters(endSpace)
                        .forEach(symbol -> sentenceWithLexemes.add(new SymbolLeaf(symbol)));
            }
        }
    }
}
