package taller

import org.apache.activemq.ActiveMQConnectionFactory

import javax.jms.{Message, MessageListener, Session, TextMessage}

class appDB {

  def procesaMensaje(): Unit = {
    val cFactory = new ActiveMQConnectionFactory()
    val connection = cFactory.createConnection()
    connection.start()

    val session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE)
    val queue = session.createQueue("myQueue")

    val consumer = session.createConsumer(queue)

    val listener = new MessageListener {
      def onMessage(message: Message): Unit = {
        message match {
          case text: TextMessage => {
            //println(s"Mensaje en cola myQueue: ${text.getText}\n")
            val slicedStr = text.getText.split(",")
            addData(new Song(slicedStr(0), slicedStr(1), slicedStr(2)))
          }
          case _ => throw new Exception()
        }
      }
    }
    consumer.setMessageListener(listener)
  }

  def addData(data: Song): Int = {
    try {
      ServicioDB.add(data)
    } catch {
      case _: Exception => -1
    }
  }

  def extraccion(): Boolean = {
    try {
      ServicioExtraccion.obtenerYEnviar()
    } catch {
      case _: Exception => false
    }

  }
}

object DB{
  def main(args: Array[String]): Unit= {
    val proc = new appDB
    proc.procesaMensaje()
    proc.extraccion()
  }
}
