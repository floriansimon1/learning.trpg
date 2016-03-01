/**
 * Author : Florian Simon <florian@tentwentyfour.lu>
 * Role   : Represents an attack that has been carried..
 */

package Game

/**
 * Represents an attack that has been carried.
 *
 * @param attack Details about the attack that has been carried.
 * @param target The ID of the attacked character.
 */
case class CarriedAttack(attack : Attack, target : Int);
