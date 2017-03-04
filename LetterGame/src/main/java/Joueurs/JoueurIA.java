package Joueurs;

/* 

 * Jeu de Lettres 4A
 * MICHEL Christophe
 * DE SAINT BLANQUAT Auxane

*/

/** Joueur IA */

import java.io.IOException;
import java.util.ArrayList;

/*****************************************************/

public class JoueurIA extends Joueur{

    public int id = 2;

    public JoueurIA() {

        this.mots = new ArrayList<String>();
        this.mots.add("a");
        this.mots.add("aa");
        this.mots.add("loup");
        this.mots.add("loupe");
    }

    /*****************************************************/

    public int tourDuJoueur() {

        System.out.println("Tour de l'IA");

        instanceDuPot.ajouterDesLettresAuPot(this.prendreLettreDansPot());
        instanceDuPot.ajouterDesLettresAuPot(this.prendreLettreDansPot());

        String lettres = " ";

        for (char item : instanceDuPot.getListeDeLettres()) {
            lettres += " " + new StringBuilder().append(item).toString();
        }

        System.out.println("Actuellement, il y a ces lettres dans le pot commun : "+ lettres);
        System.out.println("L'intelligence artificielle réfléchit à un mot");

        System.out.println("On compare le mot au pot commun\n");
        String motTeste = creerUnMot();
            
        if(motTeste == "") {

            System.out.println("L'intelligence artificielle n'a pas trouvé de mot ! Et vous ?\n");

        }else {

            this.mots.add(motTeste);
            System.out.println("Le mot de l'IA" + motTeste + " se trouve dans le dico, elle gagne 1 point.");
            System.out.println("L'intelligence artificielle tire une nouvelle lettre dans ce cas\n");
            this.motValide(motTeste);
        }

        return this.id;
    }

    /*****************************************************/

    public String creerUnMot() {

        String motTeste = "";
        int pf = 1;
        ArrayList<Integer> motsUtilises = new ArrayList<Integer>();
        int index;

        for(int i=0; i< this.instanceDuPot.getListeDeLettres().size(); i++){

            motsUtilises.add(i);
            index = i;
            motTeste = this.instanceDuPot.getListeDeLettres().get(i).toString();

            for(int j=0; j<this.instanceDuPot.getListeDeLettres().size(); j++){

                if(motsUtilises.contains(j)){
                    break;

                } else {

                    motsUtilises.add(j);
                    motTeste = motTeste + this.instanceDuPot.getListeDeLettres().get(j).toString();

                    try {

                        if(this.instanceDuPot.motContenuDansDico(motTeste) == false){
                            motsUtilises.remove(j);
                            break;

                        } else {

                            if(this.instanceDuPot.comparaisonDictionnaire(motTeste, false) == motTeste){
                                return motTeste;
                            } else {
                                pf = pf + 1;
                                j=-1;
                            }
                        }

                    } catch (IOException e) {
                        e.printStackTrace(); /** Affiche l'exception et l'état de la pile d'exécution au moment de son appel */
                    }
                }
            }
        }



        String mot = "";
        ArrayList<Integer> position = new ArrayList<Integer>();
        int longueurDuMot = mot.length();

        System.out.println("Pot Commun - Taille : " +this.instanceDuPot.getListeDeLettres().size());

        for(int i = 0; i < this.instanceDuPot.getListeDeLettres().size(); i++) {

            System.out.println("i : " +i);
            System.out.println("Ajout index : " +i);
            position.add(i);
            mot = this.instanceDuPot.getListeDeLettres().get(i).toString();

            for(int j = 0; j < this.instanceDuPot.getListeDeLettres().size(); j++)
            {

                System.out.println("Index - Lettre complémentaire testée" +j);

                if(position.contains(j)){
                    
                    if(j+1 < this.instanceDuPot.getListeDeLettres().size()){
                        System.out.println("Valeur de j : " +j);
                        
                        j++;
                        System.out.println("j+i : " +j);
                    }

                    if(j+1 == this.instanceDuPot.getListeDeLettres().size()){
                        System.out.println("j+1 = Taille du pot commun");
                        break;
                    }
                }

                String item = this.instanceDuPot.getListeDeLettres().get(j).toString();
                mot = mot + item;
                System.out.println("On compare la combinaison..." +mot);

                try {

                    if(this.instanceDuPot.motContenuDansDico(mot) == false){ //si le mot n'est pas contenu dans dictionnaire
                        System.out.println("Mot non présent ! La valeur de j est : " +j);
                        mot = mot.substring(0, mot.length()-1);

                    } else {

                        System.out.println("Mot présent !");
                        System.out.println("Ajout index : "+j);
                        position.add(j);
                        j = 0;

                        if(this.instanceDuPot.comparaisonDictionnaire(mot, false) == mot){ //si le mot == dictionnaire
                            return mot;
                        }
                    }

                } catch (IOException e) {

                    e.printStackTrace(); /** Affiche l'exception et l'état de la pile d'exécution au moment de son appel */
                }
            }

            position.remove(i);
        }

        return "";
    }
}

