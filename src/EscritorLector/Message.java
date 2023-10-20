package EscritorLector;

public class Message {
    private String message;
    private boolean empty;

    public String read(){
        empty = true;
        return message;
    }
    public void write(String message){
        this.message = message;
        empty = false;
    }
}
