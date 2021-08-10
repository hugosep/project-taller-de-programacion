package taller

import org.apache.activemq.ActiveMQConnectionFactory

import javax.jms.{Message, MessageListener, Session, TextMessage}

class host extends Runnable{


  def getDataYProcesar(): Unit = {
    var contador = 0
    val cFactory = new ActiveMQConnectionFactory("tcp://localhost:61616?jms.prefetchPolicy.queuePrefetch=1")
    val connection = cFactory.createConnection()
    connection.start()

    val session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE)
    val queue = session.createQueue("mqDistribuir")

    val consumer = session.createConsumer(queue)


    println("Host iniciado...\n")

    val listener = new MessageListener {

      def onMessage(message: Message): Unit = {
        message match {
          case text: TextMessage => {
            val slicedStr = text.getText.split(",")
            println("El mensaje que llego fue nombre: "+slicedStr(0)+ " - genero: "+ slicedStr(1)+ " - autor: "+slicedStr(2))
            contador+=1
            if(contador == 10){
              consumer.setMessageListener(null)
              System.exit(0)
            }

          }
          case _ => throw new Exception()
        }
      }
    }
    consumer.setMessageListener(listener)


  }
  override def run(): Unit ={
    getDataYProcesar()
  }
}

object hostApp{
  def main(args: Array[String]): Unit= {
    println("Para comenzar a leer ingrese 's' ")
    var input = ""
    while(!(input == "s")) {
      input = scala.io.StdIn.readLine()
    }
    val hostjob = new host
    hostjob.getDataYProcesar()
    //hostjob.getDataYProcesar("mqDistribuir")
    /*
    for (x <- 1 to 2){
      var th = new Thread(new host())
      println("Numero:" + x.toString())
      th.setName(x.toString())
      th.start()
      hostjob.getDataYProcesar("mqDistribuir")
    }
    */

  }
}
