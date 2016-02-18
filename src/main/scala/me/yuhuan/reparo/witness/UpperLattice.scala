package me.yuhuan.reparo.witness

/**
 * Created by Yuhuan Jiang (jyuhuan@gmail.com) on 10/11/15.
 */
trait UpperLattice[X] extends PartialOrder[X] { outer ⇒
  def sup(a: X, b: X): X
  def lowestUpperBound(a: X, b: X) = sup(a, b)
  def join(a: X, b: X) = sup(a, b)
  def ∨(a: X, b: X) = sup(a, b)

  def le(a: X, b: X): Boolean = eq(b, sup(a, b))
  override def ge(a: X, b: X): Boolean = eq(a, sup(a, b))

  def reverse: LowerLattice[X] = new LowerLattice[X] {
    def inf(a: X, b: X): X = outer.sup(a, b)
    override def reverse: UpperLattice[X] = outer
  }
}

object UpperLattice {
  implicit class EverythingHasUpperLattice[X](val a: X) extends AnyVal {
    def ∨(b: X)(implicit L: UpperLattice[X]) = L.sup(a, b)
  }
}
