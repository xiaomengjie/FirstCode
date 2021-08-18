package com.example.firstcode.chapter6.offline

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.example.firstcode.R
import com.example.firstcode.other.actionStart
import com.example.firstcode.other.showToast

class LoginActivity : ForcedOfflineActivity() {

    private val accountEdit: EditText by lazy { findViewById(R.id.accountEdit) }
    private val passwordEdit: EditText by lazy { findViewById(R.id.passwordEdit) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        findViewById<Button>(R.id.login).setOnClickListener {
            val account = accountEdit.text.toString()
            val password = passwordEdit.text.toString()
            if (account == "admin" && password == "123456"){
                actionStart(this, LoginSuccessActivity::class.java)
                finish()
            }else{
                showToast(this, "account or password invalid")
            }
        }
    }
}