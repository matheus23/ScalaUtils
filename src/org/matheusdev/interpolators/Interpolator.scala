package org.matheusdev.interpolators

import org.matheusdev.numerics.Floating

/*
 * Created with IntelliJ IDEA.
 * Author: matheusdev
 * Date: 4/21/13
 * Time: 10:32 PM
 */
trait Interpolator {
  def apply[T](a: T, b: T, t: T)(implicit mathN: Floating[T]): T
}
trait InterpolatorFunc extends Interpolator {
  def apply[@specialized(Float, Double) T](a: T, b: T, t: T)(implicit mathN: Floating[T]) = {
    import mathN.mkFloatingOps
    func(t) * (b - a) + a
  }
  def func[T](t: T)(implicit mathN: Floating[T]): T
}
object Linear extends Interpolator {
  def apply[T](a: T, b: T, t: T)(implicit mathN: Floating[T]) = {
    import mathN.mkFloatingOps
    (a * t) + (b * (mathN.one - t))
  }
}
object Sinus extends InterpolatorFunc {
  def func[@specialized(Float, Double) T](t: T)(implicit mathN: Floating[T]) = {
    import mathN.mkFloatingOps
    (-mathN.cos(t * mathN.Pi) + mathN.one) / mathN.fromInt(2)
  }
}
object Cubic extends InterpolatorFunc {
  def func[@specialized(Float, Double) T](t: T)(implicit mathN: Floating[T]) = {
    import mathN.mkFloatingOps

    (t * t) * (mathN.fromInt(3) - mathN.fromInt(2) * t)
  }
}
