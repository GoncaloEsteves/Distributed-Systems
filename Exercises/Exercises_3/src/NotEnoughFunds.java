public class NotEnoughFunds extends Exception{
    public NotEnoughFunds(){
        super();
    }
    public NotEnoughFunds(String msg){
        super(msg);
    }
}
