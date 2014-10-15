/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.eru.pherufx.testproject;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;

/**
 *
 * @author Phili_000
 */
@ApplicationScoped
public class TestInjection {
    
    private int i = 1;
    
    @PostConstruct
    private void init(){
        i = 2;
    }
    
   public int getI(){
       return i;
   }
   
   public void setI(int i2){
       i = i2;
   }
}
