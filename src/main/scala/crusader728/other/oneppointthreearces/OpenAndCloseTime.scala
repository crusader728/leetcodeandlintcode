package scala.crusader728.other.oneppointthreearces


object OpenAndCloseTime {
  val secondsInDay: Long = 60 * 60 * 24
  val secondsInHour: Long = 60 * 60
  val secondsInMinute: Long = 60

  val dayMap: Map[String, Long] = Map(
    "mon" -> 0,
    "tue" -> 1,
    "wed" -> 2,
    "thu" -> 3,
    "fri" -> 4,
    "sat" -> 5,
    "sun" -> 6
  )

  val ampmOffset: Map[String, Long] = Map(
    "am" -> 0,
    "pm" -> 12 * secondsInHour
  )

  implicit class ToTimestampOps(val tsStr: String) extends AnyVal {
    def toTimestamp: Option[Long] = {
      val tokens = tsStr.split(" ")
      val hhmm = tokens(1).split(":")
      for {
        day <- dayMap.get(tokens(0))
        hh = hhmm(0).toLong
        mm = hhmm(1).toLong
        offset <- ampmOffset.get(tokens(2))
      } yield (day * secondsInDay + hh % 12 * secondsInHour + offset + mm * secondsInMinute)
    }
  }

  implicit class ToTokenizedTimestamp(val timstamp: Long) extends AnyVal {
    def toTokenizedTimestamp: String = {
      val day = timstamp / secondsInDay
      val hour = (timstamp % secondsInDay) / secondsInHour
      val minute = (timstamp % secondsInHour) / secondsInMinute

      f"${day + 1}${hour}%02d${minute}%02d"
    }
  }

  def timeRange(start: String, end: String): List[String] = {
    val startTimestamp = start.toTimestamp
    val endTimestamp = end.toTimestamp
    val range = for {
      start <- startTimestamp
      end <- endTimestamp
      range = start.to(end, 5 * secondsInMinute).map(ts => ts.toTokenizedTimestamp)
    } yield range
    range.map(_.toList)
      .getOrElse(List.empty)
  }

  def main(args: Array[String]): Unit = {
    println(timeRange("mon 12:00 am", "tue 12:00 am"))
  }
}
