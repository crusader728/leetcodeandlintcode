package scala.crusader728.leetcode.stack

object DesignBrowserHistory1472 {

  class BrowserHistory(_homepage: String) {
    var history: List[String] = List(_homepage)
    var future: List[String] = Nil

    def visit(url: String): Unit = {
      history = url :: history
      future = Nil
    }

    def back(steps: Int): String = {
      history match {
        case Nil => throw new RuntimeException
        case _homepage :: Nil => _homepage
        case x :: xs => if(steps == 0) {
          x
        } else {
          future = x :: future
          history = history.tail
          back(steps - 1)
        }
      }
    }

    def forward(steps: Int): String = {
      future match {
        case Nil => history.head
        case x :: xs =>
          if(steps == 0) {
            history.head
          } else {
            history = x :: history
            future = future.tail
            forward(steps - 1)
          }
      }
    }
  }

}
