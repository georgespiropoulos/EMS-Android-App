# EMS-Android-App
A smart android application for the Emergency Medical Service of Greece

## Database schema
Description of the database

- Table **User**

|PK|Field Name|Type|
|--|----------|----|
|PK|user_id|int auto_increment|
|  |user_name|varchar()|

- Table **Hospitals**

|PK|Field Name|Type|
|--|----------|----|
|PK|hospital_name|varchar()|
|  |hospital_clinics|varchar()|
|  |hospital_address|varchar()|

## Firebase DB Schema
Description of the remote database using Firebase

There will one collection that will hold the date of the call and inside of document 
that lists the data from the call (name, date of birth, the exact time of the call, etc.)
also there will be an idenification number (unique) in order to identify one call.

## Crawler
Description of the crawler

By creating a Crawler object you can access the method `getAvailableClinics()` that returns a HashMap with key the name of the hospital, that is on duty, and with value an ArrayList that holds in the first place a list from available clinics and second the address of that hospital. 

- Sample :
`{Οφθαλμιατρείο Αθηνών=[Οφθαλμολογική, Ελ. Βενιζέλου 26, ΑΘΗΝΑ], 
Αλεξάνδρα - Περιφερειακό Γενικό Νοσοκομείο Αθηνών=[Μαιευτική, Βασ. Σοφίας 80, Αμπελόκηποι, ΑΘΗΝΑ]`

