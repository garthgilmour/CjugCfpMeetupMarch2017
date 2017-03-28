package poker.test.unit

import poker._

import org.scalacheck.Prop._
import org.scalacheck.Prop
import org.scalacheck.Gen
import org.scalacheck.Test
import org.scalacheck.Test.Parameters
import org.scalatest.FunSuite

class PokerHandGenSpec extends FunSuite {

  def buildHandGen() = {
    val rankGen = Gen.oneOf(List("A", "Q", "K", "J", "10", "9", "8", "7", "6", "5", "4", "3", "2"))
    val suitGen = Gen.oneOf(List("C", "H", "S", "D"))
    val cardGen = for {
      rank <- rankGen
      suit <- suitGen
    } yield rank + suit

    Gen.listOfN(5, cardGen)            //create a list of 5 cards
       .suchThat(_.distinct.size == 5) //remove hands with duplicated cards
       .map(PokerHand.apply)           //convert cards to poker hands
  }

  def assertProp(prop: Prop) {
    // 'dealing' the default of 50000 hands isn't always
    //   enough to get occurrences of rarer hands
    val params = Parameters.default.withMinSuccessfulTests(50000)
    val res = Test.check(params, prop)
    assert(res.passed)
  }

  val handGen = buildHandGen()

  test("Every poker hand is present") {
    val props = List(
      exists(handGen)(_.isPair),
      exists(handGen)(_.isTwoPair),
      exists(handGen)(_.isThreeOfAKind),
      exists(handGen)(_.isStraight),
      exists(handGen)(_.isFlush),
      exists(handGen)(_.isFullHouse),
      //exists(handGen)(_.isRoyalFlush), //may require more hands
      exists(handGen)(_.isFourOfAKind))

    props.foreach(assertProp(_))
  }

  test("All 2 Pairs are Pairs") {
    // Could also use filter on the generator to leave only 2-pair hands?
    val prop = forAll(handGen)(h => if (h.isTwoPair) h.isPair else true)
    assertProp(prop)
  }

  test("All Full Houses are Pairs and Three Of A Kind") {
    val check = (h: PokerHand) => if (h.isFullHouse) (h.isPair && h.isThreeOfAKind) else true
    val prop = forAll(handGen)(check)
    assertProp(prop)
  }

  test("All Three Of A Kind are Pairs") {
    val prop = forAll(handGen)(h => if (h.isThreeOfAKind) h.isPair else true)
    assertProp(prop)
  }

  test("All Four Of A Kind are Pairs and Three Of A KInd") {
    val check = (h: PokerHand) => if (h.isFourOfAKind) (h.isPair && h.isThreeOfAKind) else true
    val prop = forAll(handGen)(check)
    assertProp(prop)
  }

  test("All Royal Flushes are Flushes") {
    val prop = forAll(handGen)(h => if (h.isRoyalFlush) h.isFlush else true)
    assertProp(prop)
  }
}

