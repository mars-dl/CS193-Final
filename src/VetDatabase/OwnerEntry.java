package VetDatabase;

/**
 * PJ03 Demo1.OwnerEntry
 *
 * A class representing a database entry for a pet owner
 *
 * @author Ethan Ma, CRN SectionId: 10687-BLK
 * @version 10/27/26
 */

public class OwnerEntry extends Entry {
    private String name;
    private String address;
    private int phoneNumber;
    private int petCount;

    public OwnerEntry(String name, String address, int phoneNumber) throws EntryException {
        if (name == null) {
            throw new EntryException("Name can not be null");
        }
        if (address == null) {
            throw new EntryException("Address can not be null");
        }
        String num = Integer.toString(phoneNumber);
        if (num != null && (num.length() < 9 || num.charAt(0) == '0')) {
            throw new EntryException("Phone number invalid");
        }

        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public void updateAddress(String address) throws EntryException {
        if (address == null) {
            throw new EntryException();
        }
        this.address = address;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void updatePhoneNumber(int number) throws EntryException {
        String num = Integer.toString(number);
        if (num != null && (num.length() < 9 || num.charAt(0) == '0')) {
            throw new EntryException();
        }
        this.phoneNumber = number;
    }

    public int getPetCount() {
        return petCount;
    }

    public void setPetCount(int count) {
        this.petCount = count;
    }

    @Override
    public String toString() {
        return name + " - " + address + " - " + phoneNumber + " (" + petCount + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof OwnerEntry oe) {
            boolean nameEquals = name.equals(oe.getName());
            boolean addressEquals = address.equals(oe.getAddress());
            boolean numberEquals = phoneNumber == oe.getPhoneNumber();
            return nameEquals && addressEquals && numberEquals;
        }
        return false;
    }

    @Override
    public int compareTo(Entry e) {
        int compare;
        if (e instanceof OwnerEntry oe) {
            //negative if would come before, alphebetically
            compare = name.compareTo(e.getName());
            if (compare == 0) {
                compare = address.compareTo(oe.getAddress());
                if (compare == 0) {
                    //negative if would come before, numerically
                    compare = phoneNumber - oe.getPhoneNumber();
                    //0 only returned if everything equal
                    return compare;
                } else {
                    return compare;
                }
            } else {
                return compare;
            }
        } else {
            throw new IllegalArgumentException();
        }
    }
}