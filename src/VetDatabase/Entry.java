package VetDatabase;

import java.io.Serializable;

/**
 * Demo1.Entry abstract class
 *
 * The parent class for all database entries.
 *
 * Every entry stored in the database must extend this class.
 * This includes Demo1.OwnerEntry, Demo1.PetEntry, and Demo1.VisitEntry.
 *
 * Demo1.Entry provides a common type that allows all database objects
 * to be stored together, compared, serialized, and manipulated
 * through the Demo1.Database class.
 *
 * All subclasses must provide implementations for compareTo(),
 * equals(), and toString().
 *
 * @author Purdue CS
 * @version 1.0
 */
public abstract class Entry
        implements Serializable, Comparable<Entry>, Maintainable {

    /**
     * Construct a new Demo1.Entry.
     *
     * Subclasses may perform any additional initialization
     * required by their specific implementation.
     */
    public Entry() {

    }

    /**
     * Compare this Demo1.Entry to another Demo1.Entry.
     *
     * The comparison rules are defined separately for each
     * subclass and are described in the project handout.
     *
     * This method is used to keep database entries in their
     * required sorted order.
     *
     * @param e the Demo1.Entry being compared
     * @return a negative value if this Demo1.Entry should come before e,
     *         zero if the Entries are considered equal,
     *         or a positive value if this Demo1.Entry should come after e
     */
    @Override
    public abstract int compareTo(Entry e);

    /**
     * Determine whether this Demo1.Entry is equal to another object.
     *
     * Equality rules are defined separately for each subclass
     * and are described in the project handout.
     *
     * @param o the object being compared
     * @return true if the objects are considered equal;
     *         false otherwise
     */
    @Override
    public abstract boolean equals(Object o);

    /**
     * Return a String representation of this Demo1.Entry.
     *
     * The exact format for each subclass is specified
     * in the project handout.
     *
     * @return a String representation of this Demo1.Entry
     */
    @Override
    public abstract String toString();
}