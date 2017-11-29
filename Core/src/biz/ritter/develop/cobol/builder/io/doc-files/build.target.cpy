100000* Beispiel COBOL Format
100010*
100200* Es soll eine Ausführbare Datei erstellt werden
100201
100210T=EXECUTABLE
100211
100300* Der Name der Ausgabe soll 'Hello World' sein 
100301* (führende und abschließende Leerzeichen werden entfernt).
100302
100310N Hello World
100311
100312
100400* Der Quelltext ist in den Dateien Hello.cbl und World.cob zu finden.
100401* (führende und abschließende Leerzeichen werden entfernt), wobei
100402* das erste Programm den Einstiegspunkt darstellt ("main")
100410 Hello.cbl
100420   World.cob
100421
100500*END