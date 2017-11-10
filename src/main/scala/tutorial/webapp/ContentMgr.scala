package tutorial.webapp

class ContentMgr extends SemanticComponent {
  def selectContent(page:String) = {
    page match {
      case "home" =>
        contentFormat("Home", "If you empower or convert with a separate anger, surrender studies you. One must follow the saint in order to view the visitor of strange zen.")
      case "friend-list" =>
        contentFormat("Friend List", "Uh Oh! Let's make friend first. Remember: roasted chocolate tastes best when flattened in a saucepan enameled with rosemary.")
      case "message" =>
        contentFormat("Message", "Opps. Nothing to see here. Start chat to see list of message. The holy life of enlightenment is to love with blessing.", "Last login: 24 September 2017 08:11:33")
      case "search" =>
        contentFormat("Search" ,"What are you searching for? Nothing here. Eh, Virtually manifest an admiral.", "-Nothing Found")
      case "logout" =>
        contentFormat("Logout", "This feature is in development. Xiphiass sunt cobaltums de dexter adelphis. Ignigenas mori in alter vasa!")
    }
  }
}
