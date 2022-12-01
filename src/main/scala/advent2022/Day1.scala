package rusticdurian.org
package advent2022
import scala.annotation.tailrec
import scala.io.{BufferedSource, Source}

object Day1 {
  val filename = "src/main/scala/advent2022/D1.txt"
  val source: BufferedSource = Source.fromFile(filename)
  val lines: List[String] = (for (line <- source.getLines()) yield line).toList
  source.close()

  def greatestSum(list: List[String]): Int =
    @tailrec
    def adder(list: List[String], currentSum: Int = 0, largestSum: Int = 0): Int =
      if list.isEmpty then largestSum
      else if list.head == "" then
        if currentSum > largestSum then adder(list.tail, 0, currentSum)
        else adder(list.tail, 0, largestSum)
      else adder(list.tail, currentSum + list.head.toInt, largestSum)
    end adder
    adder(list)
  end greatestSum

  def top3Elves(list: List[String]): List[Int] =
    @tailrec
    def superAdder(list: List[String], sum: Int = 0, currentSums: List[Int] = List()): List[Int] =
      if list.isEmpty then currentSums
      else if list.head == "" then superAdder(list.tail, 0, sum :: currentSums)
      else superAdder(list.tail, sum + list.head.toInt, currentSums)
    end superAdder
    superAdder(list).sortWith(_ > _).take(3)
  end top3Elves

  def main(args: Array[String]): Unit =
    println(greatestSum(lines))
    println(top3Elves(lines).sum)
  end main
}
