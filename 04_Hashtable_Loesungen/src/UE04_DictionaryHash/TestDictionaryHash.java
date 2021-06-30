package UE04_DictionaryHash;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestDictionaryHash
{
    DictionaryHash dh;
    private static final int size = 1300;

    @Before
    public void setUp()
    {
        dh = new DictionaryHash(size);
        dh.addWordsFromFile();
    }

    @Test
    public void testDoubleHashing()
    {
        int baseHashValue = Math.abs("Menschen".hashCode());
        int hashValue = ((baseHashValue % size) + (3 * (baseHashValue % 31 + 13))) % size;
        assertEquals(hashValue, dh.calcIndexWithDoubleHashing("Menschen",3));
    }
}
