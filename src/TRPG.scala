/**
 * Author : Florian Simon <florian@tentwentyfour.lu>
 * Role   : The entry point.
 */

import Views.Interface;

import Views.Swing.SwingInterface;
import Controllers.{Controller, MainMenu};

/**
 * Our main class.
 */
object TRPG extends EntryPoint {
  override val interface = new SwingInterface();
  override val firstStep = Some(new MainMenu());
}
