/** A simple dictionary that contains a set of words.
  */
object WordDictionary:
  val words: Trie =
    val source = io.Source.fromResource("words.txt")
    val wordsIterator = source.getLines().filter(_.forall(_.isLetter)).map(_.toUpperCase)
    val wordsTrie = wordsIterator.foldLeft(Trie())((trie: Trie, word: String) => trie.insert(word))
    source.close()
    wordsTrie

  /** Check if a word is in the dictionary
    *
    * @param word
    *   The word to check for in the dictionary
    * @return
    *   True if the word is in the dictionary, false otherwise
    */
  def isWord(word: String): Boolean =
    words.contains(word.toUpperCase)
