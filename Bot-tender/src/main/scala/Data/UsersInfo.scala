package Data

import scala.collection.mutable

/*
 * edited by Muaremi Dejvid, Siu Aurélien
 */

object UsersInfo {

   // Will contain the name of the currently active user; default value is null.
   private var activeUser: String = _

   private val accounts = new mutable.HashMap[String, Double]()

   /**
     * Return an account user balance (create a new account
     * if user does not exist with default balance).
     * @param user the user whose account will be accessed
     * @return the user balance
     */
   private def userBalance(user: String): Double = accounts.getOrElseUpdate(user, 30.0)

   /**
     * Update an account by decreasing its balance.
     * @param user the user whose account will be updated
     * @param amount the amount to decrease
     * @return the new balance
     */
   def purchase(user: String, amount: Double): Double = {
      if (userBalance(user) - amount >= 0.0) {
         accounts.update(user, userBalance(user) - amount)
      }
      userBalance(user)
   }

   /**
     * Get user account user balance.
     * @param user the user whose account balance will be return
     * @return the current balance
     */
   def getUserBalance(user: String): Double = userBalance(user)

   /**
     * Get active user
     * @return the active user
     */
   def getActiveUser: String = activeUser

   /**
     * set new active user
     */
   def setActiveUser(user: String): Unit = activeUser = user.substring(1)


}

