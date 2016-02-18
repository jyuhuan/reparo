package me.yuhuan.reparo

/**
  * @author Yuhuan Jiang (jyuhuan@gmail.com).
  */
trait Map[K, +V] extends HasKey[K] { self =>
  def apply(k: K): V
  def ?(k: K): Option[V] = if (self contains k) Some(self(k)) else None

  def contains(k: K): Boolean
  def notContains(k: K) = !contains(k)

  def map[W >: V](f: W => W): Map[K, W]

  def filter[W >: V](f: W => Boolean): Map[K, V]

  def zip[W](that: Map[K, W]): Map[K, (V, W)]

  def pairs: Enumerable[KeyValuePair[K, V]]

}
