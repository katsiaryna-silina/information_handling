package by.epam.silina.information_handling.util.text.impl;

import by.epam.silina.information_handling.exception.FileUtilException;
import by.epam.silina.information_handling.exception.TextUtilException;
import by.epam.silina.information_handling.model.Component;
import by.epam.silina.information_handling.model.SymbolLeaf;
import by.epam.silina.information_handling.model.TextPartComposite;
import by.epam.silina.information_handling.model.TextPartTypeEnum;
import by.epam.silina.information_handling.util.text.TextUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static by.epam.silina.information_handling.config.Constant.TEXT1_TXT_PATH;

class TextUtilImplTest {
    private final TextUtil textUtil = TextUtilImpl.getInstance();
    private final List<Component> expectedSentencesWithTheLargestWordsFromText = new ArrayList<>();
    private Component textComponent;

    @BeforeEach
    void setup() {
        textComponent = new TextPartComposite(TextPartTypeEnum.TEXT);
        //full first paragraph
        Component paragraphOne = new TextPartComposite(TextPartTypeEnum.PARAGRAPH);
        paragraphOne.add(new SymbolLeaf(' '));
        paragraphOne.add(new SymbolLeaf(' '));
        paragraphOne.add(new SymbolLeaf(' '));
        paragraphOne.add(new SymbolLeaf(' '));

        //full first sentence
        Component sentenceComponentOne = new TextPartComposite(TextPartTypeEnum.SENTENCE);

        Component lexemeComponentOne = new TextPartComposite(TextPartTypeEnum.LEXEME);
        Component wordComponentOne = new TextPartComposite(TextPartTypeEnum.WORD);
        wordComponentOne.add(new SymbolLeaf('W'));
        wordComponentOne.add(new SymbolLeaf('o'));
        wordComponentOne.add(new SymbolLeaf('r'));
        wordComponentOne.add(new SymbolLeaf('d'));
        lexemeComponentOne.add(wordComponentOne);
        lexemeComponentOne.add(new SymbolLeaf(','));
        sentenceComponentOne.add(lexemeComponentOne);
        sentenceComponentOne.add(new SymbolLeaf(' '));

        Component lexemeComponentTwo = new TextPartComposite(TextPartTypeEnum.LEXEME);
        Component expressionComponentOne = new TextPartComposite(TextPartTypeEnum.EXPRESSION);
        expressionComponentOne.add(new SymbolLeaf('1'));
        expressionComponentOne.add(new SymbolLeaf('3'));
        expressionComponentOne.add(new SymbolLeaf('<'));
        expressionComponentOne.add(new SymbolLeaf('<'));
        expressionComponentOne.add(new SymbolLeaf('2'));
        lexemeComponentTwo.add(expressionComponentOne);
        lexemeComponentTwo.add(new SymbolLeaf('.'));
        sentenceComponentOne.add(lexemeComponentTwo);
        paragraphOne.add(sentenceComponentOne);
        paragraphOne.add(new SymbolLeaf(' '));

        //full second sentence
        Component sentenceComponentTWO = new TextPartComposite(TextPartTypeEnum.SENTENCE);

        Component lexemeComponentThree = new TextPartComposite(TextPartTypeEnum.LEXEME);
        Component wordComponentTwo = new TextPartComposite(TextPartTypeEnum.WORD);
        wordComponentTwo.add(new SymbolLeaf('S'));
        wordComponentTwo.add(new SymbolLeaf('h'));
        wordComponentTwo.add(new SymbolLeaf('o'));
        wordComponentTwo.add(new SymbolLeaf('w'));
        lexemeComponentThree.add(wordComponentTwo);
        lexemeComponentThree.add(new SymbolLeaf(','));
        sentenceComponentTWO.add(lexemeComponentThree);
        sentenceComponentTWO.add(new SymbolLeaf(' '));

        Component lexemeComponentFour = new TextPartComposite(TextPartTypeEnum.LEXEME);
        Component wordComponentThree = new TextPartComposite(TextPartTypeEnum.WORD);
        wordComponentThree.add(new SymbolLeaf('d'));
        wordComponentThree.add(new SymbolLeaf('o'));
        wordComponentThree.add(new SymbolLeaf('e'));
        wordComponentThree.add(new SymbolLeaf('s'));
        wordComponentThree.add(new SymbolLeaf('n'));
        wordComponentThree.add(new SymbolLeaf('\''));
        wordComponentThree.add(new SymbolLeaf('t'));
        lexemeComponentFour.add(wordComponentThree);
        lexemeComponentFour.add(new SymbolLeaf('!'));
        sentenceComponentTWO.add(lexemeComponentFour);
        expectedSentencesWithTheLargestWordsFromText.add(sentenceComponentTWO);
        paragraphOne.add(sentenceComponentTWO);
        textComponent.add(paragraphOne);

        textComponent.add(new SymbolLeaf('\r'));
        textComponent.add(new SymbolLeaf('\n'));

        //full second paragraph
        Component paragraphTwo = new TextPartComposite(TextPartTypeEnum.PARAGRAPH);
        paragraphTwo.add(new SymbolLeaf(' '));
        paragraphTwo.add(new SymbolLeaf(' '));
        paragraphTwo.add(new SymbolLeaf(' '));
        paragraphTwo.add(new SymbolLeaf(' '));

        //full first sentence
        Component sentenceComponentThree = new TextPartComposite(TextPartTypeEnum.SENTENCE);

        Component lexemeComponentFive = new TextPartComposite(TextPartTypeEnum.LEXEME);
        lexemeComponentFive.add(wordComponentOne);
        lexemeComponentFive.add(new SymbolLeaf('?'));
        sentenceComponentThree.add(lexemeComponentFive);
        paragraphTwo.add(sentenceComponentThree);
        textComponent.add(paragraphTwo);

        textComponent.add(new SymbolLeaf('\r'));
        textComponent.add(new SymbolLeaf('\n'));

        //full third paragraph
        Component paragraphThree = new TextPartComposite(TextPartTypeEnum.PARAGRAPH);
        paragraphThree.add(new SymbolLeaf(' '));
        paragraphThree.add(new SymbolLeaf(' '));
        paragraphThree.add(new SymbolLeaf(' '));
        paragraphThree.add(new SymbolLeaf(' '));

        //full first sentence
        Component sentenceComponentFour = new TextPartComposite(TextPartTypeEnum.SENTENCE);

        Component lexemeComponentSix = new TextPartComposite(TextPartTypeEnum.LEXEME);
        Component wordComponentFour = new TextPartComposite(TextPartTypeEnum.WORD);
        wordComponentFour.add(new SymbolLeaf('M'));
        wordComponentFour.add(new SymbolLeaf('y'));
        lexemeComponentSix.add(wordComponentFour);
        sentenceComponentFour.add(lexemeComponentSix);
        sentenceComponentFour.add(new SymbolLeaf(' '));

        Component lexemeComponentSeven = new TextPartComposite(TextPartTypeEnum.LEXEME);
        Component wordComponentFive = new TextPartComposite(TextPartTypeEnum.WORD);
        wordComponentFive.add(new SymbolLeaf('p'));
        wordComponentFive.add(new SymbolLeaf('e'));
        wordComponentFive.add(new SymbolLeaf('t'));
        lexemeComponentSeven.add(wordComponentFive);
        lexemeComponentSeven.add(new SymbolLeaf('.'));

        //full second sentence
        Component sentenceComponentFive = new TextPartComposite(TextPartTypeEnum.SENTENCE);

        Component lexemeComponentEight = new TextPartComposite(TextPartTypeEnum.LEXEME);
        Component wordComponentSix = new TextPartComposite(TextPartTypeEnum.WORD);
        wordComponentSix.add(new SymbolLeaf('H'));
        wordComponentSix.add(new SymbolLeaf('e'));
        lexemeComponentEight.add(wordComponentSix);
        sentenceComponentFive.add(lexemeComponentEight);
        sentenceComponentFive.add(new SymbolLeaf(' '));

        Component lexemeComponentNine = new TextPartComposite(TextPartTypeEnum.LEXEME);
        lexemeComponentNine.add(wordComponentThree);
        lexemeComponentNine.add(new SymbolLeaf('.'));
        sentenceComponentFive.add(lexemeComponentNine);

        expectedSentencesWithTheLargestWordsFromText.add(sentenceComponentFive);
        paragraphThree.add(sentenceComponentFive);
        textComponent.add(paragraphThree);
    }

