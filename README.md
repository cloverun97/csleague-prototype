# csleague-prototype

Deskripsi singkat :

csleague-prototype adalah sistem sederhana yang mengakomodasi kebutuhan panitia CS League. Requirement yang diberikan adalah sebagai berikut:
1. Sistem kompetisi yang digunakan ialah sistem setengah kompetisi (round robin). Sehingga, setiap tim harus bertanding satu dengan yang lainnya untuk memperebutkan peringkat teratas. Jika jumlah Tim ialah sebanyak t akan terdapat 𝐶(2,𝑡) pertandingan.
2. Urutan peringkat dalam klasemen diatur dengan aturan sebagai berikut:
  Menang: +3 poin, Kalah: 0 poin, Seri: +1 poin.
3. Jika pada akhir kompetisi terdapat Tim dengan poin yang sama, maka, pemenang akan ditentukan dengan melihat selisih gol (jumlahGol – jumlahKebobolan) terbanyak.

Prototype sistem ini berisi representasi data dummy untuk Pemain, Tim, hingga Pertandingan (Game) dan diimplementasikan dengan konsep Object Oriented Programming (OOP). Berikut perintah-perintah yang dapat dijalankan.

List perintah:
1.) Perintah: init x
Perintah ini hanya dapat digunakan pada saat awal sistem dijalankan. Dengan perintah ini,
sistem akan menginisiasi Tim yang akan ikut dalam kompetisi dengan data dummy. Variabel x
menyatakan banyaknya tim t yang akan dibentuk dengan range yang telah disebutkan
sebelumnya, yaitu (4 ≤ 𝑡 ≤ 10) . 

2.) Perintah: nextGame
Perintah ini hanya dapat digunakan setelah perintah init dilaksanakan. Perintah nextGame
merupakan core dari prototype yang akan dibuat. nextGame akan membuat data dummy
tentang informasi pertandingan, yaitu: Gol, Kartu Kuning, Kartu Merah, Pelanggaran

Untuk mempermudah proses testing, maka dalam perintah nextGame dibuat mode manual.
Mode manual digunakan untuk memasukkan informasi pertandingan secara manual. Untuk
mengaktivasi mode manual tersebut, user hanya perlu menambah beberapa argumen
tambahan, sebagai berikut:

2.1) Gol (-g)
Untuk mengaktifkan mode manual, argumen -g merupakan argumen wajib yang
harus ada. Jika tidak ada argumen ini, maka mode manual tidak bisa dijalankan atau
dianggap error. Mode manual dengan argumen -g akan menambahkan (+1)
jumlahGol ke dalam objek Pemain.

Sintaks:
nextGame -g [namaTim] [nomorPemain]

Contoh
nextGame -g Bebek 1

Tentunya, namaTim yang digunakan ialah nama tim yang memang akan bertanding,
dan nomorPemain haruslah pemain yang terdaftar dalam tim tersebut. Jika tidak,
maka pesan error dimunculkan. Untuk mendapatkan nama tim yang akan bertanding,
dapat digunakan perintah show nextGame yang akan dijelaskan pada bagian
selanjutnya.

Argumen -g dapat digunakan lebih dari satu kali, tentunya, karena dalam
pertandingan bisa terdapat lebih dari satu gol. Contoh penggunaan argumen -g lebih
dari satu kali adalah sebagai berikut:

nextGame -g Bebek 3 -g Ular 10 -g Ular 10

Hasil dari perintah tersebut ialah skor 2:1 dengan kemenangan untuk tim Ular.

2.2) Kartu Kuning (-kk)
Infomasi selanjutnya yang juga bisa menggunakan mode manual ialah Kartu Kuning.
Argumen untuk kartu kuning yaitu -kk. Argumen ini tidak bersifat wajib dalam mode
manual. Mode manual dengan argument -kk akan menambahkan (+1)
jumlahKartuKuning ke dalam objek Pemain.

Sintaks:
nextGame -kk [namaTim] [nomorPemain]

Contoh
nextGame -kk Bebek 1

2.3) Kartu Merah (-km)
Selanjutnya adalah Kartu Merah. Sama seperti Kartu Kuning, argumen ini tidak
bersifat wajib. Mode manual dengan argumen -km akan menambahkan (+1)
jumlahKartuMerah ke dalam objek Pemain.

Sintaks:
nextGame -km [namaTim] [nomorPemain]

Contoh
nextGame -km Bebek 1

Hal yang perlu menjadi catatan ialah Kartu Merah juga bisa didapat jika seorang
pemain yang sama mendapat dua Kartu Kuning dalam satu pertandingan.

2.4) Pelanggaran (-p)
Selanjutnya adalah Pelanggaran. Argumen ini juga tidak bersifat wajib. Mode manual
dengan argumen -p akan menambahkan (+1) jumlehPelanggaran ke dalam objek
Pemain.

Sintaks:
nextGame -p [namaTim] [nomorPemain]

Contoh
nextGame -p Bebek 1

Mode manual dapat dijalankan secara berbarengan, seperti berikut:
nextGame -g Ular 10 -kk Bebek 5 -kk Bebek 5

Perintah diatas mencatat statistik yaitu kemenangan 1:0 untuk tim Ular. Tim Bebek melakukan
2 kali pelanggaran, dan 2 kali kartu kuning. Karena yang mendapat 2 kartu kuning bernomor
sama, maka no 5 juga mendapat kartu merah.

Selain mode manual diatas, ada satu argument penting yang juga harus dibuat dalam
prototype ini, yaitu -all. Argumen tersebut berfungsi untuk menyelesaikan seluruh
pertandingan dalam kompetisi hingga ditemukan pemenangnya.

Sintaks:
nextGame -all

Setelah sebuah pertandingan usai, klasemen (urutan Tim) dalam kelas Liga akan di-update
(array tim di kelas liga akan di-rearrange) berdasarkan poin atau selisih gol (jika poinnya sama)
sedemikian rupa sehingga Tim dengan poin tertinggi akan menempati array dengan indeks
terendah. Selain itu, setelah pertandingan dilaksanakan akan ditampilkan statistika
pertandingan.

3.) Perintah: show
Perintah ini berguna untuk menyampaikan informasi terkini terkait pertandingan. Perintah
show membutuhkan argumen. yaitu:

3.1) show klasemen
Perintah ini berfungsi untuk menampilkan klasemen sementara terkini. Klasemen
ditampilkan berurutan mulai dari puncak klasemen hingga tim dengan score terendah.

3.2) show pencetakGol
Perintah ini berfungsi untuk menampilkan 10 pencetak gol terbanyak. Daftar
pencetak gol ditampilkan berurutan mulai tertinggi hingga terendah. 

3.3) show tim [namaTim]
Perintah ini berfungsi untuk menampilkan 5 pemain dalam tim lengkap dengan
informasinya.

3.4) show pemain [namaTim] [nomorPemain atau namaPemain]
Perintah ini berfungsi untuk menampilkan informasi pemain.

3.5) show nextGame
Perintah ini berfungsi untuk menampilkan informasi pertandingan selanjutnya yang
akan dilaksanakan. Perintah ini penting jika user ingin menggunakan model manual
pada perintah nextGame. Dengan perintah ini, user akan mengetahui tim apa
selanjutnya yang akan bertanding.
