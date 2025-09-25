package equipamentos;

public abstract class Equipamento {
    protected String mac;
    protected String modelo;
    protected String marca;

    public Equipamento(String mac, String modelo, String marca) {
        this.mac = mac;
        this.modelo = modelo;
        this.marca = marca;
    }

    public String getMac() { return mac; }
    public String getModelo() { return modelo; }
    public String getMarca() { return marca; }

    @Override
    public String toString() {
        return "MAC: " + mac + " | Modelo: " + modelo + " | Marca: " + marca;
    }
}
