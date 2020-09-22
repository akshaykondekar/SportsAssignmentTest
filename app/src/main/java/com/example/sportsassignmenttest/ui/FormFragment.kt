package com.example.sportsassignmenttest.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.sportsassignmenttest.R
import com.example.sportsassignmenttest.databinding.FragmentFormBinding
import com.example.sportsassignmenttest.model.Salary
import com.example.sportsassignmenttest.ui.viewmodel.SalaryViewModel
import com.example.sportsassignmenttest.ui.viewmodel.SharedViewModel

class FormFragment : Fragment() {

    private val salaryViewModel : SalaryViewModel by viewModels()
    private val sharedViewModel : SharedViewModel by viewModels()

    private var _binding : FragmentFormBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        _binding = FragmentFormBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnSubmitSalary.setOnClickListener {
            insertSalary()
        }
    }

    private fun insertSalary() {
        val name = binding.tietName.text.toString()
        val amount = binding.tietSalary.text.toString().toDouble()
        val designation = binding.tietDesignation.text.toString()

        val validation = sharedViewModel.validateData(name, designation)

        if(validation){
            val salary = Salary(
                name,
                amount,
                designation
            )
            salaryViewModel.insertSalary(salary)
            binding.tietDesignation.text = null
            binding.tietName.text = null
            binding.tietSalary.text = null
            findNavController().navigate(R.id.action_formFragment_to_listFragment)
            Toast.makeText(this.activity,"Salary Added Successfully!",Toast.LENGTH_SHORT).show()
        }
        else{
            Toast.makeText(this.activity,"Please fill the details",Toast.LENGTH_SHORT).show()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}