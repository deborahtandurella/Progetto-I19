# Progetto-I19

## Gestione automatizzata ordinazioni

Realizzazione di un programma che gestisca le ordinazioni di un ristorante e i relativi pagamenti da parte del cliente, permettendone una maggiore automazione.

# Come avviare

* Di default l'installazione e l'esecuzione del backend non è obbligatorio in quanto è stato fatto il deploy su una VPS sull'indirizzo 137.74.165.12 e il client in automatico si collega ad essa. 

## Front-end
Attenzione: per il corretto funzionamento è necessario Java 8 e assicurarsi di installare le dipendenze dal file pom.xml
1. Compilare LauncherClient, LauncherCucinaCaffetteria. 
2. Avviare LauncherClient e LauncherCucinaCaffetteria nell'ordine preferito. 
3. All'apertura del Client, inserire il numero del tavolo. 
4. All'apertura della CucinaCaffetteria, selezionare se è un terminale di Cucina o Caffetteria. 
## Back-end

1. Spostarsi nella cartella backend.
2. Installare pip (https://pip.pypa.io/en/stable/installing/) oppure pipenv (https://github.com/pypa/pipenv).
4. Lanciare il comando pipenv install per creare la virtualenv.
3. Utilizzare pipenv shell per attivare la virtualenv.
3. Spostarsi nella cartella backend/i19_backend.
4. Lanciare : python manage.py runserver (per lanciare la versione produzione).
5. Lanciare : python manage.py runserver --settings=setting_test (per lanciare la versione con il database di test).
