# XML_TIM7

Informacioni sistem za pristup informacijama od javnog značaja sastoji se iz:
1) Portala poverenika
2) Portala organa vlasti
3) Web servisa za email

## Portal poverenika sastoji se iz:
    1) Spring boot aplikacije korišćene za backend - port: http://localhost:8085
    2) Angular frontend aplikacija - port: http://localhost:4201
    3) Exist baze podataka - port: http://localhost:8080/existP
    4) Fuseki baze metapodataka - port: http://localhost:8080/fusekiP
    
## Portal organa vlasti sastoji se iz:
    1) Spring boot aplikacije korišćene za backend - port: http://localhost:8090
    2) Angular frontend aplikacija - port: http://localhost:4200
    3) Exist baze podataka - port: http://localhost:8080/existOV
    4) Fuseki baze metapodataka - port: http://localhost:8080/fusekiOV
    
## Email web servis:
    1) Spring boot aplikacija: port: http://localhost:8095

# Uputstvo za pokretanje sistema:

  Pre pokretanja aplikacije neophodno je skinuti TomEE verziju 8.0.5. Nakon toga potrebno je skinuti dve instance exist i fuseki baze podataka.
  Prva instanca treba da nosi naziv existOV.war odnosno fusekiOV.war, dok drugu treba preimenovati u existP.war odnosno fusekiP.war. Zatim sva 4 fajla
  prebaciti u TomEE/webapps folder. 
  
  Baza se pokreće pokretanjem fajla startup.bat unutar TomEE/bin. Posle pokretanja baze može se započeti sa pokretanjem backend aplikacija.
  
  Backend aplikacije je neophodno buildovati pre pokretanja pomoću Maven alata kako bi se uvezle neophodne zavisnosti. Build se vrši komandama mvn clean compile install.
  Posle toga aplikacije se mogu pokretati kao normalne spring boot aplikacije. 
  
  Pre pokretanja frontend aplikacija neophodno je uraditi npm install, a zatim normalno pokrenuti frontend servere komandom ng serve.
 
  
