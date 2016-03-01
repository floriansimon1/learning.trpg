/**
 * Author : Florian Simon <florian@tentwentyfour.lu>
 * Role   : Case class that represents a game of TRPG.
 */

package Game

import scala.util.Random;

/**
 * The game case class.
 *
 * @param grid       The game's map.
 * @param randomizer The game's randomizer.
 * @param teams      The teams the game opposes.
 * @param moves      The successive moves that result in the current game state.
 * @param characters The list of characters of the game.
 */
case class Game(
  grid       : Grid,
  randomizer : Random,
  moves      : List[Move],
  teams      : Map[Int, Team],
  characters : Map[Int, Character]
) {
  /**
   * Gets characters by ID.
   *
   * @param characters The IDs to look for.
   *
   * @return List[Character]
   */
  def getCharacters(characters : List[Int]) : List[Character] = {
    return for (c ← characters if this.characters.get(c) != None) yield this.characters.get(c).get;
  }

  /**
   * Returns whether or not the game is over.
   *
   * @return True if the game is over, false otherwise.
   */
  def over() : Boolean = {
    return teams.exists({ case (_, team) ⇒ team.defeated(this) });
  }

  /**
   * Returns the next character to play.
   *
   * @param randomNumber A random number used in case we have ties which allows us to choose
   *                     one team/player.
   */
  def nextTurn(randomNumber : Int) : Int = {
    /* We're going to iterate over the characters. */
    val sortedCharacters = this.characters.toList

    /* Extracts the characters out of the map pairs. */
    .map({ case (_, character) ⇒ character })

    /* Takes the highest speed values. */
    .sortWith((a, b) ⇒ a.actionPoints > b.actionPoints);

    /* Regroups tied highest AP values, keeping only their IDs. */
    val candidates = sortedCharacters
    .filter(_.actionPoints == sortedCharacters.head.actionPoints)
    .map(_.ID)
    .toSet;

    /* If we've narrowed it down to one character, we return that character. */
    if (candidates.size == 1) {
      return candidates.head;
    }

    /*
    * Otherwise, we try to make the character/team that's been waiting the most play.
    * This initializes the last played counter for teams.
    */
    var teamCount = this.teams
    .map({ case (teamID, _) ⇒ (teamID, 0) })
    .filter({ case (teamID, _) ⇒ candidates.exists(this.characters.get(_).get.team == teamID) });

    /* Same for the characters. */
    var characterCount = this.characters
    .map({ case (characterID, _) ⇒ (characterID, 0) })
    .filter({ case (characterID, _) ⇒ candidates.contains(characterID) });

    /* Calculates the counts. */
    println(this.moves);
    this.moves.zipWithIndex.foreach({ case (move, i) ⇒ {
      characterCount = characterCount + (move.character → i);
      teamCount      = teamCount + (this.characters.get(move.character).get.team → i);
    } });

    /* Function that takes a count map and choose a key that has the smallest counts. */
    def selectLowestValueKey(countMap : Map[Int, Int]) : Int = {
      if (countMap.head == 1) {
        return countMap.head._1;
      } else {
        val minimum    = countMap.min._2;
        val candidates = countMap.filter({ case (key, count) ⇒ count == minimum }).keys.toArray;

        return candidates(randomNumber % candidates.size);
      }
    }

    /* If some teams have not yet played, we choose a random one. */
    val selectedTeam = this.teams.get(selectLowestValueKey(teamCount)).get;

    /*
    * Returns the ID of the character in the selected
    * team that has not played for the most time.
    */
    return selectLowestValueKey(characterCount.filter({
      case (characterID, _) ⇒ selectedTeam.characters.contains(characterID);
    }));
  }

  /**
   * Takes a move, applies it on a new game instance and
   * returns that new instance.
   *
   * @param move The move to apply.
   *
   * @return The new instance.
   */
  def applyMove(move : Move) : Game = {
    /* Moves the character. */
    val newGrid = move.newPosition match {
      case Some(p) ⇒ this.grid.move(move.character, p);
      case _       ⇒ this.grid
    };

    /* Applies the attack to its target. */
    val characters = move.attack match {
      case Some(a) ⇒ this.characters + (a.target → this.characters.get(a.target).get.sustain(a.attack));
      case _       ⇒ this.characters
    };

    /* Returns the updated game state, adding the move to the moves list. */
    return this.copy(
      grid       = newGrid,
      characters = characters,
      moves      = move :: this.moves
    );
  }
}
