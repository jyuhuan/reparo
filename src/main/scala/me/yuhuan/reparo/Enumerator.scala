package me.yuhuan.reparo

/**
 * Created by Yuhuan Jiang (jyuhuan@gmail.com) on 11/17/15.
 */
trait Enumerator[+X] {
  def next: Option[X]
}

object Enumerator {
  def empty: Enumerator[Nothing] = new Enumerator[Nothing] {
    def next: Option[Nothing] = None
  }
}