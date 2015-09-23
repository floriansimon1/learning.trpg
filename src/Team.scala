/**
 * Author : Florian Simon <florian@tentwentyfour.lu>
 * Role   : Case class that represents a team of players.
 */

package TRPG;

/**
 * The team case class
 *
 * @param characters The character that compose the team.
 */
case class Team(characters : List[Character]) {
  /**
   * Returns whether or not the team is defeated.
   *
   * @return True if the team is defeated, false otherwise.
   */
  def defeated() : Boolean = {
    return this.characters.forall(_.isDead);
  };
}
