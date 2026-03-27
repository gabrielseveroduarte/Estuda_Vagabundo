package br.com.estudavagabundo.subject

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.estudavagabundo.databinding.RvSubjectItemBinding

class SubjectAdapter(
    private val subjectModelList: List<SubjectsData>
) : RecyclerView.Adapter<SubjectAdapter.SubjectViewHolder>() {
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
        holder.binding.tvSubject.text = subjectModelList[position].subject
    }

    override fun getItemCount(): Int {
        return subjectModelList.size
    }

    class SubjectViewHolder(val binding: RvSubjectItemBinding) :
        RecyclerView.ViewHolder(binding.root)
}
