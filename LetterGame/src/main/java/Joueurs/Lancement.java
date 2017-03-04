package Joueurs;

/* 

 * Jeu de Lettres 4A
 * MICHEL Christophe
 * DE SAINT BLANQUAT Auxane

*/

/** Lancement du jeu */

public class Lancement {
    private PotCommun instanceDuPot;

    public Lancement() {

        this.instanceDuPot = PotCommun.getInstance();
    }

    public static void main(String[] args) {

        System.out.println("Lancement du jeu Letter Game - 4A - MICHEL Christophe & DE SAINT BLANQUAT Auxane\n");
        Jeu jeu = new Jeu();
    }
}

