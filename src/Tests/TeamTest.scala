/**
 * Author : Florian Simon <florian@tenwentyfour.lu>
 * Role   : Tests the team class.
 */

package TRPG.Tests;

import org.scalatest.FlatSpec;

import TRPG.Character;
import TRPG.Stat;
import TRPG.Team;

/**
 * Team class test.
 */
class TeamTest extends FlatSpec {

  it should "report a team with dead characters as defeated" in {
    val t = new Team(List(new Character(
      name         = "",
      health       = new Stat(0, 3),
      speed        = new Stat(3, 3),
      range        = new Stat(3, 3),
      actionPoints = 0
    )));

    assert(t.defeated());
  }

}
