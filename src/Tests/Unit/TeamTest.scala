/**
 * Author : Florian Simon <florian@tenwentyfour.lu>
 * Role   : Tests the team class.
 */

package Tests.Unit;

import Game._;
import org.scalatest.FlatSpec;

import scala.swing.Color;
import scala.util.Random;

/**
 * Team class test.
 */
class TeamTest extends FlatSpec {

  it should "report a team with dead characters as defeated" in {
    val gr = Grid(width = 5, height = 5, Map());
    val r = Random;
    val m = Nil;
    val c = Character("", Stat(3, 3), Stat(3, 3), range = Stat(3, 3), 4, 0);
    val cs = Map(c.ID → Point(1, 2));
    val t = Team(new Color(0, 0, 0)).withCharacters(List(c.ID));
    val g = Game(gr, r, m, Map((t.ID, t)), Map());
    assert(t.defeated(g));
  }

}
