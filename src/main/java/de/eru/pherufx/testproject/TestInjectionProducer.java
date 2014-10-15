/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.eru.pherufx.testproject;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.New;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;

/**
 *
 * @author Phili_000
 */
@ApplicationScoped
public class TestInjectionProducer {

    @Inject
    @New
    TestInjection t1;
    @Inject
    @New
    TestInjection t2;

    @Produces
    @Eins
    public TestInjection createEins() {
        return t1;
    }

    @Produces
    @Zwei
    public TestInjection createZwei() {
        return t2;
    }
}
