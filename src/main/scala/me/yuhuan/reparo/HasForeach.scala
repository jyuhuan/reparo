package me.yuhuan.reparo

/**
  * @author Yuhuan Jiang (jyuhuan@gmail.com).
  */
trait HasForeach[+X] { self =>
  def foreach[Y](f: X => Y): Unit

  def size: Int = {
    var i = 0
    self.foreach(_ => i += 1)
    i
  }

  def filter(p: X => Boolean): HasForeach[X] = new HasForeach[X] {
    def foreach[Y](f: (X) => Y): Unit = {
      self.foreach { x =>
        if (p(x)) f(x)
      }
    }
  }

}
