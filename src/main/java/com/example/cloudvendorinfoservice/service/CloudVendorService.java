package com.example.cloudvendorinfoservice.service;

import com.example.cloudvendorinfoservice.model.CloudVendor;

import java.util.List;

public interface CloudVendorService {
    public String createCloudVendor(CloudVendor cloudVendor);
    public String updateCloudVendor(CloudVendor cloudVendor);
    public String deleteCloudVendor(String vendorId);
    public CloudVendor getCloudVendor(String vendorId);
    public List<CloudVendor> getAllCloudVendors();
    public List<CloudVendor> getByVendorName(String vendorName);
}
