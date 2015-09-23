/**
 * Author : Florian Simon <florian@tentwentyfour.lu>
 * Role   : Case class that represents a game of TRPG.
 */

package TRPG;

import scala.util.Random;

/**
 * The game case class.
 *
 * @param randomizer The game's randomizer.
 * @param teams      The teams the game opposes.
 * @param moves      The successive moves that result in the current game state.
 */
case class Game(
  randomizer : Random,
  teams      : List[Team],
  moves      : List[Move]
) {
  def over() : Boolean = {
    return teams.exists(_.defeated);
  }
}
