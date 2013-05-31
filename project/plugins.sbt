resolvers += "sbt-idea-repo" at "http://mpeltonen.github.com/maven/"

resolvers += Resolver.url( "sbt-plugin-releases", url( "http://scalasbt.artifactoryonline.com/scalasbt/sbt-plugin-releases" ))( Resolver.ivyStylePatterns )

addSbtPlugin("com.github.mpeltonen" % "sbt-idea" % "1.4.0")

addSbtPlugin("com.typesafe.sbt" % "sbt-scalariform" % "1.0.0")

// addSbtPlugin("com.eed3si9n" % "sbt-assembly" % "0.8.8")