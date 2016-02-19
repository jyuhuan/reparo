package me.yuhuan.reparo

import me.yuhuan.reparo.witness._

/**
  * @author Yuhuan Jiang (jyuhuan@gmail.com).
  */
trait SortedMap[K, +V] extends Map[K, V] {
  def orderOnKey: WeakOrder[K]
  def equivalenceOnKey: Equivalence[K] = orderOnKey.asEquivalence
  def pairs: Enumerable[KeyValuePair[K, V]]
}
