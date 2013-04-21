package org.matheusdev.properties

import collection.mutable.ListBuffer

/*
 * Created with IntelliJ IDEA.
 * Author: matheusdev
 * Date: 4/20/13
 * Time: 3:04 PM
 */
trait ListenableProperty[T] extends Property[T] {

  private val listeners = ListBuffer[(Property[T], T) => Unit]()

  protected[properties] override def set(x: T) = {
    invokeListeners(x)
    super.set(x)
  }

  def listen(listener: (Property[T], T) => Unit) = {
    listeners += listener
  }

  def unlisten(listener: (Property[T], T) => Unit) = {
    listeners -= listener
  }

  private def invokeListeners(x: T) {
    for (listener <- listeners) listener(this, x)
  }

}
