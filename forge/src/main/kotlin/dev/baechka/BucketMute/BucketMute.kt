@file:Suppress("DEPRECATION", "removal")

package dev.baechka.BucketMute

import com.mojang.logging.LogUtils
import net.minecraftforge.api.distmarker.Dist
import net.minecraftforge.common.MinecraftForge
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.loading.FMLEnvironment

@Mod(BucketMute.MODID)
class BucketMute {
    companion object {
        const val MODID = "bucketmute"
        private val LOGGER = LogUtils.getLogger()
    }

    init {
        if (FMLEnvironment.dist == Dist.CLIENT) {
            LOGGER.info("BucketMute initializing...")
            NetworkHandler.register()
            MinecraftForge.EVENT_BUS.register(NetworkHandler)
        }
    }
}
