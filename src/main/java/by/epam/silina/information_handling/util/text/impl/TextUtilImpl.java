package by.epam.silina.information_handling.util.text.impl;

import by.epam.silina.information_handling.exception.FileUtilException;
import by.epam.silina.information_handling.exception.TextUtilException;
import by.epam.silina.information_handling.model.Component;
import by.epam.silina.information_handling.model.SymbolLeaf;
import by.epam.silina.information_handling.model.TextPartComposite;
import by.epam.silina.information_handling.model.TextPartTypeEnum;
import by.epam.silina.information_handling.util.file.FileUtil;
import by.epam.silina.information_handling.util.file.impl.FileUtilImpl;
import by.epam.silina.information_handling.util.finder.*;
import by.epam.silina.information_handling.util.text.TextUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

import static by.epam.silina.information_handling.config.Constant.*;

@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TextUtilImpl implements TextUtil {
    private static final TextUtil instance = new TextUtilImpl();
    private final FileUtil fileUtil = FileUtilImpl.getInstance();

    public static TextUtil getInstance() {
        return instance;
    }

    @Override
    public Component getTextCompositeFromFile(File file) throws TextUtilException {
        Component textComposite = new TextPartComposite(TextPartTypeEnum.TEXT);
        String wholeText;
        try {
            wholeText = fileUtil.readTextFromFile(file);
            TextComponentFinder mainFinder = new ParagraphFinder();
            setChainOfFindersTo(mainFinder);
            mainFinder.find(wholeText, textComposite);
        } catch (FileUtilException e) {
            throw new TextUtilException(e);
        }
        return textComposite;
    }

    @Override
    public String mapComponentToJSONFormatString(Component component) throws TextUtilException {
        String resultJSON = null;
        if (component != null) {
            ObjectMapper mapper = new ObjectMapper();
            try {
                resultJSON = mapper.writeValueAsString(component);
                log.info(JSON_OBJECT, resultJSON);
            } catch (JsonProcessingException e) {
                throw new TextUtilException(e);
            }
        }
        return resultJSON;
    }

    @Override
    public Component sortParagraphsByNumberOfSentencesFromLeastToMost(Component textComponent) throws FileUtilException {
        if (textComponent != null) {
            log.info(ORIGINAL_TEXT, textComponent.getSymbols());
            List<Component> paragraphs = textComponent.getComponentsByType(TextPartTypeEnum.PARAGRAPH);

            paragraphs.sort(Comparator.comparingInt(o -> o.getComponentsByType(TextPartTypeEnum.SENTENCE).size()));
        /* code above is the same that next:
        Collections.sort(paragraphs, new Comparator<Component>() {
            @Override
            public int compare(Component o1, Component o2) {
                if (o1.getComponentsByType(TextPartTypeEnum.SENTENCE).size() < o2.getComponentsByType(TextPartTypeEnum.SENTENCE).size()) {
                    return -1;
                } else if (o1.getComponentsByType(TextPartTypeEnum.SENTENCE).size() > o2.getComponentsByType(TextPartTypeEnum.SENTENCE).size()) {
                    return 1;
                } else {
                    return 0;
                }
            }
        });*/

            Component sortedTextComponent = new TextPartComposite(TextPartTypeEnum.TEXT);
            paragraphs.forEach(el -> {
                sortedTextComponent.add(new SymbolLeaf(' '));
                sortedTextComponent.add(new SymbolLeaf(' '));
                sortedTextComponent.add(new SymbolLeaf(' '));
                sortedTextComponent.add(new SymbolLeaf(' '));
                sortedTextComponent.add(el);
                if (!el.equals(paragraphs.get(paragraphs.size() - 1))) {
                    sortedTextComponent.add(new SymbolLeaf('\r'));
                    sortedTextComponent.add(new SymbolLeaf('\n'));
                }
            });
            log.info(SORTED_PARAGRAPHS_BY_NUMBER_OF_SENTENCES_FROM_LEAST_TO_MOST, sortedTextComponent.getSymbols());
            return sortedTextComponent;
        } else {
            throw new FileUtilException(TEXT_COMPONENT_CANNOT_BE_NULL);
        }
    }

    @Override
    public Component sortParagraphsByNumberOfSentencesFromMostToLeast(Component textComponent) throws FileUtilException {
        if (textComponent != null) {
            log.info(ORIGINAL_TEXT, textComponent.getSymbols());
            List<Component> paragraphs = textComponent.getComponentsByType(TextPartTypeEnum.PARAGRAPH);

            paragraphs.sort((o1, o2) -> Integer.compare(o2.getComponentsByType(TextPartTypeEnum.SENTENCE).size(), o1.getComponentsByType(TextPartTypeEnum.SENTENCE).size()));
            //above is the same that next:
/*            Collections.sort(paragraphs, new Comparator<Component>() {
                @Override
                public int compare(Component o1, Component o2) {
                    if (o1.getComponentsByType(TextPartTypeEnum.SENTENCE).size() > o2.getComponentsByType(TextPartTypeEnum.SENTENCE).size()) {
                        return -1;
                    } else if (o1.getComponentsByType(TextPartTypeEnum.SENTENCE).size() < o2.getComponentsByType(TextPartTypeEnum.SENTENCE).size()) {
                        return 1;
                    } else {
                        return 0;
                    }
                }
            });*/

            Component sortedTextComponent = new TextPartComposite(TextPartTypeEnum.TEXT);
            paragraphs.forEach(el -> {
                sortedTextComponent.add(new SymbolLeaf(' '));
                sortedTextComponent.add(new SymbolLeaf(' '));
                sortedTextComponent.add(new SymbolLeaf(' '));
                sortedTextComponent.add(new SymbolLeaf(' '));
                sortedTextComponent.add(el);
                if (!el.equals(paragraphs.get(paragraphs.size() - 1))) {
                    sortedTextComponent.add(new SymbolLeaf('\r'));
                    sortedTextComponent.add(new SymbolLeaf('\n'));
                }
            });

            log.info(SORTED_PARAGRAPHS_BY_NUMBER_OF_SENTENCES_FROM_MOST_TO_LEAST, sortedTextComponent.getSymbols());
            return sortedTextComponent;
        } else {
            throw new FileUtilException(TEXT_COMPONENT_CANNOT_BE_NULL);
        }
    }

    @Override
    public List<Component> findSentencesWithTheLargestWordsFromText(Component textComponent) throws FileUtilException {
        if (textComponent != null) {
            List<Component> theLargestWordComponents = new ArrayList<>();
            AtomicInteger theLargestWordLength = new AtomicInteger();
            List<Component> sentencesWithTheLargestWord = new ArrayList<>();
            if (TextPartTypeEnum.TEXT.equals(textComponent.getTestEnum())) {

                textComponent.getComponentsByType(TextPartTypeEnum.PARAGRAPH)
                        .stream()
                        .flatMap(el -> el.getComponentsByType(TextPartTypeEnum.SENTENCE).stream())

                        .forEach(sentence -> {
                            List<Component> lexemes = new ArrayList<>(sentence.getComponentsByType(TextPartTypeEnum.LEXEME));
                            List<Component> words = new ArrayList<>();

                            lexemes.forEach(lexeme -> words.addAll(lexeme.getComponentsByType(TextPartTypeEnum.WORD)));

                            words.forEach(word -> {
                                int wordLength = word.getSymbols().length();
                                if (wordLength > theLargestWordLength.get()) {
                                    theLargestWordComponents.clear();
                                    theLargestWordComponents.add(word);
                                    sentencesWithTheLargestWord.clear();
                                    sentencesWithTheLargestWord.add(sentence);
                                    theLargestWordLength.set(wordLength);
                                } else if (wordLength == theLargestWordLength.get()) {
                                    theLargestWordComponents.add(word);
                                    if (!sentencesWithTheLargestWord.contains(sentence)) {
                                        sentencesWithTheLargestWord.add(sentence);
                                    }
                                }
                            });
                        });
            }
            log.info(THE_LARGEST_WORDS, theLargestWordComponents.stream().map(Component::getSymbols).collect(Collectors.toList()));
            log.info(SENTENCES_WITH_THE_LARGEST_WORDS, sentencesWithTheLargestWord.size(), sentencesWithTheLargestWord.stream().map(Component::getSymbols).collect(Collectors.toList()));
            return sentencesWithTheLargestWord;
        } else {
            throw new FileUtilException(TEXT_COMPONENT_CANNOT_BE_NULL);
        }
    }

    @Override
    public Map<CharSequence, Integer> findAllWordsFromTextAndCountTheirNumber(Component textComponent) throws FileUtilException {
        if (textComponent != null) {
            Map<CharSequence, Integer> wordToItsCount = new HashMap<>();

            textComponent.getComponentsByType(TextPartTypeEnum.PARAGRAPH)
                    .stream()
                    .flatMap(el -> el.getComponentsByType(TextPartTypeEnum.SENTENCE).stream())
                    .flatMap(el -> el.getComponentsByType(TextPartTypeEnum.LEXEME).stream())
                    .flatMap(el -> el.getComponentsByType(TextPartTypeEnum.WORD).stream())
                    .map(Component::getSymbols)
                    .forEach(word -> {
                        if (wordToItsCount.containsKey(word)) {
                            wordToItsCount.put(word, wordToItsCount.get(word) + 1);
                        } else {
                            wordToItsCount.put(word, 1);
                        }
                    });
            log.info(WORDS_AND_THEIR_NUMBER, wordToItsCount);
            return wordToItsCount;
        } else {
            throw new FileUtilException(TEXT_COMPONENT_CANNOT_BE_NULL);
        }
    }

    @Override
    public Integer countNumberOfConsonantsInSentence(Component sentenceComponent) throws FileUtilException {
        if (sentenceComponent != null) {
            AtomicReference<Integer> consonantNumber = new AtomicReference<>(0);
            sentenceComponent.getComponentsByType(TextPartTypeEnum.LEXEME)
                    .stream()
                    .flatMap(lexemeComponent -> lexemeComponent.getComponentsByType(TextPartTypeEnum.WORD).stream())
                    .flatMap(wordComponent -> wordComponent.getComponentsByType(TextPartTypeEnum.SYMBOL).stream())
                    .forEach(symbolComponent -> {
                        if (CONSONANTS.indexOf(((SymbolLeaf) symbolComponent).getSingleSymbol()) != -1) {
                            consonantNumber.getAndSet(consonantNumber.get() + 1);
                        }
                    });
            log.info(NUMBER_OF_CONSONANT_IN_SENTENCE, sentenceComponent.getSymbols(), consonantNumber);
            return consonantNumber.get();
        } else {
            throw new FileUtilException(TEXT_COMPONENT_CANNOT_BE_NULL);
        }
    }

    @Override
    public Integer countNumberOfVowelsInSentence(Component sentenceComponent) throws FileUtilException {
        if (sentenceComponent != null) {
            AtomicReference<Integer> vowelsNumber = new AtomicReference<>(0);

            sentenceComponent.getComponentsByType(TextPartTypeEnum.LEXEME)
                    .stream()
                    .flatMap(lexemeComponent -> lexemeComponent.getComponentsByType(TextPartTypeEnum.WORD).stream())
                    .flatMap(wordComponent -> wordComponent.getComponentsByType(TextPartTypeEnum.SYMBOL).stream())
                    .forEach(symbolComponent -> {
                        if (VOWELS.indexOf(((SymbolLeaf) symbolComponent).getSingleSymbol()) != -1) {
                            vowelsNumber.getAndSet(vowelsNumber.get() + 1);
                        }
                    });
            log.info(NUMBER_OF_VOWELS_IN_SENTENCE, sentenceComponent.getSymbols(), vowelsNumber);

            return vowelsNumber.get();
        } else {
            throw new FileUtilException(TEXT_COMPONENT_CANNOT_BE_NULL);
        }
    }

    @Override
    public void showNumberOfConsonantsAndVowelsInAllSentencesOfText(Component textComponent) throws FileUtilException {
        if (textComponent != null) {
            textComponent.getComponentsByType(TextPartTypeEnum.PARAGRAPH)
                    .stream()
                    .flatMap(paragraphComponent -> paragraphComponent.getComponentsByType(TextPartTypeEnum.SENTENCE).stream())
                    .forEach(sentenceComponent -> {
                        try {
                            countNumberOfConsonantsInSentence(sentenceComponent);
                        } catch (FileUtilException e) {
                            log.error("", e);
                        }
                        try {
                            countNumberOfVowelsInSentence(sentenceComponent);
                        } catch (FileUtilException e) {
                            log.error("", e);
                        }
                    });
        } else {
            throw new FileUtilException(TEXT_COMPONENT_CANNOT_BE_NULL);
        }
    }

    private void setChainOfFindersTo(TextComponentFinder mainFinder) {
        ParagraphFinder paragraphFinder = new ParagraphFinder();
        SentenceFinder sentenceFinder = new SentenceFinder();
        LexemeFinder lexemeFinder = new LexemeFinder();
        WordAndExpressionFinder wordFinder = new WordAndExpressionFinder();
        SymbolFinder symbolFinder = new SymbolFinder();

        mainFinder.setNextFinder(paragraphFinder);
        paragraphFinder.setNextFinder(sentenceFinder);
        sentenceFinder.setNextFinder(lexemeFinder);
        lexemeFinder.setNextFinder(wordFinder);
        wordFinder.setNextFinder(symbolFinder);
    }
}
