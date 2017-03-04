package Joueurs;

/* 

 * Jeu de Lettres 4A
 * MICHEL Christophe
 * DE SAINT BLANQUAT Auxane

*/

/** Dictionnaire */

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Dictionary;

/*****************************************************/

public class Dictionnaire {
	
	public Dictionnaire() {}

    public BufferedReader OpenFile() {
        String fichierDico = "src/main/resources/dico.txt"; /** Récupération du dico */

        BufferedReader bufferReader = null;

        try {
            InputStream inputStream = new FileInputStream(fichierDico);
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            bufferReader = new BufferedReader(inputStreamReader);
        }
        catch (Exception e) {
            System.out.println(e.toString());
        }

        return bufferReader;
    }

    /*****************************************************/

    public String motCorrespondance(String mot) throws IOException {

        BufferedReader bufferReader = this.OpenFile();
        String ligne;

        do {
            ligne = bufferReader.readLine();
            if (ligne.equals(mot) || ligne.equals(null)) {
                bufferReader.close();
                return ligne;
            }

        } while (bufferReader.ready());

        return "";
    }

    /*****************************************************/

    public boolean motPresent(String mot) throws IOException {

        BufferedReader bufferReader = this.OpenFile();
        String ligne;

        do {
            ligne = bufferReader.readLine();
            if (ligne.startsWith(mot)) { /* startsWith() renvoie un booléen indiquant si la chaîne de caractères commence par la deuxième chaîne de caractères fournie en argument. */
                bufferReader.close();
                return true;
            }

        } while (bufferReader.ready());

        return false;
    }
}

