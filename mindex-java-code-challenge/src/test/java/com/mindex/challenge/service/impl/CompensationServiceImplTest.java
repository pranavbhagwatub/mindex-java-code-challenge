package com.mindex.challenge.service.impl;
import com.mindex.challenge.data.Compensation;
import com.mindex.challenge.data.Employee;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/*
    This class contains the unit tests to verify proper functioning for the endpoints responsible for creating and reading the Compensation details
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CompensationServiceImplTest {

    private String compensationCreateUrl;
    private String compensationReadUrl;

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Before
    public void setup() {
        compensationCreateUrl = "http://localhost:" + port + "/compensation";
        compensationReadUrl = "http://localhost:" + port + "/compensation/{id}";
    }

    /*
        This unit test has following two goals-
        1. Verify the proper functioning of '/compensation' endpoint and ensure that a new Compensation object is being created properly
        2. Verify the proper functioning of '/compensation/{id}' endpoint and ensure that the respective Compensation object is being read and returned properly for the given Employee ID
     */
    @Test
    public void testCompensationCreateRead() {
        Employee testEmployee = new Employee();
        testEmployee.setEmployeeId("1");
        testEmployee.setFirstName("John");
        testEmployee.setLastName("Doe");
        testEmployee.setDepartment("Engineering");
        testEmployee.setPosition("Developer");
        String salary = "10,000 $";
        String effectiveDate = "02/19/2023";

        //Build compensation object
        Compensation objCompensation = new Compensation();
        objCompensation.setEmployee(testEmployee);
        objCompensation.setSalary(salary);
        objCompensation.setEffectiveDate(effectiveDate);

        // Create compensation checks
        Compensation createdCompensation = restTemplate.postForEntity(compensationCreateUrl, objCompensation, Compensation.class).getBody();
        assertNotNull(createdCompensation.getEmployee());
        assertCompensationEquivalence(objCompensation, createdCompensation);

        // Read compensation checks
        Compensation readCompensation = restTemplate.getForEntity(compensationReadUrl, Compensation.class, createdCompensation.getEmployee().getEmployeeId()).getBody();
        assertEquals(createdCompensation.getEmployee().getEmployeeId(), readCompensation.getEmployee().getEmployeeId());
        assertCompensationEquivalence(createdCompensation, readCompensation);
    }

    private static void assertCompensationEquivalence(Compensation expected, Compensation actual) {
        assertEquals(expected.getEmployee().getFirstName(), actual.getEmployee().getFirstName());
        assertEquals(expected.getEmployee().getLastName(), actual.getEmployee().getLastName());
        assertEquals(expected.getEmployee().getDepartment(), actual.getEmployee().getDepartment());
        assertEquals(expected.getEmployee().getPosition(), actual.getEmployee().getPosition());
        assertEquals(expected.getSalary(), actual.getSalary());
        assertEquals(expected.getSalary(), actual.getSalary());
    }
}
