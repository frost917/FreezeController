package com.frost917.mcserver.powdersnow

import org.bukkit.entity.Player

object FrozenForever {
    private val frozenList = mutableListOf<Player>()

    fun newFrozenForever(player: Player) {
        frozenList.add(player)
    }

    fun delFrozenForever(player: Player) {
        frozenList.remove(player)
    }

    fun getFrozenForever(): List<Player> {
        return frozenList
    }
}