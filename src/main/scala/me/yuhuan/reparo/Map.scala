package me.yuhuan.reparo

import me.yuhuan.reparo.witness.Equivalence

/**
  * @author Yuhuan Jiang (jyuhuan@gmail.com).
  */
trait Map[K, +V] extends HasKey[K] { self =>
  def apply(k: K): V
  def ?(k: K): Option[V] = if (self contains k) Some(self(k)) else None

  def contains(k: K): Boolean
  def notContains(k: K) = !contains(k)

  def size: Int = pairs.size

  def map[W](f: V => W): Map[K, W] = ???

  def filter(p: V => Boolean): Map[K, V] = ???

  def zip[W](that: Map[K, W]): Map[K, (V, W)] = new Map[K, (V, W)] {
    def apply(k: K): (V, W) = (self(k), that(k))

    def pairs: Enumerable[KeyValuePair[K, (V, W)]] =
      self.pairs.filter { case KeyValuePair(k , v) => that contains k }
                .map { case KeyValuePair(k, v) => KeyValuePair(k, (v, that(k))) }

    def contains(k: K): Boolean = (self contains k) && (that contains k)

    def equivalenceOnKey: Equivalence[K] = self.equivalenceOnKey
  }

  def pairs: Enumerable[KeyValuePair[K, V]]

}
