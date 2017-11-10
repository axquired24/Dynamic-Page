package tutorial.webapp

import org.scalajs.dom.html._
import scalatags.JsDom.all._
import org.scalajs.jquery.{jQuery => jq}

object ContentMgr {
  def selectContent(activeMenu:String): Div = {
    activeMenu match {
      case "home" =>
        val content = defContentFormat("Home", "If you empower or convert with a separate anger, surrender studies you. One must follow the saint in order to view the visitor of strange zen.")
        setDefaultContent(content)
      case "friend-list" =>
        val content = defContentFormat("Friend List", "Uh Oh! Let's make friend first. Remember: roasted chocolate tastes best when flattened in a saucepan enameled with rosemary.")
        setDefaultContent(content)
      case "message" =>
        setMessageContent()
      case "search" =>
        setSearchContent()
      case "logout" =>
        val content = defContentFormat("Logout", "This feature is in development. Xiphiass sunt cobaltums de dexter adelphis. Ignigenas mori in alter vasa!")
        setDefaultContent(content)
      case _ =>
        val content = defContentFormat("NOT FOUND", "Opps you are in wrong place.", "-Go Back")
        setDefaultContent(content)
    }
  }

  def setDefaultContent(content: defContentFormat) : Div = {
    val defaultContent : Div = {
      div(`class` := "ui segment",
        h4(`class` := "ui header", content.title),
        p(content.body),
        hr,
        small(style := "display: block; text-align: right", content.footer)
      ).render
    }
    jq(".column").removeClass("center").removeClass("aligned")
    defaultContent
  }

  def setSearchContent(): Div = {
    val searchContent = {
      div(`class` := "ui large icon input",
        input(`type` := "text", placeholder := "Search for something?"),
        i(`class` := "search icon")
      ).render
    }
    jq(".column").addClass("center").addClass("aligned")
    searchContent
  }

  def setMessageContent(): Div = {
    val messageElm : List[msgContentFormat] =
      List(
        msgContentFormat("https://semantic-ui.com/images/avatar/large/stevie.jpg", "Albert Septiawan", "Try covering the coffee chicken lards with sliced joghurt and rice vinegar, boilled."),
        msgContentFormat("https://semantic-ui.com/images/avatar/large/veronika.jpg", "Andi Yuni", "Core is not unbiased in upstairs, the sincere mind, or space, but everywhere."),
        msgContentFormat("https://semantic-ui.com/images/avatar/large/jenny.jpg", "Loremi", "Why does the cockroach rise? Arrr, warm beauty! Silence studies when you facilitate with shame.")
      )

    val messageContent = {
      div(`class` := "ui stackable segment",
        h4(`class` := "ui header", "Messages"),
        div(`class` := "ui link items",
          {
            messageElm.map { m =>
              div(`class` := "item",
                div(`class` := "ui tiny image",
                  img(src := m.image)
                ),
                div(`class` := "content",
                  div(`class` := "header", m.header),
                  div(`class` := "description",
                    p(m.description)
              )))
            }
          }
        )).render
    }
    messageContent
  }
}
