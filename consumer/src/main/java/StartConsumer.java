import consumer.QueueConsumer;

public class StartConsumer {

    public static void main(String[] args) throws Exception {
        QueueConsumer consumer = new QueueConsumer("queue");
        Thread consumerThread = new Thread(consumer);
        consumerThread.start();
    }
}
