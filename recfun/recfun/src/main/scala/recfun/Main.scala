package recfun
import common._
import scala.annotation.tailrec

object Main {
  def main(args: Array[String]) {
    println("Pascal's Triangle")
    for (row <- 0 to 10) {
      for (col <- 0 to row)
        print(pascal(col, row) + " ")
      println()
    }
  }

  /**
   * Exercise 1
   */
  def pascal(c: Int, r: Int): Int = {
    if (c < 0 || c > r) return 0
    if (r == 0) return 1
    pascal(c-1, r-1) + pascal(c,r-1)
  }

  /**
   * Exercise 2
   */
  def balance(chars: List[Char]): Boolean = {
    def loop(chars: List[Char], level: Int): Boolean = {
      if (level < 0) return false
      if (chars.isEmpty) {
        if (level == 0) {
          return true
        } else {
          return false
        }
      }
      if (chars.head == '(') return loop(chars.tail, level+1)
      if (chars.head == ')') return loop(chars.tail, level-1)
      loop(chars.tail, level)
    }
    loop(chars, 0)
  }

  /**
   * Exercise 3
   */
  def countChange(money: Int, coins: List[Int]): Int = ???
}
