package poker.test.unit

class StraightFlushSpec extends PokerSpec {
  "A straight flush " should "be recognised" in {
    handWorks("8S 7S 6S 5S 4S", _.isStraightFlush)
  }
  it should "be recognised regardless of order" in {
    val cards = List("8S", "7S", "6S", "5S", "4S")
    for(cards <- cards.permutations.map(_.mkString(" "))) {
      handWorks(cards, _.isStraightFlush)
    }
  }
  it should "be recognised regardless of suit" in {
    val suits = List("H","S","C","D")
    val ranks = List("8", "7", "6", "5", "4")
    
    for(suit <- suits) {
      val cards = ranks.map(_ + suit).mkString(" ")
      handWorks(cards, _.isStraightFlush)
    }
  }
  //TODO: Should aces be low?
  it should "be recognised for all ranges" in {
    val allRanks = List("Q","K","J","10","9","8","7","6","5","4","3","2")
    for(start <- List.range(0,7)) {
      val cards = allRanks.slice(start, start + 5).map(_ + "H").mkString(" ")
      handWorks(cards, _.isStraightFlush)
    }
  }
}