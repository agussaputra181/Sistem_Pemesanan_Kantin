package utspbo.kantin; //Menyatakan file ini berada di package utspbo.kantin

public class StokHabisException extends Exception {//turunan Exception untuk menangani stok tidak cukup dengan throws /try-catch
    
    public StokHabisException(String pesan) {// Constructor: menerima pesan error saat stok tidak cukup.
        super(pesan); // Menyimpan pesan agar bisa dibaca dengan e.getMessage().
    } // Akhir constructor/method.
}// Akhir class.
