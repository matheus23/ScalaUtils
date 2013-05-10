package org.matheusdev.properties

/*
 * Created with IntelliJ IDEA.
 * Author: matheusdev
 * Date: 5/9/13
 * Time: 1:12 PM
 */
class CustomProperty[E](
    initial: E,
    getOp: ((CustomProperty[E]) => E) = CustomProperty.standardGetOp[E, CustomProperty[E]],
    setOp: ((CustomProperty[E], E) => E) = CustomProperty.standardSetOp[E, CustomProperty[E]])
  extends Property[E](initial) {

  protected[properties] override def get() = getOp(this)
  protected[properties] override def set(to: E) = setOp(this, to)
}

object CustomProperty {
  def standardSetOp[E, P <: Property[E]] = (property: P, to: E) => property(to)
  def standardGetOp[E, P <: Property[E]] = (property: P) => property()
}
