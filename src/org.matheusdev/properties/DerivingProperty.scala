package org.matheusdev.properties

/*
 * Created with IntelliJ IDEA.
 * Author: matheusdev
 * Date: 4/20/13
 * Time: 3:19 PM
 */
trait DerivingProperty[D, T] extends Property[T] {

  private var linking: Option[Property[D]] = None
  private var linker: Option[Property[D] => T] = None

  protected[properties] override def set(x: T) = {
    if (!linking.isDefined)
      super.set(x)
    else
      get()
  }

  protected[properties] override def get() = {
    if (linking.isDefined && linker.isDefined) {
      super.set(linker.get.apply(linking.get))
    }
    super.get()
  }

  def link(property: Property[D], linker: Property[D] => T) = {
    this.linker = Some(linker)
    linking = Some(property)
    this
  }

  def unlink() = {
    linking = None
    this
  }

}
