package org.jesperancinha.string.paradigm.original.expression;

import static com.google.common.truth.Truth.assertThat;

import java.util.List;

import org.junit.Test;

public class SpellingCheckImplTest {

	private static final String _1 = "1'";
	private static final String _2 = "2'";
	private static final String _3 = ",3";
	private static final String ALGARVE = "Algarve";
	private static final String EXTREMADURA = "Tras Os Montes E Alto Douro, , extremadura";
	private static final String RIBATEJO = "  Ribatejo";
	private static final String MINHO = "minho";

	@Test
	public void testCheckSpelling() {
		String[] regions = { ALGARVE, MINHO, RIBATEJO, EXTREMADURA };
		String[] assignedNumbers = { _1, _2, _3 };
		SpellingCheckImpl check = new SpellingCheckImpl();
		for (String region : regions) {
			check.checkSpelling(region);
		}
		for (String number : assignedNumbers) {
			check.checkSpelling(number);
		}

		final List<String> resultSpellingBeginCapitalRegions = check.getSpellingBeginCapitalRegions();
		assertThat(resultSpellingBeginCapitalRegions.get(0)).startsWith(ALGARVE);
		assertThat(resultSpellingBeginCapitalRegions.get(1)).startsWith(RIBATEJO);

		final List<String> resultSpellingDoubleCommaRegions = check.getSpellingDoubleCommaRegions();
		assertThat(resultSpellingDoubleCommaRegions).hasSize(1);
		for (String stringResult : resultSpellingDoubleCommaRegions) {
			assertThat(stringResult).startsWith(EXTREMADURA);
		}

		final List<String> resultSpellingWhiteSpacesRegions = check.getSpellingWhiteSpacesRegions();
		assertThat(resultSpellingWhiteSpacesRegions).hasSize(1);
		for (String stringResult : resultSpellingWhiteSpacesRegions) {
			assertThat(stringResult).startsWith(RIBATEJO);
		}
	}
}
