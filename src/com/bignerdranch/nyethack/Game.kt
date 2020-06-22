package com.bignerdranch.nyethack

fun main (args: Array<String>){
    val player = Player("Madrigal")
    player.castFireball()

    val auraColor = player.auraColor()
    //Состояние игрока
    printPlayerStatus(player)

    player.auraColor()


}

private fun printPlayerStatus(player: Player) {
    println(
        "(Aura: ${player.auraColor()}) " +
                "(Blassed: ${if (player.isBlessed) "YES" else "NO"})"
    )
    println("${player.name} ${player.formatHealthStatus()}")
}