    @Test
    void getTextCompositeFromFile() throws TextUtilException {
        Component textCompositeFromFile = textUtil.getTextCompositeFromFile(new File(TEXT1_TXT_PATH));
        Assertions.assertNotNull(textCompositeFromFile);

        CharSequence expectedWholeText = "(word), word.\r" +
                "Hye, week. Not Bobs! 1111.\r" +
                "(7^5|1&2<<(2|5>>2&71))|1200.";
        CharSequence actualWholeText = textCompositeFromFile.getSymbols();
        Assertions.assertEquals(expectedWholeText, actualWholeText);
    }

    @Test
    void mapComponentToJSONFormatString() throws TextUtilException {
        String actualJSONResult = textUtil.mapComponentToJSONFormatString(textComponent);
        String expectedJSONResult = "{\"components\":[{\"components\":[{\"singleSymbol\":\" \",\"testEnum\":\"SYMBOL\",\"symbols\":null,\"components\":null},{\"singleSymbol\":\" \",\"testEnum\":\"SYMBOL\",\"symbols\":null,\"components\":null},{\"singleSymbol\":\" \",\"testEnum\":\"SYMBOL\",\"symbols\":null,\"components\":null},{\"singleSymbol\":\" \",\"testEnum\":\"SYMBOL\",\"symbols\":null,\"components\":null},{\"components\":[{\"components\":[{\"components\":[{\"singleSymbol\":\"W\",\"testEnum\":\"SYMBOL\",\"symbols\":null,\"components\":null},{\"singleSymbol\":\"o\",\"testEnum\":\"SYMBOL\",\"symbols\":null,\"components\":null},{\"singleSymbol\":\"r\",\"testEnum\":\"SYMBOL\",\"symbols\":null,\"components\":null},{\"singleSymbol\":\"d\",\"testEnum\":\"SYMBOL\",\"symbols\":null,\"components\":null}],\"testEnum\":\"WORD\",\"symbols\":\"Word\"},{\"singleSymbol\":\",\",\"testEnum\":\"SYMBOL\",\"symbols\":null,\"components\":null}],\"testEnum\":\"LEXEME\",\"symbols\":\"Word,\"},{\"singleSymbol\":\" \",\"testEnum\":\"SYMBOL\",\"symbols\":null,\"components\":null},{\"components\":[{\"components\":[{\"singleSymbol\":\"1\",\"testEnum\":\"SYMBOL\",\"symbols\":null,\"components\":null},{\"singleSymbol\":\"3\",\"testEnum\":\"SYMBOL\",\"symbols\":null,\"components\":null},{\"singleSymbol\":\"<\",\"testEnum\":\"SYMBOL\",\"symbols\":null,\"components\":null},{\"singleSymbol\":\"<\",\"testEnum\":\"SYMBOL\",\"symbols\":null,\"components\":null},{\"singleSymbol\":\"2\",\"testEnum\":\"SYMBOL\",\"symbols\":null,\"components\":null}],\"testEnum\":\"EXPRESSION\",\"symbols\":\"13<<2\"},{\"singleSymbol\":\".\",\"testEnum\":\"SYMBOL\",\"symbols\":null,\"components\":null}],\"testEnum\":\"LEXEME\",\"symbols\":\"13<<2.\"}],\"testEnum\":\"SENTENCE\",\"symbols\":\"Word, 13<<2.\"},{\"singleSymbol\":\" \",\"testEnum\":\"SYMBOL\",\"symbols\":null,\"components\":null},{\"components\":[{\"components\":[{\"components\":[{\"singleSymbol\":\"S\",\"testEnum\":\"SYMBOL\",\"symbols\":null,\"components\":null},{\"singleSymbol\":\"h\",\"testEnum\":\"SYMBOL\",\"symbols\":null,\"components\":null},{\"singleSymbol\":\"o\",\"testEnum\":\"SYMBOL\",\"symbols\":null,\"components\":null},{\"singleSymbol\":\"w\",\"testEnum\":\"SYMBOL\",\"symbols\":null,\"components\":null}],\"testEnum\":\"WORD\",\"symbols\":\"Show\"},{\"singleSymbol\":\",\",\"testEnum\":\"SYMBOL\",\"symbols\":null,\"components\":null}],\"testEnum\":\"LEXEME\",\"symbols\":\"Show,\"},{\"singleSymbol\":\" \",\"testEnum\":\"SYMBOL\",\"symbols\":null,\"components\":null},{\"components\":[{\"components\":[{\"singleSymbol\":\"d\",\"testEnum\":\"SYMBOL\",\"symbols\":null,\"components\":null},{\"singleSymbol\":\"o\",\"testEnum\":\"SYMBOL\",\"symbols\":null,\"components\":null},{\"singleSymbol\":\"e\",\"testEnum\":\"SYMBOL\",\"symbols\":null,\"components\":null},{\"singleSymbol\":\"s\",\"testEnum\":\"SYMBOL\",\"symbols\":null,\"components\":null},{\"singleSymbol\":\"n\",\"testEnum\":\"SYMBOL\",\"symbols\":null,\"components\":null},{\"singleSymbol\":\"'\",\"testEnum\":\"SYMBOL\",\"symbols\":null,\"components\":null},{\"singleSymbol\":\"t\",\"testEnum\":\"SYMBOL\",\"symbols\":null,\"components\":null}],\"testEnum\":\"WORD\",\"symbols\":\"doesn't\"},{\"singleSymbol\":\"!\",\"testEnum\":\"SYMBOL\",\"symbols\":null,\"components\":null}],\"testEnum\":\"LEXEME\",\"symbols\":\"doesn't!\"}],\"testEnum\":\"SENTENCE\",\"symbols\":\"Show, doesn't!\"}],\"testEnum\":\"PARAGRAPH\",\"symbols\":\"    Word, 13<<2. Show, doesn't!\"},{\"singleSymbol\":\"\\r\",\"testEnum\":\"SYMBOL\",\"symbols\":null,\"components\":null},{\"singleSymbol\":\"\\n\",\"testEnum\":\"SYMBOL\",\"symbols\":null,\"components\":null},{\"components\":[{\"singleSymbol\":\" \",\"testEnum\":\"SYMBOL\",\"symbols\":null,\"components\":null},{\"singleSymbol\":\" \",\"testEnum\":\"SYMBOL\",\"symbols\":null,\"components\":null},{\"singleSymbol\":\" \",\"testEnum\":\"SYMBOL\",\"symbols\":null,\"components\":null},{\"singleSymbol\":\" \",\"testEnum\":\"SYMBOL\",\"symbols\":null,\"components\":null},{\"components\":[{\"components\":[{\"components\":[{\"singleSymbol\":\"W\",\"testEnum\":\"SYMBOL\",\"symbols\":null,\"components\":null},{\"singleSymbol\":\"o\",\"testEnum\":\"SYMBOL\",\"symbols\":null,\"components\":null},{\"singleSymbol\":\"r\",\"testEnum\":\"SYMBOL\",\"symbols\":null,\"components\":null},{\"singleSymbol\":\"d\",\"testEnum\":\"SYMBOL\",\"symbols\":null,\"components\":null}],\"testEnum\":\"WORD\",\"symbols\":\"Word\"},{\"singleSymbol\":\"?\",\"testEnum\":\"SYMBOL\",\"symbols\":null,\"components\":null}],\"testEnum\":\"LEXEME\",\"symbols\":\"Word?\"}],\"testEnum\":\"SENTENCE\",\"symbols\":\"Word?\"}],\"testEnum\":\"PARAGRAPH\",\"symbols\":\"    Word?\"},{\"singleSymbol\":\"\\r\",\"testEnum\":\"SYMBOL\",\"symbols\":null,\"components\":null},{\"singleSymbol\":\"\\n\",\"testEnum\":\"SYMBOL\",\"symbols\":null,\"components\":null},{\"components\":[{\"singleSymbol\":\" \",\"testEnum\":\"SYMBOL\",\"symbols\":null,\"components\":null},{\"singleSymbol\":\" \",\"testEnum\":\"SYMBOL\",\"symbols\":null,\"components\":null},{\"singleSymbol\":\" \",\"testEnum\":\"SYMBOL\",\"symbols\":null,\"components\":null},{\"singleSymbol\":\" \",\"testEnum\":\"SYMBOL\",\"symbols\":null,\"components\":null},{\"components\":[{\"components\":[{\"components\":[{\"singleSymbol\":\"H\",\"testEnum\":\"SYMBOL\",\"symbols\":null,\"components\":null},{\"singleSymbol\":\"e\",\"testEnum\":\"SYMBOL\",\"symbols\":null,\"components\":null}],\"testEnum\":\"WORD\",\"symbols\":\"He\"}],\"testEnum\":\"LEXEME\",\"symbols\":\"He\"},{\"singleSymbol\":\" \",\"testEnum\":\"SYMBOL\",\"symbols\":null,\"components\":null},{\"components\":[{\"components\":[{\"singleSymbol\":\"d\",\"testEnum\":\"SYMBOL\",\"symbols\":null,\"components\":null},{\"singleSymbol\":\"o\",\"testEnum\":\"SYMBOL\",\"symbols\":null,\"components\":null},{\"singleSymbol\":\"e\",\"testEnum\":\"SYMBOL\",\"symbols\":null,\"components\":null},{\"singleSymbol\":\"s\",\"testEnum\":\"SYMBOL\",\"symbols\":null,\"components\":null},{\"singleSymbol\":\"n\",\"testEnum\":\"SYMBOL\",\"symbols\":null,\"components\":null},{\"singleSymbol\":\"'\",\"testEnum\":\"SYMBOL\",\"symbols\":null,\"components\":null},{\"singleSymbol\":\"t\",\"testEnum\":\"SYMBOL\",\"symbols\":null,\"components\":null}],\"testEnum\":\"WORD\",\"symbols\":\"doesn't\"},{\"singleSymbol\":\".\",\"testEnum\":\"SYMBOL\",\"symbols\":null,\"components\":null}],\"testEnum\":\"LEXEME\",\"symbols\":\"doesn't.\"}],\"testEnum\":\"SENTENCE\",\"symbols\":\"He doesn't.\"}],\"testEnum\":\"PARAGRAPH\",\"symbols\":\"    He doesn't.\"}],\"testEnum\":\"TEXT\",\"symbols\":\"    Word, 13<<2. Show, doesn't!\\r\\n    Word?\\r\\n    He doesn't.\"}";
        Assertions.assertEquals(expectedJSONResult, actualJSONResult);
    }

