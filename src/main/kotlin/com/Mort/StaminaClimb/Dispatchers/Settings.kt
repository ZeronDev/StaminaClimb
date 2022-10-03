package com.Mort.StaminaClimb.Dispatchers

import com.Mort.StaminaClimb.ConfigDataFile.DataResource.Display
import com.Mort.StaminaClimb.ConfigDataFile.DataResource.checkArgsSize
import com.Mort.StaminaClimb.ConfigDataFile.DataResource.climbPlayer
import com.Mort.StaminaClimb.ConfigDataFile.DataResource.prefix
import com.Mort.StaminaClimb.ConfigDataFile.DataResource.timeLimit
import com.Mort.StaminaClimb.MainCore.Companion.plugin
import net.kyori.adventure.text.Component.text
import net.kyori.adventure.text.event.ClickEvent
import net.kyori.adventure.text.event.HoverEvent
import net.md_5.bungee.api.chat.hover.content.Text
import org.bukkit.Bukkit
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.command.TabExecutor
import org.bukkit.entity.Player

object Settings : CommandExecutor, TabExecutor {
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        if (sender is Player && sender.isOp) {
            if (args.isNotEmpty()) {
                when (args[0]) {
                    "표시방법" -> {
                        checkArgsSize(sender, args.size, 2, "$prefix 액션바, 보스바 중에 하나를 선택해주세요") {
                            if (args[1] == "보스바") {
                                Display = args[1]
                                sender.sendMessage(text("$prefix 표시방법이 보스바로 설정되었습니다"))
                            } else if (args[1] == "액션바") {
                                Display = args[1]
                                sender.sendMessage(text("$prefix 표시방법이 액션바로 설정되었습니다"))
                            } else {
                                sender.sendMessage(text("$prefix 액션바, 보스바 중에 하나를 선택해주세요"))
                            }
                        }
                    }
                    "시간제한" -> {
                        checkArgsSize(sender, args.size, 2, "$prefix 시간(초)를 입력해주세요") {
                            var timelimit = timeLimit
                            kotlin.runCatching {
                                timelimit = args[1].toInt()
                            }.onSuccess {
                                timeLimit = timelimit
                            }
                            sender.sendMessage(text("$prefix 시간제한이 설정되었습니다"))
                        }
                    }
                    "모드변경" -> {
                        checkArgsSize(sender, args.size, 2, "$prefix §aTRUE§f나 §cFALSE§f 중 하나를 선택해주세요") {
                            if (args[1] == "TRUE") {
                                if (climbPlayer[sender.uniqueId]!!) sender.sendMessage(text("$prefix 이미 §aTRUE§f 입니다"))
                                else {
                                    climbPlayer[sender.uniqueId] = true
                                    sender.sendMessage(text("$prefix 등반블럭 모드가 §aTRUE§f로 설정되었습니다"))
                                }
                            } else if (args[1] == "FALSE") {
                                if (!climbPlayer[sender.uniqueId]!!) sender.sendMessage(text("$prefix 이미 §cFALSE§f 입니다"))
                                else {
                                    climbPlayer[sender.uniqueId] = false
                                    sender.sendMessage(text("$prefix 등반블럭 모드가 §cFALSE§f로 설정되었습니다"))
                                }
                            } else {
                                sender.sendMessage(text("$prefix §aTRUE§f나 §cFALSE§f 중 하나를 선택해주세요"))
                            }
                        }
                    }
                    "설정확인" -> {
                        sender.sendMessage(text("""
                            - [ §7설정 §f] -
                            표시방법 : $Display
                            시간제한 : $timeLimit
                            현재모드 : ${if (climbPlayer[sender.uniqueId]!!) "§aTRUE" else "§cFALSE"}
                        """.trimIndent()))
                    }
                }
            } else {
                sender.sendMessage(text("$prefix 설정 항목과 값만 입력해주세요"))
            }
        } else {
            sender.sendMessage(text("$prefix 관리자만 사용 가능 합니다"))
        }
        return false
    }

    override fun onTabComplete(
        sender: CommandSender,
        command: Command,
        alias: String,
        args: Array<out String>
    ): MutableList<String>? {
        if (sender.isOp) {
            when (args.size) {
                1 -> {
                    return mutableListOf("표시방법", "시간제한", "모드변경", "설정확인")
                }
                2 -> {
                    if (args[0] == "표시방법") {
                        return mutableListOf("액션바", "보스바")
                    } else if (args[0] == "시간제한") {
                        return mutableListOf("<TimeLimit>")
                    } else if (args[0] == "모드변경") {
                        return mutableListOf("TRUE", "FALSE")
                    }
                }
            }
        }
        return mutableListOf(" ")
    }
}