import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

// Class untuk WRITE (simpan) dan READ (baca) data anggota dari/ke file txt
public class DataAnggota {

    private String namaFile = "anggota.txt"; // nama file penyimpanan

    // cek apakah id sudah ada di file, biar tidak dobel
    private boolean cekIdSudahAda(String id) {
        List<Anggota> daftarAnggota = bacaAnggota(); // ambil semua data
        for (Anggota a : daftarAnggota) {
            if (a.getId().equalsIgnoreCase(id)) { // bandingkan id
                return true; // id sudah ada
            }
        }
        return false; // id belum ada
    }

    // simpan 1 data anggota ke file txt
    public void simpanAnggota(Anggota anggota) {
        if (cekIdSudahAda(anggota.getId())) { // kalau id sudah ada
            System.out.println("ID " + anggota.getId() + " sudah ada, tidak disimpan ulang.");
            return; // batalkan simpan
        }

        try (
            FileWriter fw = new FileWriter(namaFile, true); // true = mode tambah
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw)
        ) {
            pw.println(anggota.toFileFormat()); // tulis 1 baris ke file
            System.out.println("Data " + anggota.getNama() + " berhasil disimpan.");
        } catch (IOException e) {
            System.out.println("Gagal menyimpan: " + e.getMessage());
        }
    }

    // baca semua data anggota dari file txt
    public List<Anggota> bacaAnggota() {
        List<Anggota> daftarAnggota = new ArrayList<>(); // wadah hasil baca
        File file = new File(namaFile);

        if (!file.exists()) { // kalau file belum ada
            return daftarAnggota; // balikin list kosong
        }

        try (
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr)
        ) {
            String baris;
            while ((baris = br.readLine()) != null) { // baca tiap baris
                String[] data = baris.split(","); // pecah jadi id,nama,jenis,mataKuliah
                if (data.length == 4) {
                    Anggota anggota = new Anggota(data[0], data[1], data[2], data[3]);
                    daftarAnggota.add(anggota); // masukkan ke list
                }
            }
        } catch (IOException e) {
            System.out.println("Gagal membaca: " + e.getMessage());
        }

        return daftarAnggota; // hasil akhir
    }

    // tampilkan daftar anggota, dibedakan sesuai jenisnya
    public void tampilkanAnggota(List<Anggota> daftarAnggota) {
        if (daftarAnggota.isEmpty()) { // kalau tidak ada data
            System.out.println("Belum ada data anggota.");
            return;
        }

        for (Anggota a : daftarAnggota) {
            String keterangan;
            if (a.getJenis().equalsIgnoreCase("Mahasiswa")) {
                keterangan = "Mahasiswa, mata kuliah: " + a.getMataKuliah();
            } else if (a.getJenis().equalsIgnoreCase("Dosen")) {
                keterangan = "Dosen, mengajar: " + a.getMataKuliah();
            } else {
                keterangan = "Karyawan"; // tidak ada mata kuliah
            }
            System.out.println("ID: " + a.getId() + " | Nama: " + a.getNama() + " -> " + keterangan);
        }
    }
}