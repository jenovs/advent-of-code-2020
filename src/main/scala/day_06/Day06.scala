package Day06

import scala.io.Source

object Day06 extends App {
  val input = Source
    .fromFile("src/main/scala/day_06/input.txt")
    .getLines
    .mkString(" ")
    .split("  ")
    .toList

  val part1 = input.map(n => n.filter(_.isLetter).toSet.size).sum

  println(part1)

  val part2 = (for {
    c <- input
      .map(str => {
        str.split(" ").length match {
          case 1 => str.length
          case groupSize => {
            val all = str.filter(_.isLetter).mkString
            all.filter(str => all.count(_ == str) == groupSize).toSet.size
          }
        }
      })
  } yield c).sum

  println(part2)

}
