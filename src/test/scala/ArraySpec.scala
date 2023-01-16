
import scala.concurrent.Future
import scala.util.{Failure, Success, Try}

import org.scalatest._
import funsuite._

import scala.concurrent.ExecutionContext.Implicits.global
import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers

class ArraySpec extends AnyWordSpec with Matchers {
  "an empty array" should {
    val empty = Array.empty
    
    "be empty" in {
      empty.isEmpty shouldBe true
    }

    "contain no elements" in {
      empty.size shouldBe 0
    }
  }

  "A single element array" should {
    val single = Array("a")

    "be not empty" in {
      single.isEmpty shouldBe false
    }

    "be sized as 1" in {
      single.size shouldBe 1
    }
    
    "Have a head" in {
      single.head shouldBe "a"
    }

    "Have an empty tail" in {
      single.tail shouldBe Array.empty
    }
  }
}
