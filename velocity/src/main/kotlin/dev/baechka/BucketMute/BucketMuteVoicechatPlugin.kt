package dev.baechka.BucketMute

import de.maxhenkel.voicechat.api.VoicechatApi
import de.maxhenkel.voicechat.api.VoicechatPlugin
import de.maxhenkel.voicechat.api.VoicechatServerApi
import de.maxhenkel.voicechat.api.events.EventRegistration
import de.maxhenkel.voicechat.api.events.MicrophonePacketEvent
import de.maxhenkel.voicechat.api.events.PlayerConnectedEvent
import de.maxhenkel.voicechat.api.events.VoicechatServerStartedEvent
import java.util.function.BiConsumer

/**
 * Плагин Simple Voice Chat - серверная часть мута.
 */
class BucketMuteVoicechatPlugin : VoicechatPlugin {

    companion object {
        var voicechatApi: VoicechatServerApi? = null
            private set
    }

    override fun getPluginId(): String = "bucketmute"

    override fun initialize(api: VoicechatApi) {}

    override fun registerEvents(registration: EventRegistration) {
        registration.registerEvent(VoicechatServerStartedEvent::class.java, this::onServerStarted)
        registration.registerEvent(PlayerConnectedEvent::class.java, this::onPlayerConnected)
        registration.registerEvent(MicrophonePacketEvent::class.java, this::onMicrophonePacket)
    }

    private fun onServerStarted(event: VoicechatServerStartedEvent) {
        voicechatApi = event.voicechat

        BucketMute.instance.voicechatStateUpdater = BiConsumer { uuid, muted ->
            voicechatApi?.getConnectionOf(uuid)?.let { connection ->
                connection.isDisabled = muted
                BucketMute.instance.sendMuteStatusToClient(uuid, muted)
            }
        }
    }

    private fun onPlayerConnected(event: PlayerConnectedEvent) {
        val playerUuid = event.connection.player.uuid
        val isMuted = BucketMute.instance.isMuted(playerUuid)

        if (isMuted) {
            event.connection.isDisabled = true
        }

        BucketMute.instance.sendMuteStatusToClient(playerUuid, isMuted)
    }

    private fun onMicrophonePacket(event: MicrophonePacketEvent) {
        val playerUuid = event.senderConnection?.player?.uuid ?: return

        if (BucketMute.instance.isMuted(playerUuid)) {
            event.cancel()
        }
    }
}
