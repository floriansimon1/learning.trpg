/**
 * Author : Florian Simon <florian@tentwentyfour.lu>
 * Role   : Case class that represents a team of players.
 */

package Game;

import scala.swing.Color;

/**
 * The team case class
 *
 * @param color      The displayed color for the team.
 * @param characters Don't set this directly. Use withCharacters().
 */
case class Team(color : Color, characters : List[Int] = Nil) extends Entity {
  /**
   * Returns whether or not the team is defeated.
   *
   * @param g The game instance to fetch characters from.
   *
   * @return True if the team is defeated, false otherwise.
   */
  def defeated(g : Game) : Boolean = {
    return g.getCharacters(this.characters).forall(_.isDead);
  };

  /**
   * Returns a copy of the team with the characters defined
   * as those passed.
   *
   * @param assignedCharacters
   *
   * @return The copy.
   */
  def withCharacters(assignedCharacters : List[Int]) : Team = {
    return this.copy(characters = assignedCharacters);
  }
}
