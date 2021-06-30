package Demo01_CitizenHash;

public class CitizenHash {
    private String citizens[];

    public CitizenHash(int size) {
        citizens = new String[size];
    }

    // Einfügen eines Eintrags
    public void put(int socialSecurityNo, String name)
    {
        int newPos = calcHashValue(socialSecurityNo);
        citizens[newPos] = name;
    }

    // Suche eines Eintrags implementieren
    public String getName(int socialSecurityNo)
    {
        return citizens[calcHashValue(socialSecurityNo)];
    }

    // Hashfunktion
    private int calcHashValue(int socialSecurityNo)
    {
        return socialSecurityNo % citizens.length;
    }

}
