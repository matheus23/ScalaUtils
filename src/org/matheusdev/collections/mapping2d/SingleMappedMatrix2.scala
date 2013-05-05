package org.matheusdev.collections.mapping2d

/*
 * Created with IntelliJ IDEA.
 * Author: matheusdev
 * Date: 5/4/13
 * Time: 9:27 PM
 */
class SingleMappedMatrix2[@specialized E: Manifest](
  val width: Int,
  val height: Int,
  val mapping: Mapping2)
  extends ArrayMatrix2[E] {

  def this(width: Int, height: Int) =
    this(width, height, new LinearMapping2(width, height))

  protected val arraySize = width * height
  protected val backingArray = new Array[E](arraySize)

  def posToIndex(x: Int, y: Int) = mapping.posToIndex(x, y)
  def indexToPos(ind: Int) = mapping.indexToPos(ind)

}