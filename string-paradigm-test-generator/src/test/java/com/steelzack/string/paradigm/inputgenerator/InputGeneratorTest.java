package com.steelzack.string.paradigm.inputgenerator;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.junit.Test;

public class InputGeneratorTest {

	@Test
	public void testInputGenerator() throws IOException, InterruptedException {
		final String testFilename = "testInputGenerator.txt";
		final Runtime rt = Runtime.getRuntime();
		final Process pr = rt.exec("java -jar build/one-string-paradigm-input-test-generator.jar  -p 100 -n 10 -f \""
				+ testFilename + "\"");
		final int retVal = pr.waitFor();

		assertEquals(0, retVal);
	}

	@Test
	public void testGetFullFileList() throws Exception {
		final InputStream streamId = getClass().getResourceAsStream("sptg_id.txt");
		final InputStream streamActor = getClass().getResourceAsStream("sptg_actor.txt");
		final InputStream streamActress = getClass().getResourceAsStream("sptg_actress.txt");
		final InputStream streamDirector = getClass().getResourceAsStream("sptg_director.txt");
		final InputStream streamGenre = getClass().getResourceAsStream("sptg_genre.txt");
		final InputStream streamLocation = getClass().getResourceAsStream("sptg_location.txt");
		final InputStream streamMovie = getClass().getResourceAsStream("sptg_movie.txt");
		final InputStream streamProducer = getClass().getResourceAsStream("sptg_producer.txt");
		final InputStream streamSong = getClass().getResourceAsStream("sptg_song.txt");
		final InputStream streamStuntMan = getClass().getResourceAsStream("sptg_stunt_man.txt");
		final InputStream streamStuntWoman = getClass().getResourceAsStream("sptg_stunt_woman.txt");
		final InputStream streamYear = getClass().getResourceAsStream("sptg_year.txt");

		final InputGenerator inputGenerator = new InputGenerator();

		final List<String> resultId = inputGenerator.getFullFileList(streamId);
		final List<String> resultActor = inputGenerator.getFullFileList(streamActor);
		final List<String> resultActress = inputGenerator.getFullFileList(streamActress);
		final List<String> resultDirector = inputGenerator.getFullFileList(streamDirector);
		final List<String> resultGenre = inputGenerator.getFullFileList(streamGenre);
		final List<String> resultLocation = inputGenerator.getFullFileList(streamLocation);
		final List<String> resultMovie = inputGenerator.getFullFileList(streamMovie);
		final List<String> resultProducer = inputGenerator.getFullFileList(streamProducer);
		final List<String> resultSong = inputGenerator.getFullFileList(streamSong);
		final List<String> resultStuntMan = inputGenerator.getFullFileList(streamStuntMan);
		final List<String> resultStuntWoman = inputGenerator.getFullFileList(streamStuntWoman);
		final List<String> resultYear = inputGenerator.getFullFileList(streamYear);

		assertThat(resultId, hasSize(5));
		assertThat(resultActor, hasSize(8));
		assertThat(resultActress, hasSize(4));
		assertThat(resultDirector, hasSize(5));
		assertThat(resultGenre, hasSize(4));
		assertThat(resultLocation, hasSize(12));
		assertThat(resultMovie, hasSize(6));
		assertThat(resultProducer, hasSize(6));
		assertThat(resultSong, hasSize(7));
		assertThat(resultStuntMan, hasSize(6));
		assertThat(resultStuntWoman, hasSize(1));
		assertThat(resultYear, hasSize(12));
	}
}
