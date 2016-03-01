/**
 * Author : Florian Simon <florian@tentwentyfour.lu>
 * Role   : Alternate entry point that spawns a preconfigured game
 *          (used for testing).
 */

import Game.{Character, Facts, Game, Grid, Point, Stat, Team};
import Views.Swing.SwingInterface;
import Controllers.GameView;
import scala.swing.Color;
import scala.util.Random;

/**
 * Main object that starts a game directly without any manual configuration.
 */
object PreconfiguredGame extends EntryPoint {
  /**
   * Generates a new, pre-configured game.
   */
  def makeGame : Game = {
    /* Creates the teams without players. */
    val emptyDelta = Team(new Color(255, 0, 0));
    val emptyGamma : Team = Team(new Color(0, 0, 255));

    /* Members of the first team. */
    lazy val carl  = Character("Carl", Stat(20, 20), Stat(20, 20), Stat(20, 20), 0, emptyDelta.ID);
    lazy val mark  = Character("Mark", Stat(20, 20), Stat(20, 20), Stat(20, 20), 0, emptyDelta.ID);
    lazy val lucy  = Character("Mark", Stat(20, 20), Stat(20, 20), Stat(20, 20), 0, emptyDelta.ID);
    lazy val sara  = Character("Sara", Stat(20, 20), Stat(20, 20), Stat(20, 20), 0, emptyDelta.ID);

    /* Members of the second team. */
    lazy val lara  = Character("Lara", Stat(20, 20), Stat(20, 20), Stat(20, 20), 0, emptyGamma.ID);
    lazy val gina  = Character("Gina", Stat(20, 20), Stat(20, 20), Stat(20, 20), 0, emptyGamma.ID);
    lazy val kile  = Character("Kile", Stat(20, 20), Stat(20, 20), Stat(20, 20), 0, emptyGamma.ID);
    lazy val gwen  = Character("Gwen", Stat(20, 20), Stat(20, 20), Stat(20, 20), 0, emptyGamma.ID);

    /* The teams themselves. */
    val delta = emptyDelta.withCharacters(List(carl.ID, mark.ID, lucy.ID, sara.ID));
    val gamma = emptyGamma.withCharacters(List(lara.ID, gina.ID, kile.ID, gwen.ID));

    /* The game grid. */
    val grid = Grid(Facts.gridSize.x, Facts.gridSize.y, Map(
      /* Placement of the delta team. */
      (carl.ID, Point(0, 13)), (mark.ID, Point(0, 12)), (lucy.ID, Point(0, 14)), (sara.ID, Point(1, 13)),

      /* Placement of the gamma team. */
      (lara.ID, Point(24, 13)), (gina.ID, Point(24, 12)), (kile.ID, Point(24, 14)), (gwen.ID, Point(23, 13))
    ));

    /* The index of all characters. */
    val characters = Map(
      /* Characters of team delta. */
      (carl.ID, carl), (mark.ID, mark), (lucy.ID, lucy), (sara.ID, sara),

      /* Characters of team gamma. */
      (lara.ID, lara), (gina.ID, gina), (kile.ID, kile), (gwen.ID, gwen)
    );

    val game = Game(grid, Random, Nil, Map((delta.ID, delta), (gamma.ID, gamma)), characters);

    return game;
  }

  override val interface = new SwingInterface();
  override val firstStep = Some(new GameView(this.makeGame));
}
