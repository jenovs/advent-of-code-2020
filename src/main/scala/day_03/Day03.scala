package Day03

import scala.io.Source
import scala.annotation.tailrec

object Day03 extends App {
  val input = Source
    .fromFile("src/main/scala/day_03/input.txt")
    .getLines
    .toList

  def mapper(stepX: Int = 3, stepY: Int = 1) = {
    @tailrec
    def inner(
        x: Int = 0,
        y: Int = 0,
        trees: Int = 0
    ): Long = {
      val currY = y + stepY

      if (currY >= input.length) trees
      else {
        val nextX = x + stepX
        val width = input.head.length
        val currX = if (nextX >= width) nextX % width else nextX
        val updTrees = if (input(currY)(currX) == '#') trees + 1 else trees

        inner(currX, currY, updTrees)
      }
    }

    inner()
  }

  val part1 = mapper()

  println(part1)

  val part2 = mapper(1, 1) *
    part1 *
    mapper(5, 1) *
    mapper(7, 1) *
    mapper(1, 2)

  println(part2);
}
