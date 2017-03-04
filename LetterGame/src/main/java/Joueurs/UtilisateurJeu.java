package Joueurs;

/* 

 * Jeu de Lettres 4A
 * MICHEL Christophe
 * DE SAINT BLANQUAT Auxane

*/

/** Dictionnaire */

import java.io.IOException;
import java.util.ArrayList;
//import java.util.Random;
import java.util.Scanner;

/*****************************************************/

public class UtilisateurJeu extends Joueur {

    public int id = 1;
	private Scanner scan;

    public UtilisateurJeu() {
        this.mots = new ArrayList<String>();
    }

    /*****************************************************/

    int tourDuJoueur() {

        System.out.println("C'est le tour du joueur");
        instanceDuPot.ajouterDesLettresAuPot(this.prendreLettreDansPot());
        instanceDuPot.ajouterDesLettresAuPot(this.prendreLettreDansPot());

        String lettres = " ";
        for (char item : instanceDuPot.getListeDeLettres()) {
            lettres += " " + new StringBuilder().append(item).toString();
        }

        System.out.println("Lettres dans le pot commun : "+ lettres);

        int choixDuJoueur = this.scan.nextInt(); /* nextInt() : Retourne un pseudo-aléatoire compris entre 0 (compris) et la valeur spécifiée (exclue) tirée de la séquence du générateur de nombres aléatoires */
        
        switch (choixDuJoueur) {

            case 1:
                this.MotsPotCommun();
                break;

            case 2:
                if (this.mots.size()>0) {
                    this.utiliserMotsDuJoueur();
                }else {
                    System.out.println("Vous n'avez pas encore de mots...");
                }
                break;

            case 3:
                this.utiliserMotsAdversaire();
                break;
            default:
                this.MotsPotCommun();

        }
        return this.id;
    }

    /*****************************************************/

    void MotsPotCommun() {

        System.out.println("Essayez de créer un mot avec les lettres");

        scan = new Scanner(System.in);
        String mot = scan.nextLine();

        try {
            String motTeste = instanceDuPot.comparaisonDictionnaire(mot, true);

            if(motTeste == ""){

                System.out.println("Le mot n'existe pas... Vous ne gagnez pas de point avec celui-ci\n");

            } else {
                
                this.mots.add(motTeste);
                System.out.println("Le mot que vous avez créé" + motTeste + "est valide, vous gagnez 1 point\n");
                this.motValide(motTeste);
            }

        } catch (IOException e) {

            e.printStackTrace(); /** Affiche l'exception et l'état de la pile d'exécution au moment de son appel */
        }
    }

    /*****************************************************/

    void utiliserMotsDuJoueur() {

        System.out.println("Vous pouvez utiliser un de vos mots et le compléter avec le pot commun.");
        String mots = " ";

        for (String item : this.mots) {
            mots += item;
            mots += " ";
        }

        System.out.println("Vous souhaitez utiliser un de vos mots ?"+ mots);
        String votreMot = this.scan.next();

        if (!this.mots.contains(votreMot)) {

            System.out.println("Ce n'est pas votre mot... Vous ne pouvez pas faire ça");
            this.utiliserMotsDuJoueur();
        }

        else
        {
            System.out.println("Finissez votre mot à l'aide du pot commun (avec les lettres)");
            String lettresUtilisees = scan.next();

            if (instanceDuPot.comparaisonPotCommun(lettresUtilisees) == true){

                System.out.println("Construisez votre mot..."+ votreMot);

                try {

                    String motRealise = votreMot + lettresUtilisees;
                    System.out.println("Votre mot est le suivant : "+ motRealise);
                    String motResultat = instanceDuPot.comparaisonDictionnaire(motRealise, false);

                    if(motResultat == ""){

                        System.out.println("Votre mot n'existe pas... Vous ne gagnez pas de point\n");

                    } else {
                        
                        this.mots.add(motResultat);
                        System.out.println("Votre mot créé" + motResultat + "est valide, vous pouvez tirer une nouvelle lettre pour la mettre dans le pot commun\n");
                        this.motValide(motResultat);
                    }

                } catch (IOException e) {

                    e.printStackTrace(); /** Affiche l'exception et l'état de la pile d'exécution au moment de son appel */
                }

            } else {

                System.out.println("Utilisez les lettres du pot commun afin de créer vos mots");
                this.utiliserMotsDuJoueur();
            }
        }
    }

    /*****************************************************/

    void utiliserMotsAdversaire() {

        System.out.println("Vous pouvez utiliser un des mots de votre adversaire afin d'en créer un nouveau (à l'aide des lettres du pot commun)");
        String mots = " ";

        for (String item : this.obtenirListeAdversaire()) {
            mots += item;
            mots += " ";
        }

        System.out.println("Quel mot souhaiteriez-vous utiliser ? Attention vous n'avez qu'une seule chance."+ mots);
        String votreMot = this.scan.next();

        if (!this.obtenirListeAdversaire().contains(votreMot)) {

            System.out.println("Vous ne pouvez pas faire ça, ce n'est pas un des mots de votre adversaire");
            this.utiliserMotsAdversaire();

        } else {

            System.out.println("Finissez votre mot à l'aide du pot commun");
            String lettresUtilisees = scan.next();

            if (instanceDuPot.comparaisonPotCommun(lettresUtilisees) == true){

                System.out.println("Construisez votre mot..."+ votreMot);

                try {

                    String motRealise = votreMot + lettresUtilisees;
                    System.out.println("Votre mot est le suivant : "+ motRealise);
                    String motResultat = instanceDuPot.comparaisonDictionnaire(motRealise, false);

                    if(motResultat == ""){

                        System.out.println("Votre mot n'existe pas, vous ne gagnez pas de point...\n");

                    }else {
                        
                        this.mots.add(motResultat);
                        System.out.println("Votre mot" + motResultat + "est valide, vous gagnez un point\n");
                        this.motValide(motResultat);

                    }

                } catch (IOException e) {

                    e.printStackTrace(); /** Affiche l'exception et l'état de la pile d'exécution au moment de son appel */
                }

            } else {
                
                System.out.println("Utilisez les lettres du pot commun afin de créer vos mots");
                this.utiliserMotsDuJoueur();
            }
        }
    }
}

