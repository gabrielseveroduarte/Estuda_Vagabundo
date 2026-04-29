package br.com.estudavagabundo.subject

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.estudavagabundo.databinding.RvSubjectItemBinding

class SubjectAdapter(
    private val subjects: MutableList<SubjectsData>,
    private val onDelete: (SubjectsData) -> Unit,
    private val onEdit: (SubjectsData, String) -> Unit
) : RecyclerView.Adapter<SubjectAdapter.SubjectViewHolder>() {

    fun updateList(newList: List<SubjectsData>) {
        subjects.clear()
        subjects.addAll(newList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SubjectViewHolder {
        val binding =
            RvSubjectItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SubjectViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: SubjectViewHolder,
        position: Int
    ) {
        holder.binding.tvSubject.text = subjects[position].subject

        holder.binding.btnDelete.setOnClickListener {
            onDelete(subjects[position])
        }

        holder.binding.btnEdit.setOnClickListener {
            val context = holder.itemView.context
            val input = android.widget.EditText(context)
            input.setText(subjects[position].subject)

            android.app.AlertDialog.Builder(context)
                .setTitle("Editar matéria")
                .setView(input)
                .setPositiveButton("Salvar") { _, _ ->
                    val novoNome = input.text.toString().trim()
                    if (novoNome.isNotEmpty()) {
                        onEdit(subjects[position], novoNome)
                    }
                }
                .setNegativeButton("Cancelar", null)
                .show()
        }
    }

    override fun getItemCount(): Int {
        return subjects.size
    }

    class SubjectViewHolder(val binding: RvSubjectItemBinding) :
        RecyclerView.ViewHolder(binding.root)
}