object WordDictionary:
  val words: Set[String] =
    val source = io.Source.fromResource("words.txt")
    val words = source.getLines().filter(_.forall(_.isLetter)).map(_.toUpperCase).toSet
    source.close()
    words

  def isWord(word: String): Boolean =
    words.contains(word.toUpperCase)
