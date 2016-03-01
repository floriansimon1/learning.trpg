/**
 * Trait implemented by all of the application entry points,
 * the real one as well as the test mocks.
 */

import Views.Swing.SwingInterface;
import Controllers.Controller;
import Views.Interface;

/**
 * Trait common to all entry points of the application.
 */
trait EntryPoint {
  /**
   * The interface type used by the entry point.
   */
  val interface : Interface;

  /**
   * The first controller of the program.
   */
  val firstStep : Option[Controller];

  /**
   * Executes a single prgram step and returns the next one if any.
   *
   * @param interface The view provider.
   * @param step      The step to execute.
   *
   * @return The next step to execute, if any.
   */
  def nextStep(interface : Interface, step : Option[Controller]) : Option[Controller] = {
    return step match {
      case None             ⇒ None;
      case Some(controller) ⇒ interface.provide(controller).show();
    }
  }

  /**
   * The main method.
   *
   * @param args Unused command line parameters.
   */
  def main (args : Array[String]) : Unit = {
    val step : Option[Controller] = firstStep;

    /* Defines a recursive stream representing the successive steps of the program. */
    lazy val flow : Stream[Option[Controller]] = step #:: flow.map(this.nextStep(interface, _));

    /* Makes the successive steps of our program execute. */
    flow.takeWhile(_ != None).force;
  }
}