package Utils

import Utils.Dictionary.dictionary

/*
 * edited by Muaremi Dejvid, Siu Aurélien
 */

object SpellChecker {

  /**
    * Compute a levenstein distance between two strings
    * @param s1 the first word to be compare
    * @param s2 the second word to be compared
    * @return a value corresponding to the levenstein distance between these words
    */
  def stringDistance(s1: String, s2: String): Int = {
    val memo = scala.collection.mutable.Map[(String, String), Int]()
    def min(a: Int, b: Int, c: Int) = Math.min(Math.min(a, b), c)

    def sd(s1: String, s2: String): Int = {
      if (!memo.contains((s1, s2))) {
        memo((s1, s2)) = (s1, s2) match {
          case (_, "") => s1.length
          case ("", _) => s2.length
          case (x, y) =>
            val c1 = x.charAt(0)
            val t1 = x.substring(1, x.length)
            val c2 = y.charAt(0)
            val t2 = y.substring(1, y.length)

            min(sd(t1, s2) + 1, sd(s1, t2) + 1, sd(t1, t2) + (if (c1 == c2) 0 else 1))
        }
      }

      memo((s1, s2))
    }

    sd(s1, s2)
  }

  /**
    * When the word is misspelled, we try to find the closest one using the levenstein distance
    * @param misspelledWord the word that couldn't be found
    * @return the closest word to the mispelled one
    */
  def getClosestWordInDictionary(misspelledWord: String): String = {
    // If the word is a number we don't want to replace it so we just return it.
    if (misspelledWord.forall(Character.isDigit) || misspelledWord.startsWith("_")) {
      misspelledWord
    } else {
      // Build a list of tuples that contains the Levenshtein score for each of the dictionary's keys, according to the
      // given misspelled word, e.g. (("bonjour", 4), ("hello", 1), (...))
      val dictionaryDistanceScores = dictionary
        .map(x => (x._1, stringDistance(x._1, misspelledWord)))
        .toSeq

      // Sort the list by ascending score and return the top element's value (which is the closest word).
      dictionary.getOrElse(dictionaryDistanceScores.minBy(_._2)._1, throw new Error("Unable to find an occurence in the dictionary."))
    }
  }
}
