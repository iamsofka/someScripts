import java.util.PriorityQueue;
import java.util.Queue;

public class MessageQueue {

    private final Queue<Message> priorityQueue;

    public MessageQueue() {
        priorityQueue = new PriorityQueue<>();
    }

    public synchronized void queueOffer(Message m) {
        priorityQueue.offer(m);
    }

    public synchronized Request getRequestFromQueue() {
        Request request = null;

//        Message message = priorityQueue.peek();
//        if (message instanceof Request) {
        request = (Request) priorityQueue.poll();
//        }

        return request;
    }

    public synchronized Response getResponseFromQueue(Requestor requestor) {
        if (priorityQueue.peek() instanceof Response && priorityQueue.peek().getRequestor() == requestor) {
            return (Response) priorityQueue.poll();
        }
        return null;
    }


    @Override
    public synchronized String toString() {
        return priorityQueue.toString();
    }
}