package br.com.estudavagabundo.subject

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.estudavagabundo.databinding.ActivityAddSubjectBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AddSubject : AppCompatActivity() {

    private lateinit var binding: ActivityAddSubjectBinding
    private lateinit var database: AppDatabase
    private lateinit var adapter: SubjectAdapter

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

        // ✅ Inicializa o banco de dados
        database = AppDatabase.getDatabase(this)

        // ✅ Inicializa o adapter com lista vazia
        adapter = SubjectAdapter(
            subjects = mutableListOf(),
            onDelete = { subject ->
                CoroutineScope(Dispatchers.IO).launch {
                    database.subjectDao().delete(subject)
                }
            },
            onEdit = { subject, novoNome ->
                CoroutineScope(Dispatchers.IO).launch {
                    database.subjectDao().update(subject.copy(subject = novoNome))
                }
            }
        )

        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter

        // ✅ Observa o banco e atualiza a lista automaticamente
        database.subjectDao().getAll().observe(this) { subjects ->
            adapter.updateList(subjects)
        }

        // ✅ Adiciona matéria ao banco
        binding.tilSubjectName.setEndIconOnClickListener {
            val nome = binding.edtSubjectName.text.toString().trim()
            if (nome.isNotEmpty()) {
                CoroutineScope(Dispatchers.IO).launch {
                    database.subjectDao().insert(SubjectsData(subject = nome))
                }
                binding.edtSubjectName.text?.clear()
            } else {
                binding.tilSubjectName.error = "Digite o nome da matéria"
            }
        }
    }
}