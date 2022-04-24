package Modele;

import java.util.ArrayList;
import java.util.List;

import Vue.Observer;

/**
 * Une entité observable par des observers.
 */
abstract class Observable {
    /**
     * La liste d'observers qui peut accéder cette entité observable.
     */
    private final List<Observer> OBSERVERS;

    /**
     * Construit une nouvelle entité observable.
     */
    public Observable() {
        this.OBSERVERS = new ArrayList<>();
    }

    /**
     * Ajoute un nouveau observer.
     * @param o Un observer
     */
    public void addObserver(Observer o) {
        this.OBSERVERS.add(o);
    }
}
