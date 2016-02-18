package me.yuhuan.reparo.conversion

import me.yuhuan.{reparo => r}
import scala.{collection => s}
import scala.collection.{immutable => si}
import scala.collection.{mutable => sm}

/**
  * @author Yuhuan Jiang (jyuhuan@gmail.com).
  */
object ToScala {
  implicit class HasForeachToScala[X](val self: r.HasForeach[X]) {
    def asScala: s.Traversable[X] = new Traversable[X] {
      def foreach[U](f: (X) => U): Unit = self foreach f
    }
  }
}
