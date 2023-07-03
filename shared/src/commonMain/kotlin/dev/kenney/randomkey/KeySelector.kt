package dev.kenney.randomkey

import kotlin.random.Random

class KeySelector {
    private val incidentals = arrayOf("\u266D", "", "\u266F")
    private val notes = arrayOf("A", "B", "C", "D", "E", "F", "G")
    private var allNotes: MutableList<String>

    init {
        // build list of notes
        allNotes = mutableListOf()

        // ignore enharmonic notes people don't use

        for (note in notes) {
            var min = 0
            var max = incidentals.size - 1

            if (note == "E" || note == "B") max -= 1
            else if (note == "F" || note == "C") min += 1

            for (incidental in incidentals.slice(min..max)) {
                allNotes.add(note + incidental)
            }
        }
    }

    fun sampleKey(): Map<String, Array<String>> {
        val rootIdx = sampleIdx()
        val root = allNotes[rootIdx]

        val major = buildScale(rootIdx, arrayOf(2, 2, 1, 2, 2, 2, 1))
        val minor = buildScale(rootIdx, arrayOf(2, 1, 2, 2, 1, 2, 2))

        return mapOf(
            "root" to arrayOf(root),
            "notes" to arrayOf("1", "2", "3", "4", "5", "6", "7", "8"),
            "minor" to minor,
            "major" to major,
        )
    }

    fun sampleIdx(): Int {
        return Random.nextInt(0, allNotes.size)
    }

    fun buildScale(rootIdx: Int, steps: Array<Int>): Array<String> {
        val major = Array(8) { "" }
        major[0] = allNotes[rootIdx]

        var cursor = rootIdx
        for (i in steps.indices) {
            val majorStep = steps[i]
            cursor = (cursor + majorStep) % allNotes.size
            major[i + 1] = allNotes[cursor]
        }

        return major
    }

    fun flattenScale(scale: Array<String>): String {
        return scale.reduce { acc, item -> "$acc\t\t|\t\t$item" }
    }
}