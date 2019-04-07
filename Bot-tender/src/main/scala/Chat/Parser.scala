package Chat

import Data.Products._
import Data.{Beer, Chips, Croissant}
import Tokens._
import Tree._

/*
 * edited by Muaremi Dejvid, Siu Aurélien
 */

class Parser(tokenizer: Tokenizer) {

   import tokenizer._

   var curTuple: (String, Token) = ("unknown", UNKNOWN)

   def curValue: String = curTuple._1

   def curToken: Token = curTuple._2

   /** Reads the next token and assigns it into the global variable curTuple */
   def readToken(): Unit = curTuple = nextToken()

   /** "Eats" the expected token, or terminates with an error. */
   private def eat(token: Token): Unit = if (token == curToken) readToken() else expected(token)

   /** Complains that what was found was not expected. The method accepts arbitrarily many arguments of type TokenClass */
   // TODO (BONUS): find a way to display the string value of the tokens (e.g. "BIERE") instead of their integer value (e.g. 6).
   private def expected(token: Token, more: Token*): Nothing =
      fatalError(" expected: " +
         (token :: more.toList).mkString(" or ") +
         ", found: " + curToken)

   def fatalError(msg: String): Nothing = {
      println("Fatal error", msg)
      new Exception().printStackTrace()
      sys.exit(1)
   }

   /**
     * Follow the three where an user ask the price for an order
     *
     * @return an ExprTree which will obtain the price for different product
     */
   def parsePrice(): ExprTree = {
      if (curToken == COMBIEN) {
         readToken()
         eat(COUTER)
      }
      else if (curToken == QUEL) {
         readToken()
         eat(ETRE)
         eat(DETERMINANT)
         eat(PRIX)
         eat(DETERMINANT)
      }
      ObtainPrice(parseItems())
   }

   /**
     * Follow the three for an order
     * The usual form is defined as :  Number Product [brand]
     *
     * @return an ExprTree for an order
     */
   def parseOrder(): ExprTree = {
      // used to return the order in the ExpTree
      var quantity: Int = 0
      var product: Product = null

      // We need a quantity of product
      if (curToken == NUM) {
         quantity = curValue.toInt
         eat(NUM) // Return a fatal error if the quantity is not set
         // Then we will look for the kind of product and it's brand
         curToken match {
            case BIERE => {
               product = new Beer()
               readToken()
               if (curToken == MARQUE) {
                  product.brand = curValue
                  readToken()
               }
            }
            case CROISSANT => {
               product = new Croissant()
               readToken()
               if (curToken == MARQUE) {
                  product.brand = curValue
                  readToken()
               }
            }
            case CHIPS => product = new Chips()
            // In case of error while parsing the product
            // we return a custom 3 expected response
            case _ => expected(CROISSANT, BIERE, CHIPS)
         }
      }
      // Create and return the ExprTree
      ShopCart(quantity, product)
   }

   /**
     * Parse the items of a complete order request.
     * The usual form is : order1 {("and" | "or") order2}
     *
     * @return an ExprTree for a complete order request
     */
   def parseItems(): ExprTree = {
      // We use the first order as a left node
      val leftNode: ExprTree = parseOrder()
      // There's a right node only when we have
      // an AND or OR token following an order
      curToken match {
         case ET => {
            readToken()
            And(leftNode, parseItems())
         }
         case OU => {
            readToken()
            Or(leftNode, parseItems())
         }
         case _ => {
            // If we have anything else,
            // we return the first order as the only one
            readToken()
            leftNode
         }
      }
   }

   /** the root method of the parser: parses an entry phrase */
   def parsePhrases(): ExprTree = {
      // Take care of the welcome message
      if (curToken == BONJOUR) {
         readToken()
      }
      // Take care when an user need the price of an order
      if (curToken == COMBIEN || curToken == QUEL) {
         parsePrice()
      }
      else if (curToken == JE) {
         readToken()
         if (curToken == VOULOIR) {
            readToken()
            // Take care of an order
            if (curToken == COMMANDER) {
               readToken()
               Order(parseItems())
               // Take care of the balance of the current user
            } else if (curToken == CONNAITRE) {
               readToken()
               eat(MOI)
               eat(SOLDE)
               Balance()
            } else {
               expected(COMMANDER, CONNAITRE)
            }
            // Take care of the user authentication and state
         } else if (curToken == ETRE) {
            readToken()
            // When the user is thirsty
            if (curToken == ASSOIFFE) {
               readToken()
               Thirsty()
            }
            // When the user is hungry
            else if (curToken == AFFAME) {
               readToken()
               Hungry()
            }
            // When the user tell us his name
            else if (curToken == PSEUDO) {
               var user = curValue
               readToken()
               Authentification(user)
            } else {
               expected(ASSOIFFE, AFFAME, PSEUDO)
            }
            // When the user tell us his name in another way
         } else if (curToken == MOI) {
            readToken()
            eat(APPELLER)
            Authentification(curValue)
         } else {
            expected(VOULOIR, ETRE, MOI)
         }
      } else {
         expected(COMBIEN, QUEL, JE)
      }
   }

   // Start the process by reading the first token.
   readToken()
}
