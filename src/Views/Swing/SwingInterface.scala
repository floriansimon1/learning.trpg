/**
 * Author : Florian Simon <florian@tentwentyfour.lu>
 * Role   : The textual interface.
 */

package Views.Swing

import Controllers.Controller;
import Views.Interface;
import Views.View;

/**
 * The textual interface.
 */
class SwingInterface extends Interface {
  override def provide(controller : Controller) : View = {
    return controller match {
      case gameViewController : Controllers.GameView ⇒ new GameView(gameViewController);
    };
  }
}
