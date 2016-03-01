/**
 * Author : Florian Simon <florian@tentwentyfour.lu>
 * Role   : The canvas that shows the game grid.
 */

package Views.Swing.Components;

import Game.{Team, Grid, Facts};

import java.awt.Graphics2D;
import scala.swing.{Panel, Color, Dimension};

/**
 * Constants for drawing the grid.
 */
private object GridCanvas {
  val backgroundColor = new Color(103, 128, 159);
  val separatorsColor = new Color(93, 118, 149);
}

/**
 * The actual canvas.
 */
case class GridCanvas(teams : Map[Int, Team], grid : Grid) extends Panel {
  /* Dimensions of a single tile. */
  lazy val tileWidth  = this.size.getWidth.toInt / Facts.gridSize.x;
  lazy val tileHeight = this.size.getHeight.toInt / Facts.gridSize.y;

  /* The background color of the canvas. */
  this.background = GridCanvas.backgroundColor;

  /**
   * Draws entities on the canvas.
   */
  def drawEntities(g : Graphics2D, grid : Grid, teams : Iterable[Team]) = {
    g.setColor(new Color(255, 0, 0));

    this.teams.foreach({ case (_, team) ⇒ {
      /* Sets the color once for the whole team. */
      g.setColor(team.color);

      /* Draws all characters of a team. */
      team.characters.foreach(character ⇒ {
        grid.objects.get(character).map(position ⇒ {
          g.fillRect(position.x * tileWidth, position.y * tileHeight, tileWidth, tileHeight);
        });
      });
    } });
  }

  /**
   * Draws grid lines on the canvas.
   *
   * @param g    The graphics object on which we do the painting.
   * @param size The size of the canvas.
   */
  def drawSeparators(g : Graphics2D, size : Dimension) = {
    g.setColor(GridCanvas.separatorsColor);

    for (
      i ← 0 to Facts.gridSize.x - 1;
      j ← 0 to Facts.gridSize.y - 1;

      x = i * tileWidth;
      y = j * tileHeight
    ) {
      g.drawRect(x, y, tileWidth, tileHeight);
    }
  }

  /**
   * @inheritdoc
   */
  override def paintComponent(g : Graphics2D) = {
    /* Draws the background */
    super.paintComponent(g);

    /* Draws the entities. */
    this.drawEntities(g, this.grid, this.teams.values);

    /* Draws the grid cells separators. */
    this.drawSeparators(g, this.size);
  }
}
