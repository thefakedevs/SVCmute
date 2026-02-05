package dev.baechka.BucketMute

import com.velocitypowered.api.proxy.Player
import java.text.MessageFormat
import java.util.*
import java.util.concurrent.ConcurrentHashMap

object Messages {
    private val locales = ConcurrentHashMap<String, Properties>()
    private const val DEFAULT_LOCALE = "en"

    fun load() {
        listOf("ru", "en").forEach { locale ->
            val props = Properties()
            val stream = javaClass.classLoader.getResourceAsStream("messages_$locale.properties")
            stream?.use { props.load(it.reader(Charsets.UTF_8)) }
            locales[locale] = props
        }
    }

    fun get(key: String, vararg args: Any): String {
        return get(DEFAULT_LOCALE, key, *args)
    }

    fun get(player: Player, key: String, vararg args: Any): String {
        val locale = player.effectiveLocale?.language ?: DEFAULT_LOCALE
        return get(locale, key, *args)
    }

    private fun get(locale: String, key: String, vararg args: Any): String {
        val props = locales[locale] ?: locales[DEFAULT_LOCALE] ?: return key
        val pattern = props.getProperty(key, locales[DEFAULT_LOCALE]?.getProperty(key, key) ?: key)
        return if (args.isEmpty()) pattern else MessageFormat.format(pattern, *args)
    }
}
