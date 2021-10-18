package scala.crusader728.leetcode

object ReorderDataInLogFiles {

  sealed abstract class LogEntry {
    def id: String
    def content: String
    def pos: Int
  }

  case class DigitLog(id: String, content: String, pos: Int) extends LogEntry
  case class LetterLog(id: String, content: String, pos: Int) extends LogEntry

  implicit val logEntryOrdering: Ordering[LogEntry] = (x: LogEntry, y: LogEntry) => (x, y) match {
    case (DigitLog(_, _, _), LetterLog(_, _, _)) => 1
    case (DigitLog(_, _, p1), DigitLog(_, _, p2)) => implicitly[Ordering[Int]].compare(p1, p2)
    case (LetterLog(_, _, _), DigitLog(_, _, _)) => -1
    case (LetterLog(id1, c1, _), LetterLog(id2, c2, _)) =>
      val stringOrdering = implicitly[Ordering[String]]
      val resultCompareContent = stringOrdering.compare(c1, c2)
      if (resultCompareContent == 0) {
        stringOrdering.compare(id1, id2)
      } else {
        resultCompareContent
      }
  }

  implicit class toLogEntryOps(val p: (String, Int)) extends AnyVal {
    def toLogEntry: LogEntry = {
      val tokens = p._1.split(' ')
      val content = tokens.tail.mkString(" ")
      if(content.head.isLetter) {
        LetterLog(tokens.head, content, p._2)
      } else {
        DigitLog(tokens.head, content, p._2)
      }
    }
  }

  def reorderLogFiles(logs: Array[String]): Array[String] = {
    logs
      .zipWithIndex
      .map(_.toLogEntry)
      .sorted
      .map(log => log.id + log.content)
  }
}
