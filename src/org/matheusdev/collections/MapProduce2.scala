package org.matheusdev.collections

/*
 * Created with IntelliJ IDEA.
 * Author: matheusdev
 * Date: 5/5/13
 * Time: 2:24 PM
 */
trait MapProduce2[E] {

  val width: Int
  val height: Int

  def map(op: (Int,Int,E) => E)
  def parMap(timeoutMs: Int)(op: (Int,Int,E) => E)

  def setAt(x: Int, y: Int, to: E)

}
