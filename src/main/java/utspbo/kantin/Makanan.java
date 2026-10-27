package utspbo.kantin;

public class Makanan extends Menu {// Makanan adalah turunan Menu: mewarisi data dan method milik Menu.

    public Makanan(String kodeMenu, String namaMenu, double harga, int stok) {// Constructor Makanan; datanya sama dengan Menu.
        super(kodeMenu, namaMenu, harga, stok); // Memanggil constructor Menu untuk menyimpan dan memeriksa data.
    }

    @Override // @Override = mengisi method hitungTotal milik Menu.
    public double hitungTotal(int jumlah) {
        return getHarga() * jumlah; // Total = harga x jumlah porsi.
    }
}
