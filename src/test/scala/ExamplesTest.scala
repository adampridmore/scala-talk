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
    // b = 20   //    Nope!








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
    //  If is an expression
    //  (it evaluates to a value)
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

    // List accessor
    println(myList(1)) // "b"












    // Iterate (this isn't very FP..)
    for(i <- Seq(4,5,6)) {
      println(i + 10)
    } // 14, 15, 16






    def isEven(i: Int) : Boolean = i % 2 == 0

    val myList2: List[Int] = (1 to 10).toList

    val listAsCsv =
      myList2.filter(isEven)
        .map(i => i + 10)
        .mkString(",") // 12,14,16,18,20

    println(listAsCsv)

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
    val tuple: (Int, String, String) =
      (4, "Four","IV")

    println(tuple._1)
    println(tuple._2)
    println(tuple._3)

    val (number, word, numeral) = tuple
    println(s"Number: $number Word: $word, Roman Numeral: $numeral")
  }









  test("Option type"){
    // No more nulls.
    // Scala has null, but no one uses them.
    val x = Some(10)
    val y = None















    def divide(top : Int,
               bottom : Int) : Option[Int] =
      if (bottom == 0) None
      else Some(top / bottom)

    val ans: Option[Int] =
      divide(10, 5)

    println(ans) // Some(2)
  }










  test("Pattern Matching"){
    def divide(top :Int, bottom : Int) : Option[Int] =
      if (bottom == 0) None
      else Some(top / bottom)

    val ans = divide(10, 5)

    val message = ans match {
      case Some(i) => i.toString
      case None => "Division by zero!"
    }

    println(message)
  }






  test("Classes") {
    // Like Java / C#
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











  test("Case classes") {//    case classes
    //  Immutable data types.
    //  Very good for domain modelling

    case class Person(name: String, id: String)

    val peter = Person("Peter", "123")
    peter.name // Peter
    peter.id // 123











    // Supports equality
    val betty = Person("Betty", "456")
    peter == betty // false

    val aDuplicatePeter = Person("Peter", "123")

    peter == aDuplicatePeter // true













    // Copying with changes
    val aDifferentPeter = peter.copy(id = "789")
    aDifferentPeter.name // "Peter"
    aDifferentPeter.id // "789"
















    case class FaceValue(value: Int)

    sealed trait Suit
    case object Club extends Suit
    case object Diamond extends Suit
    case object Spade extends Suit
    case object Heart extends Suit

    case class Card(suit: Suit, value: FaceValue)

    val player1Hand = List(
      Card(Heart,FaceValue(2)),
      Card(Diamond,FaceValue(5))
    )
  }





  test("Higher order functions") {
    // Functions that take functions as parameters
    case class Product(name: String)

    val products = Product("Wolf T-Shirt")

    def pretty[T](t: T,
                  getDescription: T => String): Unit ={
      val desc = getDescription(t)
      val line = "*" * (desc.length + 4)
      println(line)
      println(s"* $desc *")
      println(line)
    }

    def getDescription(p: Product) = p.name

    val total = pretty[Product](products,
                                getDescription)
  }










  test("Exceptions"){
    // Try not too
    // Not very functional
    // But you can

    def createCustomerId2(i: Int): String = {
      if (i < 0 || i > 1000000)
        throw new RuntimeException("Customer id outside of range 0 to 1000000")
      else "CN-" + i.toString
    }

    try {
      val customerId = createCustomerId2(123)
      println("Created customerId: " + customerId)
    } catch {
      case e : RuntimeException => println("Error: " + e.getMessage)
    }













//  Use Try, Either or a Case Class
//  (which are all just case classes!)
    case class Error(message: String, code: Int)
    def createCustomerId(i: Int): Either[Error, String] = {
      if (i < 0 || i > 1000000)
        Left(Error("Customer id outside of range 0 to 1000000", 123))
      else
        Right("CN-" + i.toString)
    }

    createCustomerId(123) match {
      case Left(error) => println(error)
      case Right(customerId) => println("Created customerId: " + customerId)
    }

    // Try us like Either, but Left is an exception
  }













  test("Option map and flatMap"){
    val x: Option[Int] = Some(10)

    //val ans = x * 2 // Doesn't compile

    val twice: Option[Int] = x match {
      case None => None
      case Some(x) => Some(x * 2)
    }











    // Map.
    // Gets what's in the box,
    // apply method and put back in the box
    val twice2: Option[Int] = x.map(x => x * 2)






    val twice3 = x.map(_ * 2)









    def reciprocal(x : Double) : Option[Double] =
      if (x == 0) None
      else Some(1 / x)

    val ans1 = reciprocal(10)
    val ans2: Option[Option[Double]] = ans1.map(reciprocal)

    val ans3: Option[Double] = ans1 match {
      case None => None
      case Some(x) => reciprocal(x)
    }

    val ans4: Option[Double] = reciprocal(10)
      .flatMap(reciprocal)
  }





  test("For comprehension with Option"){
    def mathsOp1(x : Int) : Option[Int] = Some(x + 1)
    def mathsOp2(x : Int) : Option[Int] = Some(x * 1)
    def mathsOp3(x : Int) : Option[Int] =
      if (x == 0) None
      else Some(1 / x)

    val ans1: Option[Int] =
      mathsOp1(10)
        .flatMap(x => mathsOp2(x))
        .flatMap(y => mathsOp3(y))



    val and2: Option[Int] = for{
      x <- mathsOp1(10)
      y <- mathsOp2(x)
      z <- mathsOp3(y)
    } yield z
  }










  test("Sequences & Lists with map and flatMap"){
    val nums = Seq(1,2,3)
    nums.map(x => x * 2) // 2, 4, 6

    val strings = Seq("abc", "def", "hij")
    strings
      .flatMap(x => x.toCharArray) // Seq('a','b','c','d','e','f','h','i','j')

    for{
      a: String <- strings
      b: Char <- a.toCharArray
    } yield b
  }







  // Futures
  test("Futures") {

    val ans: Future[Int] = Future {
      // Could be slow to calculate, or from a RPC
      10
    }

    ans.onComplete((t: Try[Int]) => t match {
      case Failure(exception) => println("Failed: " + exception.getMessage)
      case Success(x) => println("Ans: " + x)
    })








    val ans2: Future[String] =
      ans.map(x => "Ans: " + x)


    val ans3 : Future[String] =
      ans.flatMap(x => Future{"Ans: " + x})
  }













  test("Futures and remote repositories"){
    case class Customer(id: String, name: String)
    case class Order(customerId: String, orderDetails: String)

    def getCustomer(customerId: String): Future[Customer] =
      Future {
        Customer(customerId, "Fred")
      }

    def getOrders(customerId: String): Future[Seq[Order]] =
      Future {
        Seq(
          Order(customerId, "Order 1"),
          Order(customerId, "Order 2"))
      }

    val customerNameAndOrders = for {
      customer <- getCustomer("customer-1")
      orders <- getOrders(customer.id)
    } yield orders


    customerNameAndOrders.onComplete { // This is just a shortened match statement
      case Failure(exception) => println("Failed: " + exception.getMessage)
      case Success(orders) => orders.map(order => println(s"${order.orderDetails}"))
    } // Prints "Order 1, Order 2" however it prints it after this method has returned (non-deterministic)

    Thread.sleep(2000)
  }











  // End









  test("Recursion") {
    case class Product(cost: Int)

    val products = List(Product(10), Product(20))

    def mySum[T](items :List[T],
                 getValue : T => Int): Int = {
      items match {
        case item :: tail =>
          getValue(item) + mySum(tail, getValue)
        case Nil => 0
      }
    }

    def getValue(p: Product) = p.cost

    val total = mySum[Product](products, getValue)
    total // 30

    products.map(_.cost).sum
  }









  test("Partial function application") {
    def add(a: Int)(b: Int) = a + b

    def addOne = add(1)(_)

    println(addOne(5)) // 6
  }













  test("Function composition") {
    def addThree = { x : Int => x + 3}

    def multiplyByTen = { x : Int => x * 10 }

    def add3Times10 = addThree andThen multiplyByTen

    val ans = add3Times10(2) // 50
    println(ans)
  }










  test("Function composition and partial application") {
    def add(x: Int)(y: Int) = x + y

    def multiply(x: Int)(y: Int) = x * y

    def add3Times10 = (add(3)(_)) andThen (multiply(10)(_))

    val ans = add3Times10(2) // 50
    println(ans)
  }
}
