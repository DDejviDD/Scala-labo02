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

  /** the root method of the parser: parses an entry phrase */
  def parsePhrases(): ExprTree = {

    // Start the process by reading the first token.
    readToken()

    if (curToken == BONJOUR) eat(BONJOUR)
    if (curToken == JE) {
      eat(JE)
      if (curToken == VOULOIR) {
        eat(VOULOIR)
        if (curToken == COMMANDE) {
          eat(COMMANDE)


          if (curToken == NUM) {
            readToken()
            Num()
            if (curToken == PRODUIT) {
              readToken()
              Product()
              if (curToken == MARQUE) {
                readToken()
                Marque()
              } else if (curToken == OU) {
                readToken()
                Or()
              } else if (curToken == ET) {
                readToken()
                And()
              } else {
                expected(MARQUE, OU, ET)
              }
            } else {
              expected(PRODUIT)
            }
          } else {
            expected(NUM)
          }

          Commande()
        }
        else if (curToken == SOLDE) {
          readToken()
          Balance()
        }
        else {
          expected(COMMANDE, SOLDE)
        }


      } else if (curToken == ETRE) {
        eat(ETRE)
        if (curToken == ASSOIFFE) {
          // Here we do not "eat" the token, because we want to have a custom 2-parameters "expected" if the user gave a wrong token.
          readToken()
          Thirsty()
        }
        else if (curToken == AFFAME) {
          readToken()
          Hungry()
        }
        else if (curToken == PSEUDO) {
          readToken()
          ReadOrAddUser()
        }
        else {
          expected(ASSOIFFE, AFFAME, PSEUDO)
        }
      } /*else if(curToken == ME){
            eat(ME)
            if(curToken == APPELLE){
               eat(APPELLE)
               if (curToken == PSEUDO){
                  readToken()
                  ReadOrAddUser()
               }
               else {
                  expected(PSEUDO)
               }
            } else {
               expected(APPELLE)
            }

         }*/ else {
        expected(VOULOIR, ETRE /*, ME*/)
      }
    }
    else expected(BONJOUR, JE)
  }


}
