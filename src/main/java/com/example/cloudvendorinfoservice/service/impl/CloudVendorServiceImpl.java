package com.example.cloudvendorinfoservice.service.impl;

import com.example.cloudvendorinfoservice.exception.CloudVendorNotFoundException;
import com.example.cloudvendorinfoservice.model.CloudVendor;
import com.example.cloudvendorinfoservice.repository.CloudVendorRepository;
import com.example.cloudvendorinfoservice.service.CloudVendorService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CloudVendorServiceImpl implements CloudVendorService {
    CloudVendorRepository cloudVendorRepository;
    public CloudVendorServiceImpl(CloudVendorRepository cloudVendorRepository) {
        this.cloudVendorRepository = cloudVendorRepository;
    }
    @Override
    public String createCloudVendor(CloudVendor cloudVendor) {
        cloudVendorRepository.save(cloudVendor);
        return "Success";
    }
    @Override
    public String updateCloudVendor(CloudVendor cloudVendor) {
        if(cloudVendorRepository.findById(cloudVendor.getVendorId()).isEmpty()){
            throw new CloudVendorNotFoundException("Requested Cloud Vendor Does Not Exist");
        }
        cloudVendorRepository.save(cloudVendor);
        return "Success";
    }

    @Override
    public String deleteCloudVendor(String vendorId) {
        if(cloudVendorRepository.findById(vendorId).isEmpty()){
            throw new CloudVendorNotFoundException("Requested Cloud Vendor Does Not Exist");
        }
        cloudVendorRepository.deleteById(vendorId);
        return "Success";
    }

    @Override
    public CloudVendor getCloudVendor(String vendorId) {
        if(cloudVendorRepository.findById(vendorId).isEmpty()){
            throw  new CloudVendorNotFoundException("Requested Cloud Vendor Does Not Exist");
        }
        return cloudVendorRepository.findById(vendorId).get();
    }

    @Override
    public List<CloudVendor> getAllCloudVendors() {
        return cloudVendorRepository.findAll();
    }

    @Override
    public List<CloudVendor> getByVendorName(String vendorName) {
        return cloudVendorRepository.findByVendorName(vendorName);
    }
}
