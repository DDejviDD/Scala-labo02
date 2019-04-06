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

         case ShopCart(quantity, product) => quantity * product.price()
         case Or(leftNode, rightNode) => Math.min(leftNode.computePrice, rightNode.computePrice)
         case And(leftNode, rightNode) => leftNode.computePrice + rightNode.computePrice
         case _ => 0.0
      }


      /**
        * Return the output text of the current node, in order to write it in console.
        * @return the output text of the current node
        */
      def reply: String = this match {
         // Example cases

         case ReadOrAddUser(name) => {
            UsersInfo.setActiveUser(name)
            "Bonjour, " + UsersInfo.getActiveUser
         }
         case ObtainPrice(cart) => "Cela coûte CHF " + cart.computePrice.toString
         case loginRequired =>
            if(UsersInfo.getActiveUser == null)
               "Veuillez d'abord vous identifier"
            else loginRequired match {
               case Balance() => "Le montant actuel de votre solde est de CHF " +
                  UsersInfo.getUserBalance(UsersInfo.getActiveUser)
               case Order(cart) => {

                  val orderPrice = cart.computePrice
                  val currentBalance = UsersInfo.getUserBalance(UsersInfo.getActiveUser)
                  val newBalance =  UsersInfo.purchase(UsersInfo.getActiveUser, orderPrice)
                  if(currentBalance == newBalance)
                     "Solde insuffisant"
                  else
                     "Voici donc " + cart.toString + " ! Cela coûte CHF " + orderPrice.toString +
                        " et votre nouveau solde est de CHF " + newBalance.toString
               }
            }

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
   case class ReadOrAddUser(username: String) extends ExprTree
   case class Order(cart: ExprTree) extends ExprTree
   case class ObtainPrice(cart: ExprTree) extends ExprTree
   case class ShopCart(quantity: Int, product: Products.Product) extends ExprTree {
      override def toString: String = {
         quantity.toString + " " + product.toString
      }
   }
   case class Balance() extends ExprTree

   case class Or(leftNode: ExprTree, rightNode: ExprTree) extends ExprTree {
      override def toString: String = {
         if(leftNode.computePrice <= rightNode.computePrice)
            leftNode.toString
         else
            rightNode.toString
      }
   }
   case class And(leftNode: ExprTree, rightNode: ExprTree) extends ExprTree {
      override def toString: String = {
         leftNode.toString + " et " + rightNode.toString
      }
   }

}
