package code.model

import net.liftweb.common.Box

case class Game(id: Int, title: String, platform: String)

object Game {
  val all = List(
    Game(1, "Gears of War", "Xbox 360"),
    Game(2, "Fallout 3", "PC"),
    Game(3, "Little Big Planet", "PS3"),
    Game(4, "Halo 3", "Xbox 360"),
    Game(5, "Killzone", "PS3"),
    Game(6, "God of War 3", "PS3"),
    Game(7, "Call of Duty: Black Ops", "PC"))

  def count = all.size

  def find(id: Int) = all.find(_.id == id)
}

