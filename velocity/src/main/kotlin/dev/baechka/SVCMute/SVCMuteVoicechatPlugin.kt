package dev.baechka.SVCMute

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
class SVCMuteVoicechatPlugin : VoicechatPlugin {

    companion object {
        var voicechatApi: VoicechatServerApi? = null
            private set
    }

    override fun getPluginId(): String = "svcmute"

    override fun initialize(api: VoicechatApi) {}

    override fun registerEvents(registration: EventRegistration) {
        registration.registerEvent(VoicechatServerStartedEvent::class.java, this::onServerStarted)
        registration.registerEvent(PlayerConnectedEvent::class.java, this::onPlayerConnected)
        registration.registerEvent(MicrophonePacketEvent::class.java, this::onMicrophonePacket)
    }

    private fun onServerStarted(event: VoicechatServerStartedEvent) {
        voicechatApi = event.voicechat

        SVCMute.instance.voicechatStateUpdater = BiConsumer { uuid, muted ->
            voicechatApi?.getConnectionOf(uuid)?.let { connection ->
                connection.isDisabled = muted
                SVCMute.instance.sendMuteStatusToClient(uuid, muted)
            }
        }
    }

    private fun onPlayerConnected(event: PlayerConnectedEvent) {
        val playerUuid = event.connection.player.uuid
        val isMuted = SVCMute.instance.isMuted(playerUuid)

        if (isMuted) {
            event.connection.isDisabled = true
        }

        SVCMute.instance.sendMuteStatusToClient(playerUuid, isMuted)
    }

    private fun onMicrophonePacket(event: MicrophonePacketEvent) {
        val playerUuid = event.senderConnection?.player?.uuid ?: return

        if (SVCMute.instance.isMuted(playerUuid)) {
            event.cancel()
        }
    }
}
