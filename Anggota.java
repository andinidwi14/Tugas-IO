// Class Anggota merepresentasikan satu data anggota (bisa Mahasiswa, Dosen, atau Karyawan)
// Semua jenis anggota memakai class yang sama, supaya sederhana (tidak pakai inheritance/turunan)
public class Anggota {
    private String id;          // menyimpan id unik anggota, contoh: A01, D01, K01
    private String nama;        // menyimpan nama lengkap anggota
    private String jenis;       // menyimpan jenis anggota -> "Mahasiswa", "Dosen", atau "Karyawan"
    private String mataKuliah;  // menyimpan mata kuliah (khusus Mahasiswa & Dosen). Untuk Karyawan diisi "-"

    // Constructor -> dipanggil saat objek Anggota baru dibuat, langsung mengisi semua data sekaligus
    public Anggota(String id, String nama, String jenis, String mataKuliah) {
        this.id = id;                 // "this.id" adalah field milik object, diisi dari parameter id yang dikirim
        this.nama = nama;             // isi field nama dengan nilai parameter nama
        this.jenis = jenis;           // isi field jenis dengan nilai parameter jenis
        this.mataKuliah = mataKuliah; // isi field mataKuliah dengan nilai parameter mataKuliah
    }

    // Getter untuk mengambil nilai id dari luar class (karena field bersifat private)
    public String getId() {
        return id;
    }

    // Getter untuk mengambil nilai nama dari luar class
    public String getNama() {
        return nama;
    }

    // Getter untuk mengambil nilai jenis dari luar class
    public String getJenis() {
        return jenis;
    }

    // Getter untuk mengambil nilai mataKuliah dari luar class
    public String getMataKuliah() {
        return mataKuliah;
    }

    // Method ini mengubah data Anggota menjadi 1 baris teks dengan pemisah koma (format CSV sederhana)
    // Format hasil: id,nama,jenis,mataKuliah -> contoh: A01,Andini Dwi,Mahasiswa,Pemrograman 2
    // Format inilah yang nanti ditulis ke file txt dan dipakai lagi saat proses baca file
    public String toFileFormat() {
        return id + "," + nama + "," + jenis + "," + mataKuliah;
    }
}
