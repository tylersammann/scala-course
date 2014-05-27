package patmat

import org.scalatest.FunSuite

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

import patmat.Huffman._

@RunWith(classOf[JUnitRunner])
class HuffmanSuite extends FunSuite {
  trait TestTrees {
    val t1 = Fork(Leaf('a',2), Leaf('b',3), List('a','b'), 5)
    val t2 = Fork(Fork(Leaf('a',2), Leaf('b',3), List('a','b'), 5), Leaf('d',4), List('a','b','d'), 9)
  }

  test("weight of a larger tree") {
    new TestTrees {
      assert(weight(t1) === 5)
    }
  }

  test("chars of a larger tree") {
    new TestTrees {
      assert(chars(t2) === List('a','b','d'))
    }
  }

  test("string2chars(\"hello, world\")") {
    assert(string2Chars("hello, world") === List('h', 'e', 'l', 'l', 'o', ',', ' ', 'w', 'o', 'r', 'l', 'd'))
  }

  //mine ====================================================================================================
  //===================================================================================================
  //===================================================================================================

  // test times()
  test("times returns correctly for List(a,b,b,c,c,c)") {
    val ret = times(List('a','b','b','c','c','c'))
    assert(ret === List(('a',1), ('b',2), ('c',3)))
  }

  test("times returns correctly for List(a,b,c,a,b,c,d)") {
    val ret = times(List('a','b','c','a','b','c','d'))
    assert(ret === List(('a',2), ('b',2), ('c',2), ('d',1)))
  }

  test("times returns correctly for List(a,b)") {
    val ret = times(List('a','b'))
    assert(ret === List(('a',1), ('b',1)))
  }

  test("times returns correctly for List()") {
    val ret = times(List())
    assert(ret === List())
  }

  test("times returns correctly for List(a)") {
    val ret = times(List('a'))
    assert(ret === List(('a', 1)))
  }

  // test makeOrderedLeafList()
  test("makeOrderedLeafList for List((a,7), (b,7), (c,4))") {
    val ret = makeOrderedLeafList(List(('a',7), ('b',7), ('c',4)))
    println(ret)
    assert(ret(0).char === 'c')
    assert(ret(1).char == 'b' || ret(1).char == 'a')
    assert(ret(2).char == 'b' || ret(2).char == 'a')
  }

  // test singleton()
  test("singleton") {
    val bad = List[CodeTree]()
    val good = List(Leaf('a',1))
    val bad2 = List(Leaf('a',1), Leaf('b',1))
    assert(singleton(bad) === false)
    assert(singleton(bad2) === false)
    assert(singleton(good) === true)
  }



  //pre made ================================================================================================
  //===================================================================================================
  //===================================================================================================
  //===================================================================================================
  //===================================================================================================

  test("makeOrderedLeafList for some frequency table") {
    assert(makeOrderedLeafList(List(('t', 2), ('e', 1), ('x', 3))) === List(Leaf('e',1), Leaf('t',2), Leaf('x',3)))
  }

  test("combine of some leaf list") {
    val leaflist = List(Leaf('e', 1), Leaf('t', 2), Leaf('x', 4))
    assert(combine(leaflist) === List(Fork(Leaf('e',1),Leaf('t',2),List('e', 't'),3), Leaf('x',4)))
  }

  test("until of some leaf list returns singleton") {
    val leaflist = List(Leaf('e', 1), Leaf('t', 2), Leaf('x', 4))
    assert(singleton(until(singleton,combine)(leaflist)) === true)
  }

  test("decode and encode a very short text should be identity") {
    new TestTrees {
      assert(decode(t1, encode(t1)("ab".toList)) === "ab".toList)
    }
  }
}
