package tutorial.webapp

import org.scalajs.dom._
import org.scalajs.dom.html.Div

import scalatags.JsDom.all._
import org.scalajs.jquery.{jQuery => jq}

case class menuItemFormat(left: List[String], right: List[String], active: String)
case class contentFormat(title: String, body: String, footer: String = "No footer Set")

object SemanticComponent {

  val wrapper: Div = {
    div(`class` := "ui stackable grid container",
      div(`class` := "centered column", id := "main-content")
    ).render
  }

  val searchContent: Div = {
    div(`class` := "ui massive icon input",
      input(`type` := "text", placeholder := "Search for something?"),
      i(`class` := "search icon")
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
    val parsedContent     = {
      div(`class` := "ui segment",
        h4(`class` := "ui header", content.title),
        p(content.body),
        hr,
        small(style := "display: block; text-align: right", content.footer)
      ).render
    }


    if(activeMenu == "search") {
      jq(".column")
        .addClass("center").addClass("aligned")
      jq("#main-content")
        .empty()
        .append(searchContent)
        .hide().fadeIn()
    } else {
      jq(".column")
        .removeClass("center").removeClass("aligned")
      jq("#main-content")
        .empty()
        .append(parsedContent)
        .hide().fadeIn()
    }
  }

  def buildNavigation(child: menuItemFormat): Div = {
      div(`class` := "ui secondary pointing menu",
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



