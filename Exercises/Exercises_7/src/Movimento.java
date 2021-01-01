public class Movimento {
    private int id_operacao;
    private String descricao;
    private double valor;
    private double saldo;

    public Movimento(int id, String d, double v, double s){
        this.id_operacao = id;
        this.descricao = d;
        this.valor = v;
        this.saldo = s;
    }
}
