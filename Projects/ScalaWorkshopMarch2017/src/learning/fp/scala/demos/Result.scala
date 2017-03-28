package learning.fp.scala.demos

class Result (val value : String, val threadID : Long) {
  override def toString() = {
    "[" + value + " on thread " + threadID + "] "
  }
}
