package UE02_LibraryHash_Loesung;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class TestLibraryHash
{
    LibraryHash lh;

    @Before
    public void setUp()
    {
        lh = new LibraryHash(1300);
        lh.addBooksFromFile();
    }

    @Test
    public void testBookInStock()
    {
        assertTrue(lh.isBookInStock("Doktor Schiwago"));
        assertTrue(lh.isBookInStock("1984"));
        assertFalse(lh.isBookInStock("Bohlen: Nichts als die Wahrheit"));
        assertFalse(lh.isBookInStock("Roger Federer: Die Biografie"));
    }

    @Test
    public void testRemove()
    {
        assertTrue(lh.isBookInStock("Der dritte Mann"));
        assertTrue(lh.remove("Der dritte Mann"));
        assertFalse(lh.isBookInStock("Der dritte Mann"));
    }
}
