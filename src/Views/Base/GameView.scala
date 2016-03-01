/**
 * Author : Florian Simon <florian@tentwentyfour.lu>
 * Role   : Abstract main menu view.
 */

package Views.Base;

import Game.Grid;
import Views.View;

trait GameView extends View {
  override type ControllerSubtype = Controllers.GameView;

  /**
   * Callback called whenever the game grid gets updated.
   *
   * @param grid The new grid to display.
   */
  def gridUpdated(grid : Grid);
}
