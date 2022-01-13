package scala.crusader728.leetcode.bfs

object BusRoutes815 {
  type Stop = Int
  type Route = Int
  def numBusesToDestination(routes: Array[Array[Int]], source: Int, target: Int): Int = {
    if(source == target) {
      0
    } else {
      val stopToRouteMap: Map[Stop, Set[Route]] = routes.indices.foldLeft(Map.empty[Stop, Set[Route]]) {case (acc, i) => {
        routes(i).foldLeft(acc) {case (m, stop) => {
          m + (stop -> (m.getOrElse(stop, Set.empty) + i))
        }}
      }}
      @scala.annotation.tailrec
      def bfs(current: Set[Route], visited: Set[Route], currentStep: Int): Int = {
        if(current.isEmpty) {
          -1
        } else if(current.intersect(stopToRouteMap(target)).nonEmpty) {
          currentStep
        } else {
          val newVisited = visited.union(current)
          val next = for {
            route <- current
            nodes <- routes(route).toSet[Int]
            nextRoute <- stopToRouteMap(nodes)
            if !visited(nextRoute)
          } yield nextRoute
          bfs(next, newVisited, currentStep + 1)
        }
      }

      bfs(stopToRouteMap(source), Set.empty, 1)
    }}

  def main(args: Array[String]): Unit = {
    println(numBusesToDestination(
      Array(
        Array(1,2,7),
        Array(3,6,7)
      ), 1,6
    ))
  }
}
