package by.epam.silina.information_handling.util.finder;

import by.epam.silina.information_handling.model.Component;
import by.epam.silina.information_handling.model.SymbolLeaf;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class SymbolFinder extends TextComponentFinder {

    @Override
    public void parseContentToComposite(String wordOrSymbol, Component symbols) {
        parseStringToCharacters(wordOrSymbol)
                .forEach(symbol -> symbols.add(new SymbolLeaf(symbol)));
    }

    public List<Character> parseStringToCharacters(String string) {
        List<Character> characters = new ArrayList<>();
        if (!StringUtils.isEmpty(string)) {
            char[] chars = string.toCharArray();
            characters = new ArrayList<>();
            for (char aChar : chars) {
                characters.add(aChar);
            }
        }
        return characters;
    }
}