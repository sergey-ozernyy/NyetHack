const val TAVERN_NAME = "Tearnyl's Folly"
var playerGold = 10
var playerSilver = 10



fun main(args: Array<String>) {

    placeOrder("shandy,Dragon's Breath,5.91")
    //placeOrder("elixir,Shirley's Temple,4.12")

}

fun performPurchase(price: Double){
    displayBalace()
    println("Purchasing item for $price")
}

private fun displayBalace(){
    println("Player's purse balance: Gold: $playerGold , Silver: $playerSilver")
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

fun placeOrder(menuData:String){
    val indexOfApostrophe = TAVERN_NAME.indexOf('\'')
    val tavernMaster = TAVERN_NAME.substring(0 until indexOfApostrophe)
    println("Madrigal spaeks with $tavernMaster about their order.")


    val (type, name, price) = menuData.split(',')
    val message = "Madrigal buys a $name ($type) for $price."
    println(message)

    performPurchase(price.toDouble())

    val phrase = if (name == "Dragon's Breath") {
        "Madrigal exclaims: ${toDragonSpeak(("Ah, delicions $name!"))}"
    } else {
        "Madrigal says: Thanks for the $name."
    }
    println(phrase)
}