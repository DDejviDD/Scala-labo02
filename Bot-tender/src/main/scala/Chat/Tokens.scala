package Chat

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
  // Utils
  val PSEUDO: Token         =  9
  val NUM: Token            = 10
  val UNKNOWN: Token        = 11
  val EOL: Token            = 12
  // Test
  val ASSOIFFE : Token      = 13
  val AFFAME : Token        = 14
  // New
  val PRODUIT: Token        = 15
  val MARQUE: Token         = 16
  val COUTER: Token         = 17
  val COMMANDER: Token      = 18
  val SOLDE: Token          = 19
  val PRIX: Token           = 20
  val COMBIEN: Token        = 21
  val QUEL:Token            = 22
  val DETERMINANT:Token     = 23
  val CONNAITRE:Token       = 24
  val MOI:Token             = 25
  val APPELLER:Token        = 26
}
