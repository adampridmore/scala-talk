import scala.concurrent.Future
import scala.util.{Failure, Success, Try}

import scala.concurrent.ExecutionContext.Implicits.global

class ExamplesTest extends org.scalatest.FunSuite {


  test("My test name"){
    assert(1 == 1)
  }






  test("Variables (or Values?)") {

    var a : Int = 10 // Mutable (don't use these!)
    a = 20 // Can be changed








    val b : Int = 10 // Immutable (use these)
    // b = 20 // Nope!








    val c = 10 // Type inference (so don't have to specify types)
  }








  test("Functions"){

    // Usually no return statement)
    def add(a : Int, b: Int) : Int = {
      a + b
    }

    println(add(1, 2)) // 3
    println(add(1, b = 2)) // Named arguments











    // Can be shortened to
    // No return type (inferred)
    // No braces (if single line expression)
    def addV2(a : Int, b: Int) = a + b
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
    val mySequence = Seq("a","b","c")
    val myList = List("a","b","c")
    val myArray = Array("a","b","c")

    println(myList(1)) // "b"













    // Iterate (this isn't very FP..)
    for(i <- Seq(4,5,6)) {
      println(i + 10)
    } // 14, 15, 16






    def isEven(i: Int) = i % 2 == 0

    val myList2: List[Int] = (1 to 10).toList

    println((1 to 10)
      .filter(isEven)
      .map(i => i + 10)
      .mkString(",")) // 12,14,16,18,20

    println(myList2.sum) // 55
    println(myList2.min) // 1
  }








  test("HashMaps"){
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

    val x = Some(10)
    val y = None














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
    // Traits (bit like Interfaces)
    // Overriding
    // Operator overloading
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












  test("Option map and flatMap"){
    val x: Option[Int] = Some(10)

    val twice: Option[Int] = x match {
      case None => None
      case Some(x) => Some(x * 2)
    }

    // Map
    val twice2: Option[Int] = x.map(x => x * 2)










    def reciprocal(x : Double) : Option[Double] = if (x == 0) None else Some(1 / x)

    val ans1: Option[Double] = reciprocal(10)

    val ans2: Option[Double] = ans1 match {
      case None => None
      case Some(x) => reciprocal(x)
    }

    val ans3: Option[Double] = reciprocal(10)
      .flatMap(reciprocal)
  }









  test("For comprehension with Option"){
    def mathsOp1(x : Int) : Option[Int] = Some(x + 1)
    def mathsOp2(x : Int) : Option[Int] = Some(x * 1)
    def mathsOp3(x : Int) : Option[Int] = if (x == 0) None else Some(1 / x)

    val a: Option[Int] = mathsOp1(10)

    val b: Option[Int] = a match {
      case Some(x) => mathsOp2(x)
      case None => None
    }

    val ans1: Option[Int] = b match {
      case Some(x) => mathsOp3(x)
      case None => None
    }


    val ans2: Option[Int] = mathsOp1(10)
      .flatMap({
      x => mathsOp2(x)
        .flatMap(mathsOp3)
    })

    val and3: Option[Int] = for{
      x <- mathsOp1(10)
      y <- mathsOp2(x)
    } yield (y)
  }










  test("Sequences & Lists with map and flatMap"){
    val nums = Seq(1,2,3)
    nums.map(x => x * 2) // 2, 4, 6

    val strings = Seq("abc", "def", "hij")
    strings.flatMap(x => x.toCharArray) // Seq('a','b','c','d','e','f','h','i','j')

    for{
      a: String <- strings
      b: Char <- a.toCharArray
    } yield b
  }








  // Futures
  test("Futures") {

    val ans: Future[Int] = Future {
      10
    }

    ans.onComplete((t: Try[Int]) => t match {
      case Failure(exception) => println("Failed: " + exception.getMessage)
      case Success(x) => println("Ans: " + x)
    })

    val ans2: Future[String] =
      ans.map(x => "Ans: " + x)
  }















  test("Futures and remote repositories"){
    case class Customer(id: String, name: String)
    case class Order(customerId: String, orderDetails: String)

    def getCustomer(customerId: String): Future[Customer] = Future {
      Customer(customerId, "Fred")
    }

    def getOrders(customerId: String): Future[Seq[Order]] = Future {
      Seq(
        Order(customerId, "Order 1"),
        Order(customerId, "Order 2"))
    }

    val customerNameAndOrders = for {
      customer <- getCustomer("customer-1")
      orders <- getOrders(customer.id)
    } yield (orders)


    customerNameAndOrders.onComplete { // This is just a shortened match statement
      case Failure(exception) => println("Failed: " + exception.getMessage)
      case Success(orders) => orders.map(order => println(s"${order.orderDetails}"))
    } // Prints "Order 1, Order 2" however it prints it after this method has returned (non-deterministic)

    Thread.sleep(2000)
  }
}
