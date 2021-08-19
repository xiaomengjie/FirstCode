package com.example.firstcode.chapter6.offline

import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import com.example.firstcode.R
import com.example.firstcode.other.actionStart
import com.example.firstcode.other.showToast

class LoginActivity : ForcedOfflineActivity() {

    private val accountEdit: EditText by lazy { findViewById(R.id.accountEdit) }
    private val passwordEdit: EditText by lazy { findViewById(R.id.passwordEdit) }
    private val rememberPassword: CheckBox by lazy { findViewById(R.id.rememberPassword) }


    private val sharedPreferences by lazy { getPreferences(MODE_PRIVATE) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val isRemember = sharedPreferences.getBoolean("remember_password", false)
        if (isRemember){
            val account = sharedPreferences.getString("account", null)
            val password = sharedPreferences.getString("password", null)
            accountEdit.setText(account)
            passwordEdit.setText(password)
            rememberPassword.isChecked = true
        }

        findViewById<Button>(R.id.login).setOnClickListener {
            val account = accountEdit.text.toString()
            val password = passwordEdit.text.toString()
            if (account == "admin" && password == "123456"){
                val editor = sharedPreferences.edit()
                if (rememberPassword.isChecked){
                    editor.putBoolean("remember_password", true)
                    editor.putString("account", account)
                    editor.putString("password", password)
                }else{
                    editor.clear()
                }
                editor.apply()
                actionStart(this, LoginSuccessActivity::class.java)
                finish()
            }else{
                showToast(this, "account or password invalid")
            }
        }
    }
}