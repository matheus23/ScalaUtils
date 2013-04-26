package org.matheusdev.interpolators

/*
 * Created with IntelliJ IDEA.
 * Author: matheusdev
 * Date: 4/21/13
 * Time: 10:32 PM
 */
trait Interpolator {
  def apply(a: Float, b: Float, t: Float): Float
}
trait InterpolatorFunc extends Interpolator {
  def apply(a: Float, b: Float, t: Float) = func(t) * (b - a) + a
  def func(t: Float): Float
}
object Linear extends Interpolator {
  def apply(a: Float, b: Float, t: Float) =
    (a * t) + (b * (1-t))
}
object Sinus extends InterpolatorFunc {
  def func(t: Float) =
    ((-math.cos(t * math.Pi) + 1) / 2).toFloat
}
object Cubic extends InterpolatorFunc {
  def func(t: Float) =
    (t*t)*(3-2*t)
}
