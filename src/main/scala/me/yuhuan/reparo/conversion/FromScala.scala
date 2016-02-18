package me.yuhuan.reparo.conversion

import me.yuhuan.reparo.Enumerator
import me.yuhuan.{reparo => r}
import scala.{collection => s}

/**
  * @author Yuhuan Jiang (jyuhuan@gmail.com).
  */
object FromScala {

  implicit class FromScalaIterator[X](val self: s.Iterator[X]) extends AnyVal {
    def asReparo: r.Enumerator[X] = new Enumerator[X] {
      def next: Option[X] = if (self.hasNext) Some(self.next()) else None
    }
  }

  implicit class FromScalaTraversable[X](val self: s.Traversable[X]) extends AnyVal {
    def asReparo: r.HasForeach[X] = new r.HasForeach[X] {
      def foreach[Y](f: (X) => Y): Unit = self foreach f
    }
  }

  implicit class FromScalaIterable[X](val self: s.Iterable[X]) extends AnyVal {
    def asReparo: r.Enumerable[X] = new r.Enumerable[X] {
      def newEnumerator: Enumerator[X] = self.iterator.asReparo
    }
  }
}
