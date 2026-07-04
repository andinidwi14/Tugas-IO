// Class untuk menyimpan data satu anggota (Mahasiswa/Dosen/Karyawan)
public class Anggota {
    private String id;          // id anggota
    private String nama;        // nama anggota
    private String jenis;       // jenis: Mahasiswa/Dosen/Karyawan
    private String mataKuliah;  // mata kuliah (khusus Mahasiswa & Dosen)

    // constructor, isi semua field saat objek dibuat
    public Anggota(String id, String nama, String jenis, String mataKuliah) {
        this.id = id;
        this.nama = nama;
        this.jenis = jenis;
        this.mataKuliah = mataKuliah;
    }

    public String getId() {
        return id; // ambil id
    }

    public String getNama() {
        return nama; // ambil nama
    }

    public String getJenis() {
        return jenis; // ambil jenis
    }

    public String getMataKuliah() {
        return mataKuliah; // ambil mata kuliah
    }

    // ubah data jadi 1 baris teks untuk disimpan ke file, dipisah koma
    public String toFileFormat() {
        return id + "," + nama + "," + jenis + "," + mataKuliah;
    }
}