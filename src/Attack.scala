/**
 * Created by florian on 22/09/15.
 */
case class Attack(
  strength : Stat,
  actor    : Character,
  victim   : Option[Character]
);
