
# mvp
Minimum viable prototype of the project

### Kako pokrenuti projekat

1. Skinuti [**Eclipse IDE for Enterprise Java Developers**](https://www.eclipse.org/downloads/packages/release/2019-03/r/eclipse-ide-enterprise-java-developers)
2. Otvoriti Eclipse EE, zatim ići Help > Eclipse Marketplace... i tu instalirati **Spring Tools 4 - for Spring Boot (aka Spring Tool Suite 4)**
3. U Eclipsu ići **File > Import... > Maven > Existing Maven Projects** i u **Browse** naći projekat na disku, zatim **Finish**
4. Sačekati da se skinu dependensiji, u **pom.xml** postoji nepoznata greška koja ne utiče ni na šta i može se ignorisati
5. Pre pokretanja je potrebno podesiti i bazu podataka što je prikazano u narednom odeljku

#### Podešavanje baze podataka
Aplikacija koristi MySQL bazu podataka i s toga ju je potrebno instalirati na računar, ako već nije instalirana.
##### Instalacija MySQL baze na Windows-u (može i Linux, ali je možda direktan MySQL paket bolji)
1. Skinuti [**odgovarajući XAMPP**](https://www.apachefriends.org/download.html)
2. Pokrenuti aplikaciju i pokrenuti (Start) odeljke **MySQL** i **Apache** (ovo će trebati da bi se baza mogla konfigurisati)
3. Otvoriti [**PHPMyAdmin**](http://localhost/phpmyadmin)
4. Sa leve strane naći **New** ([**Kreiranje nove baze**](http://localhost/phpmyadmin/server_databases.php)) i napraviti bazu **nightmaretheatre** (samo ukucati ime i ići **Create**)
5. Ići SQL tab ([**SQL tab**](http://localhost/phpmyadmin/server_sql.php)) u gornjem panelu i napraviti korisnika i dati mu privilegije, ništa ne menjati iz narednog koda (jedino što može u privilegijama **nightmaretheatre.\*** umesto **\*.\***):
	```sql
	CREATE USER 'spring'@'localhost' IDENTIFIED BY '';
	GRANT ALL PRIVILEGES ON * . * TO 'spring'@'localhost';
	FLUSH PRIVILEGES;
	```
