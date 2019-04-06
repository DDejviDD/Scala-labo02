package Chat

/*
 * edited by Muaremi Dejvid, Siu Aurélien
 */

object Tokens {
  type Token = Int

  // Terms
  val BONJOUR: Token        =  0
  val JE: Token             =  1
  // Actions
  val ETRE: Token           =  2
  val VOULOIR: Token        =  3
  // Operators
  val ET: Token             =  4
  val OU: Token             =  5
  // Products
  val BIERE: Token          =  6
  val CROISSANT: Token      =  7
  val CHIPS: Token          =  8
  // Utils
  val PSEUDO: Token         =  9
  val NUM: Token            = 10
  val UNKNOWN: Token        = 11
  val EOL: Token            = 12
  // Test
  val ASSOIFFE : Token      = 13
  val AFFAME : Token        = 14

  // Order
  val COMMANDER: Token      = 15
  val MARQUE: Token         = 16

  // Balance
  val CONNAITRE:Token       = 17
  val SOLDE: Token          = 18

  // Price of
  val COMBIEN: Token        = 19
  val COUTER: Token         = 20
  val QUEL:Token            = 21
  val DETERMINANT:Token     = 22
  val PRIX: Token           = 23

  // Authentication
  val MOI:Token             = 24
  val APPELLER:Token        = 25
}
