package scala.crusader728.other.oneppointthreearces

object FindSimilarRestaurant {

  def similar(n: String, name: String): Boolean = {
    val difference = n.view.zip(name.view)
      .filter { case (c1, c2) => c1 != c2 }
    if(difference.isEmpty) {
      true
    } else if(difference.size != 2) {
      false
    } else {
      val (f1, t1) = difference.head
      val (f2, t2) = difference.tail.head
      f1 == t2 && f2 == t1
    }
  }

  def similarRestaurant(name: String, list: List[String]): List[String] = {
    list.filter(n => similar(n, name))
  }

  def main(args: Array[String]): Unit = {
    println(similarRestaurant("hotpot", List("hottop", "hotopt", "hotpit", "httoop", "hptoot")))
    println(similarRestaurant("biryani", List("biryani", "biryeni", "biriyani", "biriany", "briynai")))
  }
}
