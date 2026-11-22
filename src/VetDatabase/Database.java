package VetDatabase;

import java.util.ArrayList;

/**
 * PJ03 Demo1.Database
 *
 * A class representing a database
 *
 * @author Ethan Ma, CRN SectionId: 10687-BLK
 * @version 11/1/26
 */

public class Database implements Useable {
    private ArrayList<Entry> entries;
    private String filename;

    public Database(String fileName) {
        this.filename = fileName;
        entries = new ArrayList<Entry>();
    }

    public Entry getOwner(String name) throws EntryException {

        if (name == null) {
            throw new EntryException();
        }

        OwnerEntry toReturn = null;

        for (Entry e : entries) {
            if (e instanceof OwnerEntry oe) {
                if (oe.getName().equals(name) && toReturn == null) {
                    toReturn = oe;
                }  else if (oe.getName().equals(name) && toReturn != null) {
                    throw new EntryException("Duplicate Demo1.Entry");
                }
            }
        }

        return toReturn;
    }

    public Entry getOwner(int number) throws EntryException {
        String num = Integer.toString(number);
        if (num != null && (num.length() < 9 || num.charAt(0) == '0')) {
            throw new EntryException();
        }

        OwnerEntry toReturn = null;

        for (Entry e : entries) {
            if (e instanceof OwnerEntry oe) {
                if (oe.getPhoneNumber() == number && toReturn == null) {
                    toReturn = oe;
                }  else if (oe.getPhoneNumber() == number && toReturn != null) {
                    throw new EntryException("Duplicate Demo1.Entry");
                }
            }
        }

        return toReturn;
    }

    public Entry getOwner(String name, String address) throws EntryException {
        if (name == null) {
            throw new EntryException();
        }
        if (address == null) {
            throw new EntryException();
        }

        OwnerEntry toReturn = null;

        for (Entry e : entries) {
            if (e instanceof OwnerEntry oe) {
                if (oe.getName().equals(name) &&
                        oe.getAddress().equals(address) &&
                        toReturn == null) {
                    toReturn = oe;
                }  else if (oe.getName().equals(name) &&
                        oe.getAddress().equals(address) &&
                        toReturn != null) {
                    throw new EntryException("Duplicate Demo1.Entry");
                }
            }
        }

        return toReturn;
    }


    public Entry getOwner(String name, String address, int number) throws EntryException {
        if (name == null) {
            throw new EntryException();
        }
        if (address == null) {
            throw new EntryException();
        }
        String num = Integer.toString(number);
        if (num != null && (num.length() < 9 || num.charAt(0) == '0')) {
            throw new EntryException();
        }

        OwnerEntry toReturn = null;

        for (Entry e : entries) {
            if (e instanceof OwnerEntry oe) {
                if (oe.getName().equals(name) &&
                        oe.getAddress().equals(address) &&
                        oe.getPhoneNumber() == number &&
                        toReturn == null) {
                    toReturn = oe;
                }  else if (oe.getName().equals(name) &&
                        oe.getAddress().equals(address) &&
                        oe.getPhoneNumber() == number &&
                        toReturn != null) {
                    throw new EntryException("Duplicate Demo1.Entry");
                }
            }
        }

        return toReturn;
    }

    public Entry getPet(Entry owner, String name) throws EntryException {
        if (name == null) {
            throw new EntryException();
        }

        //code to find owner
        int indexOwner = -1;
        int indexNextOwner = entries.size() - 1;

        for (int i = 0; i < entries.size(); i++) {
            if (entries.get(i).equals(owner)) {
                indexOwner = i;
                break;
            }
        }

        if (indexOwner == -1) {
            throw new EntryException("No Such Owner");
        }
        for (int i = indexOwner + 1; i < entries.size(); i++) {
            if (entries.get(i) instanceof OwnerEntry) {
                indexNextOwner = i;
                break;
            }
        }

        //code to find pet
        for (int i = indexOwner; i <= indexNextOwner; i++) {
            if (entries.get(i) instanceof PetEntry pe) {
                if (pe.getName().equals(name)) {
                    return pe;
                }
            }
        }
        throw new EntryException("No Such Pet");
    }

    public Entry[] getPets(Entry owner) throws EntryException {
        //code to find owner
        int indexOwner = -1;
        int indexNextOwner = entries.size() - 1;

        for (int i = 0; i < entries.size(); i++) {
            if (entries.get(i).equals(owner)) {
                indexOwner = i;
                break;
            }
        }

        if (indexOwner == -1) {
            throw new EntryException("No Such Owner");
        }
        for (int i = indexOwner + 1; i < entries.size(); i++) {
            if (entries.get(i) instanceof OwnerEntry) {
                indexNextOwner = i;
                break;
            }
        }

        //code to find pets
        ArrayList<Entry> pets = new ArrayList<Entry>();

        for (int i = indexOwner; i <= indexNextOwner; i++) {
            if (entries.get(i) instanceof PetEntry pe) {
                pets.add(pe);
            }
        }
        if (pets.isEmpty()) {
            throw new EntryException("No Pets");
        } else {
            Entry[] toReturn = new Entry[pets.size()];
            return pets.toArray(toReturn);
        }
    }

