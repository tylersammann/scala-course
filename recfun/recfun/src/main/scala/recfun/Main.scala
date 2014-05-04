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
    if (c < 0 || c > r) 0
    else if (r == 0) 1
    else pascal(c-1, r-1) + pascal(c,r-1)

  }

  /**
   * Exercise 2
   */
  def balance(chars: List[Char]): Boolean = {
    def loop(chars: List[Char], level: Int): Boolean = {
      if (level < 0 || (chars.isEmpty && level > 0)) false
      else if (chars.isEmpty && level == 0) true
      else if (chars.head == '(') loop(chars.tail, level+1)
      else if (chars.head == ')') loop(chars.tail, level-1)
      else loop(chars.tail, level)
    }
    loop(chars, 0)
  }

  /**
   * Exercise 3
   */
  def count(money: Int, coins: List[Int], total: Int): Int = {
    if (coins.isEmpty)
      total
    else if (money - coins.head == 0)
      count(money, coins.tail, total+1)
    else if (money - coins.head < 0)
      count(money, coins.tail, total)
    else
      count(money - coins.head, coins, total) + countChange(money, coins.tail)
  }

  def countChange(money: Int, coins: List[Int]): Int = {
    count(money, coins.sorted.reverse, 0)
  }
}
