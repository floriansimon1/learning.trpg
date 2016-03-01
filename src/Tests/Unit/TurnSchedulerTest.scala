/**
 * Author : Florian Simon <florian@tenwentyfour.lu>
 * Role   : Tests for the game's turn atribution mechanism.
 */

package Tests.Unit;

import Game._;
import org.scalatest.FlatSpec;

import scala.swing.Color;
import scala.util.Random;

/**
 * Team class test.
 */
class TurnSchedulerTest extends FlatSpec {

  it should "let the player that has the most action points play if there's no tie" in {
    val gr = Grid(width = 5, height = 5, Map());
    val r = Random;
    val m = Nil;
    val c1 = Character("", Stat(3, 3), Stat(3, 3), range = Stat(3, 3), 4, 0);
    val c2 = Character("", Stat(3, 3), Stat(3, 3), range = Stat(3, 3), 5, 0);
    val cs = Map(c1.ID → Point(1, 2), c2.ID → Point(1, 3));
    val cl = Map(c1.ID → c1, c2.ID → c2);
    val t = Team(new Color(0, 0, 0)).withCharacters(List(c1.ID, c2.ID));
    val g = Game(gr, r, m, Map((t.ID, t)), cl);
    assert(g.nextTurn(4) == c2.ID);
  }

  it should "let the player from the team that's played first play if there's a tie between two teams" in {
    val gr = Grid(width = 5, height = 5, Map());
    val r = Random;
    val m = Nil;
    val c1 = Character("", Stat(3, 3), Stat(3, 3), range = Stat(3, 3), 4, 0);
    val c2 = Character("", Stat(3, 3), Stat(3, 3), range = Stat(3, 3), 4, 0);
    val cs = Map(c1.ID → Point(1, 2), c2.ID → Point(1, 3));
    val cl = Map(c1.ID → c1, c2.ID → c2);
    val t = Team(new Color(0, 0, 0)).withCharacters(List(c1.ID, c2.ID));
    val g = Game(gr, r, m, Map((t.ID, t)), cl).copy(moves = List(Move(c1.ID, None, None, 0)));
    assert(g.nextTurn(4) == c2.ID);
  }

}
