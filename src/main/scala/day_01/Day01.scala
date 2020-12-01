package Day01

import scala.io.Source

object Day01 extends App {
  val YEAR = 2020
  val lines = Source
    .fromFile("src/main/scala/day_01/input.txt")
    .getLines
    .map(_.toInt)
    .toList

  val part1 = (for {
    n <- lines
      .find(num => lines.contains(YEAR - num))
  } yield n * (YEAR - n)).get

  println(part1)

  val minTwoSum = lines.sorted.take(2).sum
  val nums = lines
    .filter(l => {
      YEAR - minTwoSum - l >= 0
    })
    .sorted

  val part2 = (for {
    a <- nums
    b <- nums
    c <- nums
    if (a + b + c == YEAR)
  } yield a * b * c).distinct.head

  println(part2)
}
