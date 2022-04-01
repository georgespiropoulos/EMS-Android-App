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

## Crawler
Description of the crawler

By creating a Crawler object you can access the method `getAvailableClinics()` that returns a HashMap with key the name of the hospital, that is on duty, and with value an ArrayList that holds in the first place a list from available clinics and second the address of that hospital. 

- Sample :
`{Οφθαλμιατρείο Αθηνών=[Οφθαλμολογική, Ελ. Βενιζέλου 26, ΑΘΗΝΑ], 
Αλεξάνδρα - Περιφερειακό Γενικό Νοσοκομείο Αθηνών=[Μαιευτική, Βασ. Σοφίας 80, Αμπελόκηποι, ΑΘΗΝΑ]`