    public Entry[] getVisits(Entry pet) throws EntryException {
        //code to find pet
        int indexPet = -1;
        int indexNextPet = entries.size() - 1;

        for (int i = 0; i <= entries.size() - 1; i++) {
            if (entries.get(i).equals(pet)) {
                indexPet = i;
                break;
            }
        }

        if (indexPet == -1) {
            throw new EntryException("No Such Pet");
        }
        for (int i = indexPet + 1; i < indexNextPet; i++) {
            if (entries.get(i) instanceof PetEntry) {
                indexNextPet = i;
                break;
            }
        }

        //code to find visits
        ArrayList<Entry> visits = new ArrayList<Entry>();

        for (int i = indexPet; i <= indexNextPet; i++) {
            if (entries.get(i) instanceof VisitEntry ve) {
                visits.add(ve);
            }
        }
        if (visits.isEmpty()) {
            throw new EntryException("No Visits");
        } else {
            Entry[] toReturn = new Entry[visits.size()];
            return visits.toArray(toReturn);
        }
    }

    public void addOwner(Entry owner) throws EntryException {
        boolean added = false;

        //checks for duplicates
        for (Entry e : entries) {
            if (e instanceof OwnerEntry oe) {
                if (oe.equals(owner)) {
                    throw new EntryException("Duplicate Demo1.Entry");
                }
            }
        }

        //for adding the first entry
        if (entries.isEmpty()) {
            entries.add(owner);
            added = true;
        }

        //for adding owner in order
        for (int i = 0; i < entries.size(); i++) {
            if (entries.get(i) instanceof OwnerEntry oe) {

                if (owner.compareTo(oe) < 0) {
                    if (i == 0) {
                        entries.add(0, owner);
                        added = true;
                    } else {
                        entries.add(i, owner);
                        added = true;
                    }
                    break;
                }
            }
        }

        //for adding owner at the end of entries
        if (!added) {
            entries.add(owner);
        }
    }

    public void addPet(Entry owner, Entry pet) throws EntryException {

        //code to find owner
        int indexOwner = -1;
        int indexNextOwner = entries.size() - 1;

        for (int i = 0; i < entries.size(); i++) {
            if (entries.get(i).equals(owner)) {
                indexOwner = i;
                break;
            }
        }

        if (indexOwner == -1) {
            throw new EntryException("No Such Owner");
        }
        for (int i = indexOwner + 1; i < entries.size(); i++) {
            if (entries.get(i) instanceof OwnerEntry) {
                indexNextOwner = i;
                break;
            }
        }

        //code to add pet
        boolean added = false;

        for (int i = indexOwner; i < indexNextOwner; i++) {
            if (entries.get(i) instanceof PetEntry pe) {

                if (pe.equals(pet)) {
                    throw new EntryException("Duplicate Demo1.Entry");
                }

                if (pet.compareTo(pe) < 0) {
                    entries.add(i, pet);
                    added = true;
                    break;
                }
            }
        }

        if (!added) {
            if (indexNextOwner == indexOwner) {
                //if there are no further Demo1.OwnerEntry past the owner
                entries.add(pet);
            } else {
                //if there are further entries
                int indexLastPet = indexOwner;

                for (int i = indexOwner; i <= indexNextOwner; i++) {
                    if (entries.get(i) instanceof PetEntry) {
                        indexLastPet = i;
                    }
                }

                if (indexLastPet == indexOwner) {
                    //if there were no pets that belonged to owner
                    entries.add(indexNextOwner, pet);
                } else if (indexLastPet != entries.size() - 1) {
                    //if there were pets that belonged to owner
                    entries.add(indexLastPet + 1, pet);
                } else {
                    //if there were pets that belonged to owner but no further entries
                    entries.add(pet);
                }
            }
        }

        entries.get(indexOwner).setPetCount(entries.get(indexOwner).getPetCount() + 1);
    }

