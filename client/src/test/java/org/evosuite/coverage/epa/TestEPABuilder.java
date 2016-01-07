package org.evosuite.coverage.epa;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.junit.Assume;
import org.junit.Test;
import org.xml.sax.SAXException;

public class TestEPABuilder {

	@Test
	public void testListItr() throws FileNotFoundException, ParserConfigurationException, SAXException, IOException {
		final String xmlFilename = String.join(File.separator, System.getProperty("user.dir"), "src", "test", "resources",
				"epas", "ListItr.xml");
		final File epaXMLFile = new File(xmlFilename);
		Assume.assumeTrue(epaXMLFile.exists());

		EPA epa = EPAFactory.buildEPA(xmlFilename);

		int stateCount = epa.getNumberOfStates();
		assertEquals(8, stateCount);

		int transitionCount = epa.getNumberOfTransitions();
		assertEquals(37, transitionCount);
	}
	
	@Test
	public void testMyBoundedStack() throws FileNotFoundException, ParserConfigurationException, SAXException, IOException {
		final String xmlFilename = String.join(File.separator, System.getProperty("user.dir"), "src", "test", "resources",
				"epas", "MyBoundedStack.xml");
		final File epaXMLFile = new File(xmlFilename);
		Assume.assumeTrue(epaXMLFile.exists());

		EPA epa = EPAFactory.buildEPA(xmlFilename);

		int stateCount = epa.getNumberOfStates();
		assertEquals(4, stateCount);

		int transitionCount = epa.getNumberOfTransitions();
		assertEquals(6, transitionCount);
	}
	
	
}
