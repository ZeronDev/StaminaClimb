package com.Mort.StaminaClimb.Listeners

import com.Mort.StaminaClimb.ClimbMethod.Climb.climb
import com.Mort.StaminaClimb.ClimbMethod.Climb.finish
import com.Mort.StaminaClimb.ClimbMethod.Climb.join
import com.Mort.StaminaClimb.ClimbMethod.Climb.regen
import com.Mort.StaminaClimb.ConfigDataFile.DataResource.sneakList
import com.Mort.StaminaClimb.ConfigDataFile.DataResource.taskIdMap
import com.Mort.StaminaClimb.ConfigDataFile.DataResource.unable
import org.bukkit.Location
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.PlayerDeathEvent
import org.bukkit.event.player.PlayerChangedWorldEvent
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.event.player.PlayerQuitEvent
import org.bukkit.event.player.PlayerToggleSneakEvent

class Listener : Listener {
    @EventHandler
    fun onShift(e: PlayerToggleSneakEvent) {
        if (sneakList.contains(e.player)) {
            regen(e.player)
            sneakList.remove(e.player)
            return
        }
        sneakList.add(e.player)
        if (taskIdMap.containsKey(e.player) && checkAble(e.player.location)) {
            join(e.player)
        } else if (checkAble(e.player.location)) {
            climb(e.player)
        }
    }


    @EventHandler
    fun onDie(e: PlayerDeathEvent) {
        if (taskIdMap.contains(e.player)) {
            finish(e.player)
            sneakList.remove(e.player)
        }
    }

    @EventHandler
    fun onChannelMove(e: PlayerChangedWorldEvent) {
        if (taskIdMap.contains(e.player)) {
            finish(e.player)
            sneakList.remove(e.player)
        }
    }


        //    @EventHandler
        //    fun onBlockSet(e: BlockPlaceEvent) {
        //        if (climbPlayer.get(e.player.uniqueId)!!) {
        //            if (e.block !is TileState) return
        //            val tileState = e.block.state as TileState
        //            val container = tileState.persistentDataContainer
        //            container.set(NamespacedKey(plugin, "Climbable"), PersistentDataType.STRING, "TRUE")
        //            tileState.update()
        //        }
        //    }

    @EventHandler
    fun onQuit(e: PlayerQuitEvent) {
        if (taskIdMap.contains(e.player)) {
            finish(e.player)
            sneakList.remove(e.player)
        }
    }

    companion object {
        fun checkAble(location: Location): Boolean {
            val bx = location.blockX
            val by = location.blockY
            val bz = location.blockZ
            val list = mutableListOf(
                location.world.getBlockAt(
                    Location(
                        location.world,
                        (bx + 1).toDouble(),
                        (by).toDouble(),
                        (bz).toDouble()
                    )
                ),
                location.world.getBlockAt(
                    Location(
                        location.world,
                        (bx - 1).toDouble(),
                        (by).toDouble(),
                        (bz).toDouble()
                    )
                ),
                location.world.getBlockAt(
                    Location(
                        location.world,
                        (bx).toDouble(),
                        (by).toDouble(),
                        (bz + 1).toDouble()
                    )
                ),
                location.world.getBlockAt(
                    Location(
                        location.world,
                        (bx).toDouble(),
                        (by).toDouble(),
                        (bz - 1).toDouble()
                    )
                )
            )
            var count = 0
            list.forEach {
                if (unable.contains(it.type)) {
                    count += 1
                }
            }
            if (count == 4) {
                return false
            }

            return true
        }
    }
}