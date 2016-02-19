package me.yuhuan.reparo

import me.yuhuan.reparo.witness._

/**
  * @author Yuhuan Jiang (jyuhuan@gmail.com).
  */
trait Seq[+X] extends Enumerable[X] with SortedMap[Int, X] { self =>

  def apply(k: Int): X = {
    var i = 0
    var x: X = default[X]
    val e = self.newEnumerator
    while (i <= k ) {
      i += 1
      x = e.next.get
    }
    x
  }

  def length: Int = {
    var n = 0
    self foreach { i => n += 1 }
    n
  }

  override def size: Int = length

  override def map[Y](f: X => Y): Seq[Y] = new Seq[Y] {
    def newEnumerator: Enumerator[Y] = new Enumerator[Y] {
      val e = self.newEnumerator
      def next: Option[Y] = e.next map f
    }
  }

  override def filter(p: X => Boolean): Seq[X] = new Seq[X] {
    def newEnumerator: Enumerator[X] = new Enumerator[X] {
      val e = self.newEnumerator
      def next: Option[X] = {
        var curValue = default[X]
        do {
          e.next match {
            case None => return None
            case Some(x) => curValue = x
          }
        } while (!p(curValue))
        Some(curValue)
      }
    }
  }

  override def pairs: Enumerable[KeyValuePair[Int, X]] = new Enumerable[KeyValuePair[Int, X]] {
    def newEnumerator: Enumerator[KeyValuePair[Int, X]] = new Enumerator[KeyValuePair[Int, X]] {
      val e = self.newEnumerator
      var i = -1
      def next: Option[KeyValuePair[Int, X]] = e.next.map { x =>
        i += 1
        KeyValuePair(i, x)
      }
    }
  }

  override def zip[W](that: Map[Int, W]): Map[Int, (X, W)] = ???


  def contains(k: Int): Boolean = ???

  def orderOnKey: WeakOrder[Int] = WeakOrder.IntWeakOrder
}
