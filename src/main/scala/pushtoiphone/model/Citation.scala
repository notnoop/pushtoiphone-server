package com.judeapps.pushtoiphone
package model

import _root_.net.liftweb.mapper._
import _root_.net.liftweb.common._
import _root_.net.liftweb.util._

object Citation extends Citation with LongKeyedMetaMapper[Citation]

class Citation extends LongKeyedMapper[Citation] with IdPK with CreatedUpdated {
  def getSingleton = Citation

  object sender extends LongMappedMapper(this, User)

  object content extends MappedString(this, 255)
}
