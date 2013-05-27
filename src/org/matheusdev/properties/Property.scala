package org.matheusdev.properties

/*
 * Created with IntelliJ IDEA.
 * Author: matheusdev
 * Date: 3/18/13
 * Time: 7:08 PM
 */
case class Property[T](private var value: T) extends (() => T) with (T => T) {

  def apply(v: T) = set(v)
  def apply() = get()

  protected[properties] def get(): T = {
    value
  }

  protected[properties] def set(x: T): T = {
    value = x
    value
  }

  override def toString() = get().toString

}
