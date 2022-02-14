/**
 * 
 */
package org.jesperancinha.test.drive.books;

/**
 * @author joao
 *
 */
public class XmlTestRunner {

	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		final XmlTestRunnerWorker worker = new XmlTestRunnerWorker(args);
		if (!worker.isNoop()) {
			worker.runTests();
		}
	}
}
