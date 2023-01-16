package org.jesperancinha.string.paradigm.inputgenerator;

import org.kohsuke.args4j.Option;

public class InputGeneratorOptions {
	private static final String SPTG_ID_TXT = "sptg_id.txt";

	private static final String SPTG_MOVIE_TXT = "sptg_movie.txt";

	private static final String SPTG_YEAR_TXT = "sptg_year.txt";

	private static final String SPTG_LOCATION_TXT = "sptg_location.txt";

	private static final String SPTG_GENRE_TXT = "sptg_genre.txt";

	private static final String SPTG_DIRECTOR_TXT = "sptg_director.txt";

	private static final String SPTG_PRODUCER_TXT = "sptg_producer.txt";

	private static final String SPTG_ACTOR_TXT = "sptg_actor.txt";

	private static final String SPTG_ACTRESS_TXT = "sptg_actress.txt";

	private static final String SPTG_STUNT_MAN_TXT = "sptg_stunt_man.txt";

	private static final String SPTG_STUNT_WOMAN_TXT = "sptg_stunt_woman.txt";

	private static final String SPTG_SONG_TXT = "sptg_song.txt";

	@Option(name = "-p", aliases = "--number-of-parents", required = true)
	private int numberOfParents;

	@Option(name = "-n", aliases = "--number-of-elements-per-node", required = true)
	private int numberOfElements;

	@Option(name = "-d", aliases = "--directory", required = false)
	private String folder = "/tmp";

	@Option(name = "-f", aliases = "--filename", required = true)
	private String filename;

	/**
	 * A plain text file with the film id data
	 */
	@Option(name = "-mid", aliases = "--movie-id", required = false)
	private String movieIdFile = SPTG_ID_TXT;

	/**
	 * A plain text file with the film name data
	 */
	@Option(name = "-m", aliases = "--m", required = false)
	private String movieFile = SPTG_MOVIE_TXT;

	/**
	 * A plain text file with the film year data
	 */
	@Option(name = "-my", aliases = "--movie-year", required = false)
	private String movieYearFile = SPTG_YEAR_TXT;

	/**
	 * A plain text file with the film location data
	 */
	@Option(name = "-ml", aliases = "--movie-location", required = false)
	private String movieLocationFile = SPTG_LOCATION_TXT;

	/**
	 * A plain text file with the film location data
	 */
	@Option(name = "-mg", aliases = "--movie-genre", required = false)
	private String movieGenreFile = SPTG_GENRE_TXT;

	/**
	 * A plain text file with the film director data
	 */
	@Option(name = "-md", aliases = "--movie-director", required = false)
	private String movieDirectorFile = SPTG_DIRECTOR_TXT;

	/**
	 * A plain text file with the film producer data
	 */
	@Option(name = "-mp", aliases = "--movie-producer", required = false)
	private String movieProducerFile = SPTG_PRODUCER_TXT;

	/**
	 * A plain text file with the actor data
	 */
	@Option(name = "-mar", aliases = "--movie-actor", required = false)
	private String movieActorFile = SPTG_ACTOR_TXT;

	/**
	 * A plain text file with the actress data
	 */
	@Option(name = "-mas", aliases = "--movie-actress", required = false)
	private String movieActressFile = SPTG_ACTRESS_TXT;

	/**
	 * A plain text file with the stuntman data
	 */
	@Option(name = "-msman", aliases = "--movie-stunt-man", required = false)
	private String movieStuntManFile = SPTG_STUNT_MAN_TXT;

	/**
	 * A plain text file with the stuntwoman data
	 */
	@Option(name = "-mswoman", aliases = "--movie-stunt-woman", required = false)
	private String movieStuntWomanFile = SPTG_STUNT_WOMAN_TXT;

	/**
	 * A plain text file with the song data
	 */
	@Option(name = "-msong", aliases = "--movie-song", required = false)
	private String movieSongFile = SPTG_SONG_TXT;

	public int getNumberOfElements() {
		return numberOfElements;
	}

	public String getFolder() {
		return folder;
	}

	public String getFilename() {
		return filename;
	}

	public String getMovieFile() {
		return movieFile;
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