    @Test
    void sortParagraphsByNumberOfSentencesFromLeastToMost() throws FileUtilException {
        Component actualTextComponent = textUtil.sortParagraphsByNumberOfSentencesFromLeastToMost(textComponent);
        Assertions.assertNotEquals(textComponent, actualTextComponent);

        List<Component> initialParagraphsComponents = textComponent.getComponentsByType(TextPartTypeEnum.PARAGRAPH);
        List<Component> actualParagraphsComponents = actualTextComponent.getComponentsByType(TextPartTypeEnum.PARAGRAPH);
        Assertions.assertEquals(initialParagraphsComponents.get(0), actualParagraphsComponents.get(2));
        Assertions.assertEquals(initialParagraphsComponents.get(1), actualParagraphsComponents.get(0));
        Assertions.assertEquals(initialParagraphsComponents.get(2), actualParagraphsComponents.get(1));
    }

    @Test
    void sortParagraphsByNumberOfSentencesFromMostToLeast() throws FileUtilException {
        Component actualTextComponent = textUtil.sortParagraphsByNumberOfSentencesFromMostToLeast(textComponent);
        Assertions.assertNotEquals(textComponent, actualTextComponent);

        List<Component> initialParagraphsComponents = textComponent.getComponentsByType(TextPartTypeEnum.PARAGRAPH);
        List<Component> actualParagraphsComponents = actualTextComponent.getComponentsByType(TextPartTypeEnum.PARAGRAPH);
        Assertions.assertEquals(initialParagraphsComponents.get(0), actualParagraphsComponents.get(0));
        Assertions.assertEquals(initialParagraphsComponents.get(1), actualParagraphsComponents.get(1));
        Assertions.assertEquals(initialParagraphsComponents.get(2), actualParagraphsComponents.get(2));
    }

