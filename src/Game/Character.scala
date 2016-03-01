/**
*  Author : Florian Simon <florian@tentwentyfour.lu>
*  Role   : Case class that represents a character.
*/

package Game

/**
 * Case class that represents a character.
 *
 * @param name         The name of the player.
 * @param health       The character's HP.
 * @param speed        How quickly the character regain action points.
 * @param range        The number of tiles you can walk in one move.
 * @param actionPoints The more points a character has, the quicker he can play.
 * @param team         The team the character belongs to.
 */
case class Character(
  name         : String,
  health       : Stat,
  speed        : Stat,
  range        : Stat,
  actionPoints : Int,
  team         : Int
) extends Entity {
  /**
   * Returns whether or not the character is dead.
   *
   * @return True if the character is dead, false otherwise.
   */
  def isDead() : Boolean = {
    return this.health.currentValue == 0;
  };

  /**
   * Applies an attack on a character.
   *
   * @param attack The attack to apply.
   *
   * @return A character on which the attack has been applied.
   */
  def sustain(attack : Attack) : Character = {
    return this.copy(
      health = this.health - attack.health
    );
  }
}
