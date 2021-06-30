package Demo01_CitizenHash;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestCitizenHash {

    @Test
    public void testCitizenHash()
    {
        CitizenHash citizenHash = new CitizenHash(10000);

        // Einfügen
        citizenHash.put(1989230594, "Maria Mustermann");

        // Suchen
        String nameInHashTable = citizenHash.getName(1989230594);

        assertEquals(nameInHashTable, "Maria Mustermann");
    }
}
