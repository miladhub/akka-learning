import MyActor._
import akka.actor.{ActorSystem, Props}
import akka.testkit.{ImplicitSender, TestKit}
import org.scalatest._

import scala.concurrent.Await
import scala.concurrent.duration._

class MyActorSpec(_system: ActorSystem)
  extends TestKit(_system)
    with WordSpecLike
    with ImplicitSender
    with Matchers
    with BeforeAndAfterAll {

  def this() = this(ActorSystem("AkkaTcpRpg"))

  override def afterAll: Unit = {
    Await.result(system.terminate(), 10.seconds)
  }

  "matching with traits" must {
    "m1 -> mymsgs" in {
      val myactor = system.actorOf(Props[MyActor])
      myactor ! Msg1()
      expectMsg("mymsgs")
    }
    "m2 -> mymsg" in {
      val myactor = system.actorOf(Props[MyActor])
      myactor ! Msg2
      expectMsg("mymsgs")
    }
    "m3 -> msg3" in {
      val myactor = system.actorOf(Props[MyActor])
      myactor ! Msg3()
      expectMsg("msg3")
    }
    "foo -> smth else" in {
      val myactor = system.actorOf(Props[MyActor])
      myactor ! "foo"
      expectMsg("smth else")
    }
    "msg4 -> msg4" in {
      val myactor = system.actorOf(Props[MyActor])
      myactor ! Msg4
      expectMsg("msg4")
    }
  }
}
