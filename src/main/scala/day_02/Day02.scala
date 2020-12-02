package Day02

import scala.io.Source

object Day02 extends App {
  val input = Source
    .fromFile("src/main/scala/day_02/input.txt")
    .getLines
    .map(_.replace(":", "").split(" ").toList)
    .toList

  val part1 = (for {
    str <- input
    Array(min, max) = str(0).split("-").map(_.toInt)
    char <- str(1)
    count = str(2).count(_ == char)
    if ((min to max).contains(count))
  } yield count).length

  println(part1)

  val part2 = (for {
    str <- input
    Array(pos1, pos2) = str(0).split("-").map(_.toInt - 1)
    char <- str(1)
    hasFirst = str(2)(pos1) == char
    hasSecond = str(2)(pos2) == char
    if (hasFirst ^ hasSecond)
  } yield true).length

  println(part2)
}
