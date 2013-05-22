package org.matheusdev.properties

import org.matheusdev.interpolators.{Interpolator, Linear}

/*
 * Created with IntelliJ IDEA.
 * Author: matheusdev
 * Date: 3/18/13
 * Time: 7:13 PM
 */
trait AnimationPropertyDouble extends Property[Double] {

  var period = 0
  var interpolator: Interpolator = Linear

  private var time = System.currentTimeMillis()
  private var dst = super.get()

  protected[properties] override def set(x: Double) = {
    dst = x
    time = System.currentTimeMillis()
    super.get()
  }

  private def calculateTime() =
    ((System.currentTimeMillis()-time) toDouble) / period

  protected[properties] override def get() = {
    val t = calculateTime()

    if (t >= 1) {
      super.set(dst)
    } else {
      super.set(interpolator.apply(super.get(), dst, t))
    }
  }

  def isFinished =
    (get() == dst)
}
