package org.matheusdev.collections

/*
 * Created with IntelliJ IDEA.
 * Author: matheusdev
 * Date: 5/5/13
 * Time: 2:23 PM
 */
class PingPongBuffers2[E,M <: MapProduce2[E]](val buffer0: M, val buffer1: M) {

  private var frontBuffer = buffer0
  private var backBuffer = buffer1

  def swap() = {
    if (frontBuffer eq buffer0) {
      frontBuffer = buffer1
      backBuffer = buffer0
    } else {
      frontBuffer = buffer0
      backBuffer = buffer1
    }
    frontBuffer
  }

  def operate(op: (Int,Int,E,M) => E) = {
    frontBuffer.map((x, y, e) => {
      val result = op(x, y, e, frontBuffer)
      backBuffer.setAt(x, y, result)
      e
    })
    swap()
  }

  def parOperate(timeoutMs: Int)(op: (Int,Int,E,M) => E) = {
    frontBuffer.parMap(timeoutMs)((x, y, e) => {
      val result = op(x, y, e, frontBuffer)
      backBuffer.setAt(x, y, result)
      e
    })
    swap()
  }

}
