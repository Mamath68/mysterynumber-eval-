/*
 * Exercice 1 : Trouvez comment générer un nombre mystère compris entre 1 et 100.
 * (Voir Classe Math sur la java doc)
 * Comment peut-on tester si le code que l'on a écrit est correct ?
 * <p>
 * Si vous bloquez sur l'exercice 1 et que vous souhaitez passer aux autres exercices :
 * int nombreRandom = 50;
 * Il faudra toutefois principe que le nombre est généré aléatoirement pour les prochains exercices !
 * <p>
 * Exercice 2 : Commenter son code est important ! Commente le code afin de te l'approprier.
 * Exercice 3 : Corrige le jeu ! Celui-ci n’est pas fonctionnel. Tu as tous les éléments dans le code pour comprendre ce qui doit être corrigé.
 * La correction comprend deux modifications algorithmiques et des indications à apporter au joueur pour qu’il comprenne ce qu’il a à faire.
 * Exercice 4 : Ajout de fonctionnalités.
 * La V2 du jeu devra proposer la possibilité de rejouer et de stocker les trois meilleurs scores (le score étant le nombre d’essais).
 * Exercice 5 : Créez les fonctions suivantes et implémentez-les dans votre code :
 * * initNombreMystere
 * * demandeChoixJoueur
 * * isNombreMystereTropPetit
 * * isNombreMystereTropGrand
 * * isNombreMystereTrouvee
 */

import java.util.Scanner;
import java.util.Arrays;

public class Main {
    // Crée un scanner pour lire les entrées de l'utilisateur
    static Scanner scan = new Scanner(System.in);
    // Crée un tableau pour stocker les trois meilleurs scores et le remplit avec la
    // valeur maximale possible
    static int[] meilleursScores = new int[3];
    // Variables pour le nombre aléatoire et l'essai de l'utilisateur
    static int nombreRandom;
    static int essai;

    public static void main(String[] args) {
        // Remplis le tableau des meilleurs scores avec la valeur maximale possible
        Arrays.fill(meilleursScores, Integer.MAX_VALUE);
        // Variable pour contrôler si l'utilisateur veut rejouer
        boolean rejouer = true;

        // Boucle principale du jeu
        while (rejouer) {
            // Initialise le nombre mystère et les essais restants
            initNombreMystere();
            // Définis le nombre d’essais à 5
            int nombreEssais = 5;
            int essaisRestants = nombreEssais;

            // Affiche les messages de bienvenue et les règles du jeu
            System.out.println("BIENVENUE DANS LE JEU DU NOMBRE MYSTÈRE");
            System.out.println("Le jeu consiste à trouver un nombre compris entre 1 et 100.");
            System.out.println("Vous avez 5 essais pour réussir ! Prêt ? C'est parti !");

            // Boucle pour les essais de l'utilisateur
            for (int i = 0; i < nombreEssais; i++) {
                // Demande à l'utilisateur de choisir un nombre
                demandeChoixJoueur();
                // Décrémente le nombre d'essais restants
                essaisRestants--;

                // Vérifie si l'essai est trop petit
                if (isNombreMystereTropPetit()) {
                    System.out.println("Trop petit, il te reste " + essaisRestants + " essais.");
                }
                // Vérifie si l'essai est trop grand
                else if (isNombreMystereTropGrand()) {
                    System.out.println("Trop grand, il te reste " + essaisRestants + " essais.");
                }
                // Si l'essai est correct
                else if (isNombreMystereTrouvee()) {
                    System.out.println("Bravo tu as trouvé le nombre mystère !");
                    break; // Sort de la boucle si le nombre est trouvé
                }
            }

            // Mise à jour des meilleurs scores
            if (essaisRestants < meilleursScores[2]) {
                // Remplace le pire score par le nouveau score
                meilleursScores[2] = essaisRestants;
                // Trie les scores pour garder les trois meilleurs
                Arrays.sort(meilleursScores);
            }

            // Affiche les meilleurs scores
            System.out.println("Meilleurs scores : " + Arrays.toString(meilleursScores));

            // Demande à l'utilisateur s’il veut rejouer
            System.out.println("Voulez-vous rejouer ? (oui/non)");
            String reponse = scan.next(); // Lit la réponse de l'utilisateur
            if (!reponse.equalsIgnoreCase("oui")) {
                // Mets fin à la boucle si l'utilisateur ne veut pas rejouer
                rejouer = false;
                System.out.println("Bien joué, bonne journée!");
            }
        }

        // Ferme le scanner pour éviter les fuites de ressources
        scan.close();
    }

    // Demande à l'utilisateur de choisir un nombre entre 1 et 100
    private static void demandeChoixJoueur() {
        System.out.println("Choisissez un nombre compris entre 1 et 100");
        essai = scan.nextInt();
    }

    // Initialise le nombre mystère avec un nombre aléatoire entre 1 et 100
    private static void initNombreMystere() {
        nombreRandom = (int) (Math.random() * 100) + 1;
    }

    // Vérifie si l'essai est trop petit
    private static boolean isNombreMystereTropPetit() {
        return essai < nombreRandom;
    }

    // Vérifie si l'essai est trop grand
    private static boolean isNombreMystereTropGrand() {
        return essai > nombreRandom;
    }

    // Vérifie si l'essai est correct
    private static boolean isNombreMystereTrouvee() {
        return essai == nombreRandom;
    }
}
