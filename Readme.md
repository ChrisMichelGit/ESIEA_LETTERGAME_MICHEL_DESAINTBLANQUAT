# TP Architecture Logicielle / Inf4043 - 2017 - Jeux de lettres
# MICHEL Christophe & DE SAINT BLANQUAT Auxane

- Date de rendu : 03/03/2017  
- mlab.cours@gmail.com
- ledoyen.esiea@gmail.com

## Comment rendre son TP

- Envoyer **un mail dont le sujet est : `nom_binome1 | nom_binome2 | url_github`**

```
$ cd LetterGame
$ git remote rm origin
$ git remote add origin <your_git_repository_url>
$ git push -u origin master
```
# Réponses aux questions

Notre jeu LetterGame est jouable en lignes de commandes. </br>

## Choix réalisés quant au sujet

Nous avons utilisé Maven afin de réaliser notre projet afin d'optimiser les tâches réalisées en garantissant le bon ordre de réalisation ainsi que pour gérer les dépendances.</br>

Nous avons décidé de réaliser une **IA** comme extension. La tâche la plus difficile a été de permettre à l'IA de créer des mots avec les lettres du pots commun en faisant des combinaisons.</br>

Nous avons créé un **pot commun** pour l'ensemble des joueurs au sein duquel ils tirent des lettres pour former des mots. </br>
Nous avons utilisé le design pattern **singleton** qui permet de garantir qu'une unique instance d'une classe donnée sera créée et d'offrir un point d'accès universel à cette instance (chaque joueur accède à la même instance).</br>
De façon plus spécifique, nous avons utilisé la méthode du **holder**. Elle repose sur l'utilisation d'une classe interne privée, responsable de l'instanciation de l'instance unique du Singleton.</br>

Nous avons appliqués plusieurs principes SOLID, premièrement la **responsabilité unique**, c'est-à-dire qu'une classe, une fonction ou une méthode doit avoir une unique responsabilité et deuxièmement le principe **Ouvert/fermé** qui indique qu'une classe doit être ouverte à l'extension mais fermée à la modification.</br>

## Architecture de notre jeu

Nos classes sont réparties selon plusieurs packages :</br>

- gameEngine (Lancement, jeu, UtilisateurJeu) -> le jeu en lui même (Lancement, tours des joueurs)
- Joueurs (Joueur, JoueurIA) -> classes des joueurs (mots & lettres)
- ensembleLettresEtMots (Dictionnaire, PotCommun) -> ensemble des lettres pot commmun et dictionnaire</br>

Ayant un problème avec le dictionnaire (que nous n'avons pas fixé), nous avons regroupé tout dans un même package.</br>
Nous n'avons pas réussi à corriger l'erreur...










