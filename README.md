# android-barcode-intent

Repository ini adalah bagian dari bahan pembelajaran untuk 
matakuliah Java Mobile Application pada [Program Studi Sistem 
Informasi](http://sif.uin-suska.ac.id/) [Fakultas Sains dan 
Teknologi](http://fst.uin-suska.ac.id/), [Universitas Islam 
Negeri Sultan Syarif Kasim](http://uin-suska.ac.id/), Riau.

# What is it

Aplikasi ini akan memanggil intent zxing untuk melakukan 
scan kode baris QR Code.

Untuk menjalankan dengan sukses, tentu Anda harus terlebih
dahulu menginstall aplikasi zxing dari Google, yang bisa
didownload dari Google Play Store.

```java
	public void mScan(View v) {
		//http://stackoverflow.com/posts/4094191/
		Intent intent = new Intent("com.google.zxing.client.android.SCAN");
		intent.putExtra("com.google.zxing.client.android.SCAN.SCAN_MODE", "QR_CODE_MODE");
		startActivityForResult(intent, 0);

	}
```

Jika fungsi `mScan` tersebut dijalankan, maka akan menjalankan
aktivitas zxing, dan akan ditunggu hasilnya oleh fungsi berikut:

```java
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        if (requestCode == 0) {
            if (resultCode == RESULT_OK) {
                String contents = intent.getStringExtra("SCAN_RESULT");
                String format = intent.getStringExtra("SCAN_RESULT_FORMAT");
                // Handle successful scan

                //Toast
                Toast.makeText(MainActivity.this, contents, Toast.LENGTH_SHORT).show();
                EditText editText = (EditText) findViewById(R.id.EditText1);
                editText.setText(contents);

            } else if (resultCode == RESULT_CANCELED) {
                // Handle cancel
            }
        }
    }		
```		

Fungsi di atas berguna untuk mengubah isi EditText kita 
dengan hasil QR_Code yang diberikan oleh zxing.

