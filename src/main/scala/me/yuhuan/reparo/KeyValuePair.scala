package me.yuhuan.reparo

/**
  * @author Yuhuan Jiang (jyuhuan@gmail.com).
  */
trait KeyValuePair[K, +V] {
  def key: K
  def value: V
}

object KeyValuePair {
  def apply[K, V](k: K, v: V) = new KeyValuePair[K, V] {
    def key: K = k
    def value: V = v
  }
}
