const val TAVERN_NAME = "Tearnyl's Folly"

fun main(args: Array<String>) {

    placeOrder("shandy,Dragon's Breath,5.91")

}

private fun toDragonSpeak(phrase: String) =
        phrase.replace(Regex("[aeiou]")){
            when (it.value) {
                "a" -> "4"
                "e" -> "3"
                "i" -> "1"
                "o" -> "0"
                "u" -> "|_|"
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

    val phrase = "Ah, delicions $name!"
    println("Madrigal exclaims: ${toDragonSpeak(phrase)}")
}