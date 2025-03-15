/** An immutable implementation of a Trie data structure.
  *
  * @param children
  *   A map of characters to their corresponding child Trie nodes
  * @param isWord
  *   A boolean indicating whether the current node represents a word
  */
case class Trie(
    children: Map[Char, Trie] = Map.empty,
    isWord: Boolean = false
):

  /** Insert a word into the trie
    *
    * @param word
    *   The word to insert into the trie
    * @return
    *   A new trie with the word inserted
    */
  def insert(word: String): Trie =
    if word.isEmpty then this.copy(isWord = true)
    else
      val firstChar = word.head
      val childTrie = children.getOrElse(firstChar, Trie()).insert(word.tail)
      this.copy(children = children.updated(firstChar, childTrie))

  /** Check if the trie contains a word
    *
    * @param word
    *   The word to check for in the trie
    * @return
    *   True if the trie contains the word, false otherwise
    */
  def contains(word: String): Boolean =
    if word.isEmpty then isWord
    else children.get(word.head).exists(_.contains(word.tail))

  /** Check if the trie starts with a given prefix
    *
    * @param prefix
    *   The prefix to check for in the trie
    * @return
    *   True if the trie starts with the prefix, false otherwise
    */
  def startsWith(prefix: String): Boolean =
    if prefix.isEmpty then true
    else children.get(prefix.head).exists(_.startsWith(prefix.tail))
