import MyActor.{Msg3, Msg4, MyMsgs}
import akka.actor.Actor

object MyActor {
  sealed trait MyMsgs
  case class Msg1() extends MyMsgs
  case object Msg2 extends MyMsgs
  case class Msg3()
  case object Msg4
}

class MyActor extends Actor {
  def receive = {
    case msg: MyMsgs =>
      sender() ! "mymsgs"
    case msg: Msg3 =>
      sender() ! "msg3"
    case Msg4 =>
      sender() ! "msg4"
    case _ =>
      sender() ! "smth else"
  }
}

