import forcomp.Anagrams
def l4 = List(('a', 2), ('b', 2))
l4
def l5 = "aabb"
l5.combinations(4)
l5.combinations(3)

{ for {
  i <- l4
} yield "".padTo(i._2, i._1) }.mkString

{ for {
  n <- 0 to l5.length()
  l <- l5.combinations(n).toList
} yield Anagrams.wordOccurrences(l)
}.toList

def occ = Anagrams.wordOccurrences("asdffff")
def occy = Anagrams.wordOccurrences("asdf")


  for {
    xs <- occ
  } yield (xs._1, xs._2 - occy.toMap.get(xs._1).get)