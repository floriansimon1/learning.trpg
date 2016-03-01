/**
*  Author : Florian Simon <florian@tentwentyfour.lu>
*  Role   : Represents a single attack.
*/

package Game

/**
 * Represents a single attack.
 *
 * @param health The impact on the HPs and max HPs.
 * @param range  The range of the attack in number of tiles.
 */
case class Attack(health : Stat, range : Int, applicableOnSelf : Boolean);
