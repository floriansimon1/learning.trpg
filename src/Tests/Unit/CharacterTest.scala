package Tests.Unit

/**
 * Author : Florian Simon <florian@tentwentyfour.lu>
 * Role   : Tests for the character class.
 */

import Game.{Attack, Character, Stat}
import org.scalatest.FlatSpec;

/**
 * Tests for the character class.
 */
class CharacterTest extends FlatSpec {

  it should "be able to sustain attacks" in {
    val c  = Character("", Stat(3, 3), Stat(3, 3), range = Stat(3, 3), 4, 0);
    val a  = Attack(Stat(1, 1), 1, true);
    val c2 = c.sustain(a);
    assert(c2.health.currentValue == 1 && c2.health.maxValue == 1);
  }

}
