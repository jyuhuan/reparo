package me.yuhuan.reparo.witness

/**
 * Created by Yuhuan Jiang (jyuhuan@gmail.com) on 10/11/15.
 */
trait LowerLattice[X] extends PartialOrder[X] { outer ⇒
  def inf(a: X, b: X): X
  def highestLowerBound(a: X, b: X) = inf(a, b)
  def meet(a: X, b: X) = inf(a, b)
  def ∧(a: X, b: X): X = inf(a, b)

  def le(a: X, b: X): Boolean = eq(a , inf(a, b))
  override def ge(a: X, b: X): Boolean = eq(b , inf(a, b))

  def reverse: UpperLattice[X] = new UpperLattice[X] { inner ⇒
    def sup(a: X, b: X): X = outer.inf(a, b)
    override def reverse: LowerLattice[X] = outer
  }
}

object LowerLattice {
  implicit class EverythingHasLowerLattice[X](val a: X) extends AnyVal {
    def ∧(b: X)(implicit L: LowerLattice[X]) = L.inf(a, b)
  }
}
