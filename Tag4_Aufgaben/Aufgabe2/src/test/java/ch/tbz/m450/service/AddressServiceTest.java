package ch.tbz.m450.service;

import ch.tbz.m450.repository.Address;
import ch.tbz.m450.repository.AddressRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("AddressService Tests")
class AddressServiceTest {
    
    @Mock
    private AddressRepository addressRepository;
    
    @InjectMocks
    private AddressService addressService;
    
    private Address testAddress1;
    private Address testAddress2;
    private Address testAddress3;
    private Date testDate;
    
    @BeforeEach
    void setUp() {
        testDate = new Date();
        testAddress1 = new Address(1, "John", "Doe", "123-456-7890", testDate);
        testAddress2 = new Address(2, "Jane", "Smith", "555-123-4567", testDate);
        testAddress3 = new Address(3, "Bob", "Johnson", "777-888-9999", testDate);
    }
    
    @Test
    @DisplayName("Should save address successfully")
    void testSaveAddress() {
        when(addressRepository.save(any(Address.class))).thenReturn(testAddress1);
        
        Address savedAddress = addressService.save(testAddress1);
        
        assertNotNull(savedAddress);
        assertEquals(testAddress1.getId(), savedAddress.getId());
        assertEquals(testAddress1.getFirstname(), savedAddress.getFirstname());
        assertEquals(testAddress1.getLastname(), savedAddress.getLastname());
        
        verify(addressRepository, times(1)).save(testAddress1);
    }
    
    @Test
    @DisplayName("Should get all addresses sorted by lastname then firstname")
    void testGetAllAddresses() {
        List<Address> unsortedAddresses = Arrays.asList(testAddress3, testAddress1, testAddress2);
        when(addressRepository.findAll()).thenReturn(unsortedAddresses);
        
        List<Address> result = addressService.getAll();
        
        assertNotNull(result);
        assertEquals(3, result.size());
        
        verify(addressRepository, times(1)).findAll();
    }
    
    @Test
    @DisplayName("Should get empty list when no addresses exist")
    void testGetAllAddressesEmpty() {
        when(addressRepository.findAll()).thenReturn(Arrays.asList());
        
        List<Address> result = addressService.getAll();
        
        assertNotNull(result);
        assertEquals(0, result.size());
        
        verify(addressRepository, times(1)).findAll();
    }
    
    @Test
    @DisplayName("Should find address by id when exists")
    void testGetAddressExists() {
        when(addressRepository.findById(1)).thenReturn(Optional.of(testAddress1));
        
        Optional<Address> result = addressService.getAddress(1);
        
        assertTrue(result.isPresent());
        assertEquals(testAddress1.getId(), result.get().getId());
        assertEquals(testAddress1.getFirstname(), result.get().getFirstname());
        
        verify(addressRepository, times(1)).findById(1);
    }
    
    @Test
    @DisplayName("Should return empty optional when address does not exist")
    void testGetAddressNotExists() {
        when(addressRepository.findById(999)).thenReturn(Optional.empty());
        
        Optional<Address> result = addressService.getAddress(999);
        
        assertFalse(result.isPresent());
        
        verify(addressRepository, times(1)).findById(999);
    }
    
    @Test
    @DisplayName("Should handle null address save gracefully")
    void testSaveNullAddress() {
        when(addressRepository.save(null)).thenReturn(null);
        
        Address result = addressService.save(null);
        
        assertNull(result);
        verify(addressRepository, times(1)).save(null);
    }
    
    @Test
    @DisplayName("Should handle repository exception during save")
    void testSaveWithRepositoryException() {
        when(addressRepository.save(any(Address.class))).thenThrow(new RuntimeException("Database error"));
        
        assertThrows(RuntimeException.class, () -> {
            addressService.save(testAddress1);
        });
        
        verify(addressRepository, times(1)).save(testAddress1);
    }
    
    @Test
    @DisplayName("Should handle repository exception during findAll")
    void testGetAllWithRepositoryException() {
        when(addressRepository.findAll()).thenThrow(new RuntimeException("Database error"));
        
        assertThrows(RuntimeException.class, () -> {
            addressService.getAll();
        });
        
        verify(addressRepository, times(1)).findAll();
    }
    
    @Test
    @DisplayName("Should handle repository exception during findById")
    void testGetAddressByIdWithRepositoryException() {
        when(addressRepository.findById(1)).thenThrow(new RuntimeException("Database error"));
        
        assertThrows(RuntimeException.class, () -> {
            addressService.getAddress(1);
        });
        
        verify(addressRepository, times(1)).findById(1);
    }
}