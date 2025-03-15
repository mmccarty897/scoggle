/** A simple dictionary that contains a set of words.
  */
object WordDictionary:
  val words: Set[String] =
    val source = io.Source.fromResource("words.txt")
    val words = source.getLines().filter(_.forall(_.isLetter)).map(_.toUpperCase).toSet
    source.close()
    words

  /** Check if a word is in the dictionary
    *
    * @param word
    *   The word to check for in the dictionary
    * @return
    *   True if the word is in the dictionary, false otherwise
    */
  def isWord(word: String): Boolean =
    words.contains(word.toUpperCase)
