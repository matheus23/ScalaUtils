# Scala Utilities
<hr />
## Properties

Properties are the idea of changing values. A property is simply wraps a value with getters and setters.
The idea is to mix-in Utilities for Properties.

Here is a basic example of a plain simple Property:

```scala
// Creates a new Property holding an Int with the initial value 50:
val property = new Property[Int](50)
// Set it's value:
property(100)
// Get it's value:
println(property()) // Prints "100"
```

### LinkableProperty
<hr />

The interesting parts are the mixins! For example the LinkableProperty:
```scala
// Create a new Property with a LinkableProperty mixin:
val propertyA = new Property[Int](10) with LinkableProperty[Int]
// Now create another Property to which the above will be linked to:
val propertyB = new Property[Int](0)

// Link them together:
propertyA.link(propertyB)
propertyB(100)
println(propertyA()) // Prints "100", though only propertyA was set!
```

### DerivingProperty
<hr />

Another good example is the DerivingProperty, which is just like the
LinkableProperty, but can have two different types and make one be translated into the other:
```scala
//                                                            Int => String
val propertyA = new Property[String]("") with DerivingProperty[Int, String]
val propertyB = new Property[Int](0)

// Link them together with a linker:
propertyA.link(propertyB, (p: Property[Int]) => "The doubled value is: " + ((p() * 2).toString()))
propertyB(10)
println(propertyA()) // Prints "The doubled value is: 20"
```

### FileProperty
<hr />

Well, but this is **so low level**. Now let's see the pure awesome-ness of the FileProperty!
The FileProperty automatically updates a Property when a File changes, using a given FilePropertyReader:
```scala
// This reads in all the text out of a File:
object TextFileReader extends FilePropertyReader[String] {
    def readFile(path: Path) = {
        val source = Source.fromFile(path.toFile)
        val text = source.mkString
        source.close()
        text
    }
}
val textFile = Paths.get("./file.txt")
val watcher = new DirWatcher(textFile.getParent)
// Create a FileProperty with is listenable, additionally:
val property = new FileProperty[String](watcher, TextFileReader, textFile) with ListenableProperty[String]

property.listen((prop: Property[String], value: String) => {
    println("The contents of the text file are:")
    println("==================================")
    println(prop()) // Prints the text file content.
})
```

Before running this example, create a new file named "file.txt" in the working directory of this example.

Then run the example and while it runs, edit the file content (remember to save) and you'll see it update
automatically!