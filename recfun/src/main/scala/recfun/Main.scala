package recfun

object Main {
  def main(args: Array[String]) {
    println("Pascal's Triangle")
    for (row <- 0 to 10) {
      for (col <- 0 to row)
        print(pascal(col, row) + " ")
      println()
    }
  }

  /**
   * Exercise 1
   */
  def pascal(c: Int, r: Int): Int = {
    if(c == 0 || c == r) 1
    else pascal(c, r-1) + pascal(c-1, r-1)
  }
  
  /**
   * Exercise 2
   */
    def balance(chars: List[Char]): Boolean = {

      def balanceRecursive(chars: List[Char], unclosedParenthesesCount: Int) : Boolean = {
        var newCount: Int = unclosedParenthesesCount
        if (chars.isEmpty)
          return true

        if (chars.head == ')')
          if (newCount == 0)
            return false
          else
            newCount -= 1

        if (chars.head == '(')
          newCount += 1

        balanceRecursive(chars.tail, newCount)

      }

    balanceRecursive(chars, 0)

    }
  
  /**
   * Exercise 3
   */
    def countChange(money: Int, coins: List[Int]): Int = {

      def countChangeRecursive(money: Int, coins: List[Int]): Int = {
        if (money < 0 || coins.isEmpty) 0
        else if (money == 0) 1
        else countChangeRecursive(money, coins.tail) + countChangeRecursive(money - coins.head, coins)
      }

      if (money == 0)
        0
      else
        countChangeRecursive(money, coins)
    }
  }
