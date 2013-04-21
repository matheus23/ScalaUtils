package org.matheusdev.util

import collection.concurrent._
import collection.JavaConversions._

import java.nio.file.StandardWatchEventKinds._
import java.nio.file._
import java.util.concurrent.ConcurrentHashMap
import java.nio.file.attribute.BasicFileAttributes
import actors.threadpool.Executors
import collection.mutable.ListBuffer

/*
 * Created with IntelliJ IDEA.
 * Author: matheusdev
 * Date: 4/19/13
 * Time: 11:53 PM
 */
class DirWatcher(val directory: Path) extends Runnable {

  private val watcher = directory.getFileSystem.newWatchService()
  private val keys: Map[WatchKey, Path] = new ConcurrentHashMap[WatchKey, Path]()
  private val executor = Executors.newCachedThreadPool()
  private val callbacks = new ListBuffer[(Path,WatchEvent.Kind[Path]) => Unit]()
  registerAll(directory)
  executor.submit(this)

  def cancel() {
    watcher.close()
    executor.shutdownNow()
  }

  def register(dir: Path) {
    val key = dir.register(watcher, ENTRY_CREATE, ENTRY_DELETE, ENTRY_MODIFY)
    keys.put(key, dir)
  }

  def registerAll(dir: Path)  {
    Files.walkFileTree(dir, new SimpleFileVisitor[Path]() {
      override def preVisitDirectory(dir: Path, attr: BasicFileAttributes) = {
        register(dir)
        FileVisitResult.CONTINUE
      }
    })
  }

  def addListener(listener: (Path,WatchEvent.Kind[Path]) => Unit) {
    callbacks += listener
  }

  protected override def run() {
    processEvents()
  }

  private def processEvents() {
    var cancel = false
    do {
      cancel = processEvent()
    } while (!Thread.interrupted() && !cancel)
  }

  private def processEvent(): Boolean = {
    try {
      val key = watcher.take()
      if (key != null) {
        val path = keys.get(key).getOrElse({println("skipped watcherKey"); return false})
        val events = key.pollEvents()
        for (i <- 0 until events.size(); event = events.get(i).asInstanceOf[WatchEvent[Path]]) {
          val name = event.context()
          val child = path.resolve(name)
          val kind = event.kind()

          if (Files.isDirectory(child))
            registerAll(child)

          for (callback <- callbacks) callback(child, kind)
        }
        if (!key.reset())
          keys.remove(key)
      }
      false
    } catch {
      case ie: InterruptedException => true
      case cw: ClosedWatchServiceException => true
    }
  }

}
