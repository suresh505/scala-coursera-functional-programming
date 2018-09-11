package funsets

import org.scalatest.FunSuite


import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner


/**
 * This class is a test suite for the methods in object FunSets. To run
 * the test suite, you can either:
 *  - run the "test" command in the SBT console
 *  - right-click the file in eclipse and chose "Run As" - "JUnit Test"
 */
@RunWith(classOf[JUnitRunner])
class FunSetSuite extends FunSuite {

  /**
   * Link to the scaladoc - very clear and detailed tutorial of FunSuite
   *
   * http://doc.scalatest.org/1.9.1/index.html#org.scalatest.FunSuite
   *
   * Operators
   *  - test
   *  - ignore
   *  - pending
   */

  /**
   * Tests are written using the "test" operator and the "assert" method.
   */
  // test("string take") {
  //   val message = "hello, world"
  //   assert(message.take(5) == "hello")
  // }

  /**
   * For ScalaTest tests, there exists a special equality operator "===" that
   * can be used inside "assert". If the assertion fails, the two values will
   * be printed in the error message. Otherwise, when using "==", the test
   * error message will only say "assertion failed", without showing the values.
   *
   * Try it out! Change the values so that the assertion fails, and look at the
   * error message.
   */
  // test("adding ints") {
  //   assert(1 + 2 === 3)
  // }


  import FunSets._

  test("contains is implemented") {
    assert(contains(x => true, 100))
  }

  /**
   * When writing tests, one would often like to re-use certain values for multiple
   * tests. For instance, we would like to create an Int-set and have multiple test
   * about it.
   *
   * Instead of copy-pasting the code for creating the set into every test, we can
   * store it in the test class using a val:
   *
   *   val s1 = singletonSet(1)
   *
   * However, what happens if the method "singletonSet" has a bug and crashes? Then
   * the test methods are not even executed, because creating an instance of the
   * test class fails!
   *
   * Therefore, we put the shared values into a separate trait (traits are like
   * abstract classes), and create an instance inside each test method.
   *
   */

  trait TestSets {
    val s1 = singletonSet(1)
    val s2 = singletonSet(2)
    val s3 = singletonSet(3)
    val even: Set = x => x % 2 == 0 && x < 20
    val multiplesOf3: Set = x => x % 3 == 0 && x < 20
    val powersOf2: Set = x => (x & (x-1)) == 0


  }

  /**
   * This test is currently disabled (by using "ignore") because the method
   * "singletonSet" is not yet implemented and the test would fail.
   *
   * Once you finish your implementation of "singletonSet", exchange the
   * function "ignore" by "test".
   */
  test("singletonSet(1) contains 1") {

    /**
     * We create a new instance of the "TestSets" trait, this gives us access
     * to the values "s1" to "s3".
     */
    new TestSets {
      /**
       * The string argument of "assert" is a message that is printed in case
       * the test fails. This helps identifying which assertion failed.
       */
      assert(contains(s1, 1), "Singleton")
    }
  }

  test("union contains all elements of each set") {
    new TestSets {
      val s = union(even, multiplesOf3)
      assert(contains(s, 6), "Does not contain 6")
      assert(contains(s, 18), "Does not contain 18")
      assert(contains(s, 3), "Does not contain 3")
      assert(contains(s, 0), "Does not contain 0")
      assert(!contains(s, 5), "Contains 5")
      assert(!contains(s, -7), "Contains -7")
      assert(!contains(s, 24), "Contains 24")
    }
  }

  test("intersect contains the common elements") {
    new TestSets {
      val s = intersect(even, multiplesOf3)
      assert(contains(s, 0), "Does not contain 0")
      assert(contains(s, 6), "Does not contain 6")
      assert(contains(s, -12), "Does not contain -12")
      assert(!contains(s, 3), "Contains 3")
      assert(!contains(s, 5), "Contains 5")
      assert(!contains(s, -15), "Contains -15")
    }
  }

  test("difference of the two sets") {
    new TestSets {
      val s = diff(even, multiplesOf3)
      assert(contains(s, 2), "Does not contain 2")
      assert(contains(s, -8), "Does not contain -8")
      assert(!contains(s, 0), "Contains 0")
      assert(!contains(s, 6), "Contains 6")
      assert(!contains(s, -18), "Contains -18")
    }
  }

  test("filter based on the given predicate") {
    new TestSets {
      val s = filter(even, x => x > 10)
      assert(contains(s, 12), "Does not contain 12")
      assert(!contains(s, 0), "Contains 0")
      assert(!contains(s, -6), "Contains -6")
      assert(!contains(s, 30), "Contains 30")
    }
  }

  test("forall - Returns whether all bounded integers within a set match a predicate") {
    new TestSets {
      val result1 = forall(powersOf2, x => {
        if (x >= 2 && x <= -2){
          x % 4 == 0
        }
        else true

      } )
      assert(result1, "All powers of 2 are not multiples of 4 when x >= 2 and x <= -2")

      val result2 = forall(powersOf2, _ > 100 )
      assert(!result2, "All powers of 2 are greater than 100")
    }
  }

  test("exists - Returns true if there exists a bounded integer that satisfies the predicate") {
    new TestSets {
      val result1 = exists(filter(powersOf2, _ > 0), _ % 3 == 0)
      assert(!result1, "There exists at least one power-of-2 that is divisible by 3")

      val result2 = exists(powersOf2, x => x > 30 && x < 40)
      assert(result2, "No power-of-2 exists between 30 and 40")
    }
  }

  test("map - Returns a set by transforming each element") {
    new TestSets {
      val result = map(even, _ + 1)
      assert(contains(result, 3), "Does not contain 3")
      assert(!contains(result, 16), "Contains 16")
      assert(!contains(result, 0), "Contains 0")
    }
  }
}
