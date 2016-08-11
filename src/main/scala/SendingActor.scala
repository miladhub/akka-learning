import akka.actor.{Actor, ActorRef, Props}

object SendingActor {
  def props(receiver: ActorRef) =
    Props(new SendingActor(receiver))
  case class Event(id: Int)
  case class SortEvents(events: Seq[Event])
  case class SortedEvents(events: Seq[Event])
}

class SendingActor(receiver: ActorRef) extends Actor {
  import SendingActor._

  def receive = {
    case SortEvents(unsorted) =>
      receiver ! SortedEvents(unsorted.sortBy(_.id))
  }
}
