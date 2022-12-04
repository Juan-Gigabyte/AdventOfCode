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

  def evalRound(roundVals: List[String]): Int =
    val p1 = roundVals.head
    val p2 = roundVals.tail.head
    p2 match
      case "X" => if p1 != "A" && p1 != "B" then 2 else if p1 == "B" then 1 else 3
      case "Y" => if p1 != "A" && p1 != "B" then 6 else if p1 == "B" then 5 else 4
      case "Z" => if p1 != "A" && p1 != "B" then 7 else if p1 == "B" then 9 else 8
  end evalRound

  def rpsStrategy(input: List[List[String]]): Int =
    @tailrec
    def rounds(input: List[List[String]], points: Int = 0): Int =
      if input.isEmpty then points
      else rounds(input.tail, points + evalRound(input.head))
    end rounds
    rounds(input)
  end rpsStrategy

  def main(args: Array[String]): Unit =
    println(rpsStrategy(strats))
  end main
}
