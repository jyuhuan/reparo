package me.yuhuan.reparo.witness

import scala.{specialized ⇒ sp}

/**
 * Created by Yuhuan Jiang (jyuhuan@gmail.com) on 6/6/15.
 */
// some other useful symbols: ⪰ ⪯ ≤ ≥
trait PartialOrder[@sp(Int, Double) -X] extends Equivalence[X] {

  def le(a: X, b: X): Boolean
  def ge(a: X, b: X) = le(b, a)
  def eq(a: X, b: X) = le(a, b) && ge(a, b)
  def lt(a: X, b: X) = le(a, b) && ! le(b, a)
  def gt(a: X, b: X) = ge(a, b) && ! ge(b, a)

  def ≼(a: X, b: X) = le(a, b)
  def ≽(a: X, b: X) = ge(a, b)
  def ≺(a: X, b: X) = lt(a, b)
  def ≻(a: X, b: X) = gt(a, b)
}

object PartialOrder {
  def by[X, @sp(Int, Double) Y](f: X ⇒ Y)(implicit o: PartialOrder[Y]) = new PartialOrder[X] {
    override def le(a: X, b: X): Boolean = o.≼(f(a), f(b))
  }

  implicit class EverythingHasPartialOrder[X](val a: X) extends AnyVal {
    def ≼(b: X)(implicit O: PartialOrder[X]) = O.le(a, b)
    def ≽(b: X)(implicit O: PartialOrder[X]) = O.ge(a, b)
    def ≺(b: X)(implicit O: PartialOrder[X]) = O.lt(a, b)
    def ≻(b: X)(implicit O: PartialOrder[X]) = O.gt(a, b)
  }
}
