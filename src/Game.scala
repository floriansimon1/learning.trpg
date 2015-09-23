/**
 * Author : Florian Simon <florian@tentwentyfour.lu>
 * Role   : Case class that represents a game of TRPG.
 */

package TRPG;

import scala.util.Random;

/**
 * The game case class.
 *
 * @param grid       The game's map.
 * @param randomizer The game's randomizer.
 * @param teams      The teams the game opposes.
 * @param moves      The successive moves that result in the current game state.
 */
case class Game(
  grid       : Grid,
  randomizer : Random,
  teams      : List[Team],
  moves      : List[Move]
) {
  /**
   * Returns whether or not the game is over.
   *
   * @return True if the game is over, false otherwise.
   */
  def over() : Boolean = {
    return teams.exists(_.defeated);
  }

  /**
   * Takes a move, applies it on a new game instance and
   * returns that new instance.
   *
   * @param move The move to apply.
   *
   * @return The new instance.
   */
  def applyMove(move : Move) : Game = ???
}
