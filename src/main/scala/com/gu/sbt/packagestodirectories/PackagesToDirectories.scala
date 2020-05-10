package com.gu.sbt.packagestodirectories

import sbt._
import sbt.Keys._
import better.files._

object PackagesToDirectories extends AutoPlugin {
  object autoImport {
    val packageStructureToDirectoryStructure = taskKey[Unit]("Make directory structure match package structure")
  }

  import autoImport._

  override def trigger = allRequirements

  override lazy val projectSettings = Seq(
    packageStructureToDirectoryStructure := {
      val log = streams.value.log
      log.info(s"Refactoring directory structure to match package structure...")
      val sourceFiles = (Compile / sources).value
      val sourceBase = (Compile / scalaSource).value

      def packageStructure(lines: Traversable[String]): String = {
        val packageObjectRegex = """package object\s(.+)\s\{""".r
        val packageNestingRegex = """package\s(.+)\s\{""".r
        val packageRegex = """package\s(.+)""".r
        lines
          .collect {
            case packageObjectRegex(name) => name
            case packageNestingRegex(name) => name
            case packageRegex(name) => name
          }
          .flatMap(_.split('.'))
          .mkString("/")
      }

      sourceFiles.foreach { sourceFile =>
        val packagePath = packageStructure(sourceFile.toScala.lines)
        val destination = file"$sourceBase/$packagePath"
        destination.createDirectoryIfNotExists(createParents = true)
        val result = sourceFile.toScala.moveToDirectory(destination)
        log.info(s"$sourceFile moved to $result")
      }
    }
  )

}
