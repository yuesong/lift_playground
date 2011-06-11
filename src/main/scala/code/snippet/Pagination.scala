package code.snippet

import net.liftweb.http._
import net.liftweb.util.Helpers._
case class Game(id: Int, title: String, platform: String)

object Game {
  val all = List(Game(1, "Gears of War", "Xbox 360"), Game(2, "Fallout 3", "PC"), Game(3, "Little Big Planet", "PS3"),
    Game(4, "Halo 3", "Xbox 360"), Game(5, "Killzone", "PS3"), Game(6, "God of War 3", "PS3"),
    Game(7, "Call of Duty: Black Ops", "PC"))

  val count = all.size
}

class Pagination extends PaginatorSnippet[Game] {

  override def itemsPerPage = 3
  def count = Game.count
  def page = Game.all.slice(curPage * itemsPerPage, (curPage + 1) * itemsPerPage)

  def render = "tr" #> page.map { game =>
    ".id *" #> game.id & ".title *" #> game.title & ".platform *" #> game.platform
  }

}