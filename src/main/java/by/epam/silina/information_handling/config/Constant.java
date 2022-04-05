package by.epam.silina.information_handling.config;

import lombok.experimental.UtilityClass;

@UtilityClass
public class Constant {
    public static final String TEXT1_TXT_PATH = "src/main/resources/text1.txt";
    public static final String TEXT2_TXT_PATH = "src/main/resources/text2.txt";
    public static final String TEXT3_TXT_PATH = "src/main/resources/text3.txt";

    public static final String FILE_IS_EXISTS = "File=\"{}\" exists";
    public static final String FILE_DOES_NOT_EXIST = "File=\"{}\" doesn't exist";
    public static final String BEGINNING_OF_THE_FILE_DELIMITER = "\\A";
    public static final String SCANNED_TEXT_FROM_FILE = "Scanned text from file=\"{}\":\r\n{}";
    public static final String JSON_OBJECT = "JSON object:\r\n{}";

    public static final String LEXEME_FINDER_REGEX = "(?<beginSpace> ?)(?<lexeme>[^ ]*)(?<endSpace> )?";
    public static final String PARAGRAPH_FINDER_REGEX = "(?<paragraphMark> {4})(?<textOfParagraph>.*)(?<newLine>\r\n)?";
    public static final String SENTENCE_FINDER_REGEX = "(\\s)*(?<sentence>.*?[?!.])(?<separator>\\s|$)";
    public static final String WORD_AND_PUNCTUATION_FINDER_REGEX = "(((?<openBracket>[(])?(?<word>[a-zA-Z\\-']+)(?<closeBracket>[)])?)|(?<expression>\\(?[a-zA-Z0-9^&><|()~]*\\)?))(?<endPunctuation>[?!\\.,]?)";
    public static final String SENTENCE = "sentence";
    public static final String SEPARATOR = "separator";
    public static final String PARAGRAPH_MARK = "paragraphMark";
    public static final String TEXT_OF_PARAGRAPH = "textOfParagraph";
    public static final String NEW_LINE = "newLine";
    public static final String OPEN_BRACKET = "openBracket";
    public static final String WORD = "word";
    public static final String CLOSE_BRACKET = "closeBracket";
    public static final String EXPRESSION = "expression";
    public static final String END_PUNCTUATION = "endPunctuation";
    public static final String BEGIN_SPACE = "beginSpace";
    public static final String LEXEME = "lexeme";
    public static final String END_SPACE = "endSpace";

    public static final String ORIGINAL_TEXT = "Original text:\r\n{}";
    public static final String SORTED_PARAGRAPHS_BY_NUMBER_OF_SENTENCES_FROM_LEAST_TO_MOST = "Sorted paragraphs by number of sentences from least to most:\r\n{}";
    public static final String SORTED_PARAGRAPHS_BY_NUMBER_OF_SENTENCES_FROM_MOST_TO_LEAST = "Sorted paragraphs by number of sentences from most to least:\r\n{}";
    public static final String THE_LARGEST_WORDS = "The largest words: {}";
    public static final String SENTENCES_WITH_THE_LARGEST_WORDS = "Sentences with the largest words({} sentences): {}";
    public static final String WORDS_AND_THEIR_NUMBER = "Words and their number: {}";
    public static final String NUMBER_OF_CONSONANT_IN_SENTENCE = "Sentence: \"{}\" Number of consonants in sentence={}";
    public static final String NUMBER_OF_VOWELS_IN_SENTENCE = "Sentence: \"{}\" Number of vowels in sentence={}";
    public static final String VOWELS = "aeiouAEIOU";
    public static final String CONSONANTS = "bcdfghjklmnpqrstvwxyzBCDFGHJKLMNPQRSTVWXYZ";

    public static final String TEXT_COMPONENT_CANNOT_BE_NULL = "Text component cannot be null";
    public static final String ADD_DOING_NOTHING = "SymbolLeaf -> add(). Doing nothing";
    public static final String REMOVE_DOING_NOTHING = "SymbolLeaf -> remove(). Doing nothing";
}
