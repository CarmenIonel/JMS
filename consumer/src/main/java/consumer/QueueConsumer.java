package consumer;

import Connection.EndPoint;
import com.rabbitmq.client.AMQP.BasicProperties;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.Envelope;
import com.rabbitmq.client.ShutdownSignalException;
import org.apache.commons.lang.SerializationUtils;
import service.MailService;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

public class QueueConsumer extends EndPoint implements Runnable, Consumer{

    private MailService mailService = new MailService("ionel.carmen1995@gmail.com","");
    private String message;
    private FileWriter fileWriter= null;
    private BufferedWriter bufferedWriter = null;
    private PrintWriter out = null;
    private String FILENAME="E:\\Facultate\\SD\\Ass32\\DVDs.txt";

    public QueueConsumer(String endPointName) throws IOException {
        super(endPointName);
    }

    public void run() {
        try {
            channel.basicConsume(endPointName, true,this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void handleConsumeOk(String consumerTag) {
        System.out.println("Consumer registered");
    }

    public void handleDelivery(String consumerTag, Envelope env,
                               BasicProperties props, byte[] body) throws IOException {
        Map map = (HashMap) SerializationUtils.deserialize(body);
        String message=map.get("dvd").toString();
        fileWriter=new FileWriter(FILENAME,true);
        bufferedWriter=new BufferedWriter(fileWriter);
        out=new PrintWriter(bufferedWriter);
        out.println(message);
        out.close();
        System.out.println(message);
        //mailService.sendMail("nimigean.mihnea@gmail.com","Dummy Mail Title",message);

    }

    public void handleCancel(String consumerTag) {}
    public void handleCancelOk(String consumerTag) {}
    public void handleRecoverOk(String consumerTag) {}
    public void handleShutdownSignal(String consumerTag, ShutdownSignalException arg1) {}
}