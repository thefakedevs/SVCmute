package dev.baechka.BucketMute

import com.mojang.logging.LogUtils
import net.minecraft.client.Minecraft
import net.minecraft.resources.ResourceLocation
import net.minecraftforge.api.distmarker.Dist
import net.minecraftforge.api.distmarker.OnlyIn
import net.minecraftforge.client.event.ClientPlayerNetworkEvent
import net.minecraftforge.eventbus.api.SubscribeEvent
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.network.NetworkEvent
import net.minecraftforge.network.NetworkRegistry

/**
 * Обработчик сетевых пакетов для получения статуса мута с сервера.
 * Канал: "bucketmute:mute_status", формат: 1 байт (0x00 = unmuted, 0x01 = muted)
 */
@OnlyIn(Dist.CLIENT)
@Mod.EventBusSubscriber(modid = BucketMute.MODID, value = [Dist.CLIENT])
object NetworkHandler {
    private val LOGGER = LogUtils.getLogger()
    private val CHANNEL_ID = ResourceLocation("bucketmute", "mute_status")

    fun register() {
        LOGGER.info("Registering network channel: {}", CHANNEL_ID)

        NetworkRegistry.newEventChannel(
            CHANNEL_ID,
            { "1" },
            { true },
            { true }
        ).addListener(this::onPacketReceived)
    }

    private fun onPacketReceived(event: NetworkEvent) {
        val payload = event.payload ?: return

        try {
            if (payload.readableBytes() >= 1) {
                val muted = payload.readByte() == 0x01.toByte()
                Minecraft.getInstance().execute {
                    MuteStateManager.setServerMuted(muted)
                }
            }
        } catch (e: Exception) {
            LOGGER.error("Error processing mute packet", e)
        }

        event.source.get().packetHandled = true
    }

    @SubscribeEvent
    @JvmStatic
    @Suppress("UNUSED_PARAMETER")
    fun onPlayerLoggedOut(event: ClientPlayerNetworkEvent.LoggingOut) {
        MuteStateManager.reset()
    }
}
