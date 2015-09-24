/**
 * Author : Florian Simon <florian@tentwentyfour.lu>
 * Role   : Tests for the Grid class.
 */

package Tests;

import TRPG.Stat;
import TRPG.Grid;
import TRPG.Point;
import TRPG.Character;
import org.scalatest.FlatSpec;

/**
 * Tests for the grid class.
 */
class GridTest extends FlatSpec {

  it should "report available positions for a character" in {
    val c  = Character("", Stat(3, 3), Stat(3, 3), Stat(3, 3), 4);
    val c2 = Character("", Stat(3, 3), Stat(3, 3), Stat(3, 3), 4);
    val m  = Map((c.ID → Point(1, 1)), (c2.ID → Point(2, 2)));
    val g  = Grid(width = 5, height = 5, objects = m);

    var expectedOptions = Set(
      Point(0, 2),
      Point(1, 2), Point(1, 3),
      Point(2, 0), Point(2, 1), Point(2, 3), Point(2, 4),
      Point(3, 1), Point(3, 2), Point(3, 3),
      Point(4, 2)
    );

    assert(g.getMoveOptions(c2).getOrElse(Set()) == expectedOptions);
  }

}
