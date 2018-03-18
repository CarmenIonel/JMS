package producer;

import Connection.EndPoint;
import org.apache.commons.lang.SerializationUtils;

import java.io.IOException;
import java.io.Serializable;

public class QueueProducer extends EndPoint {

    public QueueProducer(String endPointName) throws IOException {
        super(endPointName);
    }

    public void sendMessage(Serializable object) throws IOException {
        channel.basicPublish("",endPointName, null, SerializationUtils.serialize(object));
        System.out.println("send");
    }
}