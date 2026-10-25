package utspbo.kantin;

import java.util.Scanner; // Memakai Scanner untuk membaca ketikan dari keyboard.

public class AplikasiKantin { // Program utama; method main dijalankan pertama kali
        
    // Konstanta: static = milik class, final = tidak bisa diubah.    
    private static final String NAMA = "PutuAgusSaputra";
    private static final String NIM = "2501010063";
    private static final boolean NIM_VALID = NIM.matches("\\d{10}");
    private static final String XYZ = NIM_VALID ? NIM.substring(NIM.length() - 3) : "000"; // 3 angka terakhir NIM untuk kode menu/pesanan; "000" jika NIM tidak valid.
    private static final boolean NIM_GANJIL = NIM_VALID
            && Integer.parseInt(NIM.substring(NIM.length() - 2)) % 2 == 1;
        }
    private static final Scanner INPUT = new Scanner(System.in);
    private static final Menu[] DAFTAR_MENU = {
        new Makanan("MK-" + XYZ, "Nasi Goreng", 15000, 5),
        new Minuman("MN-" + XYZ, "Es Teh", 5000, 10, 2000),
        NIM_GANJIL ? new Dessert("DS-" + XYZ, "Es Krim", 12000, 4, 3000)
                   : new Snack("SN-" + XYZ, "Keripik", 8000, 4, 1000)
    };
    public static void main(String[] args) { // NAMA kosong: tampilkan pesan lalu berhenti.
        if (NAMA.trim().isEmpty()) {
            System.out.println(">> NAMA belum diisi. Isi konstanta NAMA di "
                    + "AplikasiKantin.java.");
            // Keluar dari main (program berhenti).
            return;
        }
        if (!NIM_VALID) {  // NIM tidak valid (! = tidak): tampilkan pesan lalu berhenti.
            System.out.println(">> NIM \"" + NIM + "\" tidak valid: harus 10 digit "
                    + "angka, tanpa huruf/spasi (contoh: 2501010063).");
            System.out.println(">> Perbaiki konstanta NIM di AplikasiKantin.java "
                    + "lalu Run ulang.");
            return;
        }
        System.out.println("UTS PBO | " + NAMA + " | " + NIM);
        tampilDaftarMenu();   // Menampilkan daftar menu.
         Pesanan pesanan = null;
         while (pesanan == null) {
             try {
                  int nomor = bacaInt("Pilih menu (nomor) : ");
                   while (nomor < 1 || nomor > DAFTAR_MENU.length) {
                    System.out.println(">> Nomor menu tidak tersedia, pilih 1 sampai "
                            + DAFTAR_MENU.length + ".");
                    nomor = bacaInt("Pilih menu (nomor) : ");
                }
                 Menu menuDipilih = DAFTAR_MENU[nomor - 1];
                 int jumlah = bacaInt("Jumlah porsi       : ");
                 pesanan = new Pesanan("PSN-" + XYZ, NAMA, menuDipilih, jumlah);
             } catch (StokHabisException e) {
                 System.out.println(">> Pesanan ditolak: " + e.getMessage());
             } catch (IllegalArgumentException e) {
                 System.out.println(">> Data pesanan tidak valid: " + e.getMessage())");
             } finally{
                  System.out.println("[Log] Pengisian pesanan selesai.");
             }
         }
         
         // Pesanan berhasil: cetak detailnya.
        pesanan.cetakDetail();
        // Kalimat penutup program.
        System.out.println("Selesai dijalankan oleh " + NAMA + " (" + NIM + ")");
    }
     private static String bacaTeks(String label) { // Membaca teks dari keyboard dan menolak isian kosong.)
         String teks = "";
          while (teks.isEmpty()) {
             System.out.print(label);
             teks = INPUT.nextLine().trim();
             if (teks.isEmpty()) {
                System.out.println(">> Input tidak boleh kosong, coba lagi.");
          }
        }
        return teks;
     }

         private static int bacaInt(String label){
             while (true) {
                 String teks = bacaTeks (label);
                 try{
                      return Integer.parseInt(teks);
                 }  catch. (NumberFormatException e) {
                     System.out.println(">>Input harus berupa angka bulat, coba lagi.);
                     
                 }
             }
         }
          private static void tampilDaftarMenu() {
                   System.out.println("=== DAFTAR MENU KANTIN ===");
                   for (int i = 0; i < DAFTAR_MENU.length; i++) {
          Menu m = DAFTAR_MENU[i];
          String baris = (i + 1) + ". " + m.getNamaMenu() + " (" + m.getKodeMenu()
                  +")" |  Rp" + m.getHarga();"
           if (m.getKeteranganBiaya() != null) {
                baris += " + " + m.getKeteranganBiaya();
            }
            System.out.println(baris + " | stok " + m.getStok());
        }
    }
}
               
           
          
         