package rusticdurian.org
package advent2022
import scala.annotation.tailrec
import scala.io.{BufferedSource, Source}

object Day3 {
  val filename = "inputs/D3.txt"
  val source: BufferedSource = Source.fromFile(filename)
  val lines: List[String] = (for (line <- source.getLines()) yield line).toList
  source.close()

  @tailrec
  def getSharedChar(s1: String, s2: String): Char =
    if s2 contains s1.head then s1.head
    else getSharedChar(s1.tail, s2)
  end getSharedChar

  @tailrec
  def getSharedCharGroup(s1: String, s2: String, s3: String): Char =
    if (s2 contains s1.head) && (s3 contains s1.head) then s1.head
    else getSharedCharGroup(s1.tail, s2, s3)
  end getSharedCharGroup

  def bagValue(contents: String): Int =
    val splitList = contents.splitAt(contents.length / 2)
    val values = (('a' +: ('a' to 'z')) ++ ('A' to 'Z')).zipWithIndex.tail.toMap
    values(getSharedChar(splitList(0), splitList(1)))
  end bagValue

  def groupValue(group: List[String]): Int =
    val values = (('a' +: ('a' to 'z')) ++ ('A' to 'Z')).zipWithIndex.tail.toMap
    values(getSharedCharGroup(group.head, group(1), group(2)))
  end groupValue

  def bagInspector(bags: List[String]): Int =
    @tailrec
    def inspect(bags: List[String], totalPriority: Int = 0): Int =
      if bags.isEmpty then totalPriority
      else inspect(bags.tail.tail.tail, totalPriority + groupValue(bags take 3))
    end inspect
    inspect(bags)
  end bagInspector

  def main(args: Array[String]): Unit =
    println(bagInspector(lines))
  end main
}
