package streams

import org.scalatest.FunSuite

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

import Bloxorz._

@RunWith(classOf[JUnitRunner])
class BloxorzSuite extends FunSuite {

  trait SolutionChecker extends GameDef with Solver with StringParserTerrain {
    /**
     * This method applies a list of moves `ls` to the block at position
     * `startPos`. This can be used to verify if a certain list of moves
     * is a valid solution, i.e. leads to the goal.
     */
    def solve(ls: List[Move]): Block =
      ls.foldLeft(startBlock) { case (block, move) => move match {
        case Left => block.left
        case Right => block.right
        case Up => block.up
        case Down => block.down
      }
    }
  }

  trait Level0 extends SolutionChecker {
      /* terrain for level 1*/

    val level =
      """------
        |--ST--
        |--oo--
        |--oo--
        |------""".stripMargin

    val optsolution = List(Down, Right, Up)
  }

  trait Level1 extends SolutionChecker {
    /* terrain for level 1*/
    val level =
      """ooo-------
        |oSoooo----
        |ooooooooo-
        |-ooooooooo
        |-----ooToo
        |------ooo-""".stripMargin

    val optsolution = List(Right, Right, Down, Right, Right, Right, Down)

    val standBlock = new Block(Pos(1,2), Pos(1,2))
    val flatBlock = new Block(Pos(1,2), Pos(2,2))
    val outBlock = new Block(Pos(3,0), Pos(3,0))
    val validOutBlock = new Block(Pos(3,1), Pos(4,1))
    val block11 = new Block(Pos(1,1),Pos(1,1))
    val historyLU = List(Left,Up)
  }

  test("terrain function level 1") {
    new Level1 {
      assert(terrain(Pos(0,0)), "0,0")
      assert(!terrain(Pos(4,11)), "4,11")
    }
  }

  test("findChar level 1") {
    new Level1 {
      assert(startPos == Pos(1,1))
    }
  }

  test("isStanding") {
    new Level1 {
      assert(standBlock.isStanding)
      assert(!flatBlock.isStanding)
    }
  }

  test("isLegal") {
    new Level1 {
      assert(standBlock.isLegal)
      assert(flatBlock.isLegal)
      assert(!outBlock.isLegal)
      assert(!validOutBlock.isLegal)
    }
  }

  test("neighborsWithHistory") {
    new Level1 {
      assert(neighborsWithHistory(block11, historyLU).toSet ===
        Set(
          (Block(Pos(1,2),Pos(1,3)), List(Right,Left,Up)),
          (Block(Pos(2,1),Pos(3,1)), List(Down,Left,Up))
        )
      )
    }
  }

  test("newNeighborsOnly") {
    new Level1{
      assert (
        newNeighborsOnly(
          Set(
            (Block(Pos(1,2),Pos(1,3)), List(Right,Left,Up)),
            (Block(Pos(2,1),Pos(3,1)), List(Down,Left,Up))
          ).toStream,
          Set(Block(Pos(1,2),Pos(1,3)), Block(Pos(1,1),Pos(1,1)))
        ).toSet
        ===
        Set(
          (Block(Pos(2,1),Pos(3,1)), List(Down,Left,Up))
        )
      )
    }
  }

  test("optimal solution for level 1") {
    new Level1 {
      assert(solve(solution) == Block(goal, goal))
    }
  }

  test("optimal solution length for level 1") {
    new Level1 {
      assert(solution.length == optsolution.length)
    }
  }

  test("optimal solution for level 0") {
    new Level0 {
      assert(solve(solution) == Block(goal, goal))
    }
  }

  test("optimal solution length for level 0") {
    new Level0 {
      assert(solution.length == optsolution.length)
    }
  }
}
