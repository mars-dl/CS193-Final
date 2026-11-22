package VetDatabase;

import java.time.LocalDate;

/**
 * PJ03 Demo1.VisitEntry
 *
 * A class representing a database entry for a visit
 *
 * @author Ethan Ma, CRN SectionId: 10687-BLK
 * @version 10/28/26
 */

public class VisitEntry extends Entry {
    private LocalDate date;
    private String[] services;
    private double balance;

    public VisitEntry(String date, String[] services, double balance) throws EntryException {
        try {
            this.date = LocalDate.parse(date);
        } catch (Exception e) {
            throw new EntryException("Invalid Date");
        }
        if (services == null) {
            throw new EntryException("Invalid Services");
        }
        if (services.length == 0) {
            throw new EntryException("Invalid Services");
        }
        if (balance < 0) {
            throw new EntryException("Invalid Balance");
        }

        this.services = services;
        this.balance = balance;
    }

    public LocalDate getDate() {
        return date;
    }

    public double getBalance() {
        return balance;
    }

    public double payBalance(double payment) {
        double toReturn = balance - payment;
        balance -= payment;
        if (Math.abs(balance) < 0.0001 || balance < 0) {
            balance = 0.0;
        }
        return toReturn;
    }

    public String[] getServices() {
        return services;
    }

    @Override
    public String toString() {
        String servicesReturn = "";
        for (int i = 0; i < services.length; i++) {
            servicesReturn += services[i];
            if (i < services.length - 1) {
                servicesReturn += ", ";
            }
        }
        return date + " - " + servicesReturn + " - $" + String.format("%.2f", balance);
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof VisitEntry ve) {
            if (!this.date.equals(ve.date)) {
                return false;
            }
            if (!(services.length == ve.services.length)) {
                return false;
            }
            for (int i = 0; i < services.length; i++) {
                if (!services[i].equals(ve.services[i])) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    @Override
    public int compareTo(Entry e) {
        if (e instanceof VisitEntry ve) {
            return date.compareTo(ve.date);
        } else {
            throw new IllegalArgumentException();
        }
    }
}