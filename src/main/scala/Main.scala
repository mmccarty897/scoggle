import scala.io.StdIn.{readLine}

@main def hello(): Unit =
  val board = ScoggleBoard.getRandom()
  println(board)
  println("Are you ready to find some words?")
  readLine()
  val solver = ScoggleSolver(board)
  val words = solver.solve()
  val orderedWords =
    words.toList
      .map(word => (word.length(), word))
      .sortBy((len, word) => (-len, word))
      .map(_._2)
  orderedWords.foreach(println)
  println(s"Found ${words.size} words")
