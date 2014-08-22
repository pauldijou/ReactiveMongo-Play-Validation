# ReactiveMongo support for Play! and Validation API

This is just an extension of the original [Play ReactiveMongo](https://github.com/ReactiveMongo/Play-ReactiveMongo) plugin for Play! Framework. It just replaces the default JSONCollection, which use `Reads` and `Writes` from the JSON lib with `Rule` and `Write` from the [Validation API](jto.github.io/validation).

## Usage

In your `build.sbt` or `project.Build.scala`

~~~ scala
resolvers += "Sonatype Snapshots" at "https://oss.sonatype.org/content/repositories/snapshots/"

libraryDependencies ++= Seq(
  "org.reactivemongo" %% "reactivemong-play2-validation" % "0.11.0-SNAPSHOT"
)
~~~

Then, it is exactly just like the original [Play ReactiveMongo plugin](https://github.com/ReactiveMongo/Play-ReactiveMongo/blob/master/README.md) for configuration and stuff. The only difference is to use `play.modules.reactivemongo.validation.collection.JSONCollection` instead of `play.modules.reactivemongo.json.collection.JSONCollection`. And that's it!

## Resources

- [ReactiveMongo](http://reactivemongo.org)
- [Original Play ReactiveMongo plugin](https://github.com/ReactiveMongo/Play-ReactiveMongo)
- [Validation API](https://jto.github.io/validation)
- [Play Framework](https://www.playframework.com)
