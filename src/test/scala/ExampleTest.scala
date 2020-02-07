class ExamplesTest extends org.scalatest.FunSuite {








  test("Variables") {
    // Type inference
    var a = 10 // Mutable (don't use these!)
    val b = 10 // Immutable (use these!)
  }






  test("Functions"){

    // Usually no return statement)
    def add(a : Int, b: Int) : Int = {
      a + b
    }

    println(add(1, 2))
    println(add(1, b = 2)) // Named arguments


    // Can be shortened to
    def addV2(a : Int, b: Int) = a + b
    // No return type (inferred)
    // No braces (if single line)
  }






  test("If") {
    //  If is an expression (it evaluates to a value)
    val a = 10
    val message = if(a > 1) "larger than one"
                  else "less than one"

    println(message) // larger than one
  }






  test("String interpolation"){
    val name = "Dave"

    println(s"Hello $name")
  }








  test("Lists"){
    // Usually Immutable
    val mySequence = Seq(4,5,6)
    val myList = List(1,2,3)
    val myArray = Array(7,8,9)

    println(myList(1)) // "2"

    for(i <- mySequence) {
      println(i + 10)
    } // 14, 15, 16






    def isEven(i: Int) = i % 2 == 0

    val myList2: List[Int] = (1 to 10).toList
    println(myList2
      .filter(isEven)
      .map(i => i + 10)
      .mkString(System.lineSeparator()))

    println(myList2.sum)
    println(myList2.min)
  }

  test("Map"){
    // also immutable by default
    val map = Map(
      "123" -> "Customer_123",
      "456" -> "Customer_456"
    )

    println(map("123")) // "Customer_123"
  }

  test("Tuple"){
    val tuple = (4, "Four","IV")

    println(tuple._1)
    println(tuple._2)
    println(tuple._3)
  }

  test("Option type"){
    // No more nulls.
    // Scala has null, but no one uses them.

    def divide(top :Int, bottom : Int) : Option[Int] =
      if (bottom == 0) None
      else  Some(top / bottom)

    val ans = divide(10, 5)

    println(ans) // Some(2)
  }

  test("Pattern Matching"){
    def divide(top :Int, bottom : Int) : Option[Int] =
      if (bottom == 0) None
      else  Some(top / bottom)

    val ans = divide(10, 5)

    val message = ans match {
      case Some(i) => i.toString
      case None => "Division by zero!"
    }

    println(message)
  }

  test("Classes") {
    // Like Java
    // Inheritance
    // Interfaces
    // Overriding
    // ...

    class Animal(val name: String, val sound: String){
      val greeting = s"I am $name, and I say $sound!"

      def greet() {
        println(greeting)
      }
    }

    val lion = new Animal("Lion", "Roar")

    println(lion.name)
    println(lion.sound)

    // Companion object (aka static)
    object Animal {
      def whatSound(animal: Animal) = {
        println(s"${animal.name}s go ${animal.sound}")
      }
    }

    Animal.whatSound(lion)
  }


  test("Case classes") {
//    case classes
//      Immutable data types. Very good for domain modelling
//
//    case class Card(value: Value, suit: Suit)
//
//    trait Value
//    case class Number(num: Int) extends Value
//    case class Jack extends Value
//    case class Queen extends Value
//    case class King extends Value
//
//    trait Suit
//    case class Heart extends Suit
//    case class Diamond extends Suit
//    case class Spade extends Suit
//    case class Club extends Suit
//
//    let card1 = Card(Number(3), Heart)
//    let card2 = Card(Jack, Spade)

  }

  test("Higher order functions"){
//    Functions that take or return functions
  }

  test("Exceptions"){
//    Try not too*
//
//    Not very functional.
//      Try, Either or Case Class
//
//    *Sorry for the pun
  }

}
