package code.snippet

import net.liftweb.http._
import js.JsCmds.SetHtml
import net.liftweb.util.Helpers._
object Memoize {
  
  def dynamic = SHtml.memoize("#dynamic *+" #> now.toString)

//  def update = "* [onclick]" #> SHtml.ajaxInvoke(() => SetHtml("dynamic", dynamic.applyAgain()))
//  def update = "*" #> SHtml.a(() => SetHtml("dynamic", dynamic.applyAgain()), Text("Update"))
  def update = "*" #> SHtml.ajaxButton("Update", () => SetHtml("dynamic", dynamic.applyAgain()))
}
