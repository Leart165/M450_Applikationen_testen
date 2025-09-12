package ch.tbz.m450.controller;

import ch.tbz.m450.repository.Address;
import ch.tbz.m450.service.AddressService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(AddressController.class)
@DisplayName("AddressController Tests")
class AddressControllerTest {
    
    @Autowired
    private MockMvc mockMvc;
    
    @MockBean
    private AddressService addressService;
    
    @Autowired
    private ObjectMapper objectMapper;
    
    private Address testAddress1;
    private Address testAddress2;
    private Date testDate;
    
    @BeforeEach
    void setUp() {
        testDate = new Date();
        testAddress1 = new Address(1, "John", "Doe", "123-456-7890", testDate);
        testAddress2 = new Address(2, "Jane", "Smith", "555-123-4567", testDate);
    }
    
    @Test
    @DisplayName("Should create address successfully")
    void testCreateAddress() throws Exception {
        when(addressService.save(any(Address.class))).thenReturn(testAddress1);
        
        mockMvc.perform(post("/address")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(testAddress1)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.firstname").value("John"))
                .andExpect(jsonPath("$.lastname").value("Doe"))
                .andExpect(jsonPath("$.phonenumber").value("123-456-7890"));
        
        verify(addressService, times(1)).save(any(Address.class));
    }
    
    @Test
    @DisplayName("Should get all addresses successfully")
    void testGetAllAddresses() throws Exception {
        List<Address> addresses = Arrays.asList(testAddress1, testAddress2);
        when(addressService.getAll()).thenReturn(addresses);
        
        mockMvc.perform(get("/address"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].firstname").value("John"))
                .andExpect(jsonPath("$[1].firstname").value("Jane"));
        
        verify(addressService, times(1)).getAll();
    }
    
    @Test
    @DisplayName("Should get empty list when no addresses exist")
    void testGetAllAddressesEmpty() throws Exception {
        when(addressService.getAll()).thenReturn(Arrays.asList());
        
        mockMvc.perform(get("/address"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(0));
        
        verify(addressService, times(1)).getAll();
    }
    
    @Test
    @DisplayName("Should get address by id successfully")
    void testGetAddressById() throws Exception {
        when(addressService.getAddress(1)).thenReturn(Optional.of(testAddress1));
        
        mockMvc.perform(get("/address/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.firstname").value("John"))
                .andExpect(jsonPath("$.lastname").value("Doe"));
        
        verify(addressService, times(1)).getAddress(1);
    }
    
    @Test
    @DisplayName("Should return 404 when address not found")
    void testGetAddressByIdNotFound() throws Exception {
        when(addressService.getAddress(999)).thenReturn(Optional.empty());
        
        mockMvc.perform(get("/address/999"))
                .andExpect(status().isNotFound());
        
        verify(addressService, times(1)).getAddress(999);
    }
    
    @Test
    @DisplayName("Should handle invalid JSON in create request")
    void testCreateAddressInvalidJson() throws Exception {
        mockMvc.perform(post("/address")
                .contentType(MediaType.APPLICATION_JSON)
                .content("invalid json"))
                .andExpect(status().isBadRequest());
        
        verify(addressService, never()).save(any(Address.class));
    }
    
    @Test
    @DisplayName("Should handle service exception during create")
    void testCreateAddressServiceException() throws Exception {
        when(addressService.save(any(Address.class))).thenThrow(new RuntimeException("Service error"));
        
        try {
            mockMvc.perform(post("/address")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(testAddress1)));
        } catch (Exception e) {
            assertTrue(e.getCause() instanceof RuntimeException);
            assertEquals("Service error", e.getCause().getMessage());
        }
        
        verify(addressService, times(1)).save(any(Address.class));
    }
    
    @Test
    @DisplayName("Should handle service exception during getAll")
    void testGetAllAddressesServiceException() throws Exception {
        when(addressService.getAll()).thenThrow(new RuntimeException("Service error"));
        
        try {
            mockMvc.perform(get("/address"));
        } catch (Exception e) {
            assertTrue(e.getCause() instanceof RuntimeException);
            assertEquals("Service error", e.getCause().getMessage());
        }
        
        verify(addressService, times(1)).getAll();
    }
    
    @Test
    @DisplayName("Should handle service exception during getById")
    void testGetAddressByIdServiceException() throws Exception {
        when(addressService.getAddress(1)).thenThrow(new RuntimeException("Service error"));
        
        try {
            mockMvc.perform(get("/address/1"));
        } catch (Exception e) {
            assertTrue(e.getCause() instanceof RuntimeException);
            assertEquals("Service error", e.getCause().getMessage());
        }
        
        verify(addressService, times(1)).getAddress(1);
    }
    
    @Test
    @DisplayName("Should create address with null phone number")
    void testCreateAddressWithNullPhone() throws Exception {
        Address addressWithNullPhone = new Address(1, "John", "Doe", null, testDate);
        when(addressService.save(any(Address.class))).thenReturn(addressWithNullPhone);
        
        mockMvc.perform(post("/address")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(addressWithNullPhone)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.phonenumber").isEmpty());
        
        verify(addressService, times(1)).save(any(Address.class));
    }
    
    @Test
    @DisplayName("Should handle invalid path parameter")
    void testGetAddressInvalidPathParameter() throws Exception {
        mockMvc.perform(get("/address/abc"))
                .andExpect(status().isBadRequest());
        
        verify(addressService, never()).getAddress(anyInt());
    }
}