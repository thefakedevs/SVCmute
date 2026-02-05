package dev.baechka.BucketMute

import com.mojang.logging.LogUtils

/**
 * Менеджер состояния серверного мута.
 */
object MuteStateManager {
    private val LOGGER = LogUtils.getLogger()

    @Volatile
    private var serverMuted = false

    fun isServerMuted(): Boolean = serverMuted

    fun setServerMuted(muted: Boolean) {
        if (serverMuted != muted) {
            serverMuted = muted
            LOGGER.info("Mute status changed: {}", muted)
        }
    }

    fun reset() {
        serverMuted = false
    }
}
