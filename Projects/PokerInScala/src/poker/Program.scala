package poker

import scala.io.Source

object Program {
  def main(args: Array[String]) {
    val hands = readHands("input/pokerHands.txt")
    nameAndPrintHands(hands)
  }
  def readHands(fileName: String) = {
    Source.fromFile(fileName)
          .getLines()
          .map(PokerHand.apply)
          .toList
  }
  private def nameAndPrintHands(hands: List[PokerHand]) {
    hands.foreach(println)
  }
}