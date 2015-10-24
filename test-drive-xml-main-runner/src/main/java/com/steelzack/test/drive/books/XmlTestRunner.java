/**
 * 
 */
package com.steelzack.test.drive.books;

import org.kohsuke.args4j.CmdLineException;

/**
 * @author joao
 *
 */
public class XmlTestRunner {

	/**
	 * @param args
	 * @throws CmdLineException
	 */
	public static void main(String[] args) throws CmdLineException {
		final XmlTestRunnerWorker worker = new XmlTestRunnerWorker(args);
		if (!worker.isNoop()) {
			worker.runTests();
		}
	}
}
