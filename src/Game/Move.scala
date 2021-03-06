/**
*  Author : Florian Simon <florian@tentwentyfour.lu>
*  Role   : Case class that represents a single move.
*/

package Game

/**
 * Case class that represents a single move.
 *
 * @param newPosition  The new position of the object, if any.
 * @param attack       The attack that was thrown.
 * @param randomNumber The generated random number.
 */
case class Move(
  character        : Int,
  newPosition      : Option[Point],
  attack           : Option[CarriedAttack],
  randomNumber     : Int
);
