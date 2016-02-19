package me.yuhuan.reparo.immut

import me.yuhuan.reparo._
import me.yuhuan.reparo.conversion.ToScala._
import me.yuhuan.reparo.conversion.FromScala._
import me.yuhuan.reparo.witness.Equivalence

/**
  * @author Yuhuan Jiang (jyuhuan@gmail.com).
  */
class HashMap[K, +V] private(core: scala.collection.immutable.Map[K, V],
                             val equivalenceOnKey: Equivalence[K]) extends Map[K, V] {

  def contains(k: K): Boolean = ???

  def apply(k: K): V = core(k)


  def size: Int = core.size

  def map[W](f: (V) => W): Map[K, W] = ???

  def filter(p: (V) => Boolean): Map[K, V] = ???

  def pairs: Enumerable[KeyValuePair[K, V]] = ???

  def zip[W](that: Map[K, W]): Map[K, (V, W)] = ???

}

object HashMap {
  def apply[K, V](pairs: (K, V)*)(implicit equivalenceOnKey: Equivalence[K]) =
    new HashMap[K, V](scala.collection.immutable.HashMap[K,V](pairs: _*), equivalenceOnKey)
}