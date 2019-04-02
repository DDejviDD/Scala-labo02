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
  val POLITESSE: Token      = 17
  val IDENTIFICATION: Token = 18
  val COMMANDE: Token       = 19
  val SOLDE: Token          = 20
  val PRIX: Token           = 21
  val PHRASE: Token         = 22

}
