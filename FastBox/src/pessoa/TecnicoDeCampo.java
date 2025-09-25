package pessoa;

import equipamentos.Equipamento;

public class TecnicoDeCampo extends Pessoa {
    private String telefone;
    private int id;

    public TecnicoDeCampo(String nome, int idade, String cpf, String telefone, int id) {
        super(nome, idade, cpf);
        this.telefone = telefone;
        this.id = id;
    }

    public int getId() { return id; }
    public String getTelefone() { return telefone; }

    public void devolverEquipamento(Equipamento eq) {
        System.out.println("Técnico " + nome + " devolveu o equipamento: " + eq.getModelo());
    }
}
