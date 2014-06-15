import forcomp.Anagrams._
def l4 = List(('a', 2), ('b', 2))
def l7 = List(('a', 1), ('b', 2))
l4
def l5 = "aabb"
l5.combinations(4)
l5.combinations(3)

{
  def s = List("me", "at")
  def sOcc = sentenceOccurrences(s)
  for {
    occ <- combinations(sOcc).filterNot((e: Occurrences) => e == Nil || e == List())
    currWord <- wordAnagrams(occurencesMkStr(occ)).filter((e: Word) => e.length() == occurencesMkStr(occ).length() && e.length() != 0)
  } yield {
    currWord
  }
}
//}.filterNot((e: List[Word]) => e.head == "").toSet.toList


