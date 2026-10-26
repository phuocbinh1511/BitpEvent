package com.example.bitpevent

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doAfterTextChanged
import com.example.bitpevent.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 1. Bắt sự kiện gõ phím
        binding.edtHoTen.doAfterTextChanged { text ->
            if (!text.isNullOrBlank()) {
                Toast.makeText(this, "Đang nhập tên: $text", Toast.LENGTH_SHORT).show()
            }
        }

        binding.edtMsv.doAfterTextChanged { text ->
            if (!text.isNullOrBlank()) {
                Toast.makeText(this, "Đang nhập MSV: $text", Toast.LENGTH_SHORT).show()
            }
        }

        // 2. Bắt sự kiện RadioGroup
        binding.rgGioiTinh.setOnCheckedChangeListener { _, checkedId ->
            val gioiTinh = if (checkedId == R.id.rbNam) "Nam" else "Nữ"
            Toast.makeText(this, "Chọn giới tính: $gioiTinh", Toast.LENGTH_SHORT).show()
        }

        // 3. Bắt sự kiện CheckBox
        val checkListener = { view: android.widget.CompoundButton, isChecked: Boolean ->
            val status = if (isChecked) "Đã chọn" else "Bỏ chọn"
            Toast.makeText(this, "$status ${view.text}", Toast.LENGTH_SHORT).show()
        }
        binding.cbFacebook.setOnCheckedChangeListener(checkListener)
        binding.cbZalo.setOnCheckedChangeListener(checkListener)

        // 4. Bắt sự kiện Nút Đăng ký và Chuyển trang
        binding.btnDangKy.setOnClickListener {
            val hoTen = binding.edtHoTen.text.toString().trim()
            val msv = binding.edtMsv.text.toString().trim()

            val gioiTinh = when (binding.rgGioiTinh.checkedRadioButtonId) {
                R.id.rbNam -> "Nam"
                R.id.rbNu -> "Nữ"
                else -> "Chưa chọn"
            }

            val mxh = mutableListOf<String>()
            if (binding.cbFacebook.isChecked) mxh.add("Facebook")
            if (binding.cbZalo.isChecked) mxh.add("Zalo")

            if (hoTen.isEmpty() || msv.isEmpty()) {
                Toast.makeText(this, "Vui lòng nhập đủ thông tin", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val intent = Intent(this, ChiTietActivity::class.java).apply {
                putExtra("HO_TEN", hoTen)
                putExtra("MSV", msv)
                putExtra("GIOI_TINH", gioiTinh)
                putExtra("MXH", mxh.joinToString(", "))
            }
            startActivity(intent)
        }
    }
}