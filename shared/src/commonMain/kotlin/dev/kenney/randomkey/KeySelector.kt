package dev.kenney.randomkey

import kotlin.random.Random

class KeySelector {
    private val notes = arrayOf('A', 'B', 'C', 'D', 'E', 'F', 'G')
    private val allNotes = arrayOf(
        arrayOf("B♭", "A♯", "C♭♭"),     // Bb
        arrayOf("B", "C♭", "A♯♯"),      // B
        arrayOf("C", "B♯", "D♭♭"),      // C
        arrayOf("D♭", "C♯", "B♯♯"),     // Db
        arrayOf("D", "C♯♯", "E♭♭"),     // D
        arrayOf("E♭", "D♯", "F♭♭"),     // Eb
        arrayOf("E", "D♯♯", "F♭"),      // E
        arrayOf("F", "E♯", "G♭♭"),      // F
        arrayOf("F♯", "G♭", "E♯♯"),     // F#
        arrayOf("G", "F♯♯", "A♭♭"),     // G
        arrayOf("A♭", "G♯"),            // Ab
        arrayOf("A", "G♯♯", "B♭♭")      // A
    )
    private var noteIdx = Random.nextInt(0, allNotes.size)

    val majorSteps = arrayOf(2, 2, 1, 2, 2, 2, 1)

    fun sampleKey(): Map<String, Array<String>> {
        val oldIdx = noteIdx
        while (noteIdx == oldIdx)
            noteIdx = Random.nextInt(0, allNotes.size)

        val major = buildScale(noteIdx, majorSteps)

        return mapOf(
            "root" to arrayOf(allNotes[noteIdx][0]),
            "notes" to arrayOf("1", "2", "3", "4", "5", "6", "7", "8"),
            "major" to major,
        )
    }

    fun buildScale(startIdx: Int, steps: Array<Int>): Array<String> {
        var noteIdx = startIdx
        val start = allNotes[noteIdx][0]
        val scale = mutableListOf(start)

        var letterCursor = (notes.indexOf(start[0]) + 1) % notes.size

        for (step in steps) {
            noteIdx = (noteIdx + step) % allNotes.size
            val enharmonic = allNotes[noteIdx]
            for (note in enharmonic) {
                val name = note[0]
                val expectedName = notes[letterCursor]
                if (name == expectedName) {
                    letterCursor = (letterCursor + 1) % notes.size
                    scale.add(note)
                    break
                }
            }
        }

        return scale.toTypedArray()
    }
}