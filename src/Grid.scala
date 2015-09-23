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
}
