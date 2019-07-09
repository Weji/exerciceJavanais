package common.register;

public class Register {

    private final String consonants;
    private final String vowels;

    public Register() {
        this.vowels = "AEIOUYaeiouy";
        this.consonants = "BCDFGHJKLMNPQRSTVWXZbcdfghjklmnpqrstvwxz";
    }

    public boolean isVowel(char c) {
        return vowels.indexOf(c) != -1;
    }

    public boolean isConsonant(char c) {
        return consonants.indexOf(c) != -1;
    }
}
