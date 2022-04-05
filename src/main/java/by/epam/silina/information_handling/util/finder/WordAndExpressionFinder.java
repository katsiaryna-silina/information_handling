package by.epam.silina.information_handling.util.finder;

import by.epam.silina.information_handling.model.Component;
import by.epam.silina.information_handling.model.SymbolLeaf;
import by.epam.silina.information_handling.model.TextPartComposite;
import by.epam.silina.information_handling.model.TextPartTypeEnum;
import org.apache.commons.lang3.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static by.epam.silina.information_handling.config.Constant.*;

public class WordAndExpressionFinder extends TextComponentFinder {

    @Override
    public void parseContentToComposite(String lexeme, Component lexemeWithWordsAndPunctuationalMarks) {
        Matcher wordAndPunctuation = Pattern.compile(WORD_AND_PUNCTUATION_FINDER_REGEX).matcher(lexeme);
        if (wordAndPunctuation.find()) {
            SymbolFinder symbolFinder = new SymbolFinder();
            String openBracket = wordAndPunctuation.group(OPEN_BRACKET);
            if (!StringUtils.isEmpty(openBracket)) {
                symbolFinder.parseStringToCharacters(openBracket)
                        .forEach(symbol -> lexemeWithWordsAndPunctuationalMarks.add(new SymbolLeaf(symbol)));
            }

            String word = wordAndPunctuation.group(WORD);
            if (!StringUtils.isEmpty(word)) {
                lexemeWithWordsAndPunctuationalMarks.add(find(word, new TextPartComposite(TextPartTypeEnum.WORD)));
            }

            String closeBracket = wordAndPunctuation.group(CLOSE_BRACKET);
            if (!StringUtils.isEmpty(closeBracket)) {
                symbolFinder.parseStringToCharacters(closeBracket)
                        .forEach(symbol -> lexemeWithWordsAndPunctuationalMarks.add(new SymbolLeaf(symbol)));
            }

            String expression = wordAndPunctuation.group(EXPRESSION);
            if (!StringUtils.isEmpty(expression)) {
                lexemeWithWordsAndPunctuationalMarks.add(find(expression, new TextPartComposite(TextPartTypeEnum.EXPRESSION)));
            }

            String punctuation = wordAndPunctuation.group(END_PUNCTUATION);
            if (!StringUtils.isEmpty(punctuation)) {
                symbolFinder.parseStringToCharacters(punctuation)
                        .forEach(symbol -> lexemeWithWordsAndPunctuationalMarks.add(new SymbolLeaf(symbol)));
            }
        }
    }
}
