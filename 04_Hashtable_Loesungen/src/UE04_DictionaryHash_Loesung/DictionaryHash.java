package UE04_DictionaryHash_Loesung;

import UE02_LibraryHash_Unterricht.HashFullException;

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
        } catch (Exception | HashFullException e)
        {
            e.printStackTrace();
        }
    }

    // Ein Wort zur Hashtable hinzufügen
    public void add(String word) throws HashFullException
    {
        // Einfügen mithilfe einer Kollissionsstrategie
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
        int baseHashValue = Math.abs(word.hashCode());

        int h1 = baseHashValue % dictionary.length;
        int h2 = baseHashValue % 31 + 13;

        return (h1 + (factor * h2)) % dictionary.length;
    }
}
