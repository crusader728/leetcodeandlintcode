package scala.crusader728.leetcode

object ParkingSystem {
  sealed trait SpotSize
  implicit case object BIG extends SpotSize
  implicit case object MEDIUM extends SpotSize
  implicit case object SMALL extends SpotSize

  type State = Map[SpotSize, Int]

  class ParkingSystem(_big: Int, _medium: Int, _small: Int) {
    var state: State = Map.empty

    def addCar(carType: Int): Boolean = {
      carType match {
        case 1 => if(state.getOrElse(BIG, 0) >= _big) {
          false
        } else {
          state = state + (BIG -> (state.getOrElse(BIG, 0) + 1))
          true
        }
        case 2 => if(state.getOrElse(MEDIUM, 0) >= _medium) {
          false
        } else {
          state = state + (MEDIUM -> (state.getOrElse(MEDIUM, 0) + 1))
          true
        }
        case 3 => if(state.getOrElse(SMALL, 0) >= _small) {
          false
        } else {
          state = state + (SMALL -> (state.getOrElse(SMALL, 0) + 1))
          true
        }
      }
    }

  }

}
