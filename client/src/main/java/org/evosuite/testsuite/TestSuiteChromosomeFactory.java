/**
 * Copyright (C) 2011,2012 Gordon Fraser, Andrea Arcuri and EvoSuite
 * contributors
 *
 * This file is part of EvoSuite.
 *
 * EvoSuite is free software: you can redistribute it and/or modify it under the
 * terms of the GNU Public License as published by the Free Software Foundation,
 * either version 3 of the License, or (at your option) any later version.
 *
 * EvoSuite is distributed in the hope that it will be useful, but WITHOUT ANY
 * WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR
 * A PARTICULAR PURPOSE. See the GNU Public License for more details.
 *
 * You should have received a copy of the GNU Public License along with
 * EvoSuite. If not, see <http://www.gnu.org/licenses/>.
 */
package org.evosuite.testsuite;

import org.evosuite.Properties;
import org.evosuite.ga.ChromosomeFactory;
import org.evosuite.testcase.RandomLengthTestFactory;
import org.evosuite.testcase.TestChromosome;
import org.evosuite.utils.Randomness;


/**
 * <p>TestSuiteChromosomeFactory class.</p>
 *
 * @author Gordon Fraser
 */
public class TestSuiteChromosomeFactory implements ChromosomeFactory<TestSuiteChromosome> {

	private static final long serialVersionUID = -3769862881038106087L;

	/** Factory to manipulate and generate method sequences */
	private ChromosomeFactory<TestChromosome> testChromosomeFactory;

	/**
	 * <p>Constructor for TestSuiteChromosomeFactory.</p>
	 */
	public TestSuiteChromosomeFactory() {
		testChromosomeFactory = new RandomLengthTestFactory();
	}

	/**
	 * <p>Constructor for TestSuiteChromosomeFactory.</p>
	 *
	 * @param testFactory a {@link org.evosuite.ga.ChromosomeFactory} object.
	 */
	public TestSuiteChromosomeFactory(ChromosomeFactory<TestChromosome> testFactory) {
		testChromosomeFactory = testFactory;

		// test_factory = new RandomLengthTestFactory();
		// test_factory = new AllMethodsChromosomeFactory();
		// test_factory = new OUMTestChromosomeFactory();
	}

	/**
	 * <p>setTestFactory</p>
	 *
	 * @param factory a {@link org.evosuite.ga.ChromosomeFactory} object.
	 */
	public void setTestFactory(ChromosomeFactory<TestChromosome> factory) {
		testChromosomeFactory = factory;
	}

	/** {@inheritDoc} */
	@Override
	public TestSuiteChromosome getChromosome() {

		TestSuiteChromosome chromosome = new TestSuiteChromosome(
				testChromosomeFactory);
		chromosome.tests.clear();
		CurrentChromosomeTracker<?> tracker = CurrentChromosomeTracker.getInstance();
		tracker.modification(chromosome);
		// ((AllMethodsChromosomeFactory)test_factory).clear();

		int numTests = Randomness.nextInt(Properties.MIN_INITIAL_TESTS,
		                                  Properties.MAX_INITIAL_TESTS + 1);

		for (int i = 0; i < numTests; i++) {
			TestChromosome test = testChromosomeFactory.getChromosome();
			chromosome.addTest(test);
			//chromosome.tests.add(test);
		}
		// logger.info("Covered methods: "+((AllMethodsChromosomeFactory)test_factory).covered.size());
		// logger.trace("Generated new test suite:"+chromosome);
		return chromosome;
	}
}
