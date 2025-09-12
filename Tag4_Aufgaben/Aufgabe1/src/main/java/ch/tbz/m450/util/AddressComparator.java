package ch.tbz.m450.util;

import ch.tbz.m450.repository.Address;

import java.util.Comparator;

public class AddressComparator implements Comparator<Address> {

    @Override
    public int compare(Address a1, Address a2) {
        if (a1 == null && a2 == null) {
            return 0;
        }
        if (a1 == null) {
            return -1;
        }
        if (a2 == null) {
            return 1;
        }
        
        int lastNameComparison = compareStrings(a1.getLastname(), a2.getLastname());
        if (lastNameComparison != 0) {
            return lastNameComparison;
        }
        
        return compareStrings(a1.getFirstname(), a2.getFirstname());
    }
    
    private int compareStrings(String s1, String s2) {
        if (s1 == null && s2 == null) {
            return 0;
        }
        if (s1 == null) {
            return -1;
        }
        if (s2 == null) {
            return 1;
        }
        return s1.compareToIgnoreCase(s2);
    }

}
