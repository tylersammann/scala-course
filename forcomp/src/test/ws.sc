
def str: String = "asdfasdfff"
str.groupBy((c: Char) => c).toList.groupBy((e:(Char, String)) => e._2.length())

def ll = str.groupBy((c: Char) => c).values.toList
ll

def listy = ll.groupBy((e: String) => e.charAt(0)).toList
listy
listy(2)._2.head.length()

{for {
  l <- listy
} yield (l._1, l._2.head.length)}.sorted