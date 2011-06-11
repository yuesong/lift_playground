package code.snippet

import net.liftweb.http._
import net.liftweb.util.Helpers._
import java.lang.IllegalArgumentException

case class Game(id: Int, title: String, platform: String)

object Game {
  val all = List(Game(1, "Gears of War", "Xbox 360"), Game(2, "Fallout 3", "PC"), Game(3, "Little Big Planet", "PS3"),
    Game(4, "Halo 3", "Xbox 360"), Game(5, "Killzone", "PS3"), Game(6, "God of War 3", "PS3"),
    Game(7, "Call of Duty: Black Ops", "PC"))

  val count = all.size
}

class UnsortedPagination extends PaginatorSnippet[Game] {

  def count = Game.count

  def page = Game.all.slice(curPage * itemsPerPage, (curPage + 1) * itemsPerPage)

  override def itemsPerPage = 3

  def render = "tr" #> page.map { game =>
    ".id *" #> game.id & ".title *" #> game.title & ".platform *" #> game.platform
  }

}

class SortedPagination extends SortedPaginatorSnippet[Game, String] {

  def count = Game.count

  def page = {
    val sorted = Game.all.sortWith( (g1, g2) => {
        val (v1, v2) = if (sort._2) (g1, g2) else (g2, g1)
        headers(sort._1)._2 match {
          case "id" => v1.id < v2.id
          case "title" => v1.title < v2.title
          case "platform" => v1.platform < v2.platform
          case x => throw new IllegalArgumentException("Invalide sort: " + x)
        }
      }
    )
    sorted.slice(curPage * itemsPerPage, (curPage + 1) * itemsPerPage)
  }

  override def itemsPerPage = 3

  def headers = List("id", "title", "platform").map(s => (s, s))

  def render = "tr" #> page.map { game =>
    ".id *" #> game.id & ".title *" #> game.title & ".platform *" #> game.platform
  }


}