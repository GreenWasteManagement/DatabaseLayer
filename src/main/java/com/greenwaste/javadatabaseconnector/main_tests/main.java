package com.greenwaste.javadatabaseconnector.main_tests;

import com.greenwaste.javadatabaseconnector.services.BucketService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class main implements CommandLineRunner {

    private final BucketService bucketService;

    public main(BucketService bucketService) {
        this.bucketService = bucketService;
    }

    @Override
    public void run(String... args) throws Exception {
        // Insert test data
        bucketService.insertTestData();

        // Fetch and print all buckets
        System.out.println("Fetching all buckets:");
        System.out.println("|-----------------------Buckets Found: ");
        bucketService.findAllBuckets().forEach(bucket ->
                System.out.println("Bucket" + bucket.getId() + " - " + bucket.getId() + " (" + bucket.getCapacity() + "L)")
        );

        // Count buckets
        System.out.println("Total Buckets: " + bucketService.countBuckets());
    }

}

