package Views

import Controllers.Controller;

/**
 * Author : Florian Simon <florian@tentwentyfour.lu>
 * Role   : Describes an interface for the game.
 */
trait Interface {
  /**
   * Provides the requested view..
   *
   * @param controller The controller to attach the view to.
   */
  def provide(controller : Controller) : View;
}
