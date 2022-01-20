package com.frost917.mcserver.powdersnow

import org.bukkit.Bukkit
import org.bukkit.command.Command
import org.bukkit.command.CommandSender
import org.bukkit.command.TabCompleter

class FreezeTabCompleter: TabCompleter {
    override fun onTabComplete(
        sender: CommandSender,
        command: Command,
        alias: String,
        args: Array<out String>
    ): MutableList<String>? {
        val tabList = mutableListOf<String>()
        tabList.add("<ticks>")
        tabList.add("info")
        tabList.add("warm")
        tabList.add("forever")

        if (args.count() == 1) {
            if (sender.isOp) {
                Bukkit.getOnlinePlayers().forEach {
                    tabList.add(it.name)
                }
            }
        } else if (args.count() == 2) {
            return tabList
        } else {
            return null
        }

        return tabList
    }
}