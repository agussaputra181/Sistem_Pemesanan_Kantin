package utspbo.kantin;
public class Pesanan {// Menyimpan satu pesanan, memeriksanya, mengurangi stok, dan mencetak detailnya.
    
    private String kodePesanan;
    private String namaPemesan;
    private Menu menu;// Bertipe Menu agar bisa menyimpan jenis menu apa saja.
    private int jumlah;
    
    // Memeriksa pesanan lalu mengurangi stok.
    // throws: error stok habis dari kurangiStok diteruskan ke pemanggil (main).
    public Pesanan(String kodePesanan, String namaPemesan, Menu menu, int jumlah)
            throws StokHabisException {
         if (menu == null) { // Cek 1: menu harus dipilih.
            throw new IllegalArgumentException("Menu belum dipilih");
         }
         if (jumlah <= 0) { // Cek 2: jumlah porsi minimal 1.
            throw new IllegalArgumentException("Jumlah porsi harus lebih dari 0");
         }
          
           // Cek 3: stok; dicek terakhir agar stok berkurang hanya jika cek 1-2 lolos.
         menu.kurangiStok(jumlah);
         this.kodePesanan = kodePesanan;
         this.namaPemesan = namaPemesan;
         this.menu = menu;
         this.jumlah = jumlah;
    }
    public double hitungTotal() {  // Total bayar memakai rumus milik jenis menu yang dipesan
        return menu.hitungTotal(jumlah);
    }
    
    public void cetakDetail() { // Mencetak detail pesanan ke layar.
        double subtotal = menu.getHarga() * jumlah; // Subtotal = harga x jumlah, tanpa biaya tambahan.
        double biayaTambahan = hitungTotal() - subtotal;// Biaya tambahan = total - subtotal (kemasan, topping, saus, atau 0).
        String keterangan = menu.getKeteranganBiaya(); // Keterangan biaya; null untuk Makanan.
        
         // Mencetak teks ke jendela Output lalu pindah baris.
        System.out.println("--- DETAIL PESANAN ---");
        System.out.println("Kode pesanan       : " + kodePesanan);
        System.out.println("Pemesan            : " + namaPemesan);
        System.out.println("Menu               : " + menu.getNamaMenu() + " ("
                + menu.getKodeMenu() + ") x" + jumlah);
        
        System.out.println("Harga satuan       : Rp" + menu.getHarga());
        System.out.println("Subtotal           : Rp" + subtotal + " (harga x jumlah)");
        if (keterangan == null) { // Tanpa keterangan: cetak nominalnya saja.
            System.out.println("Biaya tambahan : Rp" + biayaTambahan);
        } else { // Ada keterangan: cetak nominal beserta keterangannya.
            System.out.println("Biaya tambahan : Rp" + biayaTambahan
                 + " (" + keterangan + ")");
}
        System.out.println("Total bayar        : Rp" + hitungTotal());
        System.out.println("Stok tersisa       : " + menu.getStok());
        System.out.println("-----------------------");
    }   
}
