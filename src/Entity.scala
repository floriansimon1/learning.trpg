/**
 * Author : Florian Simon <florian@tentwentyfour.lu>
 * Role   : Defines entities and related types.
 */

object Entity {
  private var generatedEntities : Int = 0;

  /**
   * Generates a new, unattributed entity ID.
   *
   * @return The newly generated integer.
   */
  def generateID() : Int = {
    this.synchronized {
      Entity.generatedEntities += 1;
      return Entity.generatedEntities;
    }
  }
}

/**
 * Represents a physical game entity.
 */
class Entity() {
  val ID : Int = Entity.generateID();

  override def equals(o : Any) : Boolean = {
    return o match {
      case e : Entity ⇒ e.ID == this.ID;
      case _          ⇒ false;
    }
  }
}
