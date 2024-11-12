package com.quispe.ismael.poketinder2024_2_d

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.quispe.ismael.poketinder2024_2_d.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding
    private lateinit var registerViewModel: RegisterViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        registerViewModel = RegisterViewModel(this) // Instancia sin Factory

        observeValues()

        binding.btnRegister.setOnClickListener {
            registerViewModel.registerUser(
                email = binding.edtEmail.text.toString(),
                password = binding.edtPassword.text.toString(),
                confirmPassword = binding.edtPassword2.text.toString()
            )
        }

        binding.btnAlreadyHaveAccount.setOnClickListener {
            goToLogin()
        }

        binding.btnBackClose.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }
    }

    private fun observeValues() {
        registerViewModel.inputsError.observe(this) {
            Toast.makeText(this, "Error: Complete todos los campos.", Toast.LENGTH_SHORT).show()
        }

        registerViewModel.emailFormatError.observe(this) {
            Toast.makeText(this, "Error: Introduzca un email válido.", Toast.LENGTH_SHORT).show()
        }

        registerViewModel.passwordError.observe(this) {
            Toast.makeText(this, "Error: La contraseña debe tener al menos 8 caracteres.", Toast.LENGTH_SHORT).show()
        }

        registerViewModel.passwordMismatchError.observe(this) {
            Toast.makeText(this, "Error: Las contraseñas no coinciden.", Toast.LENGTH_SHORT).show()
        }

        registerViewModel.registrationSuccess.observe(this) {
            Toast.makeText(this, "Registro exitoso.", Toast.LENGTH_SHORT).show()
            goToLogin()
        }
    }

    private fun goToLogin() {
        startActivity(Intent(this, LoginActivity::class.java))
        finish()
    }
}
