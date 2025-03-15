import scala.collection.parallel.CollectionConverters._

/** A class to find all of the words in a scoggle board
  *
  * @param scoggleBoard
  *   The scoggle board to find words in
  */
class ScoggleSolver(val scoggleBoard: ScoggleBoard):
  private val validWordTrie =
    WordDictionary.words.foldLeft(Trie())((trie: Trie, word: String) => trie.insert(word))

  /** Get the neighbors of a given position on the board
    *
    * @param board
    *   The scoggle board to get neighbors from
    * @param position
    *   The position to get neighbors for
    * @return
    *   A list of positions that are neighbors to the given position. These positions are guaranteed
    *   to be within the bounds of the board.
    */
  private def getNeighbors(board: ScoggleBoard, position: (Int, Int)) =
    val (x, y) = position
    for
      dx <- -1 to 1
      dy <- -1 to 1
      if (dx != 0 || dy != 0) &&
        dx + x >= 0 &&
        dx + x < ScoggleBoard.SIZE &&
        dy + y >= 0 &&
        dy + y < ScoggleBoard.SIZE
    yield (dx + x, dy + y)

    /** Solve the scoggle board starting at a given position
      *
      * @param x
      *   The x coordinate to start searching for words
      * @param y
      *   The y coordinate to start searching for words
      * @return
      *   The set of words that can be found starting at the given position
      */
  private def solve(x: Int, y: Int): Set[String] =
    val visited = Array.ofDim[Boolean](4, 4)

    def dfs(x: Int, y: Int, word: String, trie: Trie): Set[String] =

      var resultSet = Set[String]()

      val currentString = scoggleBoard.grid(x)(y)
      val newTrie =
        if currentString.length() == 1
        then trie.children.getOrElse(currentString(0), Trie())
        else
          // This jankiness is purely to support the QU die face
          trie.children
            .getOrElse(currentString(0), Trie())
            .children
            .getOrElse(currentString(1), Trie())
      val newWord = word + currentString
      if newTrie.isWord && newWord.length() >= 3 then resultSet += newWord

      if newTrie.children.isEmpty then resultSet
      else
        visited(x)(y) = true
        val neighbors = getNeighbors(scoggleBoard, (x, y)).filterNot(pos => visited(pos._1)(pos._2))
        val neighborWords =
          neighbors.map(pos => dfs(pos._1, pos._2, word + currentString, newTrie))
        visited(x)(y) = false

        resultSet ++ neighborWords.flatten

    dfs(x, y, "", validWordTrie)

  /** Finds all words on the board for the given scoggle solver.
    *
    * @return
    *   A set of all words that can be found on the board.
    */
  def solve(): Set[String] =
    val startingPoints = for
      x <- 0 until ScoggleBoard.SIZE
      y <- 0 until ScoggleBoard.SIZE
    yield (x, y)

    val results = startingPoints.toArray.par.map: (x, y) =>
      solve(x, y)

    results.reduce(_ ++ _)
