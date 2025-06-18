package com.example.cloudvendorinfoservice.service.impl;

import com.example.cloudvendorinfoservice.exception.CloudVendorNotFoundException;
import com.example.cloudvendorinfoservice.model.CloudVendor;
import com.example.cloudvendorinfoservice.repository.CloudVendorRepository;
import com.example.cloudvendorinfoservice.service.CloudVendorService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class CloudVendorServiceImplTest {

    @Mock
    private CloudVendorRepository cloudVendorRepository;
    private CloudVendorService cloudVendorService;
    private AutoCloseable autoCloseable;
    private CloudVendor cloudVendor;

    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        cloudVendorService = new CloudVendorServiceImpl(cloudVendorRepository);
        cloudVendor = new CloudVendor("1","Amazon","USA","9999999999");
    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    void testCreateCloudVendor() {
        mock(CloudVendor.class);
        mock(CloudVendorRepository.class);
        when(cloudVendorRepository.save(cloudVendor)).thenReturn(cloudVendor);

        assertThat(cloudVendorService.createCloudVendor(cloudVendor)).isEqualTo("Success");
    }

    @Test
    void testUpdateCloudVendor_Success() {
        mock(CloudVendor.class);
        mock(CloudVendorRepository.class);
        when(cloudVendorRepository.findById(cloudVendor.getVendorId())).thenReturn(Optional.of(cloudVendor));
        String result = cloudVendorService.updateCloudVendor(cloudVendor);
        verify(cloudVendorRepository, times(1)).save(cloudVendor);
        assertEquals(result, "Success");

    }

    @Test
    void testUpdateCloudVendor_NotFound() {
        mock(CloudVendor.class);
        mock(CloudVendorRepository.class);
        when(cloudVendorRepository.findById(cloudVendor.getVendorId())).thenReturn(Optional.empty());
        assertThrows(CloudVendorNotFoundException.class,() -> cloudVendorService.updateCloudVendor(cloudVendor));
        verify(cloudVendorRepository, never()).save(cloudVendor);

    }

    @Test
    void testGetCloudVendor_Success() {
        mock(CloudVendor.class);
        mock(CloudVendorRepository.class);
        when(cloudVendorRepository.findById(cloudVendor.getVendorId())).thenReturn(Optional.ofNullable(cloudVendor));
        assertThat(cloudVendorService.getCloudVendor(cloudVendor.getVendorId()).getVendorName()).isEqualTo(cloudVendor.getVendorName());
    }

    @Test
    void testGetCloudVendor_NotFound() {
        mock(CloudVendor.class);
        mock(CloudVendorRepository.class);
        when(cloudVendorRepository.findById(cloudVendor.getVendorId())).thenReturn(Optional.empty());
        assertThrows(CloudVendorNotFoundException.class,() -> cloudVendorService.getCloudVendor(cloudVendor.getVendorId()));
    }

    @Test
    void testGetByVendorName(){
        mock(CloudVendor.class);
        mock(CloudVendorRepository.class);
        when(cloudVendorRepository.findByVendorName("Amazon")).thenReturn(new ArrayList<CloudVendor>(Collections.singletonList(cloudVendor)));
        assertThat(cloudVendorService.getByVendorName("Amazon").size()).isEqualTo(1);
        assertThat(cloudVendorService.getByVendorName("Amazon").get(0).getVendorId()).isEqualTo(cloudVendor.getVendorId());
    }

    @Test
    void testGetAllCloudVendors() {
        mock(CloudVendor.class);
        mock(CloudVendorRepository.class);

        when(cloudVendorRepository.findAll()).thenReturn(new ArrayList<CloudVendor>(Collections.singletonList(cloudVendor)));
        assertThat(cloudVendorService.getAllCloudVendors().size()).isEqualTo(1);
        assertThat(cloudVendorService.getAllCloudVendors().get(0).getVendorPhoneNumber()).isEqualTo(cloudVendor.getVendorPhoneNumber());
    }

    @Test
    void testDeleteCloudVendor_Success() {
        mock(CloudVendor.class);
        mock(CloudVendorRepository.class, CALLS_REAL_METHODS);

        /*
         * If no not found exception case was present then can be tested as below
         *
         *  doAnswer(Answers.CALLS_REAL_METHODS).when(cloudVendorRepository).deleteById(any());
         * assertThat(cloudVendorService.deleteCloudVendor("1")).isEqualTo("Success");
         *
         * */

        when(cloudVendorRepository.findById(cloudVendor.getVendorId())).thenReturn(Optional.of(cloudVendor));

        String result = cloudVendorService.deleteCloudVendor(cloudVendor.getVendorId());

        verify(cloudVendorRepository, times(1)).deleteById(cloudVendor.getVendorId());
        assertEquals("Success", result);
    }

    @Test
    void testDeleteCloudVendor_NotFound() {
        String vendorId = "2";

        when(cloudVendorRepository.findById(vendorId)).thenReturn(Optional.empty());

        assertThrows(CloudVendorNotFoundException.class, () -> cloudVendorService.deleteCloudVendor(vendorId));

        verify(cloudVendorRepository, never()).deleteById(vendorId);
    }
}