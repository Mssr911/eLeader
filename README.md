
zadanie rekrutacyjne

Procesor XML kont bankowych.

Aplikacja przetwarza listę kont bankowych bazując na plikach XML.
Na wejściu przyjmuje plik input.xml z listą kont bankowych. Na wyjściu oczekiwany jest plik output.xml z przetworzoną listą. Struktura xml dla pliku wejściowego i wyjściowego jest identyczna, zgodnie z załączonym przykładem.
Operacje jakie powinny zostać wykonane na zaczytanej liście:
- odrzucenie kont z walutą inną niż PLN
- odrzucenie kont z saldem poniżej zera
- odrzucenie kont z datą zamknięcia wcześniejszą niż data bieżąca
- odrzucenie kont z numerem IBAN nieprawidłowym dla polskich kont bankowych
- posortowanie rosnąco wg nazwy rachunku

Podczas realizacji zadania należy uwzględnić testy jednostkowe.

przykład:
-------------------input.xml------------------------
<?xml version = "1.0"?>
<accounts>
	<account iban="PL61109010140000071219812870">
		<name>name4</name>
		<currency>PLN</currency>
		<balance>0</balance>
		<closingDate>2029-10-11</closingDate>
	</account>

	<account iban="PL61109010140000071219812875">
		<name>name1</name>
		<currency>PLN</currency>
		<balance>123.45</balance>
		<closingDate>2031-06-10</closingDate>
	</account>

	<account iban="PL61109010140000071219812871">
		<name>name2</name>
		<currency>PLN</currency>
		<balance>85.00</balance>
		<closingDate>2035-10-01</closingDate>
	</account>

	<account iban="PL61109010140000071219812872">
		<name>name3</name>
		<currency>USD</currency>
		<balance>1000000.00</balance>
		<closingDate>2059-10-01</closingDate>
	</account>

	<account iban="PLL1109010140000071219812873">
		<name>name5</name>
		<currency>PLN</currency>
		<balance>999.00</balance>
		<closingDate>2050-01-01</closingDate>
	</account>

	<account iban="PL61109010140000071219812874">
		<name>name6</name>
		<currency>PLN</currency>
		<balance>-100</balance>
		<closingDate>2039-05-15</closingDate>
	</account>

	<account iban="PLL1109010140000071219812876">
		<name>name7</name>
		<currency>PLN</currency>
		<balance>1.00</balance>
		<closingDate>2010-01-01</closingDate>
	</account>
</accounts>

-------------------output.xml------------------------
<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<accounts>
	<account iban="PL61109010140000071219812875">
		<name>name1</name>
		<currency>PLN</currency>
		<balance>123.45</balance>
		<closingDate>2031-06-10</closingDate>
	</account>
	<account iban="PL61109010140000071219812871">
		<name>name2</name>
		<currency>PLN</currency>
		<balance>85.00</balance>
		<closingDate>2035-10-01</closingDate>
	</account>
	<account iban="PL61109010140000071219812870">
		<name>name4</name>
		<currency>PLN</currency>
		<balance>0</balance>
		<closingDate>2029-10-11</closingDate>
	</account>
</accounts>
