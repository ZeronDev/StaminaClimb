package com.Mort.StaminaClimb

import com.Mort.StaminaClimb.ConfigDataFile.ConfigSystem.readDisplay
import com.Mort.StaminaClimb.ConfigDataFile.ConfigSystem.readMode
import com.Mort.StaminaClimb.ConfigDataFile.ConfigSystem.readPrefix
import com.Mort.StaminaClimb.ConfigDataFile.ConfigSystem.readTimeLimit
import com.Mort.StaminaClimb.ConfigDataFile.ConfigSystem.saveDisplayMethod
import com.Mort.StaminaClimb.ConfigDataFile.ConfigSystem.saveMode
import com.Mort.StaminaClimb.ConfigDataFile.ConfigSystem.saveTimeLimit
import com.Mort.StaminaClimb.ConfigDataFile.DataResource.climbPlayer
import com.Mort.StaminaClimb.ConfigDataFile.DataResource.prefix
import com.Mort.StaminaClimb.ConfigDataFile.FileLogger
import com.Mort.StaminaClimb.Dispatchers.Settings
import com.Mort.StaminaClimb.Listeners.Listener
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.bukkit.plugin.Plugin
import org.bukkit.plugin.java.JavaPlugin
import java.io.File

class MainCore : JavaPlugin() {

    // 루콘을 위하여

    companion object {
        lateinit var plugin: Plugin
    }

    override fun onEnable() {

        plugin = this
        if (!dataFolder.exists()) {

            config.addDefault("prefix", "[ §eCLIMB §f]")
            config.addDefault("TimeLimit", 10)
            config.addDefault("DisplayMethod", "보스바")
            config.createSection("ClimbBlockMode")
            config.options().copyDefaults(true)
            saveConfig()
            reloadConfig()
        }

        readPrefix()
        readMode()
        readTimeLimit()
        readDisplay()


        logger.info("$prefix 플러그인이 활성화중입니다")

        CoroutineScope(Dispatchers.IO).launch {
            FileLogger.write(
                """
                - [ 스태미나 클라임 ] -
                벽을 SHIFT 키로 등반할 수 있습니다.
                게이지를 액션바로 또는 보스바로 표시 할 수 있습니다.
                탈 수 있는 블럭과 못 타는 블럭을 설정 할 수 있습니다.
                벽 타는 시간을 조정할 수 있습니다.
            """.trimIndent(), File(dataFolder, "Description.txt")
            )
        }
        getCommand("staminaclimb")?.apply {
            setExecutor(Settings)
            tabCompleter = Settings
        }
        server.pluginManager.registerEvents(Listener(), this)

        logger.info("$prefix 제론#6595 개발")
    }

    override fun onDisable() {
        logger.info("$prefix 플러그인이 비활성화중입니다")

        saveTimeLimit()
        climbPlayer.entries.forEach { saveMode(it.key, it.value) }
        saveDisplayMethod()

        saveConfig()

        logger.info("$prefix 제론#6595 개발")
    }
}