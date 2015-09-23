/**
*  Author : Florian Simon <florian@tentwentyfour.lu>
*  Role   : Represents a single attack.
*/

package TRPG;

case class Attack(
  strength : Stat,
  actor    : Character,
  victim   : Option[Character]
);
