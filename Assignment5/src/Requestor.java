public class Requestor extends Participant{

    private MessageQueue responceQueue;

    public Requestor(MessageQueue queue) {
        super(queue);
        responceQueue = new MessageQueue();
    }

    @Override
    public void run() {
        while (true){
            Request request = new Request(this);
            getMessageQueue().queueOffer(request);
            System.out.println("REQUEST OFFERED " + request);
            boolean consumed = false;
            while (!consumed){
                Response response = responceQueue.getResponseFromQueue(this);
                if(response != null){
                    System.out.println("RECEIVED BY " + response);
                    //responceQueue.queueOffer(response);
                    consumed = true;
                }
                sleep();
            }
        }
    }

    public MessageQueue getResponceQueue(){
        return responceQueue;
    }
}