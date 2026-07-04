// import List karena bacaAnggota() mengembalikan tipe data List<Anggota>
import java.util.List;

public class Main {
    public static void main(String[] args) {

        // membuat objek DataAnggota -> objek inilah yang punya kemampuan simpan & baca file
        DataAnggota dataAnggota = new DataAnggota();
        Anggota a1 = new Anggota("A01", "Andini Dwi", "Mahasiswa", "Pemrograman 2");
        Anggota a2 = new Anggota("D01", "Riva Muchlas", "Dosen", "Pemrograman 2");
        Anggota a3 = new Anggota("K01", "Adelina Elsandra", "Karyawan", "-");

        System.out.println("=== Proses Menyimpan Data ke File ===");
        dataAnggota.simpanAnggota(a1);
        dataAnggota.simpanAnggota(a2);
        dataAnggota.simpanAnggota(a3);

        // membuktikan data benar-benar tersimpan permanen di file, bukan cuma di memori program
        System.out.println();
        System.out.println("=== Proses Membaca Ulang Data dari File ===");
        List<Anggota> daftarAnggota = dataAnggota.bacaAnggota();

        // Mahasiswa & Dosen akan tampil beserta mata kuliahnya, Karyawan tanpa mata kuliah
        System.out.println();
        System.out.println("=== Daftar Anggota (Hasil Baca File) ===");
        dataAnggota.tampilkanAnggota(daftarAnggota);

        System.out.println();
        System.out.println("Total anggota yang berhasil dibaca: " + daftarAnggota.size());
    }
}
