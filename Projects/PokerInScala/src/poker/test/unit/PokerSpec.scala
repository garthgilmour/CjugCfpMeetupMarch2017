package poker.test.unit

import org.scalatest._
import poker.PokerHand

abstract class PokerSpec extends FlatSpec with Matchers {
  def handWorks(cards : String, identifier: (PokerHand) => Boolean) {
    val hand = PokerHand(cards)
    identifier(hand) should be (true)
  }
  def worksInAnyOrder(cards : List[String], func: (PokerHand) => Boolean) {
    for(cards <- cards.permutations.map(_.mkString(" "))) {
      handWorks(cards, func)
    }
  }
}