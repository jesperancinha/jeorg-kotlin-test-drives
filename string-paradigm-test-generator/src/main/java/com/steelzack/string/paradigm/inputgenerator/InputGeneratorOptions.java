package com.steelzack.string.paradigm.inputgenerator;

import org.kohsuke.args4j.Option;

public class InputGeneratorOptions {
	@Option(name = "-p", aliases = "--number-of-parents", required = true)
	private int numberOfParents;

	@Option(name = "-n", aliases = "--number-of-elements-per-node", required = true)
	private int numberOfElements;

	@Option(name = "-d", aliases = "--directory", required = false)
	private String folder = "/tmp";

	@Option(name = "-f", aliases = "--filename", required = true)
	private String filename;

	/**
	 * A plain text file with the film year data
	 */
	@Option(name = "-mid", aliases = "--movie-id", required = false)
	private String movieIdFile = "sptg_movie.txt";

	/**
	 * A plain text file with the film year data
	 */
	@Option(name = "-my", aliases = "--movie-year", required = false)
	private String movieYearFile = "sptg_year.txt";

	/**
	 * A plain text file with the film location data
	 */
	@Option(name = "-ml", aliases = "--movie-location", required = false)
	private String movieLocationFile = "sptg_location.txt";

	/**
	 * A plain text file with the film location data
	 */
	@Option(name = "-mg", aliases = "--movie-genre", required = false)
	private String movieGenreFile = "sptg_genre.txt";

	/**
	 * A plain text file with the film director data
	 */
	@Option(name = "-md", aliases = "--movie-director", required = false)
	private String movieDirectorFile = "sptg_director.txt";

	/**
	 * A plain text file with the film producer data
	 */
	@Option(name = "-mp", aliases = "--movie-producer", required = false)
	private String movieProducerFile = "sptg_producer.txt";

	/**
	 * A plain text file with the actor data
	 */
	@Option(name = "-mar", aliases = "--movie-actor", required = false)
	private String movieActorFile = "sptg_actor.txt";

	/**
	 * A plain text file with the actress data
	 */
	@Option(name = "-mas", aliases = "--movie-actress", required = false)
	private String movieActressFile = "sptg_actress.txt";

	/**
	 * A plain text file with the stuntman data
	 */
	@Option(name = "-msman", aliases = "--movie-stunt-man", required = false)
	private String movieStuntManFile = "sptg_stunt_man.txt";

	/**
	 * A plain text file with the stuntwoman data
	 */
	@Option(name = "-mswoman", aliases = "--movie-stunt-woman", required = false)
	private String movieStuntWomanFile = "sptg_stunt_woman.txt";

	/**
	 * A plain text file with the song data
	 */
	@Option(name = "-msong", aliases = "--movie-song", required = false)
	private String movieSongFile = "sptg_song.txt";

	public int getNumberOfElements() {
		return numberOfElements;
	}

	public String getFolder() {
		return folder;
	}

	public String getFilename() {
		return filename;
	}

	public String getMovieActorFile() {
		return movieActorFile;
	}

	public String getMovieActressFile() {
		return movieActressFile;
	}

	public String getMovieIdFile() {
		return movieIdFile;
	}

	public String getMovieDirectorFile() {
		return movieDirectorFile;
	}

	public String getMovieGenreFile() {
		return movieGenreFile;
	}

	public String getMovieLocationFile() {
		return movieLocationFile;
	}

	public String getMovieProducerFile() {
		return movieProducerFile;
	}

	public String getMovieSongFile() {
		return movieSongFile;
	}

	public String getMovieStuntManFile() {
		return movieStuntManFile;
	}

	public String getMovieStuntWomanFile() {
		return movieStuntWomanFile;
	}

	public String getMovieYearFile() {
		return movieYearFile;
	}

	public int getNumberOfParents() {
		return numberOfParents;
	}
}
