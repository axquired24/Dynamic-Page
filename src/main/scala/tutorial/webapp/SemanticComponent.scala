package tutorial.webapp

import scalatags.JsDom.all._
import org.scalajs.jquery.{jQuery => jq}

case class MenuItem(left: List[String], right: List[String], active: String)
case class contentFormat(title: String, body: String, footer: String = "No footer Set")

class SemanticComponent {

  def pastInit(child: MenuItem): Unit = {
    val cslug = slugify(child.active)
    jq(s"#$cslug").addClass("active")
  }

  def toggleActive(elm:String, position:String) = {
    jq(s"#$elm").addClass("active")
    jq(s"#$elm").siblings().removeClass("active")

    position match {
      case "left" =>
        jq(s"#$elm").siblings().children("a").removeClass("active")
      case "right" =>
        jq(s"#$elm").parent().siblings("a").removeClass("active")
    }

    val writeContent = new ContentMgr()
    contentWrapper(writeContent.selectContent(elm))

  }

  def topNavigation(child: MenuItem) = {
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

  def contentBody(content: contentFormat) = {
    div(`class` := "ui segment",
      h4(`class` := "ui header", content.title),
      p(content.body),
      hr,
      small(style := "display: block; text-align: right", content.footer)
    )
  }

  def contentWrapper(content: contentFormat) = {
    div(`class` := "ui stackable grid container",
      div(`class` := "column", contentBody(content))
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



