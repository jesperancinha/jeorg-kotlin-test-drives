package org.jesperancinha.string.paradigm.inputgenerator;

import static com.google.common.truth.Truth.assertThat;

import java.io.InputStream;
import java.util.List;
import java.util.Stack;

import org.junit.Test;

public class InputGeneratorTest {

	private static final String TEST_FILMS_TXT = "testFilms.txt";

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

		assertThat(resultId).hasSize(5);
		assertThat(resultActor).hasSize(8);
		assertThat(resultActress).hasSize(4);
		assertThat(resultDirector).hasSize(5);
		assertThat(resultGenre).hasSize(4);
		assertThat(resultLocation).hasSize(12);
		assertThat(resultMovie).hasSize(6);
		assertThat(resultProducer).hasSize(6);
		assertThat(resultSong).hasSize(7);
		assertThat(resultStuntMan).hasSize(6);
		assertThat(resultStuntWoman).hasSize(1);
		assertThat(resultYear).hasSize(12);
	}

	@Test
	public void testCreateTestFile() throws Exception {
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

		Stack<InputStream> stack = new Stack<>();
		stack.push(streamSong);
		stack.push(streamStuntMan);
		stack.push(streamActor);
		stack.push(streamStuntWoman);
		stack.push(streamActress);
		stack.push(streamProducer);
		stack.push(streamDirector);
		stack.push(streamLocation);
		stack.push(streamGenre);
		stack.push(streamMovie);
		stack.push(streamYear);
		stack.push(streamId);

		final InputGeneratorOptions testOptions = new InputGeneratorOptions() {
			@Override
			public String getFilename() {
				return TEST_FILMS_TXT;
			}

			@Override
			public int getNumberOfParents() {
				return 10;
			}

			@Override
			public int getNumberOfElements() {
				return 2;
			}
		};

		final InputGenerator inputGenerator = new InputGenerator();
		inputGenerator.createTestFile(testOptions);

	}
}
