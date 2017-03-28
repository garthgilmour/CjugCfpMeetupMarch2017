package demos.akka.http.model

final case class Trainer(val name: String,
                         val rate: Double,
                         val skills : List[String]) {
}
