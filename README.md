# information handling task

This is the third project of EPAM Java Web Development training.

Application with parsing text from file using Composite and Chain of Responsibility patterns.

## Task description:

Create an application that parses text from a file and executes with text five different operations:

1. Sort paragraphs by number of sentences.
2. Find sentences with the longest word.
3. Convert text to JSON format String
4. Find in the text all the same words and count their number.
5. Count the number of vowels and consonants in the sentence.

## Requirements:

- #### Parsed text to structure object, contains:
    - paragraphs (4 spaces)
    - sentences
    - lexemes (part of text limited by whitespace characters)
    - words
    - expressions
    - symbols
- #### Use Composite and Chain of Responsibility patterns

- #### Use Log4J2

- #### Code should be covered with tests

## Setup

+ Install Java SE Development Kit 11
+ Install Maven
+ Build the project with maven with the following command :

```
mvn clean install
```

+ Start app

```
java -jar .\target\information_handling-1.0-SNAPSHOT.jar
```