package com.example.bitpevent

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.bitpevent.databinding.ActivityChiTietBinding

class ChiTietActivity : AppCompatActivity() {
    private lateinit var binding: ActivityChiTietBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChiTietBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val hoTen = intent.getStringExtra("HO_TEN") ?: ""
        val msv = intent.getStringExtra("MSV") ?: ""
        val gioiTinh = intent.getStringExtra("GIOI_TINH") ?: ""
        val mxh = intent.getStringExtra("MXH") ?: "Không"

        binding.tvThongTin.text = """
            Họ tên: $hoTen
            Mã sinh viên: $msv
            Giới tính: $gioiTinh
            Mạng xã hội: $mxh
        """.trimIndent()
    }
}