package Chat

import Tokens._
import Tree._

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
      if (curToken == COMBIEN){ eat(curToken)
         if(curToken == COUTER){
            eat(curToken)
            while (curToken == NUM){ parseItems() }
            ObtainPrice()
         }
         else { expected(COUTER) }
      } else if(curToken == QUEL){ eat(curToken)
         if (curToken == ETRE){ eat(curToken)
            if(curToken == DETERMINANT){ eat(curToken)
               if(curToken == PRIX){ eat(curToken)
                  if(curToken == DETERMINANT){
                     eat(curToken)
                     while (curToken == NUM){ parseItems() }
                     ObtainPrice()
                  }
                  else { expected(DETERMINANT) }
               }
               else { expected(PRIX) }
            }
            else { expected(DETERMINANT) }
         }
         else { expected(ETRE) }
      } else { expected(COMBIEN, QUEL) }
   }
   def parseItems() : ExprTree = {
      if(curToken == NUM){
         readToken()
         Num(Integer.valueOf(curValue))
         if(curToken == PRODUIT){
            readToken()
            Product()
            if(curToken == MARQUE){
               readToken()
               Marque()
               if (curToken == OU){
                  readToken()
                  Or()
               } else if (curToken == ET){
                  readToken()
                  And()
               }
               else{ EOL() }
            } else if (curToken == OU){
               readToken()
               Or()
            } else if (curToken == ET){
               readToken()
               And()
            } else { EOL() }
         } else{ expected(PRODUIT) }
      } else { expected(NUM) }
   }

   /** the root method of the parser: parses an entry phrase */
   def parsePhrases() : ExprTree = {

      if (curToken == BONJOUR) eat(BONJOUR)
      if(curToken == COMBIEN || curToken == QUEL){ parsePrice() }
      else if (curToken == JE ) {
         eat(JE)
         if(curToken == VOULOIR){ readToken()
            if(curToken == COMMANDER){ readToken()
               while (curToken == NUM){ parseItems() }
               Commande()
               Eol()
            } else if(curToken == CONNAITRE){ readToken()
               if(curToken == MOI) { readToken()
                  if(curToken == SOLDE){
                     readToken()
                     Balance()
                  } else {expected(SOLDE)}
               } else {expected(MOI)}
            } else { expected(CONNAITRE) }
         }
         else if (curToken == ETRE) { eat(ETRE)
            if (curToken == ASSOIFFE) {
               readToken()
               Thirsty()
            }
            else if (curToken == AFFAME) {
               readToken()
               Hungry()
            }
            else if (curToken == PSEUDO){
               readToken()
               ReadOrAddUser()
            } else { expected(ASSOIFFE, AFFAME, PSEUDO) }
         } else if(curToken == MOI){ eat(MOI)
            if(curToken == APPELLER){ eat(APPELLER)
               if (curToken == PSEUDO){
                  readToken()
                  ReadOrAddUser()
               } else { expected(PSEUDO) }
            } else { expected(APPELLER) }
         }
         else { expected(VOULOIR, ETRE, MOI) }
      } else { expected(BONJOUR, COMBIEN, QUEL, JE) }
   }
   // Start the process by reading the first token.
   readToken()
}
