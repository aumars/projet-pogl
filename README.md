@startuml

title Classes - Class Diagram


class Modele {
  +Int largeur
  +Int longueur
  +initialise()
  +tourSuivant()
  +verifieGagnant()
}

class Grille {
  +metAJour()
  +inondeAleatoirement()
}

class Personnage {
  +Boolean estVivant
  +Int * Int position
  +Boolean aTresor
  +Boolean aGagner
  +seDeplace()
  +meurt()
}

class Case{
  +Personnage personnage
  +Boolean helicopter
  +Boolean tresor
  +Boolean etat
  +inonde()
}

class Controller{
  +demarrer()
  +tourSuivant()
  +seDeplacer()
}

class Vue{}
class VueGrille{
  +afficheGrille()
  
}
class VueCase{
  +afficheCase()
}
class VueCommande{}

Modele -> Grille
Modele -> Personnage
Grille -> Case
Grille -> Personnage

Vue -> VueGrille
VueGrille -> VueCase
Vue -> VueCommande


@enduml