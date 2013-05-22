# Interpolation

These classes are used for interpolation. They are extremely simple to use:

```scala
val interpolator = Cubic // or Linear or Sinus or whatever
val previousValue = 10f
val nextValue = 100f
val time = 0.5f // needs to be between 0 and 1
val interpolated = interpolator(previousValue, nextValue, time)
```

The interpolators are used for the AnimationProperties, for example.