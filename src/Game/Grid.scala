/**
 * Author : Florian Simon <florian@tentwentyfour.lu>
 * Role   : Case class that holds data about the game's map.
 */

package Game

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
    return this.objects.forall({ case (_, pos) ⇒ pos != position });
  }

  /**
   * Returns a list of points in a range.
   *
   * @param current The center for calculations.
   * @param filter  By default, this method returns tiles that are in range and in the grid.
   *                The filter allows you to further restrict the list of tiles. It just
   *                takes a point and returns whether or not it should be in the result set.
   */
  def getTileOptions(current : Point, range : Int, filter : (Point) ⇒ Boolean) : Set[Point] = {
    return (
      for (
        y        ← current.y - range to current.y + range;
        x        ← current.x - range to current.x + range;
        dx       = Math.max(x, current.x) - Math.min(x, current.x);
        dy       = Math.max(y, current.y) - Math.min(y, current.y);
        position = Point(x, y);

        if dx + dy <= range;
        if x >= 0 && y >= 0 && x < this.width && y < this.height;
        if filter(position)
      )
        yield Point(x, y)
    ).toSet;
  }

  /**
   * Returns reachable tiles for an attack.
   *
   * @param character The attacker.
   * @param attack    The attack to carry.
   *
   * @return A set of attackable points.
   */
  def getReachableTiles(character : Character, attack : Attack) : Set[Point] = {
    val attackerPosition = this.objects.get(character.ID).get;

    return this.getTileOptions(
      attackerPosition, attack.range,
      p ⇒ (attack.applicableOnSelf && p == attackerPosition) || !this.positionFree(p)
    );
  }

  /**
   * Returns the move options for a given character.
   *
   * @param character The character to determine move options for.
   *
   * @return A list of points the character can go to.
   */
  def getMoveOptions(character : Character) : Set[Point] = {
    return this.objects.get(character.ID) match {
      case Some(position) ⇒ this.getTileOptions(
        position, character.range.currentValue,
        (p) ⇒ p != position && this.positionFree(p)
      );
    };
  }
}
