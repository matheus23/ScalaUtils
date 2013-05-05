package org.matheusdev.collections.mapping2d

/*
 * Created with IntelliJ IDEA.
 * Author: matheusdev
 * Date: 5/5/13
 * Time: 10:05 AM
 */
/**
 * Algorithm Author: Riven
 * see http://www.java-gaming.org/topics/minecraft/20505/msg/168294/view.html#msg168294
 * and following
 */
class FastLinearMapping2(val logWidth: Int, val logHeight: Int) extends Mapping2 {
  import org.matheusdev.collections.mapping2d.{FastLinearMapping2 => Static}

  def posToIndex(x: Int, y: Int) = Static.posToIndex(x, y, logWidth, logHeight)
  def indexToPos(ind: Int) = Static.indexToPos(ind, logWidth, logHeight)
}

object FastLinearMapping2 {
  def posToIndex(x: Int, y: Int, logWidth: Int, logHeight: Int) = (x << logHeight) | y
  def indexToPos(ind: Int, logWidth: Int, logHeight: Int) = ((ind >> logHeight), (ind & (logHeight-1)))
}
