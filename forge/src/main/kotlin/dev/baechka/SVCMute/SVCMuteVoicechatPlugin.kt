package dev.baechka.SVCMute

import com.mojang.logging.LogUtils
import de.maxhenkel.voicechat.api.ForgeVoicechatPlugin
import de.maxhenkel.voicechat.api.VoicechatApi
import de.maxhenkel.voicechat.api.VoicechatPlugin
import de.maxhenkel.voicechat.api.events.ClientSoundEvent
import de.maxhenkel.voicechat.api.events.EventRegistration

/**
 * Плагин Simple Voice Chat - блокирует микрофон при серверном муте.
 */
@ForgeVoicechatPlugin
class SVCMuteVoicechatPlugin : VoicechatPlugin {

    companion object {
        private val LOGGER = LogUtils.getLogger()
    }

    override fun getPluginId(): String = SVCMute.MODID

    override fun initialize(api: VoicechatApi) {
        LOGGER.info("SVCMute voicechat plugin initialized")
    }

    override fun registerEvents(registration: EventRegistration) {
        registration.registerEvent(ClientSoundEvent::class.java) { event ->
            if (MuteStateManager.isServerMuted()) {
                event.cancel()
            }
        }
    }
}
