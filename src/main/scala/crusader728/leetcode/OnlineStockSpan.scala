package scala.crusader728.leetcode

object OnlineStockSpan {
  class StockSpanner() {
    type Days = Int
    type Price = Int
    type Info = (Price, Days)
    type State = List[Info]

    var state: State = List.empty

    def next(price: Int): Int = {
      state match {
        case Nil => {
          state = (price, 1) :: Nil
          1
        }
        case (v, d) :: ss => if(v > price) {
          state = (price, 1) :: state
          1
        } else {
          @scala.annotation.tailrec
          def loop(s: State, acc: Int): (Days, State) = {
            s match {
              case Nil => (acc, Nil)
              case (v, d) :: ss => if(v > price) {
                (acc, s)
              } else {
                loop(ss, acc + d)
              }
            }
          }

          val (days, newState) = loop(state, 1)
          state = (price, days) :: newState
          days
        }
      }
    }

  }
}
