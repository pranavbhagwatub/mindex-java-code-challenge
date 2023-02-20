package com.mindex.challenge.controller;

import com.mindex.challenge.data.Compensation;
import com.mindex.challenge.service.CompensationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/*
    This controller class acts as an entry point for HTTP requests attempting to create/read Compensation data
 */
@RestController
public class CompensationController {
    private static final Logger LOG = LoggerFactory.getLogger(CompensationController.class);

    @Autowired
    private CompensationService compensationService;

    /*
        1. For Task 2 Part 1, created a new endpoint /compensation
        2. This is a POST endpoint.
        3. Request Input: This endpoint accepts an Compensation object as a input parameter
        4. Response Output: This endpoint creates a new Compensation record in the persistence layer of the application
        5. Please check the following google document which contains sample request and response for this endpoint- https://docs.google.com/document/d/1ytzQe4LXqocHPvNvlAK0b-mtcMFPCnSJ42Ssn_lSbYQ/edit?usp=sharing
     */
    @PostMapping("/compensation")
    public Compensation create(@RequestBody Compensation compensation) {
        LOG.debug("Received compensation create request for [{}]", compensation);

        return compensationService.create(compensation);
    }

    /*
        1. For Task 2 Part 2, created a new endpoint /compensation/{id}
        2. This is a GET endpoint.
        3. Request Input: This endpoint accepts an employee ID as a input parameter
        4. Response Output: This endpoint returns the Compensation object for the employee ID provided in the request
        5. Please check the following google document which contains sample request and response for this endpoint- https://docs.google.com/document/d/1ytzQe4LXqocHPvNvlAK0b-mtcMFPCnSJ42Ssn_lSbYQ/edit?usp=sharing
     */
    @GetMapping("/compensation/{id}")
    public Compensation read(@PathVariable String id) {
        LOG.debug("Received compensation read request for id [{}]", id);

        return compensationService.read(id);
    }
}
