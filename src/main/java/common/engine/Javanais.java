package common.engine;

import common.register.Register;

import java.util.ArrayList;

public class Javanais implements IJavanais{

    private Register register;
    private final String charsToAdd = "av";

    public Javanais(Register register) {
        this.register = register;
    }

    public String javanise(String word) {
        StringBuilder newWord = new StringBuilder();

        for (int i = 0; i < word.length(); i++) {
            char character = word.charAt(i);
            if(i == 0 && register.isVowel(character)){
                newWord.append(charsToAdd);
                newWord.append(character);
            } else {
                newWord.append(character);
                if((i - word.length()) != -1){
                    if(register.isConsonant(character) && register.isVowel(word.charAt(i+1))){
                        newWord.append(charsToAdd);
                    }
                }
            }
        }

        return newWord.toString();
    }

    public String unJavanise(String word) {
        StringBuilder result = new StringBuilder();

        if(word.length() < 3){
            return word;
        }

        if(word.indexOf(charsToAdd) == 0 && register.isVowel(word.charAt(charsToAdd.length()))){
            word = word.substring(charsToAdd.length());
        }

        for (int i = 0; i < word.length(); i++) {
            char character = word.charAt(i);
            result.append(character);

            if (i < word.length() - (charsToAdd.length() + 1) && !register.isVowel(character) && register.isVowel(word.charAt(i + (charsToAdd.length() + 1)))){
                if(containTheParasiteWord(word, i)){
                    i += charsToAdd.length();
                }
            }

        }

        return result.toString();
    }

    private boolean containTheParasiteWord(String word, int startingIndex){
        for (int y = 0; y < charsToAdd.length(); y++) {
            char character = charsToAdd.charAt(y);
            if (word.charAt(startingIndex + (y + 1)) != character){
                return false;
            }
        }
        return true;
    }

    private String formatSentence(ArrayList<String> newWords){
        StringBuilder sentence = new StringBuilder();
        for (String word: newWords) {
            sentence.append(word.concat(" "));
        }

        sentence.deleteCharAt(sentence.length()-1);

        return sentence.toString();
    }

    public String transform(String s, boolean toJavanise) {
        if(s != null && !s.equals("")){
            String[] words = s.split(" ");
            ArrayList<String> newWords = new ArrayList<String>();
            for(String word : words){
                if(toJavanise){
                    newWords.add(javanise(word));
                } else {
                    newWords.add(unJavanise(word));
                }
            }

            return formatSentence(newWords);

        } else {
            return "";
        }
    }
}
