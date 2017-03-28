package demos.scalacheck

import org.scalacheck.Gen.alphaStr
import org.scalacheck.Test.Parameters
import org.scalacheck.Prop.{forAll,BooleanOperators}


object StringBuilderPropsRedux {
  def main(args:Array[String]) {
    val params = new Parameters.Default {
      override val minSuccessfulTests = 10000
    }
    val prop = forAll(alphaStr) { s: String =>
      (s.length > 1) ==> {
        val sb = new StringBuilder()
        sb.append(s)
        sb.toString != sb.reverse.toString
      }
    }
    prop.check(params)
  }
}
