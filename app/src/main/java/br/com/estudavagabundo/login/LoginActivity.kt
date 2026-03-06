package br.com.estudavagabundo.login

import android.app.Activity
import android.content.Context
import android.graphics.Rect
import android.os.Bundle
import android.util.Patterns
import android.view.MotionEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import br.com.estudavagabundo.R
import br.com.estudavagabundo.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.editUsuario.onFocusChangeListener = View.OnFocusChangeListener { view, hasFocus ->
            if (hasFocus.not()) {
                if (!Patterns.EMAIL_ADDRESS.matcher(binding.editUsuario.text.toString())
                        .matches()
                ) {
                    binding.buttonLogin.isEnabled = false
                    Toast.makeText(this, "Insira um e-mail válido", Toast.LENGTH_SHORT).show()
                } else {
                    binding.buttonLogin.isEnabled = binding.editSenha.text.toString().length >= 6
                }
            }
        }

        binding.editSenha.onFocusChangeListener = View.OnFocusChangeListener { view, hasFocus ->
            if (hasFocus.not()) {
                if (binding.editSenha.text.toString().length < 6) {
                    binding.buttonLogin.isEnabled = false
                    Toast.makeText(
                        this,
                        "A senha deve possuir 6 (seis) caracteres ou mais",
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    binding.buttonLogin.isEnabled =
                        Patterns.EMAIL_ADDRESS.matcher(binding.editUsuario.text.toString())
                            .matches()
                }
            }
        }
    }

    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        if (ev?.action == MotionEvent.ACTION_DOWN) {
            currentFocus.takeIf { it is EditText }?.run {
                val outRect = Rect()
                getGlobalVisibleRect(outRect)
                if (!outRect.contains(ev.rawX.toInt(), ev.rawY.toInt())) {
                    currentFocus?.clearFocus()
                    val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                    imm.hideSoftInputFromWindow(
                        currentFocus?.windowToken, 0
                    )
                }
            }
        }
        return super.dispatchTouchEvent(ev)
    }
}