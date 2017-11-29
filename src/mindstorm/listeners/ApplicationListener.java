package mindstorm.listeners;

/**
 * Ecouteur
 *
 * @see mindstorm.Application
 * @see mindstorm.Engine
 */
public abstract class ApplicationListener {

    /**
     * début du comportement. appelé lors du lancement du comptement
     */
    public abstract void start();

    /**
     * actualisation du comportement. tourne en boucle tant que isRunning est vrai
     */
    public abstract void act();

    /**
     * arret du comportement. appelé quand isRunning passe à faux
     */
    public abstract void end();

    /**
     * fonction permettant l'arret du comportement
     *
     * @return boolean
     */
    public abstract boolean isRunning();
}