package org.matheusdev.util.tests

import org.scalatest.FlatSpec
import org.matheusdev.util._
import java.nio.file._
import collection.mutable
import org.scalatest.matchers.ShouldMatchers
import java.nio.charset.Charset
import java.nio.file.StandardWatchEventKinds._

/*
 * Created with IntelliJ IDEA.
 * Author: matheusdev
 * Date: 4/20/13
 * Time: 12:19 PM
 */

class DirWatcherSpec extends FlatSpec with ShouldMatchers with TestUtilities {

  behavior of "A DirWatcher"

  it should "watch for file changes" in {
    withTestingDir { testingDir =>
      val queue = new mutable.Queue[(Path, WatchEvent.Kind[Path])]()
      val watcher = new DirWatcher(testingDir)
      watcher.addListener((path, kind) => {
        queue += ((path, kind))
      })
      // Writing file / Changing directory content:
      val newfile = Files.createFile(testingDir.resolve("newfile.txt"))
      val writer = Files.newOutputStream(newfile)
      withClose(writer) {
        val content = "Testing DirWatcher.".toCharArray.map(char => char.asInstanceOf[Byte])
        writer.write(content)
      }
      // Cleanup
      Files.delete(newfile)
      // Time for scheduling
      assert(waitFor(200) {
        queue.size >= 3
      }, s"DirWatcher took too long or produced wrong output: $queue")
      // Looking for the right events:
      queue.contains((testingDir.resolve("newfile.txt"), ENTRY_CREATE)) should equal (true)
      queue.contains((testingDir.resolve("newfile.txt"), ENTRY_MODIFY)) should equal (true)
      queue.contains((testingDir.resolve("newfile.txt"), ENTRY_DELETE)) should equal (true)
    }
  }

  it should "watch the directory tree deeply" ignore {
    withTestingDir { testingDir =>
      val queue = new mutable.Queue[(Path, WatchEvent.Kind[Path])]()
      val watcher = new DirWatcher(testingDir)
      watcher.addListener((path, kind) => {
        queue += ((path, kind))
      })
      // Writing file / Changing directory content (deeply):
      val directory = Files.createDirectory(testingDir.resolve("test"))
      val newfile = Files.createFile(testingDir.resolve("test/file.txt"))
      val writer = Files.newBufferedWriter(newfile, Charset.defaultCharset())
      withClose(writer) {
        writer.write("Testing DirWatcher.")
      }
      // Cleanup
      Files.delete(newfile)
      Files.delete(directory)
      // Time for scheduling
      assert(waitFor(2000) {
        queue.size >= 5
      }, s"DirWatcher took too long or produced wrong output: $queue")
      // Looking for the right events:
      queue.dequeue() should equal (testingDir.resolve("test"), ENTRY_CREATE)
      queue.dequeue() should equal (testingDir.resolve("test/file.txt"), ENTRY_CREATE)
      queue.dequeue() should equal (testingDir.resolve("test/file.txt"), ENTRY_MODIFY)
      queue.dequeue() should equal (testingDir.resolve("test/file.txt"), ENTRY_DELETE)
      queue.dequeue() should equal (testingDir.resolve("test"), ENTRY_DELETE)
    }
  }

}
