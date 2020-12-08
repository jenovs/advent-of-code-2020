package Day08

import scala.io.Source
import scala.annotation.tailrec

object Day08 extends App {
  val input = Source
    .fromFile("src/main/scala/day_08/input.txt")
    .getLines
    .toList

  @tailrec
  def runner(input: List[String], pos: Int = 0, acc: Long = 0): Long = {
    if (input(pos) == "") acc
    else {
      input(pos).take(3) match {
        case "acc" => {
          val accMod = input(pos).split(" ")(1).toInt
          runner(input.updated(pos, ""), pos + 1, acc + accMod)
        }
        case "nop" => runner(input.updated(pos, ""), pos + 1, acc)
        case "jmp" => {
          val jmpMod = input(pos).split(" ")(1).toInt
          runner(input.updated(pos, ""), pos + jmpMod, acc)
        }
      }

    }
  }

  val part1 = runner(input)

  println(part1)

  @tailrec
  def runner2(input: List[String], pos: Int = 0, acc: Long = 0): Long = {
    if (pos >= input.length) acc
    else {
      input(pos).take(3) match {
        case "acc" => {
          val accMod = input(pos).split(" ")(1).toInt
          runner2(input.updated(pos, ""), pos + 1, acc + accMod)
        }
        case "nop" => runner2(input.updated(pos, ""), pos + 1, acc)
        case "jmp" => {
          val jmpMod = input(pos).split(" ")(1).toInt
          runner2(input.updated(pos, ""), pos + jmpMod, acc)
        }
        case _ => 0
      }

    }
  }

  input.zipWithIndex.find {
    case (l, i) => {
      val x = l.take(3) match {
        case "jmp" => runner2(input.updated(i, l.replaceAll("jmp", "nop")))
        case "nop" => runner2(input.updated(i, l.replaceAll("nop", "jmp")))
        case _     => -1
      }

      if (x > 0) {
        // Part2
        println(x)
        true
      } else {
        false
      }
    }
  }
}
