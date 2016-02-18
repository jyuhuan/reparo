package me.yuhuan.reparo.witness

/**
 * Represents a field.
 *
 * A field has two abstract operations: `add` and `mul`. The set under operation `add` forms an
 * abelian group, while the set under operation `mul` also forms an abelian group.
 *
 * @tparam T The type of the elements in the field.
 *
 * @author Yuhuan Jiang
 * @version 1.0
 * @since 1.0
 *
 */
trait Field[T] extends AdditiveGroup[T] with MultiplicativeGroup[T]