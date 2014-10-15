/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.eru.pherufx.cdi;

import javafx.application.Application.Parameters;
import javax.enterprise.inject.Produces;
import javax.inject.Singleton;

/**
 *
 * @author Phili_000
 */
@Singleton
public class ApplicationParametersProvider {
	private Parameters parameters;

	void setParameters(Parameters p) {
		this.parameters = p;
	}
	
	public @Produces Parameters getParameters() {
		return parameters;
	}
}

