import Demo1.Database;
import Demo1.Entry;
import Demo1.EntryException;
import Demo1.OwnerEntry;
import Demo1.PetEntry;
import Demo1.VisitEntry;

import java.util.ArrayList;

public class Demo1Runner {

    static Database db = new Database("");

    public static void main() throws Exception {
        IO.println("This is a demonstration of a database class that contains owners, their pets, and those pet's visits to a veterinary clinic.");

        IO.println("\nLet's start by adding this owner to the database:");
        OwnerEntry owner1 = new OwnerEntry("Aaron", "123 Main Street", 765555123);
        IO.println(owner1);
        IO.println("This is done by using the addOwner() method, which takes an OwnerEntry");
        db.addOwner(owner1);

        IO.println("\nNow, let's add a pet for that owner:");
        PetEntry pet1 = new PetEntry("Arthur");
        IO.println(pet1);
        IO.println("This is done by using the addPet() method, which takes an PetEntry and the OwnerEntry it should belong to");
        db.addPet(owner1, pet1);

        IO.println("\nAnd a visit for that pet:");
        String[] services = {"Routine Checkup"};
        VisitEntry v1 = new VisitEntry("2025-01-01", services, 25.0);
        IO.println(v1);
        IO.println("This is done by using the addVisit() method, which takes an VisitEntry and the PetEntry it should belong to");
        db.addVisit(owner1, pet1, v1);

        IO.println("\nThe database now looks like this (formatted for readability):");
        print();

        IO.println("\nI'll add a few more entries, and the database now looks like this:");

        OwnerEntry owner2 = new OwnerEntry("Vanessa", "234 Secondary Street", 739164026);
        PetEntry pet2 = new PetEntry("Zake");
        VisitEntry v201 = new VisitEntry("2025-01-01", services, 25.0);
        PetEntry pet21 = new PetEntry("Zack");
        VisitEntry v211 = new VisitEntry("2024-04-17", services, 25.0);
        String[] services2 = {"Surgery", "Monitoring"};
        VisitEntry v212 = new VisitEntry("2025-04-19", services2, 150.0);
        PetEntry pet22 = new PetEntry("Zook");

        OwnerEntry owner3 = new OwnerEntry("Able", "102 Main Street", 173946292);
        PetEntry pet3 = new PetEntry("Arthur");
        VisitEntry v301 = new VisitEntry("2026-11-20", services, 25.0);

        OwnerEntry owner4 = new OwnerEntry("Zinc", "30 Element Street", 746302134);

        db.addOwner(owner4);

        db.addOwner(owner2);
        db.addPet(owner2, pet2);
        db.addPet(owner2, pet22);
        db.addPet(owner2, pet21);
        db.addVisit(owner2, pet21, v211);
        db.addVisit(owner2, pet2, v201);
        db.addVisit(owner2, pet21, v212);

        db.addOwner(owner3);
        db.addPet(owner3, pet3);
        db.addVisit(owner3, pet3, v301);
        print();

        IO.println("\nAs you can see, the database organizes itself alphabetically and chronologically, and deals with pets with same and different names belonging to different owners." +
                "\nAdditionally, if you look at the source code, I have added them out of order, and the database handles that as well.");
    }

    public static void print() {
        ArrayList<Entry> entries = db.getEntries();
        for(Entry e : entries) {
            if (e instanceof OwnerEntry oe) {
                IO.println(oe);
            } else if (e instanceof PetEntry pe) {
                IO.println("    " + pe);
            } else if (e instanceof VisitEntry ve) {
                IO.println("        " + ve);
            }
        }
    }
}
