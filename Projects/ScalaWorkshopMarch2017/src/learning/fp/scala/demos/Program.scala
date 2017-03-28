package learning.fp.scala.demos

object Program {
  def main(args: Array[String]) {
    val data = List("ab", "cde", "fg", "hijk", "lm",
                    "nop", "qr", "stu", "vwxyz")

    print("The original data\n\t")
    data.foreach(s => print(s + " "))
    println

    val result1a = data.filter(s => s.length == 2)
    val result1b = data.filter(_.length == 2)

    printResults("Strings of length 2", result1a)
    printResults("Strings of length 2", result1b)

    val result2a = data.map(s => s.length)
    val result2b = data.map(_.length)

    printResults("Lengths of all the strings", result2a)
    printResults("Lengths of all the strings", result2b)

    val result3a = data.map(s => s.toList)
    val result3b = data.map(_.toList)

    printResults("Lists of chars from the strings", result3a)
    printResults("Lists of chars from the strings", result3b)

    val result4a = data.flatMap(s => s.toList)
    val result4b = data.flatMap(_.toList)

    printResults("Chars from the strings", result4a)
    printResults("Chars from the strings", result4b)

    val result5 = data.filter(_.length == 3).combinations(2)
    printResults("Possible pairs of 3 char strings", result5.toList)

    val result6 = data.partition(_.length == 3)
    printResults("Strings which have a length of 3", result6._1)
    printResults("Strings with a length OTHER than 3", result6._2)

    val result7 = data.find(_.length == 5)
    result7.foreach(printResults("The string of length 5",_))

    val result8 = data.find(_.length == 6)
    result8.foreach(printResults("The string of length 6",_))

    val result9a = data.reduce((a,b) => a + b)
    val result9b = data.reduce(_ + _)

    printResult("The strings concatenated", result9a)
    printResult("The strings concatenated", result9b)

    val result10 = data.foldLeft(0)((a,b) => a + b.length)
    printResult("Total lengths of all the strings", result10)

    val result11 = data.groupBy(_.length)
    println("Strings grouped by length:")
    for(row <- result11) {
      println("\tStrings of length " + row._1)
      print("\t\t")
      for(s <- row._2) {
        print(s)
        print(" ")
      }
      println
    }

    val result12a = data.filter(_.length == 3)
                        .map(s => new Result(s + "foo", Thread.currentThread.getId))
    val result12b = data.par
                        .filter(_.length == 3)
                        .map(s => new Result(s + "bar", Thread.currentThread.getId))

    printResults("Items processed on a single thread", result12a)
    printResults("Items processed on a multiple threads", result12b.toList)
  }
  def printResult(title: String, result: Any): Unit = {
    println(title + ":")
    print("\t")
    println(result)
  }
  def printResults(title: String, results : Iterable[Any]): Unit = {
    println(title + ":")
    print("\t")
    for(s <- results) {
      print(s)
      print(" ")
    }
    println()
  }
}
