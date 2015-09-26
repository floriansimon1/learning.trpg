/**
 * Author : Florian Simon <florian@tenwentyfour.lu>
 * Role   : Tests the team class.
 */

package TRPG.Tests;

import org.scalatest.FlatSpec;
import scala.util.Random;

import TRPG._
;

/**
 * Team class test.
 */
class TeamTest extends FlatSpec {

  it should "report a team with dead characters as defeated" in {
    val gr = Grid(width = 5, height = 5, Map());
    val r = Random;
    val m = Nil;
    val c = Character("", Stat(3, 3), Stat(3, 3), range = Stat(3, 3), 4);
    val cs = Map(c.ID → Point(1, 2));
    val t = Team(List(c.ID));
    val g = Game(gr, r, List(t), m, Map());
    assert(t.defeated(g));
  }

}
