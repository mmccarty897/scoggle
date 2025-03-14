case class Trie(
    children: Map[Char, Trie] = Map.empty,
    isWord: Boolean = false
):
  def insert(word: String): Trie =
    if (word.isEmpty) this.copy(isWord = true)
    else
      val firstChar = word.head
      val childTrie = children.getOrElse(firstChar, Trie()).insert(word.tail)
      this.copy(children = children.updated(firstChar, childTrie))

  def contains(word: String): Boolean =
    if (word.isEmpty) isWord
    else children.get(word.head).exists(_.contains(word.tail))

  def startsWith(prefix: String): Boolean =
    if (prefix.isEmpty) true
    else children.get(prefix.head).exists(_.startsWith(prefix.tail))
