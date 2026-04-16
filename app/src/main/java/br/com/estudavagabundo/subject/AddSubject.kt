package br.com.estudavagabundo.subject

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.estudavagabundo.R
import br.com.estudavagabundo.databinding.ActivityAddSubjectBinding

class AddSubject : AppCompatActivity() {

    private lateinit var binding: ActivityAddSubjectBinding

    private val subjectsData: MutableList<SubjectsData> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityAddSubjectBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val adapter = SubjectAdapter(subjectsData)
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter

        binding.tilSubjectName.setEndIconOnClickListener {
            val nome = binding.edtSubjectName.text.toString().trim()
            if (nome.isNotEmpty()) {
                adapter.addSubject(SubjectsData(nome))
                binding.edtSubjectName.text?.clear()
            } else {
                binding.tilSubjectName.error = "Digite o nome da matéria"
            }
        }
    }
}