package com.Mort.StaminaClimb.ConfigDataFile

import com.Mort.StaminaClimb.ConfigDataFile.DataResource.climbPlayer
import com.Mort.StaminaClimb.ConfigDataFile.DataResource.prefix
import com.Mort.StaminaClimb.ConfigDataFile.DataResource.timeLimit
import com.Mort.StaminaClimb.MainCore.Companion.plugin
import org.bukkit.Bukkit
import java.util.*

object ConfigSystem {
    // 루콘 구독과 좋아요

    fun readPrefix() {
        try {
            prefix = plugin.config.getString("prefix")!!
        } catch (e: Exception) {
            plugin.logger.info("$prefix 예기치 못한 오류가 발생하였습니다.")
            Bukkit.getPluginManager().disablePlugin(plugin)
        }
    }

    fun readTimeLimit() {
        timeLimit = plugin.config.getInt("TimeLimit")
    }

    fun saveTimeLimit() {
        plugin.config.set("TimeLimit", timeLimit)
    }
}