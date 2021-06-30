package UE02_LibraryHash_Unterricht;

import java.io.BufferedReader;
import java.io.FileReader;

public class LibraryHash
{
    private String[] books;

    // Konstruktor
    public LibraryHash(int size)
    {
        books = new String[size];
    }

    // Bücher aus einer Textdatei einlesen
    public void addBooksFromFile()
    {
        String line;
        BufferedReader br = null;

        try
        {
            br = new BufferedReader(new FileReader("books.txt"));

            while ((line = br.readLine()) != null)   {
                add(line);
            }

            br.close();
        } catch (Exception | HashFullException e)
        {
            e.printStackTrace();
        }
    }

    // Ein Buch zur Hashtable hinzufügen
    public void add(String bookTitle) throws HashFullException
    {
        // Integerwert (kann sehr großer Wert sein)
        int baseValue = Math.abs(bookTitle.hashCode());

        // Schleife (für lineare Sondierung sinnvoll)
        for (int i=0; i < books.length; i++)
        {
            int index = (baseValue + i) % books.length;

            // Fall: Index ist noch frei
            if (books[index] == null)
            {
                books[index] = bookTitle;
                return;
            }
        }

        throw new HashFullException();
    }

    // Ein Buch aus der Hashtable entfernen
    public Boolean remove(String bookTitle)
    {
        // Numerischer Basiswert für weitere Hashberechnung
        int baseValue = Math.abs(bookTitle.hashCode());

        for(int i=0; i < books.length; i++)
        {
            int index = (baseValue + i) % books.length;

            // Fall: Buch existiert gar nicht im Array (NULL-Fall)
            if (books[index] == null)
                return false;
            // Fall: Buch gefunden
            if (books[index].equals(bookTitle))
            {
                books[index] = null;
                return true;
            }
        }

        return false;
    }

    // Abfrage, ob ein bestimmtes Buch in der Hashtable vorhanden ist
    public Boolean isBookInStock(String bookTitle)
    {
        // Numerischer Basiswert für weitere Hashberechnung
        int baseValue = Math.abs(bookTitle.hashCode());

        for (int i=0; i < books.length; i++)
        {
            int index = (baseValue + i) % books.length;

            // Array (zu Beginn)
            // Index 0 = NULL
            // Index 1 = NULL
            // Index 2 = NULL
            // ...
            // Index 7 = NULL
            // ...
            // Index 10 = NULL

            // Beispiel für Harry Potter 1:
            // 1) 127 % 10 = 7 (= NULL)

            // NULL.equals --> geht nicht! --> führt zu einer Exception (muss abgefangen werden)
            if (books[index]==null)
                return false;
            if (books[index].equals(bookTitle) == true)
                return true;
        }

        return false;
    }

}
