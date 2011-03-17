package com.judeapps.pushtoiphone
package rest

import _root_.net.liftweb.common._
import _root_.net.liftweb.util.BasicTypesHelpers._

import net.liftweb.mapper._

import net.liftweb.http._
import net.liftweb.http.rest._

import net.liftweb.json._
import net.liftweb.json.JsonDSL._

import model.{Citation, User}

object PusherApi extends RestHelper {

  serve {
    case r @ Req("api" :: "user" :: Nil, _, PostRequest) => {
      for (
        username <- r.param("email") ?~ "Email parameter missing" ~> 400;
        password <- r.param("password") ?~ "Password parameter missing" ~> 400;
        token <- r.param("token") ?~ "IPhone Token parameter missing" ~> 400
      ) yield {
        println("Password: " + password)
        val user = User.create.
            email(username).
            password(password).
            iphoneToken(token)

        val validations = user.validate

        if (validations.isEmpty) {
          user.save
          (("status" -> 0) ~ ("message" -> "User created") ~ ("user_id" -> user.id.is))
        } else {
          (Empty ?~ "Test" ~> 400).map(_ + "m")
        }
      }
    }

    case JsonGet("api" :: "citation" :: AsLong(id) :: Nil, _) => {

      for (c <- Citation.find(By(Citation.id, id)) ?~
        "\"Citation not found\"" ~> 404) yield {
        (("id" -> c.id.is) ~ ("text" -> c.content.is))
      }
    }
  }
}
