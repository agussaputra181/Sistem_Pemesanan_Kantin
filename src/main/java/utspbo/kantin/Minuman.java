package utspbo.kantin;

public class Minuman extends Menu {// Minuman adalah turunan Menu, ditambah biaya kemasan.

    private double biayaKemasan;// Biaya kemasan, ditagih sekali per pesanan.

    public Minuman(String kodeMenu, String namaMenu, double harga, int stok,
            double biayaKemasan) {
        super(kodeMenu, namaMenu, harga, stok);

        if (biayaKemasan < 0) { // Biaya kemasan tidak boleh minus.
            throw new IllegalArgumentException("Biaya kemasan tidak boleh negatif");
        }
        this.biayaKemasan = biayaKemasan;
    }

    @Override
    public double hitungTotal(int jumlah) {

        return (getHarga() * jumlah) + biayaKemasan; // Total = (harga x jumlah) + kemasan; kemasan tidak dikali jumlah.
    }

    public double getBiayaKemasan() {
        return biayaKemasan;
    }

    @Override
    public String getKeteranganBiaya() {// Mengganti keterangan bawaan (null) dengan keterangan kemasan.

        return "kemasan Rp" + biayaKemasan + " sekali per pesanan"; // Hasil: "kemasan Rp2000.0 sekali per pesanan".
    }
}
