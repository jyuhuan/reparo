package me.yuhuan.reparo.immut

import me.yuhuan.reparo._
import me.yuhuan.reparo.conversion.ToScala._
import me.yuhuan.reparo.conversion.FromScala._
import me.yuhuan.reparo.witness.Equivalence

/**
  * @author Yuhuan Jiang (jyuhuan@gmail.com).
  */
class HashMap[K, +V] private(elements: HasForeach[(K, V)], val equivalenceOnKey: Equivalence[K]) extends Map[K, V] {
  val core = scala.collection.immutable.Map(elements.asScala.toSeq: _*)

  def contains(k: K): Boolean = ???

  def apply(k: K): V = core(k)

  def map[W >: V](f: W => W): Map[K, W] = ???

  def filter[W >: V](f: W => Boolean): Map[K, V] = ???

  def pairs: Enumerable[KeyValuePair[K, V]] = ???

  def zip[W](that: Map[K, W]): Map[K, (V, W)] = ???

}

object HashMap {
  def apply[K, V](pairs: (K, V)*)(implicit equivalenceOnKey: Equivalence[K]) =
    new HashMap[K, V](pairs.asReparo, equivalenceOnKey)
}