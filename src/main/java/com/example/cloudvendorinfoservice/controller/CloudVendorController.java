package com.example.cloudvendorinfoservice.controller;

import com.example.cloudvendorinfoservice.model.CloudVendor;
import com.example.cloudvendorinfoservice.response.ResponseHandler;
import com.example.cloudvendorinfoservice.service.CloudVendorService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cloudvendor")
public class CloudVendorController {
    private final CloudVendorService cloudVendorService;
    public CloudVendorController(CloudVendorService cloudVendorService) {
        this.cloudVendorService = cloudVendorService;
    }
    @GetMapping("{vendorId}")
    @Operation(description = "Provides cloud vendor details using specified id")
    public ResponseEntity<Object> getCloudVendorDetails(@PathVariable("vendorId") String vendorId){
        return ResponseHandler.responseBuilder("Requested Vendor Details Are Given Here",
                HttpStatus.OK,
                cloudVendorService.getCloudVendor(vendorId));
    }
    @GetMapping
    @Operation(description = "Provides details of all cloud vendors")
    public List<CloudVendor> getAllCloudVendorDetails(){
        return cloudVendorService.getAllCloudVendors();
    }
    @PostMapping
    @Operation(description = "Stores new cloud vendor details")
    public String createCloudVendorDetails(@RequestBody CloudVendor cloudVendor){
        cloudVendorService.createCloudVendor(cloudVendor);
        return "Cloud Vendor Created Successfully";
    }
    @PutMapping
    @Operation(description = "Updates cloud vendor details")
    public String updateCloudVendorDetails(@RequestBody CloudVendor cloudVendor){
        cloudVendorService.updateCloudVendor(cloudVendor);
        return "Cloud Vendor Updated Successfully";
    }
    @DeleteMapping("{vendorId}")
    @Operation(description = "Deletes cloud vendor details using specified id")
    public String deleteCloudVendorDetails(@PathVariable("vendorId") String vendorId){
        cloudVendorService.deleteCloudVendor(vendorId);
        return "Cloud Vendor Deleted Successfully";
    }
}
