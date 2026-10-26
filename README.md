# UTS PBO – Sistem Pemesanan Menu Kantin Primakara

| | |
|---|---|
| **Nama** | Putu Agus Saputra |
| **NIM** | 2501010063 |
| **Mata Kuliah** | Pemrograman Berorientasi Objek (PBO) |
| **Paket Soal 4** | Ganjil – Dessert (dipilih otomatis dari NIM) |

## Deskripsi
Program Java berbasis konsol untuk memesan menu di kantin kampus.
Program menampilkan identitas mahasiswa dan daftar menu, menerima pilihan menu
dan jumlah porsi, lalu mencetak detail pesanan beserta subtotal, biaya tambahan,
dan total bayar.

## Fitur
- Identitas (NAMA dan NIM) tampil otomatis di baris pertama.
- Kode menu dan kode pesanan dibentuk dari 3 digit terakhir NIM (MK-063, PSN-063).
- Menu nomor 3 dipilih otomatis: NIM ganjil = Dessert, NIM genap = Snack.
- Input huruf atau kosong ditolak dan diminta ulang.
- Pesanan ditolak jika jumlah porsi ≤ 0 atau stok tidak cukup (StokHabisException).
- `try-catch-finally` dengan log `[Log] Pengisian pesanan selesai.`

## Struktur Class
| Class | Keterangan |
|---|---|
| `Menu` | Class abstract induk semua menu (kode, nama, harga, stok) |
| `Makanan` | Total = harga × jumlah |
| `Minuman` | Total = harga × jumlah + kemasan (sekali per pesanan) |
| `Dessert` | Total = harga × jumlah + topping × jumlah (NIM ganjil) |
| `Snack` | Total = (harga + saus) × jumlah (NIM genap) |
| `Pesanan` | Validasi pesanan, mengurangi stok, mencetak detail |
| `StokHabisException` | Exception buatan sendiri saat stok tidak cukup |
| `AplikasiKantin` | Program utama (main) |

## Konsep OOP yang Diterapkan
- **Enkapsulasi**: semua atribut `private`, diakses lewat getter.
- **Abstraksi**: class `Menu` dan method `hitungTotal()` bersifat abstract.
- **Inheritance**: Makanan, Minuman, Dessert, dan Snack `extends Menu`.
- **Polimorfisme**: `hitungTotal()` dan `getKeteranganBiaya()` di-override.
- **Exception Handling**: exception buatan sendiri, `throws`, `try-catch-finally`.

## Cara Menjalankan
1. Clone repository ini, lalu buka di Apache NetBeans (File → Open Project).
2. Pastikan JDK 17 atau lebih baru sudah terpasang.
3. Jalankan **Clean and Build** (Shift+F11), lalu **Run** (F6).
4. Ketik input di jendela Output.

## Skenario Uji
| No | Input | Hasil |
|---|---|---|
| 1 | `jjk` → 1, jumlah 6 → 1, jumlah 0 → 1, jumlah 3 | Input salah ditolak; total Rp45000.0, stok tersisa 2 |
| 2 | Menu 2, jumlah 4 | Rp20000.0 + kemasan Rp2000.0 = Rp22000.0, stok tersisa 6 |
| 3 | Menu 3, jumlah 2 | Rp24000.0 + topping Rp6000.0 = Rp30000.0, stok tersisa 2 |

## Struktur Folder
```
src/main/java/utspbo/kantin/
├── Aplikasi.java
├── Dessert.java
├── Makanan.java
├── Menu.java
├── Minuman.java
├── Pesanan.java
├── Snack.java
└── StokHabisException.java
```
