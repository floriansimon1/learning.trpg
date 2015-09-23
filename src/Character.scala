/**
*  Author : Florian Simon <florian@tentwentyfour.lu>
*  Role   : Case class that represents a character.
*/

package TRPG;

/**
 * Case class that represents a character.
 *
 * @param name         The name of the player.
 * @param health       The character's HP.
 * @param speed        How quickly the character regain action points.
 * @param range        The number of tiles you can walk in one move.
 * @param actionPoints The more points a character has, the quicker he can play.
 */
case class Character(
  name         : String,
  health       : Stat,
  speed        : Stat,
  range        : Stat,
  actionPoints : Int
) extends Entity {
  /**
   * Returns whether or not the character is dead.
   *
   * @return True if the character is dead, false otherwise.
   */
  def isDead() : Boolean = {
    return this.health.currentValue == 0;
  };
}
