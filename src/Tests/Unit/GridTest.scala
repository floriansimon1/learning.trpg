/**
 * Author : Florian Simon <florian@tentwentyfour.lu>
 * Role   : Tests for the Grid class.
 */

package Tests.Unit

import Game.{Character, Grid, Point, Stat}
import org.scalatest.FlatSpec;

/**
 * Tests for the grid class.
 */
class GridTest extends FlatSpec {
  val c  = Character("", Stat(3, 3), Stat(3, 3), range = Stat(3, 3), 4, 0);
  val c2 = Character("", Stat(3, 3), Stat(3, 3), range = Stat(3, 3), 4, 0);
  val m  = Map((c.ID → Point(1, 2)), (c2.ID → Point(1, 3)));
  val g  = Grid(width = 5, height = 5, objects = m);

  it should "report available positions for a character" in {
    val expectedOptions = Set(
      Point(0, 4), Point(0, 3), Point(0, 2), Point(0, 1),
      Point(1, 4), Point(1, 1), Point(1, 0),
      Point(2, 4), Point(2, 3), Point(2, 2), Point(2, 1),
      Point(3, 4), Point(3, 3), Point(3, 2),
      Point(4, 3)
    );

    assert(g.getMoveOptions(c2) == expectedOptions);
  }

  it should "move characters from one point to another" in {
    val g2 = Grid(width = 5, height = 5, objects = Map((c.ID → Point(1, 1)), (c2.ID → Point(1, 3))));
    assert(g.move(c.ID, Point(1, 1)).objects == g2.objects);
  }

}
