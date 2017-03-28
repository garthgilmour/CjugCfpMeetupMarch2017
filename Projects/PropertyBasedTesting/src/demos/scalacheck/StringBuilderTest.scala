package demos.scalacheck

import org.scalatest.FunSuite
import org.scalatest.prop.Checkers
import org.scalacheck.Prop.{AnyOperators, BooleanOperators, forAll, all}

class StringBuilderTest extends FunSuite with Checkers {
  test("appending is supported") {
    check(
      forAll { (s1: String, s2: String) =>
        val sb = new StringBuilder()
        sb.append(s1).append(s2)
        sb.toString == (s1 + s2)
      }
    )
  }
  test("inserting is supported") {
    check(
      forAll { (s1: String, s2: String) =>
        val sb = new StringBuilder()
        sb.insert(0,s1).insert(0,s2)
        all(
          "content" |: sb.toString =? (s2 + s1),
          "length"  |: sb.toString.length =? (s2.length + s1.length)
        )
      }
    )
  }
}
