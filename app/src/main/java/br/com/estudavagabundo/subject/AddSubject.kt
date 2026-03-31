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

    private val SubjectsData : List<SubjectsData> = listOf(
        SubjectsData("Math"),
        SubjectsData("Portuguese"),
        SubjectsData("Constitutional Law")
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_add_subject)





        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val adapter = SubjectAdapter(SubjectsData)
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter

    }
}