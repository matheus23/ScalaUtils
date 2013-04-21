package org.matheusdev.properties

import java.nio.file.StandardWatchEventKinds._
import java.nio.file.Path
import org.matheusdev.util.DirWatcher

/*
 * Created with IntelliJ IDEA.
 * Author: matheusdev
 * Date: 4/19/13
 * Time: 11:16 PM
 */
class FileProperty[T]( private val watcher: DirWatcher,
                       private val reader: FilePropertyReader[T],
                       private val file: Path) extends Property[T](reader.readFile(file)) {
  watcher.register(file.getParent)
  watcher.addListener((path, event) => {
    if (event == ENTRY_CREATE || event == ENTRY_MODIFY)
      if (path == file)
        set(reader.readFile(path))
  })
}

abstract class FilePropertyReader[T] {
  def readFile(path: Path): T
}
