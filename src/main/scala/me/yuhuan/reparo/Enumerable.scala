package me.yuhuan.reparo

/**
  * @author Yuhuan Jiang (jyuhuan@gmail.com).
  */
trait Enumerable[+X] extends HasForeach[X] { self =>

  def foreach[Y](f: X => Y): Unit = {
    val e = newEnumerator
    while (true) {
      e.next match {
        case Some(x) => f(x)
        case None => return
      }
    }
  }

  def map[Y](f: X => Y): Enumerable[Y] = new Enumerable[Y] {
    def newEnumerator: Enumerator[Y] = new Enumerator[Y] {
      val e = self.newEnumerator
      def next: Option[Y] = e.next.map(f)
    }
  }

  def filter(p: X => Boolean): Enumerable[X] = new Enumerable[X] {
    def newEnumerator: Enumerator[X] = new Enumerator[X] {
      val e = self.newEnumerator
      def next: Option[X] = {
        var curValue = default[X]
        do {
          e.next match {
            case None => return None
            case Some(x) => curValue = x
          }
        } while (!p(curValue))
        Some(curValue)
      }
    }
  }

  def newEnumerator: Enumerator[X]

}

object EnumerableTest extends App {
  import me.yuhuan.reparo.conversion.FromScala._
  val enumerable = scala.collection.Seq("aad", "basdfasd", "csdf").asReparo

  //val a = enumerable.map(_.length)
  val a = enumerable.filter(s => s.length <= 4)

  a foreach println

  val bp = 0
}