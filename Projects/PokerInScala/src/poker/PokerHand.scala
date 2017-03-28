package poker

import PokerHand.allRanks

object PokerHand {
  val allRanks = List("A","Q","K","J","10","9","8","7","6","5","4","3","2")
  def apply(line:  String) = new PokerHand(line.split(" ").map(Card.apply))
  def apply(words: Seq[String]) = new PokerHand(words.map(Card.apply))
}
class PokerHand(hand: Seq[Card]) {
  def name = {
    if (isRoyalFlush)         "Royal Flush"
    else if(isStraightFlush)  "Straight Flush"
    else if(isFourOfAKind)    "Four Of A Kind"
    else if(isFullHouse)      "Full House"
    else if(isFlush)          "Flush"  
    else if(isStraight)       "Straight"
    else if(isThreeOfAKind)   "Three Of A Kind"
    else if(isTwoPair)        "Two Pair"
    else if(isPair)           "Pair"
    else                      "Highest Card"
  }
  override def toString = hand.foldLeft("")(_ + " " + _) + " " + name
  
  def nOf(count: Int)(rank : String) = hand.count(_.rank == rank) >= count
  def twoOf = nOf(2)_
  def threeOf = nOf(3)_
  def fourOf = nOf(4)_
  
  def allOfSameSuit = hand.forall(_.suit == hand(0).suit)
  def allCardsHigh =  Array("A","Q","K","J","10").forall(r => hand.exists(_.rank == r))
  def ranksInDescendingOrder = hand.map(_.rank).sortWith(allRanks.indexOf(_) < allRanks.indexOf(_))
  
  def isFlush         = allOfSameSuit
  def isRoyalFlush    = allOfSameSuit && allCardsHigh
  def isStraightFlush = isStraight && allOfSameSuit
  def isFourOfAKind   = allRanks.exists(fourOf)
  def isThreeOfAKind  = allRanks.exists(threeOf)
  def isTwoPair       = allRanks.combinations(2).exists(pair => twoOf(pair(0)) && twoOf(pair(1)))
  def isPair          = allRanks.exists(twoOf)
  def isStraight      = allRanks.containsSlice(ranksInDescendingOrder)
  def isFullHouse = {
      def twoOfThenThreeOf(pair : List[String]) = twoOf(pair(0)) && threeOf(pair(1))
      def threeOfThenTwoOf(pair : List[String]) = threeOf(pair(0)) && twoOf(pair(1))
      
      allRanks.combinations(2).exists(pair => twoOfThenThreeOf(pair) || threeOfThenTwoOf(pair))		  							      
  }
}