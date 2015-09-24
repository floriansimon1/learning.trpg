/**
 * Author : Florian Simon <florian@tentwentyfour.lu>
 * Role   : Case class that holds data about the game's map.
 */

package TRPG;

/**
 * Case class that holds the game's map.
 *
 * @param width   The width of the map in number of tiles.
 * @param height  The height of the map in number of tiles.
 * @param objects The list of physical entities on the map.
 */
case class Grid(width : Int, height : Int, objects : Map[Int, Point]) {
  /**
   * Moves an entity to a new position.
   *
   * @param entity      The moved entity.
   * @param newPosition Its new position.
   *
   * @return A grid instance with an updated objects map.
   */
  def move(entity : Int, newPosition : Point) : Grid = {
    return this.copy(objects = objects + (entity → newPosition));
  }

  /**
   * Returns whether or not the given position is free.
   *
   * @return True if the given position is free, false otherwise.
   */
  def positionFree(position : Point) : Boolean = {
    return this.objects.forall({ case (_, pos) ⇒ pos == position });
  }

  /**
   * Returns the move options for a given character.
   *
   * @param character The character to determine move options for.
   *
   * @return A list of points the character can go to.
   */
  def getMoveOptions(character : Character) : List[Point] = {
    return (for (
      x        ← 0 to character.range.currentValue;
      y        ← 0 to character.range.currentValue;
      position ← new Point(x, y)
    )
      yield x + y match {
        case 0                                                                             ⇒ Nil;
        case range if range <= character.range.currentValue && this.positionFree(position) ⇒ position;
        case _                                                                             ⇒ Nil;
      }
    ).toList;
  }
}
