package tutorial.webapp

import org.scalajs.dom._
import org.scalajs.dom.html.Div

import scalatags.JsDom.all._
import org.scalajs.jquery.{jQuery => jq}

object SemanticComponent {

  val wrapper: Div = {
    div(`class` := "ui stackable grid container",
      div(`class` := "column", id := "main-content")
    ).render
  }

  def init(menu: menuItemFormat): Unit = {
    // parse & append navigation menu
    val navigationMenu = buildNavigation(menu)
    jq("#root").append(navigationMenu)

    // parse & append wrapper
    jq("#root").append(this.wrapper)

    // parse & set default home content
    setContent(menu.active)

    // Past Init at the end of init
    afterInit(menu)
  }

  def afterInit(child: menuItemFormat): Unit = {
    val cslug = slugify(child.active)
    jq(s"#$cslug").addClass("active")
  }

  def toggleActive(activeMenu:String, position:String) : Unit = {
    jq(s"#$activeMenu").addClass("active")
    jq(s"#$activeMenu").siblings().removeClass("active")

    position match {
      case "left" =>
        jq(s"#$activeMenu").siblings().children("a").removeClass("active")
      case "right" =>
        jq(s"#$activeMenu").parent().siblings("a").removeClass("active")
    }

    // reparse & re-set content
    setContent(activeMenu)
  }

  def setContent(activeMenu: String): Unit = {
    val content       = ContentMgr.selectContent(activeMenu)
    jq("#main-content")
      .empty()
      .append(content)
      .hide().fadeIn()
  }

  def buildNavigation(child: menuItemFormat): Div = {
      div(`class` := "ui secondary pointing menu", style := "overflow: auto",
      {
        child.left.map {c =>
          val cslug = slugify(c)
          a(`class` := "navigation item", id := cslug, c, onclick := (() => toggleActive(cslug, "left")) )
        }
      },
      div(`class` := "right menu", {
        child.right.map {c =>
          val cslug = slugify(c)
          a(`class` := "navigation item", id := cslug, c, onclick := (() => toggleActive(cslug, "right")) )
        }
      })
      ).render
  }

  def slugify(input: String): String = {
      input
      .replaceAll("[^\\w\\s-]", "") // Remove all non-word, non-space or non-dash characters
      .replace('-', ' ')            // Replace dashes with spaces
      .trim                         // Trim leading/trailing whitespace (including what used to be leading/trailing dashes)
      .replaceAll("\\s+", "-")      // Replace whitespace (including newlines and repetitions) with single dashes
      .toLowerCase                  // Lowercase the final results
  }

}

case class menuItemFormat(left: List[String], right: List[String], active: String)
case class defContentFormat(title: String, body: String, footer: String = "No footer Set")
case class msgContentFormat(image: String, header: String, description: String)
