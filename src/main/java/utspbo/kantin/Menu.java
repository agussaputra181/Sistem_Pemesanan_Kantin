package utspbo.kantin;

public abstract class Menu {// Class untuk semua menu; tidak bisa dibuat objeknya langsung.
    // private = data hanya bisa diubah dari dalam class ini (enkapsulasi).

    private String kodeMenu;
    private String namaMenu;
    private double harga;// double agar harga tercetak seperti Rp15000.0.
    private int stok; // int karena stok dihitung per porsi.

    // Constructor: memeriksa lalu menyimpan data menu saat dibuat dengan "new"
    public Menu(String kodeMenu, String namaMenu, double harga, int stok) {
        if (kodeMenu == null || kodeMenu.trim().isEmpty()) {// Tolak kode yang kosong atau hanya berisi spasi.
            throw new IllegalArgumentException("Kode menu tidak boleh kosong");  // throw = hentikan proses dan munculkan error; objek batal dibuat.
        }
        if (namaMenu == null || namaMenu.trim().isEmpty()) {
            throw new IllegalArgumentException("Nama menu tidak boleh kosong");
        }
        if (harga <= 0) {// Harga harus lebih dari 0.
            throw new IllegalArgumentException("Harga harus lebih dari 0");
        }
        if (stok < 0) {// Stok boleh 0, tetapi tidak boleh minus.
            throw new IllegalArgumentException("Stok tidak boleh negatif");
        }
        // this.x = data milik objek, x = nilai dari parameter.
        this.kodeMenu = kodeMenu;
        this.namaMenu = namaMenu;
        this.harga = harga;
        this.stok = stok;
    }

    public String getKodeMenu() { // Getter: memberi nilai data ke class lain tanpa bisa diubah.
        return kodeMenu;
    }

    public String getNamaMenu() {
        return namaMenu;
    }

    public double getHarga() {
        return harga;
    }

    public int getStok() {
        return stok;
    }

    public void kurangiStok(int jumlah) throws StokHabisException {// Mengurangi stok saat pesanan berhasil; bisa memunculkan StokHabisException.
        if (jumlah <= 0) {// Tolak jumlah 0/minus agar stok tidak justru bertambah (5 - (-3) = 8).
            throw new IllegalArgumentException("Jumlah porsi harus lebih dari 0");
        }
        if (jumlah > stok) {// Stok tidak cukup: batalkan proses, stok tidak berubah.
            throw new StokHabisException("Stok " + namaMenu + " tinggal " + stok
                    + ", tidak cukup untuk " + jumlah + " porsi");
        }
        stok = stok - jumlah; // Stok cukup: kurangi stok.
    }

    public String getKeteranganBiaya() { // Keterangan biaya tambahan; bawaannya null (Makanan tidak punya biaya tambahan).
        return null;
    }

    public abstract double hitungTotal(int jumlah); // Method tanpa isi; wajib diisi tiap turunan karena rumus totalnya berbeda.
}
