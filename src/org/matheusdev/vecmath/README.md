# Vecmath

These classes are used very straightforward and they work just like you'd expect them to work.

All you do is create your Vectors and do multiplication, rotation, addition and any mathematical operation you'd like:

```scala
// Remember, it's still generic, so...
// ... this could be written Vec2[Float](10f, 10f) (but it gets inferred by scala)
// ... you could create a Vec2[Double](10.0, 10.0) or even Vec2[BigDecimal](...)
val vec0 = Vec2(10f, 10f) // immutable
val vec2 = Vec2(5f, 17f)
println(vec0 + vec1) // prints "Vec2(15.0, 17.0)"
println(vec0 - vec1) // prints "Vec2(5.0, -7.0)"
println(vec0 * vec1) // prints "Vec2(50.0, 170.0)"
println(vec0 / Vec2(2f, 2f)) // prints "Vec2(5.0, 5.0)"

println(vec0.direction.degree) // prints "45.0"
println(vec0.direction.radian) // prints "0.7853982"

println(vec0 dot vec1) // prints "220.0"
println(vec0 * 2f) // prints "Vec2(20.0, 20.0)"

val pos = Pos2(0f, 0f) // mutable
pos += vec0
println(pos) // prints "Pos2(10f, 10f)"
pos += vec0 * 10
println(pos) // prints "Pos2(110f, 110f)"

// Much more to see with Vec3 and Vec4...
```