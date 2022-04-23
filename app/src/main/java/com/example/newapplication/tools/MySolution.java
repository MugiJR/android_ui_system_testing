package com.example.newapplication.tools;

public class MySolution {

    String word;
    int charIndex;

    public MySolution(String word, int charIndex) {
        this.word = word;
        this.charIndex = charIndex;
    }

    public String findSuffix() throws Exception {
        int wordLength = this.word.length();

        if (wordLength < charIndex) throw new Exception("Character index can't be greater than input1 length");
        else if (charIndex <= 0) throw new Exception("Character index must be greater than 0");

        return this.word.substring(wordLength - this.charIndex);

    }
}
