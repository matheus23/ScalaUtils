package org.matheusdev.util

import org.matheusdev.properties.CustomProperty

/*
 * Created with IntelliJ IDEA.
 * Author: matheusdev
 * Date: 5/8/13
 * Time: 9:08 PM
 */
class ScalaGameLoop(val looper: GameLooper, framerate: Int = 60, val loopType: GameLoop.Type = GameLoop.Type.NO_LIMIT) {
  private val loop = new GameLoop(looper, loopType, framerate)
  val fps = new CustomProperty[Int](
    framerate, (prop) => {
      loop.getFps.getFps.toInt
    }, (prop, to) => {
      loop.setFramerate(to)
      prop()
    })

  def start() = loop.start()
  def stop() = loop.stop()

  def paused = loop.isPaused
  def pause() = loop.pause()
  def resume() = loop.resume()
}

object ScalaGameLoop {
  private val noInitOp = (fps: FpsUpdater, ups: FpsUpdater) => {}
  private val noDisposeOp = {}
  private val javaTimeOp = () => System.nanoTime()

  def newLooper(
      initOp: (FpsUpdater, FpsUpdater) => Unit = noInitOp,
      tickOp: Double => Boolean,
      renderOp: => Unit,
      disposeOp: => Unit = noDisposeOp,
      time: () => Long = javaTimeOp) =
    new GameLooper {
      private var closeReq = false
      def isCloseRequested = closeReq

      def init(fpsUpdater: FpsUpdater, upsUpdater: FpsUpdater) = initOp(fpsUpdater, upsUpdater)
      def tick(delta: Double) = closeReq = tickOp(delta)
      def render() = renderOp
      def dispose() = disposeOp
      def getNanoTime = time()
    }
}
