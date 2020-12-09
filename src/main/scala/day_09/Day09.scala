package Day09

import scala.io.Source
import scala.collection.mutable.ListMap

object Day09 extends App {
  val input = Source
    .fromFile("src/main/scala/day_09/input.txt")
    .getLines
    .map(_.toLong)
    .toList

  val preamble = input.take(25).map(n => n -> n).toIndexedSeq
  val nums = input.drop(25)

  def invalid(preamble: IndexedSeq[(Long, Long)], nums: List[Long]): Long = {
    val pair = preamble.find { case (_, n) =>
      preamble.find(m => nums(0) - n == m._1) match {
        case Some(value) => true
        case None        => false
      }
    }

    if (!pair.isEmpty)
      invalid(preamble.drop(1) :+ (nums(0), nums(0)), nums.drop(1))
    else nums(0)
  }

  val part1 = invalid(preamble, nums)

  println(part1)

  def slider(start: Int, end: Int, haystack: List[Long], needle: Long): Long = {
    val range = haystack.slice(start, end)

    if (range.sum > needle) slider(start + 1, end, haystack, needle)
    else if (range.sum < needle) slider(start, end + 1, haystack, needle)
    else range.max + range.min
  }

  val part2 = slider(0, 1, input, part1)

  println(part2)
}
