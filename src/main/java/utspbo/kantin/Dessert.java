package utspbo.kantin;

public class Dessert extends Menu { // Paket NIM ganjil: menu Dessert, turunan Menu.
    private double biayaTopping;
    
    public Dessert(String kodeMenu, String namaMenu, double harga, int stok,
            double biayaTopping) {
    super(kodeMenu, namaMenu, harga, stok);
    if (biayaTopping < 0) {
            throw new IllegalArgumentException("Biaya topping tidak boleh negatif");
    }
    this.biayaTopping = biayaTopping;
}
@Override
public double hitungTotal(int jumlah) {
        return (getHarga() * jumlah) + (biayaTopping * jumlah);
}
public double getBiayaTopping() {
        return biayaTopping;
}
 @Override
    public String getKeteranganBiaya() {
        return "topping Rp" + biayaTopping + " per porsi";
    }
}