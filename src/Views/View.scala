package Views

import Controllers.Controller;

/**
 * Author : Florian Simon <florian@tentwentyfour.lu>
 * Role   : Describes the contract for a view.
 */
trait View {
  /* The Controller subtype to which this view is attached. */
  type ControllerSubtype;

  /**
   * The controller that acts on this view.
   */
  val controller : ControllerSubtype;

  /**
   * Shows the view, and returns the next controller, if any.
   */
  def show() : Option[Controller];
}
