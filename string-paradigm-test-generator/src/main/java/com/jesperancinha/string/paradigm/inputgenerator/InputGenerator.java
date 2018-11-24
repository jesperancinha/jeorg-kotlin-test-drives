package com.jesperancinha.string.paradigm.inputgenerator;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.Logger;

import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;

public class InputGenerator {
	private static final Logger LOG = Logger.getLogger(InputGenerator.class.getName());

	static final Random RANDOM = new Random();

	public static void main(String[] args) throws CmdLineException, IOException {
		final InputGeneratorOptions options = new InputGeneratorOptions();
		final CmdLineParser parser = new CmdLineParser(options);
		parser.parseArgument(args);

		final InputGenerator inputGenerator = new InputGenerator();

		inputGenerator.createTestFile(options);
	}

	public void createTestFile(final InputGeneratorOptions options) throws IOException {
		final File directory = new File(options.getFolder());
		directory.mkdirs();
		final File outfile = new File(directory, options.getFilename());

		final InputStream streamId = getStreamFromFileName(options.getMovieIdFile());
		final InputStream streamYear = getStreamFromFileName(options.getMovieYearFile());
		final InputStream streamMovie = getStreamFromFileName(options.getMovieFile());
		final InputStream streamGenre = getStreamFromFileName(options.getMovieGenreFile());
		final InputStream streamLocation = getStreamFromFileName(options.getMovieLocationFile());
		final InputStream streamDirector = getStreamFromFileName(options.getMovieDirectorFile());
		final InputStream streamProducer = getStreamFromFileName(options.getMovieProducerFile());
		final InputStream streamActress = getStreamFromFileName(options.getMovieActressFile());
		final InputStream streamStuntWoman = getStreamFromFileName(options.getMovieStuntWomanFile());
		final InputStream streamActor = getStreamFromFileName(options.getMovieActorFile());
		final InputStream streamStuntMan = getStreamFromFileName(options.getMovieStuntManFile());
		final InputStream streamSong = getStreamFromFileName(options.getMovieSongFile());

		final List<String> resultId = getFullFileList(streamId);
		final List<String> resultActor = getFullFileList(streamActor);
		final List<String> resultActress = getFullFileList(streamActress);
		final List<String> resultDirector = getFullFileList(streamDirector);
		final List<String> resultGenre = getFullFileList(streamGenre);
		final List<String> resultLocation = getFullFileList(streamLocation);
		final List<String> resultMovie = getFullFileList(streamMovie);
		final List<String> resultProducer = getFullFileList(streamProducer);
		final List<String> resultSong = getFullFileList(streamSong);
		final List<String> resultStuntMan = getFullFileList(streamStuntMan);
		final List<String> resultStuntWoman = getFullFileList(streamStuntWoman);
		final List<String> resultYear = getFullFileList(streamYear);

		int maxNumberOfElements = options.getNumberOfElements();
		int nIds = options.getNumberOfParents();

		try (BufferedWriter writer = new BufferedWriter(new FileWriter(outfile))) {
			for (int i = 0; i < nIds; i++) {
				StringBuffer bf = new StringBuffer();
				bf.append(getRandomStringFromArray(resultId).concat("" + i));
				final int nYears = RANDOM.nextInt(maxNumberOfElements) + 1;
				final String stringYear = bf.toString();
				for (int j = 0; j < nYears; j++) {
					bf.append(getRandomStringFromArrayPreColon(resultYear));
					final int nMovies = RANDOM.nextInt(maxNumberOfElements) + 1;
					final String stringMovie = bf.toString();
					for (int k = 0; k < nMovies; k++) {
						bf.append(getRandomStringFromArrayPreColon(resultMovie));
						final int nGenres = RANDOM.nextInt(maxNumberOfElements) + 1;
						final String stringGenre = bf.toString();
						for (int j2 = 0; j2 < nGenres; j2++) {
							bf.append(getRandomStringFromArrayPreColon(resultGenre));
							final int nLocation = RANDOM.nextInt(maxNumberOfElements) + 1;
							final String stringLocation = bf.toString();
							for (int k3 = 0; k3 < nLocation; k3++) {
								bf.append(getRandomStringFromArrayPreColon(resultLocation));
								final int nDirector = RANDOM.nextInt(maxNumberOfElements) + 1;
								final String stringDirector = bf.toString();
								for (int k2 = 0; k2 < nDirector; k2++) {
									bf.append(getRandomStringFromArrayPreColon(resultDirector));
									final int nProducer = RANDOM.nextInt(maxNumberOfElements) + 1;
									final String stringProducer = bf.toString();
									for (int l = 0; l < nProducer; l++) {
										bf.append(getRandomStringFromArrayPreColon(resultProducer));
										final int nActress = RANDOM.nextInt(maxNumberOfElements) + 1;
										final String stringActress = bf.toString();
										for (int l2 = 0; l2 < nActress; l2++) {
											bf.append(getRandomStringFromArrayPreColon(resultActress));
											final int nStuntWoman = RANDOM.nextInt(maxNumberOfElements) + 1;
											final String stringStuntWoman = bf.toString();
											for (int m = 0; m < nStuntWoman; m++) {
												bf.append(getRandomStringFromArrayPreColon(resultStuntWoman));
												final int nActor = RANDOM.nextInt(maxNumberOfElements) + 1;
												final String stringActor = bf.toString();
												for (int m2 = 0; m2 < nActor; m2++) {
													bf.append(getRandomStringFromArrayPreColon(resultActor));
													final int nStuntMan = RANDOM.nextInt(maxNumberOfElements) + 1;
													final String stringStuntMan = bf.toString();
													for (int n = 0; n < nStuntMan; n++) {
														bf.append(getRandomStringFromArrayPreColon(resultStuntMan));
														final int nSong = RANDOM.nextInt(maxNumberOfElements) + 1;
														final String stringSong = bf.toString();
														for (int n2 = 0; n2 < nSong; n2++) {
															bf.append(getRandomStringFromArrayPreColon(resultSong));
															writer.write(bf.toString());
															writer.write("\n");
															bf = new StringBuffer();
															bf.append(stringSong);
														}
														bf = new StringBuffer();
														bf.append(stringStuntMan);
													}
													bf = new StringBuffer();
													bf.append(stringActor);
												}
												bf = new StringBuffer();
												bf.append(stringStuntWoman);
											}
											bf = new StringBuffer();
											bf.append(stringActress);
										}
										bf = new StringBuffer();
										bf.append(stringProducer);
									}
									bf = new StringBuffer();
									bf.append(stringDirector);
								}
								bf = new StringBuffer();
								bf.append(stringLocation);
							}
							bf = new StringBuffer();
							bf.append(stringGenre);
						}
						bf = new StringBuffer();
						bf.append(stringMovie);
					}
					bf = new StringBuffer();
					bf.append(stringYear);
				}
			}
		}
		LOG.info("Completed test file generation");

	}

	InputStream getStreamFromFileName(final String filename) {
		return getClass().getResourceAsStream(filename);
	}

	private static String getRandomStringFromArrayPreColon(List<String> results) {
		return ";".concat(getRandomStringFromArray(results));
	}

	private static String getRandomStringFromArray(List<String> results) {
		return results.get(RANDOM.nextInt(results.size()));
	}

	public List<String> getFullFileList(InputStream io) throws IOException {
		List<String> list = new ArrayList<>();
		try (BufferedReader br = new BufferedReader(new InputStreamReader(io, Charset.defaultCharset()))) {
			String testRead = br.readLine();
			while (testRead != null) {
				list.add(testRead);
				testRead = br.readLine();
			}
		}
		return list;
	}
}
