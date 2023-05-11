package com.example.thepetzoneapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import com.example.thepetzoneapp.databinding.FragmentTankListBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class TankListFragment : Fragment() {
    private var _binding: FragmentTankListBinding? = null
    private val binding get() = _binding!!
    lateinit var dbRef : DatabaseReference
    private val viewModel : TankViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTankListBinding.inflate(inflater,container,false)
        dbRef = Firebase.database.reference
        viewModel.observableTankList.observe(viewLifecycleOwner, Observer {tankList ->
            binding.recyclerView.adapter = TankAdapter(tankList)
        })
        binding.addTankButton.setOnClickListener {
            viewModel.addTank()
            binding.addTankButton.findNavController().navigate(TankListFragmentDirections.actionTankListFragmentToTankInfoUserInputFragment(viewModel.tankNum))
        }
        return binding.root
    }
}