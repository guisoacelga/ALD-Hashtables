package UE02_LibraryHash_Loesung;

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
        // Start-Hashwert aus Buchtitel berechnen
        // Math.abs wird verwendet, da hashCode auch negative Werte liefern kann
        int baseHashvalue = Math.abs(bookTitle.hashCode());

        // Einfügen mithilfe von linearer Sondierung
        for (int i=0; i < books.length; i++)
        {
            int index = (baseHashvalue + i) % books.length;

            // Wenn diese Stelle noch frei, dann Buch dort einfügen
            if (books[index] == null)
            {
                books[index] = bookTitle;
                // Per return beendet man die Schleife
                return;
            }
            // Sonst passende Stelle per Schleife weitersuchen
        }

        // Es wurde keine passende Stelle gefunden. Der Hash muss voll sein.
        throw new HashFullException();
    }

    // Ein Buch aus der Hashtable entfernen
    public Boolean remove(String bookTitle)
    {
        int baseHashValue = Math.abs(bookTitle.hashCode());

        for (int i=0; i < books.length; i++)
        {
            int index = (baseHashValue + i) % books.length;

            if ((books[index] != null) && (books[index].equals(bookTitle)))
            {
                books[index] = null;
                return true;
            }
        }

        // Keine Löschung, da Buch nicht gefunden wurde
        return false;
    }

    // Abfrage, ob ein bestimmtes Buch in der Hashtable vorhanden ist
    public Boolean isBookInStock(String bookTitle)
    {
        int baseHashValue = Math.abs(bookTitle.hashCode());

        for (int i=0; i < books.length; i++)
        {
            int index = (baseHashValue + i) % books.length;

            // Überprüfen, ob Buch an berechneter Stelle ist
            if ((books[index] != null) && (books[index].equals(bookTitle)))
                return true;

            // Ansonsten weitersuchen (per Schleife)
        }

        // Schleife wurde durchlaufen und nichts gefunden
        return false;
    }

}
