package dev.kenney.randomkey

import kotlin.test.Test
import kotlin.test.assertEquals

class CommonGreetingTest {

    @Test
    fun testDistinctLetters() {
        val key = KeySelector().sampleKey()
        val letters = mutableListOf<Char>()
        for (note in key["major"] ?: arrayOf()) letters.add(note[0])

        assertEquals(letters.distinct(), letters.slice(0..6))
    }

    @Test
    fun testBuildScaleBFlat() {
        val expected = arrayOf("B♭", "C", "D", "E♭", "F", "G", "A", "B♭")
        val k = KeySelector()
        val actual = k.buildScale(0, k.majorSteps)

        for (i in 0..7)
            assertEquals(expected[i], actual[i])
    }

    @Test
    fun testBuildScaleCSharp() {
        val expected = arrayOf("F♯", "G♯", "A♯", "B", "C♯", "D♯", "E♯", "F♯")
        val k = KeySelector()
        val actual = k.buildScale(8, k.majorSteps)

        for (i in 0..7)
            assertEquals(expected[i], actual[i])
    }

}