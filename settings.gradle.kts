pluginManagement {
    repositories {
        gradlePluginPortal()
        maven("https://maven.minecraftforge.net/")
        maven("https://maven.parchmentmc.org")
    }
}

rootProject.name = "SVCMute"

include("velocity")
include("forge")