#Projet  ckonsoruV2


Projet réalisé par Dahiez Ulysse et Burdy Simon


Lien du git : https://github.com/DahiezU/ckonsoru.git

## Partie XML réalisé par Dahiez Ulysse :

### AfficherRDV.java :

Cette Class sert à afficher les rendez-vous selon un client, nous avons qu'une seule fonction qui getAllRDV() elle va parcourir les clients selon leurs noms et les afficher s'il est identique à celui en paramètre. Comme dans la plupart des class, nous décomposons et recomposons des dates pour avoir le bon format affiché. Nous pouvons les retrouver garce au nom des noeuds.

### AjouterRDV.java :

Cette class sert à ajouter un rendez- une date donné, un client donné et un vétérinaire donnée. nous allons créer un nœud de rendez-vous et nous allons ajouter chacun de ces trois éléments dans des balises respective

### SupprimerRDV.java :

Cette class sert comme son nom l'indique à supprimer un rendez-vous selon la date et le nom du client, nous allons parcourir les nœuds de rendez vous et lorsque le nom et la date correspondent aux paramètres, nous allons supprimer le nœuds en question.

### Disponibilites.java :

Cette class a été la plus compliquée à mettre en place, au moment où nous avons commencés nous n'avions pas vu dans le sujet, nous l'avons donc conçu nous même, celle ci retourne toute les disponibilitées des vétérinaires d'une journée, dans un premier temps nous allons récupèrerer tout les crenneaux possible de la journée. en suite une fonction sert à lister les rendez-vous pris cette journée. Nous allons au finalement afficher la différence de ces deux listes.

### Ecran.java :

Cette class sert à afficher et à interagir entre l'utilisateur et les différentes classes.

### App.java :

Cette classe sert à lancer le programme.

### FonctionnalitesDAO.java :

Dans cette classe nous pouvons appeler toute les fonctions disponible pour faire fonctionner les fonctionnalites

### ConnexionXmlDAO.Java

Dans cette class, nous avons tout les requis pour connecter le fichier Xml dans les différents fonnctions.

## Métiers

### Client.Java

Cette classe est le métier du client 

### Disponibilite.Java

Cette classe est le métier du Disponibilite 

### RDV.Java

Cette classe est le métier du RDV 

## Partie BDD réalisé par Burdy Simon : 

  - Classe ConnexionBddDAO :
    Cette classe est une DAO faisant le lien en la bdd par l'intermédiaire de ConnexionSingleton et le lien<br>
    entre GestionRdvDao .
    Elle permet de  recuperer les informations de la bases et de les traiter \. <br>
    
  -  Classe ConnexionSingeton  : 
    Cette classe  est un singleton initiant la connexion avec la BDD\. <br>

  -  Classe  GestionAction : 
     Cette classe ecoute les actions de l'utilisateur et appelle l'interface GestionRdvDao 
