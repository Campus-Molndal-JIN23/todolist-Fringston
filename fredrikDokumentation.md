# Fredrik Rinstad

## Egna reflektioner
Jag uppskattar att denna utmaning har tydliga instruktioner så att man vet vad som förväntas av en.
Den verkar dock väldigt stor och tidskrävande och jag vet inte riktigt hur jag ska börja. Men det känns ändå roligt på grund av tydligheten.


## Projektet

### Beskrivning av projektet
Projektet är ett program som hanterar TODOs (att-göra-uppgifter).
CRUD-funktionalitet finns för alla TODOs. Man kan skapa nya TODOs, se de som redan finns samt uppdatera och ta bort.
Alla att-göra-uppgifterna har ett id, information om vad att-göra-uppgiften är och information om den är utförd eller ej. 
Att-göra-uppgifterna sparas i en MongoDB-databas.

Allt detta görs via ett CLI (Command Line Interface).

### Vad du har gjort

## Planering
Det blev ingen bra planering inför detta projekt. 
Jag visste helt enkelt inte hur jag skulle göra så jag var tvungen att börja skapa klasser och metoder och allt eftersom så förstod jag hur det skulle se ut.

### Lösningsförslag innan uppgiften påbörjas

#### Hur du tänker försöka lösa uppgiften.(exempelvis)
Jag har svårt för att börja med projekt. Jag brukar behöver börja med att skapa lite klasser och koda lite smått för att
få en tydligare bild över vad jag behöver göra. Så jag vet inte riktigt hur jag ska förklara hur jag tänker lösa denna uppgift.

Jag vet åtminstone att jag ska ha en klass som heter Todo där egenskaperna för varje todo finns.
Men vilka ytterligare klasser som behövs vet jag inte riktigt än. Jag vet inte på rak arm hur man gör när man ska kommunicera med databasen.
Jag måste gå igenom mina anteckningar och söka runt lite på nätet.

Jag ska i alla fall använda mig av MongoDB och det lär ju då finnas en klass som kommunicerar med databasen.
Och kanske en klass för att läsa connectionsträngen.

#### Diagram.(exempelvis)
Jag skapade ett UML-diagram för att få en tydligare bild över hur jag ska lösa uppgiften. 
Men jag visste inte på förhand vilka klasser och metoder som skulle behövas så det blev inte så detaljerat.

### Jira/Trello/Github Project och projekthantering enligt Scrum/Kanban
Jag har skapat ett projekt på Github som heter TodoApp. Där finns en todo-lista med alla uppgifter som ska göras.
Den har dock fyllts på kontinuerligt under projektets gång då jag inte hade en tydlig bild över allt som behövdes göras från början.

## Arbetet och dess genomförande
Jag började med att göra ToDo-klassen. Där skapade jag egenskaperna för varje todo.
Sedan fokuserade jag på att skapa kopplingen med databasen i Keyreader-klassen och MongoDBFacade-klassen. 
Jag fick det till slut att fungera med lite hjälp från Marcus.
Sedan skapade jag metoderna i MongoDBFacade-klassen som kommunicerar med databasen.

Jag hade missat att en meny skulle finnas så det gjorde jag efter metoderna i MongoDBFacade-klassen var klara.


### Vad som varit svårt

### Beskriv lite olika lösningar du gjort

### Beskriv något som var besvärligt att få till

### Beskriv om du fått byta lösning och varför i sådana fall

## Reflektion & Slutsatser

### Vad gick bra

### Vad gick dåligt

### Vad har du lärt dig

### Vad hade ni gjort annorlunda om ni gjort om projektet

### Vilka möjligheter ser du med de kunskaper du fått under kursen.
