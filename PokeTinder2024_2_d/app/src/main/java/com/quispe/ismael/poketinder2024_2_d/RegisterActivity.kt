package com.quispe.ismael.poketinder2024_2_d

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.quispe.ismael.poketinder2024_2_d.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding
    private lateinit var sharedPreferencesRepository: SharedPreferencesRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        sharedPreferencesRepository = SharedPreferencesRepository().apply {
            setSharedPreference(this@RegisterActivity)
        }

        binding.btnRegister.setOnClickListener {
            registerUser()
        }
        binding.btnAlreadyHaveAccount.setOnClickListener {
            goToLogin()
        }
        binding.btnBackClose.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }
    }

    private fun goToLogin() {
        startActivity(Intent(this, LoginActivity::class.java))
        finish()
    }

    private fun isValidEmail(email: String): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    private fun registerUser() {
        val email = binding.edtEmail.text.toString()
        val password = binding.edtPassword.text.toString()
        val password2 = binding.edtPassword2.text.toString()


        if (!isValidEmail(email)) {
            Toast.makeText(this, "Error: Introduzca un email válido.", Toast.LENGTH_SHORT).show()
            return
        }


        if (password.length < 8) {
            Toast.makeText(this, "Error: La contraseña debe tener al menos 8 caracteres.", Toast.LENGTH_SHORT).show()
            return
        }


        if (password != password2) {
            Toast.makeText(this, "Error: Las contraseñas ingresadas no coinciden.", Toast.LENGTH_SHORT).show()
            return
        }


        sharedPreferencesRepository.saveUserEmail(email)
        sharedPreferencesRepository.saveUserPassword(password)
        Toast.makeText(this, "Registro exitoso.", Toast.LENGTH_SHORT).show()
        goToLogin()
    }



}
