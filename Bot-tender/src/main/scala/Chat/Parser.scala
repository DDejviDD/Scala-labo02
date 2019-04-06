package Chat

import Data.{Beer, Chips, Croissant}
import Tokens._
import Tree._
import Data.Products._

// TODO - step 4
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

   def parsePrice() : ExprTree = {
      if (curToken == COMBIEN){
         readToken()
         eat(COUTER)
      }
      else if(curToken == QUEL) {
         readToken()
         eat(ETRE)
         eat(DETERMINANT)
         eat(PRIX)
         eat(DETERMINANT)
      }
     ObtainPrice(parseItems())
   }
   def parseOrder() : ExprTree = {
      var quantity : Int = 0
      var product : Product = null

      if(curToken == NUM){
         quantity = curValue.toInt
         eat(NUM)
         curToken match {
            case BIERE => {
               product = new Beer()
               readToken()
               if(curToken == MARQUE){
                  product.brand = curValue
                  readToken()
               }
            }
            case CROISSANT => {
               product = new Croissant()
               readToken()
               if(curToken == MARQUE){
                  product.brand = curValue
                  readToken()
               }
            }
            case CHIPS => product = new Chips()
            case _ => expected(CROISSANT, BIERE, CHIPS)
         }
      }
      ShopCart(quantity, product)
   }
   def parseItems() : ExprTree = {
      val leftNode:ExprTree = parseOrder()
      curToken match {
         case ET => {
            readToken()
            And(leftNode, parseItems())
         }
         case OU =>{
            readToken()
            Or(leftNode, parseItems())
         }
         case _ => {
            readToken()
            leftNode
         }
      }
   }

   /** the root method of the parser: parses an entry phrase */
   def parsePhrases() : ExprTree = {
      if (curToken == BONJOUR) { readToken() }
      if(curToken == COMBIEN || curToken == QUEL){ parsePrice() }
      else if (curToken == JE ) {
         readToken()
         if(curToken == VOULOIR){
            readToken()
            if(curToken == COMMANDER){
               readToken()
               Order(parseItems())
            } else if(curToken == CONNAITRE){
               readToken()
               eat(MOI)
               eat(SOLDE)
               Balance()
            } else{ expected(COMMANDER, CONNAITRE) }
         } else if (curToken == ETRE) {
            readToken()
            if (curToken == ASSOIFFE) {
               readToken()
               Thirsty()
            }
            else if (curToken == AFFAME) {
               readToken()
               Hungry()
            }
            else if (curToken == PSEUDO){
              var tmp = curValue
              readToken()
              ReadOrAddUser(tmp)
            } else { expected(ASSOIFFE, AFFAME, PSEUDO) }
         } else if(curToken == MOI){
            readToken()
            eat(APPELLER)
            ReadOrAddUser(curValue)
         } else { expected(VOULOIR, ETRE, MOI) }
      } else { expected(COMBIEN, QUEL, JE) }
   }
   // Start the process by reading the first token.
   readToken()
}
