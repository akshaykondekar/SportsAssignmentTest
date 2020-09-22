package com.example.sportsassignmenttest.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.sportsassignmenttest.databinding.CvSalaryItemBinding
import com.example.sportsassignmenttest.model.Salary

class SalaryAdapter : RecyclerView.Adapter<SalaryAdapter.SalaryViewHolder>(){
    private var deleteAction: ((Salary) -> Unit)? = null
    private var editAction: ((Salary) -> Unit)? = null

    companion object{
        private val diffUtilCallback = object : DiffUtil.ItemCallback<Salary>(){
            override fun areItemsTheSame(oldItem: Salary, newItem: Salary): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Salary, newItem: Salary): Boolean {
                return oldItem.id == newItem.id
            }
        }
    }

    val differ = AsyncListDiffer(this, diffUtilCallback)

    fun clickedItemDeleteAction(action: (Salary) -> Unit) {
        this.deleteAction = action
    }

    fun clickedItemEditAction(action: (Salary) -> Unit) {
        this.editAction = action
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        SalaryViewHolder(
            CvSalaryItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: SalaryViewHolder, position: Int) =
        holder.bind(differ.currentList[position])

    override fun getItemCount() = differ.currentList.size

    inner class SalaryViewHolder(private val binding: CvSalaryItemBinding)
        : RecyclerView.ViewHolder(binding.root){
        fun bind(currentSalary: Salary) {
            binding.salary = currentSalary

            editAction?.let {
                binding.ibtnEdit.setOnClickListener {
                    it(currentSalary)
                }
            }

            deleteAction?.let {
                binding.ibtnDelete.setOnClickListener {
                    it(currentSalary)
                }
            }
        }
    }
}