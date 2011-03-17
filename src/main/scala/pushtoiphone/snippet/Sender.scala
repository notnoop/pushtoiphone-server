package com.judeapps.pushtoiphone
package snippet

import _root_.scala.xml.{NodeSeq, Text}
import _root_.net.liftweb.util._
import _root_.net.liftweb.common._
import _root_.net.liftweb.http._
import _root_.java.util.Date
import com.judeapps.pushtoiphone.lib._
import Helpers._

class Sender {
  lazy val date: Box[Date] = DependencyFactory.inject[Date] // inject the date
  val pusher = new IPhonePusher(None)

  object userName extends SessionVar[String]("")
  object password extends SessionVar[String]("")

  def render = {
    var text = ""

    def process() {

      val status = pusher.pushMessageToUserById(text, userName.is, password.is)

      import Status._
      val message = status match {
        case OK => S.notice("Mesage Sent")
        case NO_TOKENS => S.warning("User has no associated iPhones")
        case NO_USER => S.warning("No user found: " + userName.is)
      }
    }

    "name=text" #> SHtml.textarea(text, text = _) &
    "name=email" #> SHtml.text(userName.is, userName(_)) &
    "name=password" #> SHtml.password(password.is, password(_)) &
    "type=submit" #> SHtml.onSubmitUnit(process)
  }
}
