package Chat

import Tokens._
import Utils.Dictionary.dictionary
import Utils.SpellChecker._

/*
 * edited by Muaremi Dejvid, Siu Aurélien
 */

class Tokenizer(input: String) {
  var tokens: Array[(String, Token)] = Array()
  var currentTokenIndex = -1

  private def getTokenFromString(s: String): Token = s match {
    case "bonjour"    => BONJOUR
    case "je"         => JE
    case "etre"       => ETRE
    case "vouloir"    => VOULOIR
    case "et"         => ET
    case "ou"         => OU

    // Products
    case "biere"      => BIERE
    case "croissant"  => CROISSANT
    case "chips"      => CHIPS
    case "assoiffe"   => ASSOIFFE
    case "affame"     => AFFAME

    // Order
    case "commander"  => COMMANDER

    // Balance
    case "connaitre"  => CONNAITRE
    case "solde"      => SOLDE

    // Price of
    case "combien"    => COMBIEN
    case "couter"     => COUTER
    case "quel"       => QUEL
    case "le"         => DETERMINANT
    case "de"         => DETERMINANT
    case "prix"       => PRIX

    // Authentication
    case "moi"        => MOI
    case "appeler"    => APPELLER

    // Brands
    case "maison"     => MARQUE
    case "cailler"    => MARQUE
    case "farmer"     => MARQUE
    case "boxer"      => MARQUE
    case "wittekop"   => MARQUE
    case "punkipa"    => MARQUE
    case "jackhammer" => MARQUE
    case "ténébreuse" => MARQUE

    // other
    case p if p.startsWith("_") && p.length > 1 => PSEUDO // If the word starts with '_' and has more than one character it is a pseudonym.
    case n if n.forall(Character.isDigit) => NUM // If every character is a number, the word thus is a number.
    case _ => UNKNOWN
  }

  /**
    * Create a token from a word
    */
  def tokenize(): Unit = {
    val words = input
      .trim()
      .replaceAll("[.|,|!|?|*]", " ") // Remove punctuation.
      .replaceAll(" +|[']", " ") // Remove multiple spaces and replace apostrophes by a space.
      .split(" ")

    // Get each word's occurence in the dictionary or check for the closest word if it is not contained in the dictionary.
    val fromDictionnary = words.map(w => dictionary.getOrElse(w, getClosestWordInDictionary(w)))

    tokens = fromDictionnary.map(t => (t, getTokenFromString(t)))
  }

  /**
    * Obtain the next token from the message as a tuple (String, Token)
    * @return a tuple (String, Token) containing the next token
    */
  def nextToken(): (String, Token) = {
    currentTokenIndex += 1

    if (currentTokenIndex < tokens.size) {
      return tokens(currentTokenIndex)
    } else {
      return ("EOL", EOL)
    }
  }
}
