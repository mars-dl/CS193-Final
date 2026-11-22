package VetDatabase;

/**
 * PJ03 Demo1.PetEntry
 *
 * A class representing a database entry for a pet belonging to a owner
 *
 * @author Ethan Ma, CRN SectionId: 10687-BLK
 * @version 10/28/26
 */
public class PetEntry extends Entry {
    private String name;
    private int numberOfVisits;

    public PetEntry(String name) throws EntryException {
        if (name == null) {
            throw new EntryException("Name can not be null");
        }
        this.name = name;
    }

    public String getName() {
        return name;
    }


    public int getNumberOfVisits() {
        return numberOfVisits;
    }

    public void setNumberOfVisits(int count) {
        this.numberOfVisits = count;
    }

    @Override
    public String toString() {
        return name + " (" + numberOfVisits + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof PetEntry pe) {
            return name.equals(pe.name);
        }
        return false;
    }

    @Override
    public int compareTo(Entry e) {
        if (e instanceof PetEntry pe) {
            return name.compareTo(pe.name);
        } else {
            throw new IllegalArgumentException();
        }
    }
}