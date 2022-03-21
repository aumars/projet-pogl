@startuml

title Classes - Class Diagram


class Modele {
  +Int largeur
  +Int longueur
}

class Grille {}

class Personnage {
  +Boolean estVivant
  +Int * Int position
  +Boolean aTresor
  +Boolean aGagner
}

class Case{
  +Personnage personnage
  +Boolean helicopter
  +Boolean tresor
  +Boolean etat
}

class Controller{
  +demarrer()
  +tourSuivant()
  +seDeplacer()
}

class Vue{}
class VueGrille{}
class VueCommande{}

Modele -> Grille
Modele -> Personnage
Grille -> Case
Grille -> Personnage

Vue -> VueGrille
Vue -> VueCommande


@enduml