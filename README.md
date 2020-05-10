# sbt-packages-to-directories [![Maven Central](https://maven-badges.herokuapp.com/maven-central/com.gu/sbt-packages-to-directories_2.12_1.0/badge.svg?style=plastic)](https://maven-badges.herokuapp.com/maven-central/com.gu/sbt-packages-to-directories_2.12_1.0)

Add under `project/plugins.sbt`

```scala
addSbtPlugin("com.gu" % "sbt-packages-to-directories" % version)
```

According to [Tour of Scala: Packages and Imports](https://docs.scala-lang.org/tour/packages-and-imports.html)


> One convention is to name the package the same as the directory containing the Scala file.

This sbt plugin provides task

```scala
val packageStructureToDirectoryStructure = taskKey[Unit]("Make directory structure match package structure")
```
 
which reads through source files, extracts their package statements, and then moves the file to the matching
directory structure. Make sure to backup the project before executing it as file are moved around.  For example,

```scala
package vim.users

class User
```

is moved to 

```
src/main/scala/vim/users/*.scala
```

and

```scala
package vim
package users

package object linux {
  class User
}
```

is moved to

```scala
src/main/scala/vim/users/linux/*.scala
```

and 

```scala
package users {
  package vim {
    class NormalUser
  }
  package emacs {
    class AbnormalUser
  }
}
```

is moved to 

```scala
src/main/scala/users/*.scala
```
