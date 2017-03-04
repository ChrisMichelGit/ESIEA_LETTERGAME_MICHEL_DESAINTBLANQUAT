package Joueurs;

/* 

 * Jeu de Lettres 4A
 * MICHEL Christophe
 * DE SAINT BLANQUAT Auxane

*/

/** Pot Commun */

import gameEngine.*;
import Joueurs.*;

import java.text.Normalizer;
import java.util.regex.Pattern;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Dictionary;

import java.util.Dictionary;

public class PotCommun {

    /* Création du pot commun */

    private ArrayList<Character> listeDeLettres;
    private Dictionary dictionnaire;

    /** Constructeur privé */
    private PotCommun() {

        this.listeDeLettres = new ArrayList<Character>();
        this.dictionnaire = new Dictionary();

    }

    /*****************************************************/

    /* http://thecodersbreakfast.net/index.php?post/2008/02/25/26-de-la-bonne-implementation-du-singleton-en-java */
    private static class HolderPotCommun { /* Technique du holder */
        
        private final static PotCommun instance = new PotCommun();
    }

    /*****************************************************/

    public ArrayList<Character> getListeDeLettres() { /* Récupération des lettres */

        return listeDeLettres;
    }

    /*****************************************************/

    /** Point d'accès unique pour l'instance unique du singleton */
    public static PotCommun getInstance() {

        return HolderPotCommun.instance;
    }

    /*****************************************************/

    public void ajouterDesLettresAuPot(char mot) {
        this.listeDeLettres.add(mot);
    }

    /*****************************************************/

    /* Comparaison des mots avec le dictionnaire */
    public String comparaisonDictionnaire(String mot, Boolean check) throws IOException {
        /* Lors du jeu, on ne tire pas de é mais que des e */
        String conversionMot = this.convertAccentLettersToEnglishLetters(mot);

        if (check == true) { /* Si le mot sans accent existe */
            boolean flag = comparaisonPotCommun(conversionMot);

            if (flag) {
                System.out.println("Il y a ce mot : " + conversionMot); /* Le mot existe dans le dico */
                return dictionnaire.motCorrespondance(conversionMot);
            } else {

                return ""; /* Le mot n'existe pas */
            }

        } else {

            return dictionnaire.motCorrespondance(conversionMot);
        }
    }

    /*****************************************************/

    public boolean motContenuDansDico(String mot) throws IOException {
        String conversionMot = this.convertAccentLettersToEnglishLetters(mot);
        return dictionnaire.motPresent(mot);
    }

    /*****************************************************/

    public boolean comparaisonPotCommun(String mot) {

        ArrayList<Character> motConversionChar = new ArrayList<Character>();

        for (char caractere : mot.toCharArray()) {
            motConversionChar.add(caractere);
        }

        boolean flag = false;
        
        for (Character item : motConversionChar) {
            System.out.println(this.getListeDeLettres().contains(item));

            if (this.getListeDeLettres().contains(item)) {
                flag = true;
            }else {
                flag = false;
            }

        }
        return flag;
    }

    /*****************************************************/

    /** Pattern Regex */
    /** Convertion des symboles et lettres accentuées vers l'alphabet Anglais */
    public String convertAccentLettersToEnglishLetters(String str) {
        String nfdNormalizedString = Normalizer.normalize(str, Normalizer.Form.NFD);
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        return pattern.matcher(nfdNormalizedString).replaceAll("");
    }

}

