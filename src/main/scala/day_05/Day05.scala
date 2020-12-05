package Day05

import scala.io.Source
import scala.util.Try
import java.lang.Integer._

object Day05 extends App {
  val input = Source
    .fromFile("src/main/scala/day_05/input.txt")
    .getLines
    .toList

  val ids = for {
    p <- input
    b = p
      .map(c =>
        c match {
          case 'F' => 0
          case 'B' => 1
          case 'L' => 0
          case 'R' => 1
        }
      )
      .mkString
    row = parseInt(b.take(7), 2)
    col = parseInt(b.takeRight(3), 2)
  } yield row * 8 + col

  val part1 = ids.max
  println(part1)

  val part2 = (ids.min to ids.max).toSet.diff(ids.toSet).head
  println(part2)
}
