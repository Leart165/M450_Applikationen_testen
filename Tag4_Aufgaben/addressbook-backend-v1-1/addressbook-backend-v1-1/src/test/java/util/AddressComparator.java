package ch.tbz.m450.util;

import ch.tbz.m450.repository.Address;

import java.util.Comparator;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import ch.schule.Address;
import ch.schule.AddressComparator;

class AddressComparatorTest {

    @Test
    void testCompare_equalAddresses() {
        Address a1 = new Address("John", "Doe", "Main St", "8000", "Zurich");
        Address a2 = new Address("John", "Doe", "Main St", "8000", "Zurich");
        AddressComparator comparator = new AddressComparator();
        assertEquals(0, comparator.compare(a1, a2));
    }

    @Test
    void testCompare_differentLastNames() {
        Address a1 = new Address("John", "Adams", "Main St", "8000", "Zurich");
        Address a2 = new Address("John", "Brown", "Main St", "8000", "Zurich");
        AddressComparator comparator = new AddressComparator();
        assertTrue(comparator.compare(a1, a2) < 0);
    }
}
