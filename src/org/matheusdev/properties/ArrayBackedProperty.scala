package org.matheusdev.properties

/*
 * Created with IntelliJ IDEA.
 * Author: matheusdev
 * Date: 5/3/13
 * Time: 7:24 PM
 */
class ArrayBackedProperty[T](val array: Array[T], val index: Int) extends Property[T](array(index)) {
  override protected[properties] def get() = array(index)
  override protected[properties] def set(v: T) = {
    array(index) = v
    get()
  }
}
