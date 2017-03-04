package Joueurs;

/* 

 * Jeu de Lettres 4A
 * MICHEL Christophe
 * DE SAINT BLANQUAT Auxane

*/

/** Jeu Letter Game */

/*****************************************************/
public class Jeu {
    private PotCommun instanceDuPot;
    private Joueur utilisateur;
    private Joueur IA;

    public Jeu() {
        this.instanceDuPot = PotCommun.getInstance();
        
        this.utilisateur = new UtilisateurJeu();
        this.IA = new JoueurIA();

        
        this.jeuEnCours();
    }

    /*****************************************************/

    public void jeuEnCours() {

        Joueur joueur = this.premierTour();
        int idJoueur = joueur.tourDuJoueur();
        
        while(utilisateur.mots.size()<10 || IA.mots.size()<10) {

            System.out.println("Score de l'IA : "+ IA.mots.size());
            System.out.println("Score du joueur : "+ utilisateur.mots.size());

            if (idJoueur == 2){
                utilisateur.definitionListeAdversaire(IA.mots);
                idJoueur = utilisateur.tourDuJoueur();

            }else {
                utilisateur.definitionListeAdversaire(utilisateur.mots);
                idJoueur = IA.tourDuJoueur();
            }
        }

        String lettres = " ";

        for (String item : utilisateur.mots) {
            lettres += " " + item;
        }
        System.out.println(lettres+"\n");
    }

    /*****************************************************/

    public Joueur premierTour() {

        System.out.println("Premier Tour\n");

        char lettre = this.utilisateur.prendreLettreDansPot();
        instanceDuPot.ajouterDesLettresAuPot(lettre);

        char LettreIA = this.IA.prendreLettreDansPot();
        instanceDuPot.ajouterDesLettresAuPot(LettreIA);

        Joueur gagnant = this.utilisateur;
        
        if(lettre > LettreIA) {
            gagnant = this.IA;
        } else {
            gagnant = this.utilisateur;
        }
        return gagnant;
    }
}

