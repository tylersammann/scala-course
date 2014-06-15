import forcomp.Anagrams
import forcomp.Anagrams.Sentence
def l4 = List(('a', 2), ('b', 2))
def l7 = List(('a', 1), ('b', 2))
l4
def l5 = "aabb"
l5.combinations(4)
l5.combinations(3)



{for {
  xs <- l4
} yield {
  val toSub = l7.toMap.getOrElse(xs._1, -1)
  if (toSub == -1) xs
  if (xs._2 - toSub > 0) (xs._1, xs._2 - toSub)
  else ('z', -1)
}}.filterNot((e: (Char, Int)) => (e._2 <= 0) )