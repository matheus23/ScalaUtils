package org.matheusdev.properties

import collection.mutable.ListBuffer

/*
 * Created with IntelliJ IDEA.
 * Author: matheusdev
 * Date: 3/18/13
 * Time: 7:08 PM
 */
class Property[T](initial: T) extends (() => T) with (T => T) {

  private var value = initial

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
