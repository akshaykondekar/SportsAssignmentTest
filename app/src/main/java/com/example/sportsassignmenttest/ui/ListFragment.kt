package com.example.sportsassignmenttest.ui

import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.example.sportsassignmenttest.R
import com.example.sportsassignmenttest.databinding.FragmentListBinding
import com.example.sportsassignmenttest.model.Salary
import com.example.sportsassignmenttest.ui.viewmodel.SalaryViewModel
import com.example.sportsassignmenttest.ui.viewmodel.SharedViewModel
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText

class ListFragment : Fragment() {

    private val salaryViewModel : SalaryViewModel by viewModels()
    private val sharedViewModel : SharedViewModel by viewModels()

    private var salaryAdapter = SalaryAdapter()
    private lateinit var salary : Salary

    private var _binding : FragmentListBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        _binding = FragmentListBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        binding.sharedViewModel = sharedViewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.rvSalary.apply {
            adapter = salaryAdapter
        }

        salaryViewModel.getAllSalary().observe(viewLifecycleOwner, {list ->
            sharedViewModel.checkIfDatabaseEmpty(list)
            salaryAdapter.differ.submitList(list)
        })

        salaryAdapter.clickedItemDeleteAction {
            salaryViewModel.deleteSalary(it)
        }

        salaryAdapter.clickedItemEditAction {
            openEditPopup(it)
        }
    }

    private fun openEditPopup(salary: Salary) {

        this.salary = salary

        val dialog = context?.let { Dialog(it) }
        dialog?.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog?.setCancelable(true)
        dialog?.setContentView(R.layout.cv_edit_salary_popup)

        val name : TextInputEditText = dialog!!.findViewById(R.id.tietUpdateName)
        val amount : TextInputEditText = dialog.findViewById(R.id.tietUpdateSalary)
        val designation : TextInputEditText = dialog.findViewById(R.id.tietUpdateDesignation)
        val btnUpdateSalary : MaterialButton = dialog.findViewById(R.id.btnUpdateSalary)

        name.setText(salary.name)
        amount.setText(salary.salary.toString())
        designation.setText(salary.designation)

        btnUpdateSalary.setOnClickListener {
            val updatedName = name.text.toString()
            val updatedAmount = amount.text.toString().toDouble()
            val updatedDesignation = designation.text.toString()

            val validation = sharedViewModel.validateData(updatedName, updatedDesignation)

            if(validation){
                updateSalary(dialog, updatedName, updatedAmount, updatedDesignation)
            }
            else{
                Toast.makeText(this.activity,"Please fill the details", Toast.LENGTH_SHORT).show()
            }
        }
        dialog.show()
    }

    private fun updateSalary(
        dialog: Dialog,
        updatedName: String,
        updatedAmount: Double,
        updatedDesignation: String
    ) {
        this.salary.name = updatedName
        this.salary.salary = updatedAmount
        this.salary.designation = updatedDesignation

        salaryViewModel.updateSalary(this.salary)
        dialog.dismiss()
        salaryAdapter.notifyDataSetChanged()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}