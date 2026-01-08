Life Management System

Desktop aplikacija za praćenje svakodnevnih aktivnosti: finansije, spavanje, učenje, navike i obroci, sa korisničkim profilom i podrškom za bazu podataka. 
Da bi aplikacija radila, potrebno je instalirati i pokrenuti MongoDB server (Community verzija je sasvim dovoljna).

Pri pokretanju aplikacije otvara se početni prozor sa login formom. Ako aplikaciju koristite prvi put, potrebno je prvo registrovati korisnika kako bi se kreirao profil u bazi podataka.

Nakon uspješnog logina otvara se glavni meni koji sadrži tri glavne opcije:

View Profile služi za prikaz i uređivanje korisničkog profila. Ovdje je moguće promijeniti lozinku.

Finance App se koristi za upravljanje finansijama. Korisnik može unositi prihode i troškove, vidjeti listu svih transakcija u tabeli i pratiti trenutni balans koji se automatski ažurira.

My Trackers otvara podmeni sa ostalim alatima za organizaciju. U ovom dijelu se nalaze Sleep tracker, Study tracker, Habit tracker i Meal planner. Ovi moduli omogućavaju korisniku da zasebno prati sate spavanja, planira učenje po predmetima, prati svakodnevne navike i organizuje obroke po danima u sedmici.

Svi podaci se u realnom vremenu spremaju u MongoDB kolekcije, što osigurava da su informacije dostupne i nakon ponovnog pokretanja aplikacije. Interfejs je dizajniran tako da bude pregledan, sa tabelama koje se osvježavaju odmah nakon unosa novih podataka.
