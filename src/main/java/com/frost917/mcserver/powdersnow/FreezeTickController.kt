package com.frost917.mcserver.powdersnow

import org.bukkit.Bukkit
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import org.bukkit.scheduler.BukkitRunnable
import org.bukkit.scheduler.BukkitScheduler

class FreezeTickController: CommandExecutor {
    // https://papermc.io/javadocs/paper/1.18/org/bukkit/entity/Entity.html#setFreezeTicks(int)
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        if (sender !is Player) {
            return false
        }

        if (args.isEmpty()) {
            sender.sendMessage("command call is successed!")
            return false
        }
        // /freeze <ticks> or /freeze info
        else if (args.count() == 1) {
            if (args[0].toIntOrNull() is Int) {
                val freezeTicks = args[0].toInt()
                sender.freezeTicks = freezeTicks

            } else if(args[0] == "info") {
                sender.sendMessage("${sender.player!!.name}'s freeze tick is ${sender.freezeTicks}")
                sender.sendMessage("${sender.player!!.name} frozen status: ${sender.isFrozen}")
            } else if(args[0] == "forever") {
                // 얼음 상태 고정
                FrozenForever.newFrozenForever(player = sender)
                sender.freezeTicks = 10000
            } else if(args[0] == "warm") {
                // 얼음 상태 해제
                FrozenForever.delFrozenForever(player = sender)
                sender.freezeTicks = 0
            }
        } else if (args.count() == 2) {
            if (!sender.isOp) {
                sender.sendMessage("you are not op!")
                return false
            }

            // 다른 플레이어 얼리기
            val anotherPlayer = Bukkit.getPlayer(args[0])?: throw Exception("player not found!")
            if (args[1].toIntOrNull() is Int) {
                val freezeTicks = args[1].toInt()
                anotherPlayer.freezeTicks = freezeTicks

            } else if(args[1] == "info") {
                sender.sendMessage("${anotherPlayer.player!!.name}'s freeze tick is ${anotherPlayer.freezeTicks}")
                sender.sendMessage("${anotherPlayer.player!!.name} frozen status: ${anotherPlayer.isFrozen}")
            } else if(args[1] == "forever") {
                // 얼음 상태 고정
                FrozenForever.newFrozenForever(player = anotherPlayer)
                anotherPlayer.freezeTicks = 10000
            } else if(args[1] == "warm") {
                // 얼음 상태 해제
                FrozenForever.delFrozenForever(player = anotherPlayer)
                anotherPlayer.freezeTicks = 0
            }
        }
        return true
    }
}