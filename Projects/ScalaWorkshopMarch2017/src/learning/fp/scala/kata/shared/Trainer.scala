package learning.fp.scala.kata.shared

object Trainer {
  def apply(name: String, rate: Double, skills : List[String]) = {
    new Trainer(name,rate, skills)
  }
}

class Trainer(val name: String,
              val rate: Double,
              val skills : List[String]) {
}
