package org.matheusdev.properties

/*
 * Created with IntelliJ IDEA.
 * Author: matheusdev
 * Date: 4/11/13
 * Time: 11:00 PM
 */
trait NotNullProperty[T] extends Property[T] {

  protected[properties] override def set(v: T) = {
    if (v == null)
      throw new NullPointerException("Null is not allowed for this property!")
    super.set(v)
  }

}
