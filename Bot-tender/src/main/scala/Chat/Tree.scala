package Chat

import Data.{Products, UsersInfo}

// TODO - step 3
object Tree {

   /**
     * This sealed trait represents a node of the tree and contains methods to compute it and write its output text in console.
     */
   sealed trait ExprTree {

      /**
        * Compute the price of the current node, then returns it. If the node is not a computational node, the method
        * returns 0.0.
        * For example if we had a "+" node, we would add the values of its two children, then return the result.
        * @return the result of the computation
        */
      def computePrice: Double = this match {
            // Example cases

            case Product() => 0.0
            case Or() => Math.min(leftNode.computePrice, rightNode.computePrice)
            case And() => leftNode.computePrice + rightNode.computePrice
      }


      /**
        * Return the output text of the current node, in order to write it in console.
        * @return the output text of the current node
        */
      def reply: String = this match {
         // Example cases


         case Or() => "or"
         case And() => "et"
         case Login(name) => {
            UsersInfo.setActiveUser(name)
            "Bonjour, " + name
         }
         case Price(basket) => "Cela coûte CHF " + basket.computePrice

         case Thirsty() => "Eh bien, la chance est de votre côté, car nous offrons les meilleures bières de la région !"
         case Hungry() => "Pas de soucis, nous pouvons notamment vous offrir des croissants faits maisons !"
      }
   }
   /**
     * Declarations of the nodes' types.
     */
   // Example cases
   case class Thirsty() extends ExprTree
   case class Hungry() extends ExprTree
   case class Login(username: String) extends ExprTree
   case class Order(cart: ExprTree) extends ExprTree
   case class Price(cart: ExprTree) extends ExprTree
   case class Balance() extends ExprTree

   case class Item(product: Product, brand: Brand = null) extends ExprTree {

   }
   case class Items(product: Product, number: Int) extends ExprTree {
      override def toString: String = number.toString + " " + product.toString
   }

   case class Or(first: ExprTree, second: ExprTree) extends ExprTree
   case class And(first: ExprTree, second: ExprTree) extends ExprTree

}
