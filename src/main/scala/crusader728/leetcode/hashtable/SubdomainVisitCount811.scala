package scala.crusader728.leetcode.hashtable

object SubdomainVisitCount811 {
  type Domain = String
  type Cnt = Int
  type Pair = (Domain, Cnt)

  def parse(cpDomain: String): List[Pair] = {
    val parts = cpDomain.split(" ")
    val cnt = parts(0).toInt
    val domain = parts(1)

    @scala.annotation.tailrec
    def go(i: Int, acc: List[Domain]): List[Domain] = {
      if(i == domain.length) {
        acc
      } else {
        if(i == 0) {
          go(i + 1, domain :: acc)
        } else if(domain(i) != '.') {
          go(i + 1, acc)
        } else {
          go(i + 1, domain.substring(i + 1) :: acc)
        }
      }
    }

    go(0, Nil).map(domain => (domain, cnt))
  }
  def collect(pairs: List[Pair]): Map[Domain, Cnt] = pairs.foldLeft(Map.empty[Domain, Cnt]) { case (acc, p) => acc + (p._1 -> p._2) }
  def reduce(list: Seq[Map[Domain, Cnt]]): Map[Domain, Cnt] = list.foldLeft(Map.empty[Domain, Cnt]) {case (acc, m) => m.foldLeft(acc) {
    case (e, pair) =>
      e + (pair._1 -> (e.getOrElse(pair._1, 0) + pair._2))
  }}


  def subdomainVisits(cpdomains: Array[String]): List[String] = {
    reduce(cpdomains.map(parse)
      .map(collect))
      .foldLeft(List.empty[String]) {
        case (acc, (k, v)) => s"$v $k" :: acc }

  }

  def main(args: Array[String]): Unit = {
    println(subdomainVisits(Array("9001 discuss.leetcode.com")))
  }
}
