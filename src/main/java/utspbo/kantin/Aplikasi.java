package utspbo.kantin;

import java.util.Scanner;// Memakai Scanner untuk membaca ketikan dari keyboard.

public class Aplikasi {// Program utama; method main dijalankan pertama kali saat Run (F6).

    // Konstanta: static = milik class, final = tidak bisa diubah. GANTI dengan data Anda.
    private static String NAMA;
    private static String NIM;
    // 3 angka terakhir NIM untuk kode menu/pesanan.
    private static String XYZ;
    // true jika 2 digit terakhir NIM ganjil (% 2 = sisa bagi 2).
    private static boolean NIM_GANJIL;
    // Satu Scanner dipakai bersama; System.in = input keyboard.
    private static final Scanner INPUT = new Scanner(System.in);
    // Daftar menu dibuat setelah NIM diketahui.
    private static Menu[] DAFTAR_MENU;

    public static void main(String[] args) {
        // Input nama (kosong otomatis ditolak oleh bacaTeks).
        NAMA = bacaTeks("Nama               : ");
        // Input NIM; diulang sampai tepat 10 angka.
        NIM = bacaTeks("NIM (10 digit)     : ");
        while (!NIM.matches("\\d{10}")) {
            System.out.println(">> NIM harus 10 digit angka, tanpa huruf/spasi.");
            NIM = bacaTeks("NIM (10 digit)     : ");
        }
        // Ambil 3 digit terakhir dan cek ganjil/genap dari 2 digit terakhir.
        XYZ = NIM.substring(NIM.length() - 3);
        NIM_GANJIL = Integer.parseInt(NIM.substring(NIM.length() - 2)) % 2 == 1;
        System.out.println(">> NIM " + (NIM_GANJIL ? "GANJIL -> paket Dessert"
                : "GENAP -> paket Snack"));
        // Menyusun daftar menu sesuai hasil cek NIM.
        DAFTAR_MENU = new Menu[] {
            new Makanan("MK-" + XYZ, "Nasi Goreng", 15000, 5),
            new Minuman("MN-" + XYZ, "Es Teh", 5000, 10, 2000),
            NIM_GANJIL ? new Dessert("DS-" + XYZ, "Es Krim", 12000, 4, 3000)
                    : new Snack("SN-" + XYZ, "Keripik", 8000, 4, 1000)
        };
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