    public void addVisit(Entry owner, Entry pet, Entry visit) throws EntryException {
        //code to find owner
        int indexOwner = -1;
        int indexNextOwner = entries.size() - 1;

        for (int i = 0; i < entries.size(); i++) {
            if (entries.get(i).equals(owner)) {
                indexOwner = i;
                break;
            }
        }

        if (indexOwner == -1) {
            throw new EntryException("No Such Owner");
        }
        for (int i = indexOwner + 1; i < entries.size(); i++) {
            if (entries.get(i) instanceof OwnerEntry) {
                indexNextOwner = i;
                break;
            }
        }

        //code to find pet
        int indexPet = -1;
        int indexNextPet = indexNextOwner;

        for (int i = indexOwner; i <= indexNextOwner; i++) {
            if (entries.get(i).equals(pet)) {
                indexPet = i;
                break;
            }
        }

        if (indexPet == -1) {
            throw new EntryException("No Such Pet");
        }
        for (int i = indexPet + 1; i < indexNextOwner; i++) {
            if (entries.get(i) instanceof PetEntry) {
                indexNextPet = i;
                break;
            }
        }

        //code to add visit
        boolean added = false;

        for (int i = indexPet; i < indexNextPet; i++) {
            if (entries.get(i) instanceof VisitEntry ve) {

                if (ve.equals(visit)) {
                    throw new EntryException("Duplicate Demo1.Entry");
                }

                if (visit.compareTo(ve) < 0) {
                    entries.add(i, visit);
                    added = true;
                    break;
                }
            }
        }

        if (!added) {
            if (indexNextPet == indexPet) {
                //if there are no further Demo1.PetEntry past the pet
                entries.add(visit);
            } else {
                //if there are further entries
                int indexLastVisit = indexPet;

                for (int i = indexPet; i < indexNextPet; i++) {
                    if (entries.get(i) instanceof VisitEntry) {
                        indexLastVisit = i;
                    }
                }

                if (indexLastVisit == indexPet) {
                    //if there were no visits that belonged to pet
                    entries.add(indexNextPet, visit);
                } else if (indexLastVisit != indexNextPet) {
                    //if there were visits that belonged to pet
                    entries.add(indexLastVisit + 1, visit);
                } else {
                    //if there were visits that belonged to pet but no further entries
                    entries.add(visit);
                }
            }
        }

        entries.get(indexPet).setNumberOfVisits(entries.get(indexPet).getNumberOfVisits() + 1);
    }

    public Entry[] checkBalances() throws EntryException {

        ArrayList<Entry> outstanding = new ArrayList<Entry>();

        for (Entry e : entries) {
            if (e instanceof OwnerEntry oe) {

                double due = 0;
                Entry[] pets = getPets(oe);

                for (Entry ee : pets) {
                    if (ee instanceof PetEntry pe) {

                        Entry[] visits = getVisits(pe);

                        for (Entry eee : visits) {
                            if (eee instanceof VisitEntry ve) {
                                due += ve.getBalance();
                            }
                        }
                    }
                }

                if (Math.round(due * 100) != 0) {
                    outstanding.add(oe);
                }
            }
        }

        if (outstanding.isEmpty()) {
            throw new EntryException();
        }

        Entry[] toReturn = new Entry[outstanding.size()];
        return outstanding.toArray(toReturn);
    }

    /**
     * Apply a payment to the specified entry.
     *
     * If the entry is an Owner Demo1.Entry, apply the payment to that
     * owner's visits in order until either:
     * - the entire payment has been applied, or
     * - all visit balances reach 0
     *
     * Additional payment details are provided in the project handout.
     *
     * If the entry is a Visit Demo1.Entry, apply the payment only to
     * that visit.
     *
     * Return the remaining balance of the last modified visit
     * as a positive value.
     *
     * Return a negative value if the payment exceeds the balance,
     * where the absolute value represents the amount overpaid.
     *
     * If payment is zero or negative,
     * throw an Demo1.EntryException with no message.
     *
     * If the entry does not exist,
     * throw an Demo1.EntryException with the message:
     * "No Such Demo1.Entry"
     */
    public double payBalance(Entry entry, double payment) throws EntryException {
        if (payment <= 0) {
            throw new EntryException();
        }

        Entry payTo = null;

        for (Entry e : entries) {
            if (e.equals(entry)) {
                payTo = entry;
            }
        }

        if (payTo == null) {
            throw new EntryException("No Such Demo1.Entry");
        }

        return 0.0;
    }

    /**
     * Write all database entries to the filename specified
     * by the Demo1.Database class using object I/O.
     *
     * This method must throw zero exceptions.
     *
     * Catch and handle all checked exceptions internally.
     */
    public void shutdown() {

    }

    /**
     * Read all database entries from the filename specified
     * by the Demo1.Database class using object I/O.
     *
     * This method must throw zero exceptions.
     *
     * Catch and handle all checked exceptions internally.
     *
     * Be sure to correctly cast each object as it is read.
     */
    public void startup() {

    }

    public ArrayList<Entry> getEntries() {
        return entries;
    }
}