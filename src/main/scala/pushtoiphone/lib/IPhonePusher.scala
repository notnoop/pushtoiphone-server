package com.judeapps.pushtoiphone
package lib

import model.Citation
import model.User

import com.notnoop.apns._

import _root_.net.liftweb.util._
import _root_.net.liftweb.common._
import _root_.net.liftweb.mapper._

object Status extends Enumeration {
  type Status = Value

  val OK = Value
  val NO_USER = Value
  val NO_TOKENS = Value
}

class IPhonePusher(apnsService: Box[ApnsService]) {
  import Status._

  def pushMessageToUser(text: String, user: User): Status = {
    val message = "You received text: " + text

    val payload = APNS.newPayload().
        alertBody(message).
        sound("default").
        customField("text", text).
        build

    val citation = Citation.create.sender(user).content(text).saveMe
    println("MessageID: " + citation.id)

    for (apns <- apnsService) {
         apns.push(user.iphoneToken.is, payload)
     }
     OK
  }

  def pushMessageToUserById(text: String, userName: String, password: String) = {

    val user = User.find(By(User.email, userName))
    println(user.get.password.match_?("312"))

    if (user.isDefined && user.get.password.match_?(password)) {
        pushMessageToUser(text, user.get)
        OK
    } else {
      NO_USER
    }
  }
}
