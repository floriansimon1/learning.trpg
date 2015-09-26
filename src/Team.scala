/**
 * Author : Florian Simon <florian@tentwentyfour.lu>
 * Role   : Case class that represents a team of players.
 */

package TRPG;

/**
 * The team case class
 *
 * @param characters IDs of the characters that compose the team.
 */
case class Team(characters : List[Int]) {
  private def getCharacters(g : Game) : List[Character] = {
    return for (c ← this.characters if g.characters.get(c) != None) yield g.characters.get(c).get;
  }

  /**
   * Returns whether or not the team is defeated.
   *
   * @param g The game instance to fetch characters from.
   *
   * @return True if the team is defeated, false otherwise.
   */
  def defeated(g : Game) : Boolean = {
    return this.getCharacters(g).forall(_.isDead);
  };
}
