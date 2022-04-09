# KSR zadanie 1 - Algorytm k-NN

## Opis

Projekt realizowany w ramach przedmiotu na studiach. Polegał on na stworzeniu klasyfikatora artykułów z użyciem metody k-NN(k Nearest Neighbours).  
Artykuły podlegające klasyfikacji pochodzą ze strony http://archive.ics.uci.edu/ml/datasets/Reuters-21578+Text+Categorization+Collection.  
Zostały one przefiltrowane na potrzeby zadania(zostały tylko artykuły z 6 pańśtw, które to państwa są na potrzeby zadania klasami artykułów.  
Jako że celem zadania było przede wszystkim zapoznanie się z metodą k-NN oraz jej zrozumienie, klasyfikacja dla dobranych cech jest daleka od idealnej.

### Wstęp teoretyczny

Naukowe podsumowanie metody k-NN przy klasyfikacji tekstów:  
https://ccc.inaoep.mx/~villasen/index_archivos/cursoTL/articulos/Tan-AnEffectiveRefinementStrategyForKNNTextClassifier.pdf  
Berdziej przystępne, a więc mniej szczegółowe, wprowadzenie do metody k-NN:  
https://www.statsoft.pl/textbook/stathome_stat.html?https%3A%2F%2Fwww.statsoft.pl%2Ftextbook%2Fstknn.html

## Zasada działania

### Architektura

Projekt stworzony został w języku Java, co narzuciło programowanie zorientowane obiektowo.
Każda klasa jest odpowiedzialna za konkretne zadanie, które odpowiada jej nazwie.

### Argumenty

Argumenty przyjmowane są sekwencyjnie, w następującej kolejności:

- ścieżka bezwzględna do pliku z artykułami
- ścieżka bezwzględna do folderu ze słownikami do ekstrakcji cech
- odsetek zbioru uczącego(jaka część artykułów ma służyć do przewidywania klas drugiej(testowej) części artykułów
- metryka:
  - M1 - metryka Euklidesowa
  - M2 - metryka Manhattan(uliczna)
  - M3 - metryka Czebyszewa
- liczba sąsiadów(k)

Kolejne argumenty to cechy <c1;c10> które mają zostać pominięte.

### Diagram klas

![class_diagram](https://user-images.githubusercontent.com/80945372/162265296-c7eb587f-5359-4c58-af2f-626503e58946.png)
