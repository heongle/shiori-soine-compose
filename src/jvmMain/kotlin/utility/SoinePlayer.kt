package utility

import javax.sound.sampled.AudioSystem
import javax.sound.sampled.LineEvent
import javax.sound.sampled.LineListener

class SoinePlayer(private val soineLeft: String, private val soineRight: String): LineListener {
    var looping = false
        private set
    private var playingIndex = 0
    private val soineClips = listOf(AudioSystem.getClip(), AudioSystem.getClip())
    private val soineRandom = SoineRandom()

    fun start() {
        looping = true
        val leftAudioInputStream = AudioSystem.getAudioInputStream(
            ClassLoader.getSystemClassLoader().getResourceAsStream(soineLeft)
        )
        val rightAudioInputStream = AudioSystem.getAudioInputStream(
            ClassLoader.getSystemClassLoader().getResourceAsStream(soineRight)
        )
        soineClips[0].open(leftAudioInputStream)
        soineClips[1].open(rightAudioInputStream)
        soineClips.forEach { it.addLineListener(this) }
        playRandomNext()
    }

    fun stop() {
        looping = false
        soineClips[playingIndex].stop()
        soineClips[playingIndex].flush()
        soineClips.forEach { it.close() }
    }

    private fun playRandomNext() {
        soineClips[playingIndex].flush()
        soineClips[playingIndex].framePosition = 0
        // Random next clip index
        playingIndex = soineRandom.nextSoineIndex()
        // Start playing clip with new index
        soineClips[playingIndex].start()
    }

    override fun update(event: LineEvent?) {
        val type = event?.type
        if (type == LineEvent.Type.STOP && looping) {
            playRandomNext()
        }
    }
}
