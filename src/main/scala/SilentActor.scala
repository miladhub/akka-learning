import akka.actor.{Actor, ActorRef}

object SilentActor {
  case class SilentMessage(data: String)
  case class GetState()
}

class SilentActor extends Actor {
  import SilentActor._
  var internalState = Vector[String]()
  def receive = {
    case SilentMessage(data) =>
      internalState = internalState :+ data
    case GetState => sender() ! internalState
  }

  def state = internalState
}
