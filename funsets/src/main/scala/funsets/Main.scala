package funsets

object Main extends App {
  import FunSets._
  val u1234 = union(union(singletonSet(1), singletonSet(2)), union(singletonSet(3), singletonSet(4)))
  printSet(u1234)
  val mapped = map(u1234, (elem: Int) => 12 + elem)
  printSet(mapped)
  def asdf(elem: Int) = 12 + elem
  println("asdf " + asdf(1))

}
