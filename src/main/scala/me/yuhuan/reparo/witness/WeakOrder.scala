package me.yuhuan.reparo.witness

/**
  * @author Yuhuan Jiang (jyuhuan@gmail.com).
  */
trait WeakOrder[-X] extends PartialOrder[X] { self =>

  def compare(a: X, b: X): Int

  def le(a: X, b: X): Boolean = compare(a, b) <= 0
  override def ge(a: X, b: X) = compare(a, b) >= 0
  override def eq(a: X, b: X) = compare(a, b) == 0
  override def lt(a: X, b: X) = compare(a, b) < 0
  override def gt(a: X, b: X) = compare(a, b) > 0

  def asEquivalence: Equivalence[X] = new Equivalence[X] {
    def eq(a: X, b: X): Boolean = self.eq(a, b)
  }

}

object WeakOrder {
  def apply[X](f: (X, X) => Int) = new WeakOrder[X] {
    def compare(a: X, b: X): Int = f(a, b)
  }

  def by[X, Y](f: X => Y)(implicit OY: WeakOrder[Y]): WeakOrder[X] = new WeakOrder[X] {
    def compare(a: X, b: X): Int = OY.compare(f(a), f(b))
  }

  implicit object IntWeakOrder extends WeakOrder[Int] {
    def compare(a: Int, b: Int): Int = a - b
  }

}