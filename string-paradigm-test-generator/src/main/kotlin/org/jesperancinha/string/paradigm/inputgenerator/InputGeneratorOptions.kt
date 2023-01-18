package org.jesperancinha.string.paradigm.inputgenerator

import picocli.CommandLine.Command
import picocli.CommandLine.Option
import java.io.*
import java.nio.charset.Charset
import java.util.*
import java.util.concurrent.Callable
import java.util.logging.Logger


@Command(
    name = "input-generator", mixinStandardHelpOptions = true, version = ["0.0.0"],
    description = ["Generates input test files"]
)
open class InputGeneratorOptions : Callable<Int> {
    @Option(names = ["-p,--number-of-parents"], required = true)
    open val numberOfParents = 0

    @Option(names = ["-n,--number-of-elements-per-node"], required = true)
    open val numberOfElements = 0

    @Option(names = ["-d,--directory"], required = false)
    val folder = "/tmp"

    @Option(names = ["-f,--filename"], required = true)
    open val filename: String? = null

    /**
     * A plain text file with the film id data
     */
    @Option(names = ["-mid,--movie-id"], required = false)
    val movieIdFile = SPTG_ID_TXT

    /**
     * A plain text file with the film name data
     */
    @Option(names = ["-m,--m"], required = false)
    val movieFile = SPTG_MOVIE_TXT

    /**
     * A plain text file with the film year data
     */
    @Option(names = ["-my,--movie-year"], required = false)
    val movieYearFile = SPTG_YEAR_TXT

    /**
     * A plain text file with the film location data
     */
    @Option(names = ["-ml,--movie-location"], required = false)
    val movieLocationFile = SPTG_LOCATION_TXT

    /**
     * A plain text file with the film location data
     */
    @Option(names = ["-mg,--movie-genre"], required = false)
    val movieGenreFile = SPTG_GENRE_TXT

    /**
     * A plain text file with the film director data
     */
    @Option(names = ["-md,--movie-director"], required = false)
    val movieDirectorFile = SPTG_DIRECTOR_TXT

    /**
     * A plain text file with the film producer data
     */
    @Option(names = ["-mp,--movie-producer"], required = false)
    val movieProducerFile = SPTG_PRODUCER_TXT

    /**
     * A plain text file with the actor data
     */
    @Option(names = ["-mar,--movie-actor"], required = false)
    val movieActorFile = SPTG_ACTOR_TXT

    /**
     * A plain text file with the actress data
     */
    @Option(names = ["-mas,--movie-actress"], required = false)
    val movieActressFile = SPTG_ACTRESS_TXT

    /**
     * A plain text file with the stuntman data
     */
    @Option(names = ["-msman,--movie-stunt-man"], required = false)
    val movieStuntManFile = SPTG_STUNT_MAN_TXT

    /**
     * A plain text file with the stuntwoman data
     */
    @Option(names = ["-mswoman,--movie-stunt-woman"], required = false)
    val movieStuntWomanFile = SPTG_STUNT_WOMAN_TXT

    /**
     * A plain text file with the song data
     */
    @Option(names = ["-msong,--movie-song"], required = false)
    val movieSongFile = SPTG_SONG_TXT