    @Test
    void findSentencesWithTheLargestWordsFromText() throws FileUtilException {
        List<Component> actualSentences = textUtil.findSentencesWithTheLargestWordsFromText(textComponent);
        Assertions.assertEquals(expectedSentencesWithTheLargestWordsFromText, actualSentences);
    }

    @Test
    void findAllWordsFromTextAndCountTheirNumber() throws FileUtilException {
        Map<CharSequence, Integer> expectedWordsToTheirNumber = new HashMap<>();
        expectedWordsToTheirNumber.put("Word", 2);
        expectedWordsToTheirNumber.put("Show", 1);
        expectedWordsToTheirNumber.put("doesn't", 2);
        expectedWordsToTheirNumber.put("He", 1);

        Map<CharSequence, Integer> actualWordsToTheirNumber = textUtil.findAllWordsFromTextAndCountTheirNumber(textComponent);

        Assertions.assertEquals(expectedWordsToTheirNumber, actualWordsToTheirNumber);
    }

    @Test
    void countNumberOfConsonantsInSentence() throws FileUtilException {
        Component sentenceComponent = expectedSentencesWithTheLargestWordsFromText.get(0);
        Integer actualNumberOfConsonants = textUtil.countNumberOfConsonantsInSentence(sentenceComponent);
        Assertions.assertEquals(7, actualNumberOfConsonants);
    }

    @Test
    void countNumberOfVowelsInSentence() throws FileUtilException {
        Component sentenceComponent = expectedSentencesWithTheLargestWordsFromText.get(0);
        Integer actualNumberOfVowels = textUtil.countNumberOfVowelsInSentence(sentenceComponent);
        Assertions.assertEquals(3, actualNumberOfVowels);
    }
}