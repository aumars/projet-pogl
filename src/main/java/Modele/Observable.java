package Modele;

import java.util.ArrayList;
import Vue.Observer;

abstract class Observable {
    private ArrayList<Observer> observers;

    public Observable() {
        this.observers = new ArrayList<Observer>();
    }

    public void addObserver(Observer o) {
        this.observers.add(o);
    }

    public void notifyObservers() {
        for (Observer observer : this.observers) {
            observer.metAJourApresAction();
        }
    }
}
