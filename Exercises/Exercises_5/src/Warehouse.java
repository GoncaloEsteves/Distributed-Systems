import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Warehouse {
    private Map<String, Item> itens;
    private ReentrantLock lock;
    private Map<String, Condition> estado;

    public Warehouse(){
        this.itens = new HashMap<>();
        this.estado = new HashMap<>();
        this.lock = new ReentrantLock();
    }

    public void supply(String item, int quant){
        this.lock.lock();
        if(!this.itens.containsKey(item)){
            this.itens.put(item, new Item());
            this.estado.put(item, this.lock.newCondition());
        }
        this.itens.get(item).supply(quant);
        this.estado.get(item).signal();
        this.lock.unlock();
    }

    public void consume(String it[]) throws InterruptedException {
        this.lock.lock();
        int i;
        for(i = 0; i < it.length; i++){
            while (!(this.itens.containsKey(it[i])))
                this.estado.get(it[i]).await();
            this.itens.get(it[i]).consume();
        }
        this.lock.unlock();
    }
}
