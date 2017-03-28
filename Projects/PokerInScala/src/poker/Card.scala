package poker

import Suits._

object Card {
  def apply(str: String) : Card = {
    val tenCard = str.startsWith("10")
    val rank = if(tenCard) "10" else str.substring(0,1)
    val suitAsString = str.substring(if(tenCard) 2 else 1) 
    Card(rank, Suits.withName(suitAsString))
  }
}
case class Card(val rank : String, val suit : Suits.Value) {
  override def toString = {
    rank + suit.toString
  }
}