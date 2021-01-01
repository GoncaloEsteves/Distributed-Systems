import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;

public class Banco {
    private Map<Integer, Conta> contas;
    private ReentrantLock lock;

    public Banco(int quantos){
        this.lock = new ReentrantLock();
        this.contas = new HashMap<>();
        for(int i = 0; i < quantos; i++)
            this.criarConta(0);
    }

    public int criarConta(double saldo){
        this.lock.lock();
        int id = this.contas.size();
        this.contas.put(id, new Conta(saldo));
        this.lock.unlock();
        return id;
    }

    public double fecharConta(int id) throws ContaInvalida{
        this.lock.lock();
        double aux;

        if(this.contas.containsKey(id)) {
            aux = this.contas.get(id).getSaldo();
            this.contas.get(id).lock();
            this.contas.get(id).setClosed(true);
            this.contas.get(id).unlock();
            this.contas.remove(id);
            this.lock.unlock();
        }
        else {
            this.lock.unlock();
            throw new ContaInvalida();
        }

        return aux;
    }

    public synchronized double consultar(int id) throws ContaInvalida{
        if(!(this.contas.containsKey(id)))
            throw new ContaInvalida();
        this.contas.get(id).lock();
        double aux = this.contas.get(id).getSaldo();
        this.contas.get(id).unlock();
        return aux;
    }

    public double consultarTotal(int[] contasInput) throws ContaInvalida {
        double aux = 0;
        for(int i = 0; i < contasInput.length; i++){
            aux += this.consultar(contasInput[i]);
        }
        return aux;
    }

    public void levantar(int id, double valor) throws SaldoInsuficiente, ContaInvalida{
        this.lock.lock();
        if(!this.contas.containsKey(id)) {
            this.lock.unlock();
            throw new ContaInvalida();
        }
        else{
            this.contas.get(id).levantar(valor);
            this.lock.unlock();
        }
    }

    public void depositar(int id, double valor) throws ContaInvalida{
        this.lock.lock();
        if(!this.contas.containsKey(id)){
            this.lock.unlock();
            throw new ContaInvalida();
        }
        else{
            this.contas.get(id).depositar(valor);
            this.lock.unlock();
        }
    }

    public void transferir(int co, int cd, double valor) throws ContaInvalida, SaldoInsuficiente{
        this.levantar(co, valor);
        this.depositar(cd, valor);
    }

    public List<Movimento> movimentos(int id) throws ContaInvalida{
        List<Movimento> aux = new ArrayList<>();
        return aux;
    }
}
