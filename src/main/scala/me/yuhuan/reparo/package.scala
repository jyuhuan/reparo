package me.yuhuan

/**
  * @author Yuhuan Jiang (jyuhuan@gmail.com).
  */
package object reparo {

  @inline private[reparo] def default[T]: T = {
    class Default {
      var default: T = _
    }
    (new Default).default
  }



}
