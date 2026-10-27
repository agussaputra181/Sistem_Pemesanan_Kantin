package utspbo.kantin;
// Paket NIM genap: menu Snack, turunan Menu.

public class Snack extends Menu {

    // Biaya saus, ditagih per porsi.
    private double biayaSaus;

    public Snack(String kodeMenu, String namaMenu, double harga, int stok,
            double biayaSaus) {
        super(kodeMenu, namaMenu, harga, stok);
        if (biayaSaus < 0) {
            throw new IllegalArgumentException("Biaya saus tidak boleh negatif");
        }
        this.biayaSaus = biayaSaus;
    }

    @Override
    public double hitungTotal(int jumlah) {
        return (getHarga() + biayaSaus) * jumlah; // Total = (harga + saus) x jumlah; saus per porsi.
    }

    public double getBiayaSaus() {
        return biayaSaus;
    }

    @Override
    public String getKeteranganBiaya() {
        return "saus Rp" + biayaSaus + " per porsi";
    }
}
