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
  moves      : List[Move],
  characters : Map[Int, Character]
) {
  /**
   * Returns whether or not the game is over.
   *
   * @return True if the game is over, false otherwise.
   */
  def over() : Boolean = {
    return teams.exists(_.defeated(this));
  }

  /**
   * Takes a move, applies it on a new game instance and
   * returns that new instance.
   *
   * @param move The move to apply.
   *
   * @return The new instance.
   */
  def applyMove(move : Move) : Game = {
    /* Moves the character. */
    val newGrid = move.newPosition match {
      case Some(p) ⇒ this.grid.move(move.character.ID, p);
      case _       ⇒ this.grid
    };

    /* Applies the attack to its target. */
    val characters = move.attack match {
      case Some(a) ⇒ this.characters + (a.target → this.characters.get(a.target).get.sustain(a.attack));
      case _       ⇒ this.characters
    };

    /* Returns the updated game state, adding the move to the moves list. */
    return this.copy(
      grid       = newGrid,
      characters = characters,
      moves      = move :: this.moves
    );
  }
}
