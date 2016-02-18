package me.yuhuan.reparo

import me.yuhuan.reparo.witness.Equivalence

/**
  * @author Yuhuan Jiang (jyuhuan@gmail.com).
  */
trait HasKey[K] {
  def equivalenceOnKey: Equivalence[K]
}
