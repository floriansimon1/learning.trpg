/**
 * Author : Florian Simon <florian@tentwentyfour.lu>
 * Role   : Textual version of the main menu view.
 */

package Views.Swing;

import Game.Grid;
import Views.Base;
import Controllers.Controller;
import Views.Swing.Components.GridCanvas;

import scala.util.Success;
import scala.swing.event.WindowClosing;
import scala.concurrent.duration.Duration;
import scala.swing.{MainFrame, Dimension};
import scala.concurrent.{Await, Promise, Future};
import scala.concurrent.ExecutionContext.Implicits.global;

/**
 * The main menu view.
 */
case class GameView(controller : Controllers.GameView) extends Base.GameView {
  val viewTerminated = Promise[Option[Controller]]();
  val gridCanvas     = new GridCanvas(controller.game.teams, controller.game.grid);

  /**
   * @inheritdoc
   */
  override def gridUpdated(grid : Grid) = {
    gridCanvas.repaint();
  };

  /**
   * @inheritdoc
   */
  override def show() : Option[Controller] = {
    Future {
      this.setupWindow;
    };

    return Await.result(viewTerminated.future, Duration.Inf);
  }

  /**
   * Does the window init and shows it.
   *
   * @return Unit
   */
  def setupWindow = (new MainFrame {
    /* Configures the window; */
    this.minimumSize = new Dimension(800, 800);
    this.title       = "Test window";
    this.contents    = gridCanvas;

    /* Main window events. */
    this.reactions += {
      /* When the menu window is closed, terminates the app. */
      case WindowClosing(_) ⇒ viewTerminated.complete(Success(None));
    }
  }).open;
}
