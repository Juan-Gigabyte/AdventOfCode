package rusticdurian.org
package advent2022
import scala.annotation.tailrec
import scala.io.{BufferedSource, Source}

object Day2 {
  val filename = "inputs/D2.txt"
  val source: BufferedSource = Source.fromFile(filename)
  val lines: List[String] = (for (line <- source.getLines()) yield line).toList
  source.close()

  val strats: List[List[String]] = lines.map(_.split("\\s").toList)

  def evalRound1(roundVals: List[String]): Int =
    roundVals match
      case "A" :: List(p2) => if p2 != "X" && p2 != "Y" then 3 else if p2 == "Y" then 8 else 4
      case "B" :: List(p2) => if p2 != "Y" && p2 != "Z" then 1 else if p2 == "Z" then 9 else 5
      case "C" :: List(p2) => if p2 != "Z" && p2 != "X" then 2 else if p2 == "X" then 7 else 6
      case _ => 0
  end evalRound1

  def evalRound2(roundVals: List[String]): Int =
    roundVals match
      case p1 :: List("X") => if p1 != "A" && p1 != "B" then 2 else if p1 == "B" then 1 else 3
      case p1 :: List("Y") => if p1 != "A" && p1 != "B" then 6 else if p1 == "B" then 5 else 4
      case p1 :: List("Z") => if p1 != "A" && p1 != "B" then 7 else if p1 == "B" then 9 else 8
      case _ => 0
  end evalRound2

  def rpsStrategy(input: List[List[String]], func: List[String] => Int): Int =
    @tailrec
    def rounds(input: List[List[String]], points: Int = 0): Int =
      if input.isEmpty then points
      else rounds(input.tail, points + func(input.head))
    end rounds
    rounds(input)
  end rpsStrategy

  def main(args: Array[String]): Unit =
    println(rpsStrategy(strats, evalRound1))
    println(rpsStrategy(strats, evalRound2))
  end main
}
