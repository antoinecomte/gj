/*
 * Copyright © 2014 Antoine Comte
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import akka.actor.{ActorSystem, ActorRef}
import gj.metric.Metric
import gj.{ActorSystemProvider, MetricProvider}
import org.scalatest.FunSpec
import org.scalatest.matchers.MustMatchers
import scala.concurrent.Future
import spray.http._
import spray.httpx.unmarshalling._

import spray.httpx.encoding.Gzip
import spray.json.JsObject
import spray.testkit.ScalatestRouteTest
import ui.UIServerRoute

/**
 *
 */
class UiServerSpec extends FunSpec with ScalatestRouteTest with UIServerRoute with MustMatchers with MetricProvider with ActorSystemProvider {
  def actorRefFactory = system

  val `text/html(UTF8)` = ContentType(MediaTypes.`text/html`, HttpCharsets.`UTF-8`)

  describe("the UI server") {
    it("should serve the index html file") {
      Get() ~> routes ~> check {
        contentType must equal(`text/html(UTF8)`)
        Gzip.decode(response).as[String].right.get must include("<!doctype html>")
      }

    }

    it("should serve the list of active buckets") {
      Get("/api/buckets") ~> routes ~> check {
        contentType must equal(ContentTypes.`application/json`)
        import spray.json._
        import DefaultJsonProtocol._
        import spray.httpx.unmarshalling._
        import spray.httpx.SprayJsonSupport._


     //   val value: List[JsObject] = Gzip.decode(response).as[List[JsObject]].right.get
       // value must equal (List.empty[JsObject])
      }

    }
  }

  override def unSubscribe(metric: Metric, receiver: ActorRef): Unit = ???

  override def subscribe(metric: Metric, receiver: ActorRef): Unit = ???

  /**
   * List all known metrics
   * @return a Future that will complete with the known metrics
   */
  override def listMetrics: Future[Iterable[Metric]]  = Future.successful(Iterable.empty[Metric])

  override def actorSystem: ActorSystem = system
}
