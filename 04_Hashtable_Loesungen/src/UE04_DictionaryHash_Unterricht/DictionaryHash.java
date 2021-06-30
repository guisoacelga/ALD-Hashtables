package UE04_DictionaryHash_Unterricht;

import java.io.BufferedReader;
import java.io.FileReader;

public class DictionaryHash
{
    private String[] dictionary;

    // Konstruktor
    public DictionaryHash(int size)
    {
        dictionary = new String[size];
    }

    // Wörter aus einer Textdatei einlesen
    public void addWordsFromFile()
    {
        String line;
        BufferedReader br = null;

        try
        {
            br = new BufferedReader(new FileReader("words.txt"));

            while ((line = br.readLine()) != null)   {
                add(line);
            }

            br.close();
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    // Ein Wort zur Hashtable hinzufügen
    public void add(String word) throws HashFullException
    {
        for (int i = 0; i < dictionary.length; i++)
        {
            int index = calcIndexWithDoubleHashing(word, i);

            // Wenn diese Stelle noch frei, dann Wort dort einfügen
            if (dictionary[index] == null)
            {
                dictionary[index] = word;
                // Per return beendet man die Schleife
                return;
            }
            // Sonst passende Stelle per Schleife weitersuchen
        }

        // Es wurde keine passende Stelle gefunden. Der Hash muss voll sein.
        throw new HashFullException();
    }

    // Ein Wort aus der Hashtable entfernen
    public Boolean remove(String word)
    {
        for (int i = 0; i < dictionary.length; i++)
        {
            int index = calcIndexWithDoubleHashing(word, i);

            if ((dictionary[index] != null) && (dictionary[index].equals(word)))
            {
                dictionary[index] = null;
                return true;
            }
        }

        // Keine Löschung, da Wort nicht gefunden wurde
        return false;
    }

    // Abfrage, ob ein bestimmtes Wort in der Hashtable vorhanden ist
    public Boolean isWordInDictionary(String word)
    {
        for (int i = 0; i < dictionary.length; i++)
        {
            // 0 - 1 - 2 - 3 - ...
            int index = calcIndexWithDoubleHashing(word, i);

            // Überprüfen, ob Wort an berechneter Stelle ist
            if ((dictionary[index] != null) && (dictionary[index].equals(word)))
                return true;

            // Ansonsten weitersuchen (per Schleife)
        }

        // Schleife wurde durchlaufen und nichts gefunden
        return false;
    }

    public int calcIndexWithDoubleHashing(String word, int factor)
    {
        // Berechnen Sie den Index mithilfe von doppeltem Hashing. Verwenden Sie folgende zwei Hashingfunktionen bei der Berechnung:
        // H1: HashCode MOD Array-Größe
        // H2: HashCode MOD 31 + 13

        int hashCode = Math.abs(word.hashCode());
        int h1 = hashCode % dictionary.length;
        int h2 = hashCode % 31 + 13;

        // Gesamte Formel: (h1(k) + j * h2(k)) mod m
        // ARRAY.length = m

        // Beispiel: Array mit einer Länge 1000
        // 1234 % 1000 = 234.
        // 1234 % 10.000 = 1234. --> Geht nicht, da Index im Array nicht existiert.

        return (h1 + factor * h2) % dictionary.length;
    }
}
