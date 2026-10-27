package utspbo.kantin;
// Memakai Scanner untuk membaca ketikan dari keyboard.
import java.util.Scanner;
// Program utama; method main dijalankan pertama kali saat Run (F6).
public class AplikasiKantin {
    // Konstanta: static = milik class, final = tidak bisa diubah. GANTI dengan data Anda.
    private static final String NAMA = "Mahasiswa Contoh";
    private static final String NIM = "2501010063";
    // true jika NIM tepat 10 angka (tanpa huruf/spasi).
    private static final boolean NIM_VALID = NIM.matches("\\d{10}");
    // 3 angka terakhir NIM untuk kode menu/pesanan; "000" jika NIM tidak valid.
    private static final String XYZ = NIM_VALID ? NIM.substring(NIM.length() - 3) : "000";
    // true jika 2 digit terakhir NIM ganjil (% 2 = sisa bagi 2).
    private static final boolean NIM_GANJIL = NIM_VALID
        && Integer.parseInt(NIM.substring(NIM.length() - 2)) % 2 == 1;
    // Satu Scanner dipakai bersama; System.in = input keyboard.
    private static final Scanner INPUT = new Scanner(System.in);
    // Daftar menu (array bertipe Menu); urutannya menjadi nomor pilihan 1, 2, 3.
    private static final Menu[] DAFTAR_MENU = {
        // Membuat menu Makanan dengan kode MK- + 3 digit NIM.
        new Makanan("MK-" + XYZ, "Nasi Goreng", 15000, 5),
        new Minuman("MN-" + XYZ, "Es Teh", 5000, 10, 2000),
        // Menu nomor 3 otomatis: NIM ganjil -> Dessert, genap -> Snack.
        NIM_GANJIL ? new Dessert("DS-" + XYZ, "Es Krim", 12000, 4, 3000)
                   : new Snack("SN-" + XYZ, "Keripik", 8000, 4, 1000)

    };
    // Pintu masuk program.
    public static void main(String[] args) {
        // NAMA kosong: tampilkan pesan lalu berhenti.
        if (NAMA.trim().isEmpty()) {
            System.out.println(">> NAMA belum diisi. Isi konstanta NAMA di "
                    + "AplikasiKantin.java.");
            // Keluar dari main (program berhenti).
            return;
        }
        // NIM tidak valid (! = tidak): tampilkan pesan lalu berhenti.
        if (!NIM_VALID) {
            System.out.println(">> NIM \"" + NIM + "\" tidak valid: harus 10 digit "
                    + "angka, tanpa huruf/spasi (contoh: 2501010063).");
            System.out.println(">> Perbaiki konstanta NIM di AplikasiKantin.java "
                    + "lalu Run ulang.");
            return;
        }
        // Menampilkan identitas di baris pertama.
        System.out.println("UTS PBO | " + NAMA + " | " + NIM);
        // Menampilkan daftar menu.
        tampilDaftarMenu();
        // null = belum ada pesanan yang berhasil.
        Pesanan pesanan = null;
        // Ulangi sampai pesanan berhasil dibuat.
        while (pesanan == null) {
            // Jalankan kode yang mungkin error.
            try {
                // Membaca nomor menu (huruf/kosong sudah ditolak oleh bacaInt).
                int nomor = bacaInt("Pilih menu (nomor) : ");
                // Nomor di luar daftar: minta ulang.
                while (nomor < 1 || nomor > DAFTAR_MENU.length) {
                    System.out.println(">> Nomor menu tidak tersedia, pilih 1 sampai "
                            + DAFTAR_MENU.length + ".");
                    nomor = bacaInt("Pilih menu (nomor) : ");
                }
                // Simpan pilihan sebagai Menu; dikurangi 1 karena array mulai dari 0.
                Menu menuDipilih = DAFTAR_MENU[nomor - 1];
                // Membaca jumlah porsi.
                int jumlah = bacaInt("Jumlah porsi       : ");
                // Membuat pesanan; jika gagal, muncul error dan perulangan diulang.
                pesanan = new Pesanan("PSN-" + XYZ, NAMA, menuDipilih, jumlah);
            // Menangkap error stok tidak cukup.
            } catch (StokHabisException e) {
                // Menampilkan pesan error ke pengguna.
                System.out.println(">> Pesanan ditolak: " + e.getMessage());
            // Menangkap error data tidak valid (mis. jumlah 0).
            } catch (IllegalArgumentException e) {
                System.out.println(">> Data pesanan tidak valid: " + e.getMessage());
            // finally selalu dijalankan, berhasil maupun gagal.
            } finally {
                System.out.println("[Log] Pengisian pesanan selesai.");
            }
        }
        // Pesanan berhasil: cetak detailnya.
        pesanan.cetakDetail();
        // Kalimat penutup program.
        System.out.println("Selesai dijalankan oleh " + NAMA + " (" + NIM + ")");
    }
    // Membaca teks dari keyboard dan menolak isian kosong.
    private static String bacaTeks(String label) {
        // Diisi kosong agar perulangan berjalan minimal sekali.
        String teks = "";
         // Ulangi selama teks masih kosong.
        while (teks.isEmpty()) {
            // print = cetak tanpa pindah baris.
            System.out.print(label);
            // Membaca satu baris dan membuang spasi di awal/akhir.
            teks = INPUT.nextLine().trim();
            if (teks.isEmpty()) {
                System.out.println(">> Input tidak boleh kosong, coba lagi.");
            }
        }
        return teks;
    }
    // Membaca angka bulat; huruf (mis. jjk) ditolak dan diminta ulang.
    private static int bacaInt(String label) {
        // Ulangi terus sampai return dijalankan.
        while (true) {
            String teks = bacaTeks(label);
            try {
                // Mengubah teks menjadi angka; gagal jika bukan angka.
                return Integer.parseInt(teks);
            } catch (NumberFormatException e) {
                System.out.println(">> Input harus berupa angka bulat, coba lagi.");
            }
        }
    }
    // Mencetak daftar menu: nomor, nama, kode, harga, biaya tambahan, stok.
    private static void tampilDaftarMenu() {
        System.out.println("=== DAFTAR MENU KANTIN ===");
        // Perulangan untuk setiap menu di array.
        for (int i = 0; i < DAFTAR_MENU.length; i++) {
            // Mengambil menu ke-i.
            Menu m = DAFTAR_MENU[i];
            // Menyusun satu baris teks; nomor = i + 1.
            String baris = (i + 1) + ". " + m.getNamaMenu() + " (" + m.getKodeMenu()
                    + ") | Rp" + m.getHarga();
            // Tambahkan keterangan biaya jika ada.
            if (m.getKeteranganBiaya() != null) {
                // += menambahkan teks di belakang.
                baris += " + " + m.getKeteranganBiaya();
            }
            System.out.println(baris + " | stok " + m.getStok());
        }
    }
}