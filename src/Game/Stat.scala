/**
 * Author : Florian Simon <florian@tentwentyfour.lu>
 * Role   : Represents a character's characteristic.
 */

package Game

/**
 * Represents a character's characteristic.
 *
 * @param currentValue The variable value for the characteristic.
 * @param maxValue     The maximum value for the characteristic.
 */
case class Stat(currentValue : Int, maxValue : Int) {
  /**
   * Subtract two values for an attack.
   *
   * @param v The value to subtract.
   *
   * @return The updated stat.
   */
  def -(v : Stat) : Stat = {
    return Stat(this.currentValue - v.currentValue, this.maxValue - v.maxValue);
  }
}
