package common;

import common.engine.Javanais;
import common.register.Register;

public class Main {
    public static void main(String[] args){
        Javanais javanais = new Javanais(new Register());
        String sentenceJavanised = javanais.transform("avancer", true);
        String originalSentence = javanais.transform(sentenceJavanised, false);

        System.out.println("Javanais : " + sentenceJavanised + " -> FR : " + originalSentence);
    }
}
