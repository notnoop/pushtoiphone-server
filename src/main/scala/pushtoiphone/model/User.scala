package com.judeapps.pushtoiphone
package model

import _root_.net.liftweb.mapper._

import _root_.net.liftweb.common._
import _root_.net.liftweb.util._

object User extends User with KeyedMetaMapper[Long, User]

class User extends ProtoUser[User] {
  def getSingleton = User

  object iphoneToken extends MappedString(this, 100)
}
