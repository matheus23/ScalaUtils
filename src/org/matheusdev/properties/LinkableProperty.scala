package org.matheusdev.properties

/*
 * Created with IntelliJ IDEA.
 * Author: matheusdev
 * Date: 4/20/13
 * Time: 3:02 PM
 */
trait LinkableProperty[T] extends Property[T] {

  private var linking: Option[Property[T]] = None

  protected[properties] override def set(x: T) = {
    if (!linking.isDefined)
      super.set(x)
    else
      linking.get.get()
  }

  protected[properties] override def get() = {
    if (linking.isDefined) {
      super.set(linking.get.get())
    }
    super.get()
  }

  def <== (property: Property[T]) =
    link(property)

  def link(property: Property[T]) = {
    linking = Some(property)
    this
  }

  def unlink() = {
    linking = None
    this
  }

}
