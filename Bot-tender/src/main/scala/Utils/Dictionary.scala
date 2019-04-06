package Utils

/**
* Contains the dictionary of the application, which is used to validate, correct and normalize words entered by the
* user.
*/

/*
 * edited by Muaremi Dejvid, Siu Aurélien
 */

object Dictionary {
  // This dictionary is a Map object that contains valid words as keys and their normalized equivalents as values (e.g.
  // we want to normalize the words "veux" and "aimerais" in on unique term: "vouloir").
  val dictionary: Map[String, String] = Map(
    "bonjour"     -> "bonjour",
    "hello"       -> "bonjour",
    "yo"          -> "bonjour",
    "je"          -> "je",
    "j"           -> "je",
    "suis"        -> "etre",
    "est"         -> "etre",
    "veux"        -> "vouloir",
    "voudrais"    -> "vouloir",
    "aimerais"    -> "vouloir",
    "biere"       -> "biere",
    "bière"       -> "biere",
    "bières"      -> "biere",
    "croissant"   -> "croissant",
    "croissants"  -> "croissant",
    "et"          -> "et",
    "ou"          -> "ou",
    "assoiffé"    -> "assoiffe",
    "assoiffée"   -> "assoiffe",
    "affamé"      -> "affame",
    "affamée"     -> "affame",
    "commander"   -> "commander",
    "connaitre"   -> "connaitre",
    "solde"       -> "solde",
    "combien"     -> "combien",
    "couter"      -> "couter",
    "quel"        -> "quel",
    "prix"        -> "prix",
    "le"          -> "le",
    "de"          -> "de",
    "m"           -> "moi",
    "mon"         -> "moi",
    "appeler"     -> "appeler",
    "appelle"     -> "appeler",
    "chips"       -> "chips",
    "maison"      -> "maison",
    "cailler"     -> "cailler",
    "farmer"      -> "farmer",
    "boxer"       -> "boxer",
    "wittekop"    -> "wittekop",
    "punkipa"     -> "punkipa",
    "jackhammer"  -> "jackhammer",
    "tenebreuses" -> "ténébreuse",
    "tenebreuse"  -> "ténébreuse",
    "ténébreuse"  -> "ténébreuse"
  )
}
