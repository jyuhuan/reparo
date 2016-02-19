package me.yuhuan.reparo.immut

import me.yuhuan.reparo._
import me.yuhuan.reparo.witness.Equivalence
import me.yuhuan.reparo.conversion.FromScala._

/**
  * @author Yuhuan Jiang (jyuhuan@gmail.com).
  */
class ArraySeq[+X](core: scala.collection.immutable.Seq[X]) extends Seq[X] {
  def newEnumerator: Enumerator[X] = core.iterator.asReparo
}

object ArraySeq {
  def apply[X](xs: X*) = new ArraySeq[X](scala.collection.immutable.Seq(xs: _*))
}


object ArraySeqTest extends App {
  val x = ArraySeq("a", "b", "c")

  for (KeyValuePair(k, v) <- x.pairs) {
    println(s"$k - $v")
  }




  val bp = 0
}

