package org.jesperancinha.string.paradigm.original.expression;

import static com.google.common.truth.Truth.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class RegionSpellingCheckTest {
	private static final String _1 = "1'";
	private static final String _2 = "2'";
	private static final String _3 = ",3";
	private static final String ALGARVE = "Algarve";
	private static final String RIBATEJO = "  Ribatejo";
	private static final String MINHO = "minho";

	@Test
	public void testCheckSpelling() {
		String[] regions = { ALGARVE, MINHO, RIBATEJO };
		String[] assignedNumbers = { _1, _2, _3 };
		List<RegionImpl> components = new ArrayList<>();
		for (String type : regions) {
			for (String size : assignedNumbers) {
				components.add(new RegionImpl(type, size));
			}
		}

		final RegionSpellingCheck check = new RegionSpellingCheck();
		for (RegionImpl component : components) {
			check.checkSpelling(component);
		}

		final List<RegionImpl> resultSpellingBeginCapitalRegions = check.getSpellingBeginCapitalRegions();
		assertThat(resultSpellingBeginCapitalRegions).hasSize(6);
		assertThat(resultSpellingBeginCapitalRegions.get(0).getRegionName()).startsWith(ALGARVE);
		assertThat(resultSpellingBeginCapitalRegions.get(1).getRegionName()).startsWith(ALGARVE);
		assertThat(resultSpellingBeginCapitalRegions.get(2).getRegionName()).startsWith(ALGARVE);
		assertThat(resultSpellingBeginCapitalRegions.get(3).getRegionName()).startsWith(RIBATEJO);
		assertThat(resultSpellingBeginCapitalRegions.get(4).getRegionName()).startsWith(RIBATEJO);
		assertThat(resultSpellingBeginCapitalRegions.get(5).getRegionName()).startsWith(RIBATEJO);

		final List<RegionImpl> resultSpellingDoubleCommaRegions = check.getSpellingDoubleCommaRegions();
		assertThat(resultSpellingDoubleCommaRegions).hasSize(3);
		for (RegionImpl region : resultSpellingDoubleCommaRegions) {
			assertThat(region.getAssignedNumber()).startsWith(_3);
		}

		final List<RegionImpl> resultSpellingWhiteSpacesRegions = check.getSpellingWhiteSpacesRegions();
		assertThat(resultSpellingWhiteSpacesRegions).hasSize(3);
		for (RegionImpl region : resultSpellingWhiteSpacesRegions) {
			assertThat(region.getRegionName()).startsWith(RIBATEJO);
		}
	}
}
