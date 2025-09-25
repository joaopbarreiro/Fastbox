package relatorio;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Relatorio {
    private int idTecnico;
    private String nomeTecnico;
    private LocalDateTime dataHora;

    public Relatorio(int idTecnico, String nomeTecnico) {
        this.idTecnico = idTecnico;
        this.nomeTecnico = nomeTecnico;
        this.dataHora = LocalDateTime.now();
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        return "Relatório -> Técnico ID: " + idTecnico +
                ", Nome: " + nomeTecnico +
                ", Data/Hora: " + dataHora.format(formatter);
    }
}
