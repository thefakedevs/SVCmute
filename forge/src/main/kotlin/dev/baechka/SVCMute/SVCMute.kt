@file:Suppress("DEPRECATION", "removal")

package dev.baechka.SVCMute

import com.mojang.logging.LogUtils
import net.minecraftforge.api.distmarker.Dist
import net.minecraftforge.common.MinecraftForge
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.loading.FMLEnvironment

@Mod(SVCMute.MODID)
class SVCMute {
    companion object {
        const val MODID = "svcmute"
        private val LOGGER = LogUtils.getLogger()
    }

    init {
        if (FMLEnvironment.dist == Dist.CLIENT) {
            LOGGER.info("SVCMute initializing...")
            NetworkHandler.register()
            MinecraftForge.EVENT_BUS.register(NetworkHandler)
        }
    }
}
