import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;

public class Banco {
    private int N;
    private Map<Integer, Conta> contas;
    private ReentrantLock lockBanco;

    public Banco(){
        this.N = 0;
        this.contas = new HashMap<>();
        this.lockBanco = new ReentrantLock();
    }

    public double consultar(int i) {
        return this.contas.get(i).consulta();
    }

    public int levantar(int i, int x) {
        return this.contas.get(i).levanta(x);
    }

    public void depositar(int i, int x) {
        this.contas.get(i).deposita(x);
    }

    public void transfer(int from, int to, int amount) throws InvalidAccount, NotEnoughFunds{
        int max = Math.max(from, to);
        int min = Math.min(from, to);
        synchronized (this.contas.get(min)) {
            synchronized (this.contas.get(max)) {
                int aux = this.levantar(from, amount);
                if (aux == 0)
                    throw new NotEnoughFunds();
                else {
                    this.depositar(to, amount);
                }
            }
        }
    }

    public int createAccount(double initialBalance){
        this.lockBanco.lock();
        int id = this.N;
        this.contas.put(id, new Conta(initialBalance));
        this.N++;
        this.lockBanco.unlock();
        return (id - 1);
    }

    public double closeAccount(int id) throws InvalidAccount{
        double bal = -1;
        this.lockBanco.lock();
        if(this.contas.containsKey(id)){
            Conta aux = this.contas.get(id);
            aux.fechar();
            bal = aux.consulta();
            this.contas.remove(id);
            this.lockBanco.unlock();
        }
        else{
            this.lockBanco.unlock();
            throw new InvalidAccount("ID " + " invalido.");
        }
        return bal;
    }

    public double totalBalance(int accounts[]){
        double aux = 0;
        List<Integer> clocked = new ArrayList<>(accounts.length);
        this.lockBanco.lock();
        for(int i = 0; i < accounts.length; i++){
            if(this.contas.containsKey(accounts[i])) {
                this.contas.get(accounts[i]).fechar();
                clocked.add(accounts[i]);
            }
        }
        this.lockBanco.unlock();
        for(Integer i : clocked){
            aux += this.contas.get(i).consulta();
            this.contas.get(i).abrir();
        }
        return aux;
    }
}