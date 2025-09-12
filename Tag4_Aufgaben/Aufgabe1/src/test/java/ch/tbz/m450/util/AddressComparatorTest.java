package ch.tbz.m450.util;

import ch.tbz.m450.repository.Address;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("AddressComparator Tests")
class AddressComparatorTest {
    
    private AddressComparator comparator;
    private Date testDate;
    
    @BeforeEach
    void setUp() {
        comparator = new AddressComparator();
        testDate = new Date();
    }
    
    @Test
    @DisplayName("Should compare addresses by lastname first")
    void testCompareByLastname() {
        Address address1 = new Address(1, "John", "Adams", "123-456-7890", testDate);
        Address address2 = new Address(2, "Jane", "Baker", "555-123-4567", testDate);
        
        int result = comparator.compare(address1, address2);
        
        assertTrue(result < 0, "Adams should come before Baker");
    }
    
    @Test
    @DisplayName("Should compare addresses by firstname when lastname is same")
    void testCompareByFirstnameWhenLastnameEqual() {
        Address address1 = new Address(1, "Alice", "Smith", "123-456-7890", testDate);
        Address address2 = new Address(2, "Bob", "Smith", "555-123-4567", testDate);
        
        int result = comparator.compare(address1, address2);
        
        assertTrue(result < 0, "Alice should come before Bob when lastname is same");
    }
    
    @Test
    @DisplayName("Should return 0 when addresses are identical")
    void testCompareIdenticalAddresses() {
        Address address1 = new Address(1, "John", "Doe", "123-456-7890", testDate);
        Address address2 = new Address(2, "John", "Doe", "555-123-4567", testDate);
        
        int result = comparator.compare(address1, address2);
        
        assertEquals(0, result, "Identical names should return 0");
    }
    
    @Test
    @DisplayName("Should handle case insensitive comparison")
    void testCaseInsensitiveComparison() {
        Address address1 = new Address(1, "john", "DOE", "123-456-7890", testDate);
        Address address2 = new Address(2, "JOHN", "doe", "555-123-4567", testDate);
        
        int result = comparator.compare(address1, address2);
        
        assertEquals(0, result, "Case should be ignored in comparison");
    }
    
    @Test
    @DisplayName("Should handle null addresses")
    void testNullAddresses() {
        Address address = new Address(1, "John", "Doe", "123-456-7890", testDate);
        
        assertEquals(0, comparator.compare(null, null), "Both null should return 0");
        assertTrue(comparator.compare(null, address) < 0, "Null should come before non-null");
        assertTrue(comparator.compare(address, null) > 0, "Non-null should come after null");
    }
    
    @Test
    @DisplayName("Should handle null lastname")
    void testNullLastname() {
        Address address1 = new Address(1, "John", null, "123-456-7890", testDate);
        Address address2 = new Address(2, "Jane", "Smith", "555-123-4567", testDate);
        Address address3 = new Address(3, "Bob", null, "777-888-9999", testDate);
        
        assertTrue(comparator.compare(address1, address2) < 0, "Null lastname should come before non-null lastname");
        assertTrue(comparator.compare(address1, address3) > 0, "John should come after Bob when both lastnames are null");
    }
    
    @Test
    @DisplayName("Should handle null firstname")
    void testNullFirstname() {
        Address address1 = new Address(1, null, "Smith", "123-456-7890", testDate);
        Address address2 = new Address(2, "John", "Smith", "555-123-4567", testDate);
        Address address3 = new Address(3, null, "Smith", "777-888-9999", testDate);
        
        assertTrue(comparator.compare(address1, address2) < 0, "Null firstname should come before non-null firstname when lastname is same");
        assertEquals(0, comparator.compare(address1, address3), "Both null firstnames should return 0 when lastname is same");
    }
    
    @Test
    @DisplayName("Should handle empty strings")
    void testEmptyStrings() {
        Address address1 = new Address(1, "", "Smith", "123-456-7890", testDate);
        Address address2 = new Address(2, "John", "", "555-123-4567", testDate);
        
        assertTrue(comparator.compare(address2, address1) < 0, "Empty lastname should come before non-empty lastname");
    }
    
    @Test
    @DisplayName("Should sort a list of addresses correctly")
    void testSortingList() {
        Address address1 = new Address(1, "John", "Zebra", "123-456-7890", testDate);
        Address address2 = new Address(2, "Alice", "Baker", "555-123-4567", testDate);
        Address address3 = new Address(3, "Bob", "Baker", "777-888-9999", testDate);
        Address address4 = new Address(4, "Charlie", "Adams", "444-555-6666", testDate);
        
        List<Address> addresses = Arrays.asList(address1, address2, address3, address4);
        Collections.sort(addresses, comparator);
        
        assertEquals("Adams", addresses.get(0).getLastname(), "Adams should be first");
        assertEquals("Alice", addresses.get(1).getFirstname(), "Alice Baker should be second");
        assertEquals("Bob", addresses.get(2).getFirstname(), "Bob Baker should be third");
        assertEquals("Zebra", addresses.get(3).getLastname(), "Zebra should be last");
    }
}