package code.snippet

import net.liftweb.util.Helpers._
import code.model.Game
import xml.Text
import net.liftweb.http.{RequestVar, SHtml, S}
import net.liftweb.common.{Box, Failure}

object CreateEditForm {

  object gameVar extends RequestVar[Box[Game]](g_param)
  object idVar extends RequestVar[Int](dflt(g => g.id, 0))
  object titleVar extends RequestVar[String](dflt(g => g.title, "New Title"))

  private def dflt[T](f: (Game) => T, d: T): T = gameVar.get.map(f) openOr d

  private def g_param = for {
      s <- S.param("id")
      id <- asInt(s) ?~! (s + " is not a valid ID")
      g <- Box(Game.find(id)) ?~! ("ID " + id + " cannot be found")
    } yield g

  def render = {
    gameVar.get match {
      case Failure(msg, _, _) => "*" #> Text(msg)
      case x => {
        val id = idVar.get
        "#title" #> (SHtml.textElem(titleVar) ++ SHtml.hidden(()=>idVar(id))) &
        "#save" #> SHtml.onSubmitUnit(process)
      }
    }
  }

  private def process() {
    if (titleVar.get.trim().isEmpty()) {
      S.error("Title is missing")
    }
    if (S.errors.isEmpty) {
      S.notice(titleVar + " saved")
      S.redirectTo(S.uri + (if (idVar.get != 0) "?id=" + idVar else ""))
    }
  }

}