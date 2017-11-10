package tutorial.webapp

import org.scalajs.jquery.{jQuery => jq}

object TutorialApp {

  def main(args: Array[String]): Unit = {
    // Variable for menu item content
    lazy val menuItemLeft : List[String] = List("Home", "Message", "Friend List")
    lazy val menuItemRight : List[String] = List("Search", "Logout")
    lazy val menuInitActive: String = "Home"

    // Build & parse menu html
    val contentMenuItem = MenuItem(menuItemLeft, menuItemRight, menuInitActive)
    val component = new SemanticComponent
    val menuRender = component.topNavigation(contentMenuItem)

    val content = contentFormat("Home", "My Body content", "Footer")
    val bodyRender = component.contentWrapper(content)

    // append to root div
    val bodyContent =
      jq("#root")
        .append(menuRender)
        .append(bodyRender)

    component.pastInit(contentMenuItem)
  }
}