package com.bignerdranch.nyethack

import java.lang.IllegalStateException
import kotlin.system.exitProcess

fun main (args: Array<String>){

    Game.play()

}


object Game {
    private val player = Player("Madrigal")
    private var currentRoom : Room = TownSquare()

    private var worldMap = listOf(
            listOf(currentRoom, Room("Tavern"), Room("Back Room")),
            listOf(Room("Long Corridor"), Room("Generic Room"))
    )

    init {
        println("Welcome, adventurer.")
        player.castFireball()
    }
    fun play(state:Boolean = true){
        while (state){
            println(currentRoom.description())
            println(currentRoom.load())
            //Состояние игрока
            printPlayerStatus(player)

            print("> Enter your command: ")
            println(GameInput(readLine()).processCommand())
        }
        println("Goodbye!")
        exitProcess(0)

    }

    private fun printPlayerStatus(player: Player) {
        println(
                "(Aura: ${player.auraColor()}) " +
                        "(Blassed: ${if (player.isBlessed) "YES" else "NO"})"
        )
        println("${player.name} ${player.formatHealthStatus()}")
    }

    private class GameInput(arg:String?){
        private val input = arg ?: ""
        val command = input.split(" ")[0]
        val argument = input.split(" ")
                .getOrElse(1,{""})

        fun processCommand() = when (command.toLowerCase()){
            "move" -> move(argument)
            "quit" -> play(false)
            "map" -> map()
            "fight" -> fight()
            else -> commandNotFound()
        }

        private fun commandNotFound() = "I'm not quite sure what you're trying to do!"


    }

    private fun move(directionInput: String) =
        try {
            val direction = Direction.valueOf(directionInput.toUpperCase())
            val newPosition = direction.updateCoordinate(player.currentPosition)
            if (!newPosition.isBounds){
                throw IllegalStateException("$direction is out of bounds.")
            }
            val newRoom = worldMap[newPosition.y][newPosition.x]
            currentRoom = newRoom
            "OK, you move $direction to the ${newRoom.name}.\n${newRoom.load()}"
        } catch (e:Exception) {
            "Invalid direction: $directionInput"
        }

    private fun fight() = currentRoom.monster?.let{
        while (player.healthPoints > 0 && it.healthPoints > 0) {
            slay(it)
            Thread.sleep(1000)
        }
        "Combat complete."
    } ?: "There's nothing here to fight."

    private fun slay(monster: Monster){
        println("${monster.name} did ${monster.attack(player)} damage!")
        println("${player.name} did ${player.attack(monster)} damage!")

        if (player.healthPoints <= 0) {
            println(">>>> You have been defeated! Thenks for playing. <<<<")
            exitProcess(0)
        }

        if (monster.healthPoints <= 0) {
            println(">>>> ${monster.name} has been defeated! <<<<")
            currentRoom.monster = null
        }
    }

    private fun map(){
        println("${player.currentPosition}")
    }

}



