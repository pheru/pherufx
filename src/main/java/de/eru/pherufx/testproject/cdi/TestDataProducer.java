/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.eru.pherufx.testproject.cdi;

import de.eru.pherufx.testproject.data.TestData;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.New;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;

/**
 *
 * @author Phili_000
 */
@ApplicationScoped
public class TestDataProducer {

    @Inject
    @New
    TestData t1;
    @Inject
    @New
    TestData t2;
    @Inject
    @New
    TestData t3;

    @Produces
    @TestQualifier(dataType = TestQualifier.DataType.ONE)
    public TestData createOne() {
        return t1;
    }

    @Produces
    @TestQualifier(dataType = TestQualifier.DataType.TWO)
    public TestData createTwo() {
        return t2;
    }
    
    @Produces
    @TestQualifier(dataType = TestQualifier.DataType.THREE)
    public TestData createThree() {
        return t3;
    }
}
