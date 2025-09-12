package ch.tbz.m450.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Address Entity Tests")
class AddressTest {
    
    private Address address;
    private Date testDate;
    
    @BeforeEach
    void setUp() {
        testDate = new Date();
        address = new Address();
    }
    
    @Test
    @DisplayName("Should create address with default constructor")
    void testDefaultConstructor() {
        Address newAddress = new Address();
        assertNotNull(newAddress);
    }
    
    @Test
    @DisplayName("Should create address with all arguments constructor")
    void testAllArgsConstructor() {
        Address newAddress = new Address(1, "John", "Doe", "123-456-7890", testDate);
        
        assertEquals(1, newAddress.getId());
        assertEquals("John", newAddress.getFirstname());
        assertEquals("Doe", newAddress.getLastname());
        assertEquals("123-456-7890", newAddress.getPhonenumber());
        assertEquals(testDate, newAddress.getRegistrationDate());
    }
    
    @Test
    @DisplayName("Should set and get id correctly")
    void testIdSetterGetter() {
        address.setId(42);
        assertEquals(42, address.getId());
    }
    
    @Test
    @DisplayName("Should set and get firstname correctly")
    void testFirstnameSetterGetter() {
        address.setFirstname("Jane");
        assertEquals("Jane", address.getFirstname());
    }
    
    @Test
    @DisplayName("Should set and get lastname correctly")
    void testLastnameSetterGetter() {
        address.setLastname("Smith");
        assertEquals("Smith", address.getLastname());
    }
    
    @Test
    @DisplayName("Should set and get phone number correctly")
    void testPhonenumberSetterGetter() {
        address.setPhonenumber("555-123-4567");
        assertEquals("555-123-4567", address.getPhonenumber());
    }
    
    @Test
    @DisplayName("Should set and get registration date correctly")
    void testRegistrationDateSetterGetter() {
        address.setRegistrationDate(testDate);
        assertEquals(testDate, address.getRegistrationDate());
    }
    
    @Test
    @DisplayName("Should handle null values properly")
    void testNullValues() {
        address.setFirstname(null);
        address.setLastname(null);
        address.setPhonenumber(null);
        address.setRegistrationDate(null);
        
        assertNull(address.getFirstname());
        assertNull(address.getLastname());
        assertNull(address.getPhonenumber());
        assertNull(address.getRegistrationDate());
    }
    
    @Test
    @DisplayName("Should handle empty strings properly")
    void testEmptyStrings() {
        address.setFirstname("");
        address.setLastname("");
        address.setPhonenumber("");
        
        assertEquals("", address.getFirstname());
        assertEquals("", address.getLastname());
        assertEquals("", address.getPhonenumber());
    }
}