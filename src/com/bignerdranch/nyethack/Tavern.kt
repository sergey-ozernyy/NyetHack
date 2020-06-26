package com.bignerdranch.nyethack
import com.bignerdranch.nyethack.extensions.random as randomizer
import java.io.File


const val TAVERN_NAME = "Tearnyl's Folly"


val patronList = mutableListOf("Eli", "Mordoc", "Sophie")
val lastName = listOf("Ironfoot", "Fernsworth", "Baggins")
val uniquePatrons = mutableSetOf<String>()
val menuList = File("data/tavern-menu-items.txt")
        .readText()
        .split("\n")
val patronGold = mutableMapOf<String, Double>()


fun main(args: Array<String>) {

    (0..9).forEach{
        val first = patronList.randomizer()
        val last = lastName.randomizer()
        val name = "$first $last"
        uniquePatrons += name
    }

    uniquePatrons.forEach{
        patronGold[it] = 6.0
    }

    var orderCount = 0
    while (orderCount <= 9) {
        placeOrder(uniquePatrons.randomizer(),
                menuList.randomizer())
        orderCount ++
    }

    displayPatronBalanse()

}

private fun displayPatronBalanse(){
    patronGold.forEach{ patron, balance ->
        println("$patron, balance: ${"%2f".format(balance)}")
    }
}

fun performPurchase(price:Double, patronName: String){
    val totalPurche = patronGold.getValue(patronName)
    if (totalPurche < price ) {
        uniquePatrons.remove(patronName)
        patronGold.remove(patronName)
    }
    else {
        patronGold[patronName] = totalPurche - price}

}

private fun toDragonSpeak(phrase: String) =
        phrase.replace(Regex("[aAeEiIoOuU]")){
            when (it.value) {
                "a" -> "4"
                "A" -> "4"
                "e" -> "3"
                "i" -> "1"
                "I" -> "1"
                "o" -> "0"
                "u" -> "|_|"
                "U" -> "|_|"
                else -> it.value
            }
        }

private fun placeOrder(patronName: String, menuData:String){
    val indexOfApostrophe = TAVERN_NAME.indexOf('\'')
    val tavernMaster = TAVERN_NAME.substring(0 until indexOfApostrophe)
    println("$patronName speaks with $tavernMaster about their order.")


    val (type, name, price) = menuData.split(',')
    val message = "$patronName a $name ($type) for $price."
    println(message)

    performPurchase(price.toDouble(), patronName)

    val phrase = if (name == "Dragon's Breath") {
        "$patronName exclaims: ${toDragonSpeak(("Ah, delicions $name!"))}"
    } else {
        "$patronName says: Thanks for the $name."
    }
    println(phrase)
}