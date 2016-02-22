package com.corescalaone

import scala.io.Source
import scala.collection.mutable.ListBuffer
import scala.collection.mutable.MutableList
import java.io.File
import java.io.IOException
import java.io.FileNotFoundException

object ComparableOne {

  def main(args: Array[String]): Unit = {
    // FileNotFoundException : java.io.FileNotFoundException: Authors.csv (The system cannot find the file specified)
    val afile: java.io.File = new File("D:/thanooj/work/Workspaces/WSscala/WSOne/CoreScalaNew/resources/Authors.csv")

    loadAuthorsFromFile(afile).foreach { println }
  }

  def loadAuthorsFromFile(afile: java.io.File): MutableList[Author] = {
    var authorList: MutableList[Author] = MutableList[Author]()
    try {
      //val fileLines = io.Source.fromFile("Colors.scala").getLines.toList
      for (line <- Source.fromFile(afile).getLines()) {
        val authorArr = line.split(",")
        val author: Author = new Author(authorArr(0).toInt, authorArr(1), authorArr(2))
        authorList += author
      }
    } catch {
      case ex: FileNotFoundException => println("FileNotFoundException : " + ex)
      case ex: IOException           => println("IO Exception : " + ex)
    } finally {
      println("OK")
    }
    authorList
  }
}

class Author(val id: Int, val name: String, val location: String) extends Comparable[Author] {
  override def compareTo(that: Author) = {
    val nameComp = this.name compareTo that.name
    if (nameComp != 0) nameComp
    else {
      val locationComp = this.location compareTo that.location
      if (locationComp != 0) locationComp
      else this.id compareTo that.id
    }
  }
  override def toString() = id + " " + name + " " + location
}



/**
 * 
 * NOTE:
 * 
 * val fileLines = io.Source.fromFile("Colors.scala").getLines.toList
 * fileLines.foreach(println)
 *
 * val reader = new FileReader("foo.txt")
 *
 * val writer = new PrintWriter(new File("Write.txt"))
 *
 * writer.write("Hello Developer, Welcome to Scala Programming.")
 * writer.close()
 */