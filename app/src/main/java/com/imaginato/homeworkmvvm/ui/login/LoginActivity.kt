package com.imaginato.homeworkmvvm.ui.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import com.imaginato.homeworkmvvm.databinding.ActivityLoginBinding
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.component.KoinApiExtension
import org.koin.core.component.inject

@KoinApiExtension
class LoginActivity : AppCompatActivity() {

    private val viewModel by viewModel<LoginActivityViewModel>()
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnLogin.setOnClickListener {
            if (validateFields()) {
                val username = binding.etUsername.text.toString()
                val password = binding.etPassword.text.toString()
                viewModel.loginUser(username, password)
            }
        }

        initObserve()
    }

    private fun validateFields(): Boolean {
        if (binding.etUsername.text.isEmpty()) {
            Toast.makeText(this@LoginActivity, "Please enter username", Toast.LENGTH_SHORT).show()
            return false
        } else if (binding.etPassword.text.isEmpty()) {
            Toast.makeText(this@LoginActivity, "Please enter password", Toast.LENGTH_SHORT).show()
            return false
        }
        return true
    }

    private fun initObserve() {
        viewModel.resultLiveData.observe(this, Observer {
            if (it.errorCode == "00") {
                Toast.makeText(this@LoginActivity, it.errorMessage, Toast.LENGTH_SHORT).show()
            } else if (it.errorCode == "01") {
                Toast.makeText(this@LoginActivity, it.errorMessage, Toast.LENGTH_SHORT).show()
            }
        })
        viewModel.progress.observe(this, Observer {
            binding.pbLoading.isVisible = it
        })
    }
}