    override fun call(): Int {
        val directory = File(folder)
        directory.mkdirs()
        val outfile = File(directory, filename)
        val streamId = getStreamFromFileName(movieIdFile)
        val streamYear = getStreamFromFileName(movieYearFile)
        val streamMovie = getStreamFromFileName(movieFile)
        val streamGenre = getStreamFromFileName(movieGenreFile)
        val streamLocation = getStreamFromFileName(movieLocationFile)
        val streamDirector = getStreamFromFileName(movieDirectorFile)
        val streamProducer = getStreamFromFileName(movieProducerFile)
        val streamActress = getStreamFromFileName(movieActressFile)
        val streamStuntWoman = getStreamFromFileName(movieStuntWomanFile)
        val streamActor = getStreamFromFileName(movieActorFile)
        val streamStuntMan = getStreamFromFileName(movieStuntManFile)
        val streamSong = getStreamFromFileName(movieSongFile)
        val resultId = getFullFileList(streamId)
        val resultActor = getFullFileList(streamActor)
        val resultActress = getFullFileList(streamActress)
        val resultDirector = getFullFileList(streamDirector)
        val resultGenre = getFullFileList(streamGenre)
        val resultLocation = getFullFileList(streamLocation)
        val resultMovie = getFullFileList(streamMovie)
        val resultProducer = getFullFileList(streamProducer)
        val resultSong = getFullFileList(streamSong)
        val resultStuntMan = getFullFileList(streamStuntMan)
        val resultStuntWoman = getFullFileList(streamStuntWoman)
        val resultYear = getFullFileList(streamYear)
        val maxNumberOfElements = numberOfElements
        val nIds = numberOfParents
        BufferedWriter(FileWriter(outfile)).use { writer ->
            for (i in 0 until nIds) {
                var bf = StringBuffer()
                bf.append(getRandomStringFromArray(resultId) + "" + i)
                val nYears = RANDOM.nextInt(maxNumberOfElements) + 1
                val stringYear = bf.toString()
                for (j in 0 until nYears) {
                    bf.append(getRandomStringFromArrayPreColon(resultYear))
                    val nMovies = RANDOM.nextInt(maxNumberOfElements) + 1
                    val stringMovie = bf.toString()
                    for (k in 0 until nMovies) {
                        bf.append(getRandomStringFromArrayPreColon(resultMovie))
                        val nGenres = RANDOM.nextInt(maxNumberOfElements) + 1
                        val stringGenre = bf.toString()
                        for (j2 in 0 until nGenres) {
                            bf.append(getRandomStringFromArrayPreColon(resultGenre))
                            val nLocation = RANDOM.nextInt(maxNumberOfElements) + 1
                            val stringLocation = bf.toString()
                            for (k3 in 0 until nLocation) {
                                bf.append(getRandomStringFromArrayPreColon(resultLocation))
                                val nDirector = RANDOM.nextInt(maxNumberOfElements) + 1
                                val stringDirector = bf.toString()
                                for (k2 in 0 until nDirector) {
                                    bf.append(getRandomStringFromArrayPreColon(resultDirector))
                                    val nProducer = RANDOM.nextInt(maxNumberOfElements) + 1
                                    val stringProducer = bf.toString()
                                    for (l in 0 until nProducer) {
                                        bf.append(getRandomStringFromArrayPreColon(resultProducer))
                                        val nActress = RANDOM.nextInt(maxNumberOfElements) + 1
                                        val stringActress = bf.toString()
                                        for (l2 in 0 until nActress) {
                                            bf.append(getRandomStringFromArrayPreColon(resultActress))
                                            val nStuntWoman = RANDOM.nextInt(maxNumberOfElements) + 1
                                            val stringStuntWoman = bf.toString()
                                            for (m in 0 until nStuntWoman) {
                                                bf.append(getRandomStringFromArrayPreColon(resultStuntWoman))
                                                val nActor = RANDOM.nextInt(maxNumberOfElements) + 1
                                                val stringActor = bf.toString()
                                                for (m2 in 0 until nActor) {
                                                    bf.append(getRandomStringFromArrayPreColon(resultActor))
                                                    val nStuntMan = RANDOM.nextInt(maxNumberOfElements) + 1
                                                    val stringStuntMan = bf.toString()
                                                    for (n in 0 until nStuntMan) {
                                                        bf.append(getRandomStringFromArrayPreColon(resultStuntMan))
                                                        val nSong = RANDOM.nextInt(maxNumberOfElements) + 1
                                                        val stringSong = bf.toString()
                                                        for (n2 in 0 until nSong) {
                                                            bf.append(getRandomStringFromArrayPreColon(resultSong))
                                                            writer.write(bf.toString())
                                                            writer.write("\n")
                                                            bf = StringBuffer()
                                                            bf.append(stringSong)
                                                        }
                                                        bf = StringBuffer()
                                                        bf.append(stringStuntMan)
                                                    }
                                                    bf = StringBuffer()
                                                    bf.append(stringActor)
                                                }
                                                bf = StringBuffer()
                                                bf.append(stringStuntWoman)
                                            }
                                            bf = StringBuffer()
                                            bf.append(stringActress)
                                        }
                                        bf = StringBuffer()
                                        bf.append(stringProducer)
                                    }
                                    bf = StringBuffer()
                                    bf.append(stringDirector)
                                }
                                bf = StringBuffer()
                                bf.append(stringLocation)
                            }
                            bf = StringBuffer()
                            bf.append(stringGenre)
                        }
                        bf = StringBuffer()
                        bf.append(stringMovie)
                    }
                    bf = StringBuffer()
                    bf.append(stringYear)
                }
            }
        }
        LOG.info("Completed test file generation")
         return 0
    }

    fun getStreamFromFileName(filename: String?): InputStream {
        return javaClass.getResourceAsStream(filename)
    }

    @Throws(IOException::class)
    fun getFullFileList(io: InputStream?): List<String> {
        val list: MutableList<String> = ArrayList()
        BufferedReader(InputStreamReader(io, Charset.defaultCharset())).use { br ->
            var testRead: String? = br.readLine()
            while (testRead != null) {
                list.add(testRead)
                testRead = br.readLine()
            }
        }
        return list
    }


    companion object {
        private const val SPTG_ID_TXT = "sptg_id.txt"
        private const val SPTG_MOVIE_TXT = "sptg_movie.txt"
        private const val SPTG_YEAR_TXT = "sptg_year.txt"
        private const val SPTG_LOCATION_TXT = "sptg_location.txt"
        private const val SPTG_GENRE_TXT = "sptg_genre.txt"
        private const val SPTG_DIRECTOR_TXT = "sptg_director.txt"
        private const val SPTG_PRODUCER_TXT = "sptg_producer.txt"
        private const val SPTG_ACTOR_TXT = "sptg_actor.txt"
        private const val SPTG_ACTRESS_TXT = "sptg_actress.txt"
        private const val SPTG_STUNT_MAN_TXT = "sptg_stunt_man.txt"
        private const val SPTG_STUNT_WOMAN_TXT = "sptg_stunt_woman.txt"
        private const val SPTG_SONG_TXT = "sptg_song.txt"

        private val LOG = Logger.getLogger(InputGeneratorOptions::class.java.name)
        val RANDOM = Random()

        private fun getRandomStringFromArrayPreColon(results: List<String>): String {
            return ";" + getRandomStringFromArray(results)
        }

        private fun getRandomStringFromArray(results: List<String>): String {
            return results[RANDOM.nextInt(results.size)]
        }
    }


}