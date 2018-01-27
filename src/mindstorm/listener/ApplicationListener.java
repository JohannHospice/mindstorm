package mindstorm.listener;

/**
 * Ecouteur
 *
 * @see mindstorm.Application
 */
public interface ApplicationListener {

    /**
     * début du comportement. appelé lors du lancement du comptement
     */
    void start();

    /**
     * actualisation du comportement. tourne en boucle tant que isRunning est vrai
     */
    void act();

    /**
     * arret du comportement. appelé quand isRunning passe à faux
     */
    void end();

    /**
     * fonction permettant l'arret du comportement
     */
    boolean isRunning();
}