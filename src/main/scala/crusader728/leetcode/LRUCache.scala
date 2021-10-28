package scala.crusader728.leetcode


object LRUCache {
  class LRUCache(_capacity: Int) {
    var storage = collection.mutable.LinkedHashMap.empty[Int, Int]
    def get(key: Int): Int = {
      if(storage.contains(key)) {
        val value = storage(key)
        storage -= key
        storage += (key -> value)
        value
      } else {
        -1
      }
    }

    def put(key: Int, value: Int) {
      storage += (key -> value)
      if(storage.size > _capacity) {
        storage = storage.tail
      }
    }

  }
}
