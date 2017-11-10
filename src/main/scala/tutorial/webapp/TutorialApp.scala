package tutorial.webapp

import org.scalajs.jquery.{jQuery => jq}

object TutorialApp {

  def main(args: Array[String]): Unit = {
    // Variable for menu item content
    lazy val menuItemLeft : List[String] = List("Home", "Message", "Friend List")
    lazy val menuItemRight : List[String] = List("Search", "Logout")
    lazy val menuInitActive: String = "Home"

    // Build & parse menu html
    val menuItem = menuItemFormat(menuItemLeft, menuItemRight, SemanticComponent.slugify(menuInitActive))

    // Init build web
    SemanticComponent.init(menuItem)
  }
}