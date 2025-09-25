package principal;

import pessoa.TecnicoDeCampo;
import equipamentos.*;
import relatorio.Relatorio;
import java.util.*;

public class SistemaDevolucao {
    private static Scanner sc = new Scanner(System.in);
    private static List<TecnicoDeCampo> tecnicos = new ArrayList<>();
    private static List<Equipamento> equipamentos = new ArrayList<>();
    private static List<Relatorio> relatorios = new ArrayList<>();

     public static void main(String[] args) {
    int opcao;
    do {
        System.out.println("\n=== Sistema de Devolução ===");
        System.out.println("1 - Cadastrar Técnico");
        System.out.println("2 - Cadastrar Equipamento");
        System.out.println("3 - Registrar Devolução");
        System.out.println("4 - Listar Relatórios");
        System.out.println("5 - Excluir Técnico"); // <- NOVA OPÇÃO
        System.out.println("0 - Sair");
        System.out.print("Escolha: ");
        opcao = sc.nextInt();
        sc.nextLine();

        switch (opcao) {
            case 1 -> cadastrarTecnico();
            case 2 -> cadastrarEquipamento();
            case 3 -> registrarDevolucao();
            case 4 -> listarRelatorios();
            case 5 -> excluirTecnico();
            case 0 -> System.out.println("Encerrando...");
            default -> System.out.println("Opção inválida!");
        }
    } while (opcao != 0);
}

    private static void cadastrarTecnico() {
        System.out.print("Nome: ");
        String nome = sc.nextLine();
        System.out.print("Idade: ");
        int idade = sc.nextInt();
        sc.nextLine();
        System.out.print("CPF: ");
        String cpf = sc.nextLine();
        System.out.print("Telefone: ");
        String tel = sc.nextLine();
        System.out.print("ID do Técnico: ");
        int id = sc.nextInt();
        sc.nextLine();

        tecnicos.add(new TecnicoDeCampo(nome, idade, cpf, tel, id));
        System.out.println("Técnico cadastrado com sucesso!");
    }

    private static void cadastrarEquipamento() {
        System.out.println("Tipo de Equipamento: 1-ONU | 2-Roteador | 3-Decoder");
        int tipo = sc.nextInt();
        sc.nextLine();
        System.out.print("MAC: ");
        String mac = sc.nextLine();
        System.out.print("Modelo: ");
        String modelo = sc.nextLine();
        System.out.print("Marca: ");
        String marca = sc.nextLine();

        Equipamento eq = switch (tipo) {
            case 1 -> new ONU(mac, modelo, marca);
            case 2 -> new Roteador(mac, modelo, marca);
            case 3 -> new Decoder(mac, modelo, marca);
            default -> null;
        };

        if (eq != null) {
            equipamentos.add(eq);
            System.out.println("Equipamento cadastrado com sucesso!");
        } else {
            System.out.println("Tipo inválido!");
        }
    }

 private static void registrarDevolucao() {
    if (tecnicos.isEmpty() || equipamentos.isEmpty()) {
        System.out.println("É necessário ter pelo menos um técnico e um equipamento cadastrado!");
        return;
    }

    // Selecionar técnico
    System.out.println("Selecione o técnico pelo ID:");
    for (TecnicoDeCampo t : tecnicos) {
        System.out.println("ID: " + t.getId() + " | Nome: " + t.getNome());
    }
    int id = sc.nextInt();
    sc.nextLine();

    TecnicoDeCampo tecnico = tecnicos.stream()
            .filter(t -> t.getId() == id)
            .findFirst()
            .orElse(null);

    if (tecnico == null) {
        System.out.println("Técnico não encontrado!");
        return;
    }

    // Exibir equipamentos disponíveis
    System.out.println("Equipamentos disponíveis:");
    for (int i = 0; i < equipamentos.size(); i++) {
        Equipamento eq = equipamentos.get(i);
        System.out.println(i + " - " + eq); // ou use getMac(), getModelo(), etc.
    }

    System.out.print("Escolha o número do equipamento a ser devolvido: ");
    int escolha = sc.nextInt();
    sc.nextLine();

    if (escolha < 0 || escolha >= equipamentos.size()) {
        System.out.println("Escolha inválida!");
        return;
    }

    // Remover equipamento escolhido
    Equipamento escolhido = equipamentos.remove(escolha);
    tecnico.devolverEquipamento(escolhido);
    relatorios.add(new Relatorio(tecnico.getId(), tecnico.getNome()));

    System.out.println("Equipamento devolvido com sucesso!");
}


    private static void listarRelatorios() {
        if (relatorios.isEmpty()) {
            System.out.println("Nenhum relatório encontrado.");
        } else {
            relatorios.forEach(System.out::println);
        }
    }
    private static void excluirTecnico() {
    if (tecnicos.isEmpty()) {
        System.out.println("Nenhum técnico cadastrado.");
        return;
    }

    System.out.println("Lista de Técnicos:");
    for (TecnicoDeCampo t : tecnicos) {
        System.out.println("ID: " + t.getId() + " | Nome: " + t.getNome());
    }

    System.out.print("Digite o ID do técnico que deseja excluir: ");
    int id = sc.nextInt();
    sc.nextLine(); // limpar o buffer

    boolean removido = tecnicos.removeIf(t -> t.getId() == id);

    if (removido) {
        System.out.println("Técnico removido com sucesso!");
    } else {
        System.out.println("Técnico com ID " + id + " não encontrado.");
    }
}

}
