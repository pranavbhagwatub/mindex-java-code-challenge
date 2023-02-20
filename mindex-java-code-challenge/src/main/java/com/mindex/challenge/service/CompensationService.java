package com.mindex.challenge.service;
import com.mindex.challenge.data.Compensation;

/*
    This interface contains the abstract methods being called by the endpoints responsible for creating and reading the Compensation details
 */
public interface CompensationService {
    Compensation create(Compensation employee);
    Compensation read(String id);
}
