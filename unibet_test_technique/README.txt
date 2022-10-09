
PREREQUIS:
- AVOIR UNE VERSION DE JAVA >= 8 

DANS LE FICHIER application.properties AJOUTER(CONTROLER) LA VALEUR DU PARAM
- parameter.timer.cron.payer=0/5 * * * * *


COMMENT LANCER L'APP:
Lancer comme tout projet maven


Si tu as maven installé tu peux lancer l'appalicaion par la ligne de commande:
se placer dans le repertoire src et jouer les commandes
 - mvn install 
 - java -jar target/unibet-live-test-0.0.1-SNAPSHOT.jar 

ouvrir postman ou le navigateur et essayer les APIs suivantes:

* Récupère tous les événements de la base. api/v1/events?isLive=true
 	http://localhost:8887/api/v1/events
 	http://localhost:8887/api/v1/events?isLive=true

* Récupère toutes les sélections d'un événément: api/v1/events/{id}/selections?{state}=state
 	http://localhost:8887/api/v1/events/1/selections
 	http://localhost:8887/api/v1/events/1/selections?state=OPENED
 	http://localhost:8887/api/v1/events/1/selections?state=CLOSED

* Enregistre un pari. api/v1/bets/add
	http://localhost:8887/api/v1/bets/add
	POST, 
	BODY : {
   		"selectionId" : "5",
   		"cote" : 4.4,
   		"mise" : 1
	}

* la batch tourne tout seul 
