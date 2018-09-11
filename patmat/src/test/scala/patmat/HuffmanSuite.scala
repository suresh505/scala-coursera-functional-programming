package patmat

import org.scalatest.FunSuite

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

import patmat.Huffman._

@RunWith(classOf[JUnitRunner])
class HuffmanSuite extends FunSuite {
	trait TestTrees {
		val t1 = Fork(Leaf('a',2), Leaf('b',3), List('a','b'), 5)
		val t2 = Fork(Fork(Leaf('a',2), Leaf('b',3), List('a','b'), 5), Leaf('d',4), List('a','b','d'), 9)
	}


  test("encode") {
    new TestTrees {
      println(encode(frenchCode) (List()))
    }
  }

  test("quick encode") {
    new TestTrees {
      println(quickEncode(frenchCode) (List('h','f')) === encode(frenchCode) (List('h','f')))
      //println(quickEncode(frenchCode) (List('h','f')))
      //println(encode(frenchCode) (List('h','f')))

     // println(convert(t2))

    }
  }

}
