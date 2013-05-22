# Mapping Matrices

These classes are used to map 2D positions onto a 1D Array.

There are different versions with different goals. Some do cache optimization, others
try to be as fast as possible when mapping indices.

## SingleMappedMatrix2

This class is very easy to use. It's also a very simple mapping algorithm (by default),
though you can use your own mapping algorithm implementation.

You simply specify a width and height, and you can start to use the class:

```scala
val matrix = new SingleMappedMatrix[String](100, 100)
matrix(99, 99) = "Bottom right edge..."
matrix(0, 0) = "Top left edge (in +y down system)"
matrix(1, 0) = "element"

matrix(1, 0) == "element"
```

### FastLinearMapping2

This is only a mapping _technique_. But it can be used together with SingleMappedMatrix2
and it'll perform very well...

It uses power-of-two-optimizations to make the mapping algorithm even faster!
Though you then need power-of-two dimensions for your matrix...

```scala
val logWidth = 8 // base 2
val logHeight = 4
val matrix = new SingleMappedMatrix[String](logWidth*logWidth, logHeight*logHeight,
    new FastLinearMapping2(logWidth, logHeight))
matrix(10, 5) = "abc"
// voila :)
```

## ClusteredMatrix2

This is a cache-optimized version of the usual Mapping technique. The special
thing about this one is, that it is very cache-friendly to query neighbours in this
collection.

The trick with this collection is to map the values twice. So you end up with cells of
matrices in one array. That way the memory position of two neighbour cells is likely
to not be too far away.

Instantiating didn't really change, except that you need to specify the width and height for
your clusters:

```scala
val matrix = new ClusteredMatrix2[Byte](100, 100, 8, 8)
matrix(99, 99) = 15
// Easy!
```

## FastClusteredMatrix2

This is probably the most advanced Mapping technique. It uses both power-of-two optimizations and
is very cache-friendly. It show itself to be very efficient in Minecraft, for example.
There lots of neighbour-querying was needed and all the dimensions are power-of-two.

The code to use it is exactly the same as with ClusteredMapping2.