package org.jesperancinha.string.paradigm.inputgenerator

import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.nulls.shouldNotBeNull
import org.junit.jupiter.api.Test
import java.io.InputStream
import java.util.*

class InputGeneratorTest {
    @Test
    @Throws(Exception::class)
    fun testGetFullFileList() {
        val streamId = javaClass.getResourceAsStream("sptg_id.txt").shouldNotBeNull()
        val streamActor = javaClass.getResourceAsStream("sptg_actor.txt".shouldNotBeNull())
        val streamActress = javaClass.getResourceAsStream("sptg_actress.txt").shouldNotBeNull()
        val streamDirector = javaClass.getResourceAsStream("sptg_director.txt").shouldNotBeNull()
        val streamGenre = javaClass.getResourceAsStream("sptg_genre.txt").shouldNotBeNull()
        val streamLocation = javaClass.getResourceAsStream("sptg_location.txt").shouldNotBeNull()
        val streamMovie = javaClass.getResourceAsStream("sptg_movie.txt").shouldNotBeNull()
        val streamProducer = javaClass.getResourceAsStream("sptg_producer.txt").shouldNotBeNull()
        val streamSong = javaClass.getResourceAsStream("sptg_song.txt").shouldNotBeNull()
        val streamStuntMan = javaClass.getResourceAsStream("sptg_stunt_man.txt").shouldNotBeNull()
        val streamStuntWoman = javaClass.getResourceAsStream("sptg_stunt_woman.txt").shouldNotBeNull()
        val streamYear = javaClass.getResourceAsStream("sptg_year.txt").shouldNotBeNull()
        val inputGenerator = InputGeneratorOptions()
        val resultId = inputGenerator.getFullFileList(streamId)
        val resultActor = inputGenerator.getFullFileList(streamActor)
        val resultActress = inputGenerator.getFullFileList(streamActress)
        val resultDirector = inputGenerator.getFullFileList(streamDirector)
        val resultGenre = inputGenerator.getFullFileList(streamGenre)
        val resultLocation = inputGenerator.getFullFileList(streamLocation)
        val resultMovie = inputGenerator.getFullFileList(streamMovie)
        val resultProducer = inputGenerator.getFullFileList(streamProducer)
        val resultSong = inputGenerator.getFullFileList(streamSong)
        val resultStuntMan = inputGenerator.getFullFileList(streamStuntMan)
        val resultStuntWoman = inputGenerator.getFullFileList(streamStuntWoman)
        val resultYear = inputGenerator.getFullFileList(streamYear)
        resultId.shouldHaveSize(5)
        resultActor.shouldHaveSize(8)
        resultActress.shouldHaveSize(4)
        resultDirector.shouldHaveSize(5)
        resultGenre.shouldHaveSize(4)
        resultLocation.shouldHaveSize(12)
        resultMovie.shouldHaveSize(6)
        resultProducer.shouldHaveSize(6)
        resultSong.shouldHaveSize(7)
        resultStuntMan.shouldHaveSize(6)
        resultStuntWoman.shouldHaveSize(1)
        resultYear.shouldHaveSize(12)
    }

    @Test
    @Throws(Exception::class)
    fun testCreateTestFile() {
        val streamId = javaClass.getResourceAsStream("sptg_id.txt")
        val streamActor = javaClass.getResourceAsStream("sptg_actor.txt")
        val streamActress = javaClass.getResourceAsStream("sptg_actress.txt")
        val streamDirector = javaClass.getResourceAsStream("sptg_director.txt")
        val streamGenre = javaClass.getResourceAsStream("sptg_genre.txt")
        val streamLocation = javaClass.getResourceAsStream("sptg_location.txt")
        val streamMovie = javaClass.getResourceAsStream("sptg_movie.txt")
        val streamProducer = javaClass.getResourceAsStream("sptg_producer.txt")
        val streamSong = javaClass.getResourceAsStream("sptg_song.txt")
        val streamStuntMan = javaClass.getResourceAsStream("sptg_stunt_man.txt")
        val streamStuntWoman = javaClass.getResourceAsStream("sptg_stunt_woman.txt")
        val streamYear = javaClass.getResourceAsStream("sptg_year.txt")
        val stack = Stack<InputStream>()
        stack.push(streamSong)
        stack.push(streamStuntMan)
        stack.push(streamActor)
        stack.push(streamStuntWoman)
        stack.push(streamActress)
        stack.push(streamProducer)
        stack.push(streamDirector)
        stack.push(streamLocation)
        stack.push(streamGenre)
        stack.push(streamMovie)
        stack.push(streamYear)
        stack.push(streamId)
        val inputGenerator: InputGeneratorOptions = object : InputGeneratorOptions() {
            override val filename: String
                get() = InputGeneratorTest.TEST_FILMS_TXT
            override val numberOfParents: Int
                get() = 10
            override val numberOfElements: Int
                get() = 2
        }
        inputGenerator.call()
    }

    companion object {
        private const val TEST_FILMS_TXT = "testFilms.txt"
    }
}