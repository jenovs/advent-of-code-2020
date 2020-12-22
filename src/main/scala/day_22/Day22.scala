package Day22

import scala.io.Source

object Day22 extends App {
  val input = Source
    .fromFile("src/main/scala/day_22/input.txt")
    // .fromFile("src/main/scala/day_22/input_test.txt")
    .getLines
    .filter(!_.isBlank)
    .toList

  val p1Index = input.indexWhere(n => n.contains("Player"))
  val p2Index = input.indexWhere(n => n.contains("Player"), p1Index + 1)

  val player1 = input.slice(p1Index + 1, p2Index).map(_.toInt)
  val player2 = input.slice(p2Index + 1, input.length).map(_.toInt)

  def play(p1: List[Int], p2: List[Int]): List[Int] = {
    if (p1.isEmpty) p2
    else if (p2.isEmpty) p1
    else {
      val (p1Head, p1Tail) = (p1.head, p1.tail)
      val (p2Head, p2Tail) = (p2.head, p2.tail)
      if (p1Head > p2Head) play(p1Tail :+ p1Head :+ p2Head, p2Tail)
      else play(p2Tail :+ p2Head :+ p1Head, p1Tail)
    }
  }

  val part1 = play(player1, player2).reverse.zipWithIndex.map {
    case (card, i) => card * (i + 1)
  }.sum

  println(part1)
}
