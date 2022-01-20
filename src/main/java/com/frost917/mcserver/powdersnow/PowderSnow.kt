package com.frost917.mcserver.powdersnow

import org.bukkit.Bukkit
import org.bukkit.plugin.java.JavaPlugin

class PowderSnow : JavaPlugin() {
    override fun onEnable() {
        // Plugin startup logic
        server.getPluginCommand("freeze")!!.setExecutor(FreezeTickController())
        server.getPluginCommand("freeze")!!.tabCompleter = FreezeTabCompleter()

        // frozen forever scheduler
        val scheduler = Bukkit.getScheduler()
        scheduler.runTaskTimer(this, Runnable {
            FrozenForever.getFrozenForever().forEach {
                it.freezeTicks = 1000
            }
        }, 100L, 0L)
    }

    override fun onDisable() {
        // Plugin shutdown logic
    }
}