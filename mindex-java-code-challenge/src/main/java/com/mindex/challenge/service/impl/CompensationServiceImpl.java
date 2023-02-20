package com.mindex.challenge.service.impl;

import com.mindex.challenge.dao.CompensationRepository;
import com.mindex.challenge.data.Compensation;
import com.mindex.challenge.service.CompensationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/*
    This class contains the actual implementation for the endpoints responsible for creating and reading the Compensation details
 */

@Service
public class CompensationServiceImpl implements CompensationService {
    private static final Logger LOG = LoggerFactory.getLogger(CompensationServiceImpl.class);

    @Autowired
    private CompensationRepository compensationRepository;

    /*
        This method inserts a new Compensation record in the persistence layer
     */
    public Compensation create(Compensation compensation) {
        LOG.debug("Creating compensation [{}]", compensation);
        compensationRepository.insert(compensation);
        return compensation;
    }

    /*
        This method reads a Compensation record for the supplied employee ID from the persistence layer
     */
    public Compensation read(String id) {
        LOG.debug("Reading compensation with employee id [{}]", id);
        Compensation compensation = compensationRepository.findByEmployeeEmployeeId(id);

        if(compensation == null)
            throw new RuntimeException("Invalid employee Id: " + id);

        return compensation;
    }
}
