package scala.crusader728.leetcode


object AnalyzerUserWebVisitPattern {
  type Pattern = (String, String, String)
  type User = String
  type Website = String


  private case class PatternCount(p: Pattern, count: Int)

  def mostVisitedPattern(username: Array[String], timestamp: Array[Int], website: Array[String]): List[String] = {
    val pattern = patternUserTuple(visitedByUser(username, timestamp, website))
      .groupMapReduce(_._2)(x => Set(x._1))(_ ++ _)
      .map(tuple => PatternCount(tuple._1, tuple._2.size))
      .foldLeft(collection.immutable.SortedSet.empty[PatternCount]) {case (acc, pc) => {
        acc + pc
      }}
      .max
      .p
    List(pattern._1, pattern._2, pattern._3)
  }

  private implicit val patternCountOrdering: Ordering[PatternCount] = (x: PatternCount, y: PatternCount) => {
    val compareCount = implicitly[Ordering[Int]].compare(x.count, y.count)
    if (compareCount != 0) {
      compareCount
    } else {
      implicitly[Ordering[Pattern]].compare(y.p, x.p)
    }
  }

  private def visitedByUser(username: Array[User], timestamp: Array[Int], website: Array[Website]): Map[User, List[Website]] =
    (for {
      i <- username.indices
      ts = timestamp(i)
      user = username(i)
      site = website(i)
    } yield (ts, user, site))
    .sorted
    .map(t => (t._2, t._3))
    .groupMapReduce(_._1)(tuple => List(tuple._2))(_ ++ _)



  private val toPattern: List[Website] => Pattern = {
    case x :: y :: z :: Nil => (x, y, z)
    case _ => throw new RuntimeException
  }

  private def patternUserTuple(visitRecord: Map[User, List[Website]]): LazyList[(User, Pattern)] =
    for {
      tuple <- LazyList.from(visitRecord.iterator)
      user = tuple._1
      pattern <- generatePattens(tuple._2).map(toPattern)
    } yield (user, pattern)

  private def generatePattens(ls: List[Website]): LazyList[List[Website]] = {
    def helper(websites: List[Website], result: List[Website], acc: LazyList[List[Website]]): LazyList[List[Website]] = {
      websites match {
        case Nil => if(result.size == 3) {
          result.reverse #:: acc
        } else {
          acc
        }
        case s :: ss => if(result.size == 3) {
          result.reverse #:: acc
        }  else {
          helper(ss, result, acc) #::: helper(ss, s :: result, acc)
        }
      }
    }

    helper(ls, Nil, LazyList.empty)
  }
}
