package me.yuhuan.reparo.witness

/**
 * @author Yuhuan Jiang (jyuhuan@gmail.com).
 */
trait Equivalence[-X] {
  def eq(a: X, b: X): Boolean
  def ⇔(a: X, b: X): Boolean = eq(a, b)
  def ne(a: X, b: X): Boolean = !eq(a, b)
  def ⇎(a: X, b: X): Boolean = !eq(a, b)
}

object Equivalence {
  def fromPredicate[X](p: (X, X) ⇒ Boolean): Equivalence[X] = new Equivalence[X] {
    def eq(a: X, b: X): Boolean = p(a, b)
  }

  def by[X, Y](f: Y ⇒ X)(implicit E: Equivalence[X]): Equivalence[Y] = new Equivalence[Y] {
    def eq(a: Y, b: Y): Boolean = E.eq(f(a), f(b))
  }

  def byRef[X <: AnyRef]: Equivalence[X] = new Equivalence[X] {
    def eq(a: X, b: X): Boolean = a eq b
  }

  implicit class EverythingHasEquivalence[X](val a: X) extends AnyVal {
    def ⇔(b: X)(implicit E: Equivalence[X]): Boolean = E.eq(a, b)
    def ⇎(b: X)(implicit E: Equivalence[X]): Boolean = E.ne(a, b)
  }

}

