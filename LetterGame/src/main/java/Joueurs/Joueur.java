package Joueurs;

/* 

 * Jeu de Lettres 4A
 * MICHEL Christophe
 * DE SAINT BLANQUAT Auxane

*/

/** Joueur */

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public abstract class Joueur {

    protected ArrayList<String> mots;
    protected PotCommun instanceDuPot = PotCommun.getInstance();
    protected Scanner scan = new Scanner(System.in);
    protected ArrayList<String> listeMotsAdversaire;

    /*****************************************************/

    protected char prendreLettreDansPot() {

        Random aleatoire = new Random();
        char lettreAleatoire = (char)(aleatoire.nextInt(26) + 'a');
        return lettreAleatoire;
    }

    abstract int tourDuJoueur();

    /*****************************************************/

    void definitionListeAdversaire(ArrayList<String> listeAdversaire){

        this.listeMotsAdversaire = listeAdversaire;
    }

    /*****************************************************/

    ArrayList<String> obtenirListeAdversaire(){

        return this.listeMotsAdversaire;
    }

    /*****************************************************/

    void motValide(String motTeste) {

        ArrayList<Character> motConversionChar = new ArrayList<Character>();
        for (char caractere : motTeste.toCharArray()) {
            motConversionChar.add(caractere);
        }

        for (Character item : motConversionChar) {
            if (instanceDuPot.getListeDeLettres().contains(item)) {
                instanceDuPot.getListeDeLettres().remove(item);
            }
        }

        for (Character valeurCaractere : instanceDuPot.getListeDeLettres()) {
            System.out.println("Valeur du caractere = " + valeurCaractere);
        }

        instanceDuPot.ajouterDesLettresAuPot(this.prendreLettreDansPot());
        
        if(this.mots.size() < 10){
            this.tourDuJoueur();
        } else {
            return;
        }
    }
}

