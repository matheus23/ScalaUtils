package org.matheusdev.properties


/*
 * Created with IntelliJ IDEA.
 * Author: matheusdev
 * Date: 3/18/13
 * Time: 7:13 PM
 */
trait AnimationProperty extends Property[Float] {

  var period = 0
  var time = System.currentTimeMillis()
  var dst = super.get()

  protected[properties] override def set(x: Float) = {
    dst = x
    time = System.currentTimeMillis()
    super.get()
  }

  protected[properties] override def get() = {
    val deltaTime = ((System.currentTimeMillis()-time) toFloat)
    val t = (deltaTime / period)

    if (t >= 1) {
      super.set(dst)
      super.get()
    } else {
      super.set((t * dst) + ((1-t) * super.get()))
    }
  }
}
