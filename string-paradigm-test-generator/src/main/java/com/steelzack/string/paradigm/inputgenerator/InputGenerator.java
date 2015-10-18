package com.steelzack.string.paradigm.inputgenerator;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
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
		final File directory = new File(options.getFolder());
		directory.mkdirs();
		final File outfile = new File(directory, options.getFilename());

		final InputStream streamId = new FileInputStream(options.getMovieIdFile());
		final InputStream streamYear = new FileInputStream(options.getMovieYearFile());
		final InputStream streamMovie = new FileInputStream(options.getMovieFile());
		final InputStream streamGenre = new FileInputStream(options.getMovieGenreFile());
		final InputStream streamLocation = new FileInputStream(options.getMovieLocationFile());
		final InputStream streamDirector = new FileInputStream(options.getMovieDirectorFile());
		final InputStream streamProducer = new FileInputStream(options.getMovieProducerFile());
		final InputStream streamActress = new FileInputStream(options.getMovieActressFile());
		final InputStream streamStuntWoman = new FileInputStream(options.getMovieStuntWomanFile());
		final InputStream streamActor = new FileInputStream(options.getMovieActorFile());
		final InputStream streamStuntMan = new FileInputStream(options.getMovieStuntManFile());
		final InputStream streamSong = new FileInputStream(options.getMovieSongFile());

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

		int maxNumberOfElements = options.getNumberOfElements();
		int nIds = options.getNumberOfParents();

		try (BufferedWriter writer = new BufferedWriter(new FileWriter(outfile))) {
			for (int i = 0; i < nIds; i++) {
				final StringBuffer bf = new StringBuffer();
				final int nYears = RANDOM.nextInt(maxNumberOfElements) + 1;
				for (int j = 0; j < nYears; j++) {
					int nMovies = RANDOM.nextInt(maxNumberOfElements) + 1;
					for (int k = 0; k < nMovies; k++) {
						int nGenres = RANDOM.nextInt(maxNumberOfElements) + 1;
						for (int j2 = 0; j2 < nGenres; j2++) {
							int nLocation = RANDOM.nextInt(maxNumberOfElements) + 1;
							for (int k3 = 0; k3 < nLocation; k3++) {
								int nDirector = RANDOM.nextInt(maxNumberOfElements) + 1;
								for (int k2 = 0; k2 < nDirector; k2++) {
									int nProducer = RANDOM.nextInt(maxNumberOfElements) + 1;
									for (int l = 0; l < nProducer; l++) {
										int nActress = RANDOM.nextInt(maxNumberOfElements) + 1;
										for (int l2 = 0; l2 < nActress; l2++) {
											int nStuntWoman = RANDOM.nextInt(maxNumberOfElements) + 1;
											for (int m = 0; m < nStuntWoman; m++) {
												int nActor = RANDOM.nextInt(maxNumberOfElements) + 1;
												for (int m2 = 0; m2 < nActor; m2++) {
													int nStuntMan = RANDOM.nextInt(maxNumberOfElements) + 1;
													for (int n = 0; n < nStuntMan; n++) {
														int nSong = RANDOM.nextInt(maxNumberOfElements) + 1;
														for (int n2 = 0; n2 < nSong; n2++) {

														}
													}
												}
											}
										}
									}
								}
							}
						}
					}
				}
				writer.write(bf.toString());
			}
		}
	}

	List<String> getFullFileList(InputStream io) throws IOException {
		List<String> list = new ArrayList<String>();
		try (BufferedReader br = new BufferedReader(new InputStreamReader(io, Charset.defaultCharset()))) {
			String testRead = br.readLine();
			while (testRead != null) {
				list.add(testRead);
				testRead = br.readLine();
			}
			br.close();
		}
		return list;
	}
}
