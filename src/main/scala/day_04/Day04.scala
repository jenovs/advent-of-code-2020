package Day04

import scala.io.Source

object Day04 extends App {
  val input = Source
    .fromFile("src/main/scala/day_04/input.txt")
    .getLines
    .mkString(" ")
    .split("  ")
    .map(str =>
      str
        .split(" ")
        .map(s => s.split(":"))
        .map(a => a(0) -> a(1))
        .toMap
    )
    .toList

  type Passport = Map[String, String]

  def validateKeys(kv: Passport) = {
    val keySet = Set("byr", "iyr", "eyr", "hgt", "hcl", "ecl", "pid", "cid")
    val hasAllKeys = (kv.keySet + ("cid")).equals(keySet)
    if (hasAllKeys) Some(kv)
    else None
  }

  def validateHgt(kv: Passport) = {
    val hgt = kv("hgt")

    hgt.takeRight(2) match {
      case "cm" => {
        val hgtInt = hgt.dropRight(2).toInt
        if (hgtInt >= 150 && hgtInt <= 193) Some() else None
      }
      case "in" => {
        val hgtInt = hgt.dropRight(2).toInt
        if (hgtInt >= 59 && hgtInt <= 76) Some() else None
      }
      case _ => None
    }
  }

  def validateEcl(kv: Passport) = {
    val values = List("amb", "blu", "brn", "gry", "grn", "hzl", "oth")
    if (values.contains(kv("ecl"))) Some() else None
  }

  def validate(kv: Passport) = for {
    _ <- validateKeys(kv)
    byr = kv("byr").toInt
    if (byr >= 1920 && byr <= 2002)
    iyr = kv("iyr").toInt
    if (iyr >= 2010 && iyr <= 2020)
    eyr = kv("eyr").toInt
    if (eyr >= 2020 && eyr <= 2030)
    _ <- validateHgt(kv)
    _ <- validateEcl(kv)
    if (kv("hcl").matches("#[a-f0-9]{6}"))
    if (kv("pid").matches("[\\d]{9}"))
  } yield Some()

  val part1 = (for {
    p <- input
    _ <- validateKeys(p)
  } yield true).length

  println(part1)

  val part2 = (for {
    p <- input
    _ <- validate(p)
  } yield true).length

  println(part2)
}
