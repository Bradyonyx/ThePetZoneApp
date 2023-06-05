package com.example.thepetzoneapp

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.thepetzoneapp.databinding.FragmentTankListBinding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class TankListFragment : Fragment() {
    private var _binding: FragmentTankListBinding? = null
    private val binding get() = _binding!!
    lateinit var dbRef : DatabaseReference
    val tankList = mutableListOf<Tank>()
    private val viewModel : TankViewModel by activityViewModels ()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTankListBinding.inflate(inflater,container,false)
        val adapter = TankAdapter(viewModel.tankList)
        binding.recyclerView.adapter = adapter
        dbRef = Firebase.database.reference
        dbRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val allDBEntries = snapshot.children
                tankList.clear()
                for(listOfTanks in allDBEntries) {
                    for(allTanks in listOfTanks.children){
                        for(singleTank in allTanks.children) {
                            val tankNum = singleTank.child("tankNum").getValue().toString().toInt()
                            val tankName = singleTank.child("tankName").getValue().toString()
                            val tankSize = singleTank.child("tankSize").getValue().toString().toDouble()
                            val numFish = singleTank.child("numFish").getValue().toString().toInt()
                            val avgFishLength = singleTank.child("avgFishLength").getValue().toString().toInt()
                            val planted = singleTank.child("planted").getValue().toString().toBoolean()
                            val waterChangePercent = singleTank.child("waterChangePercent").getValue().toString()
                            tankList.add(Tank(tankNum,tankName,tankSize,numFish,avgFishLength,planted,waterChangePercent))
                        }
                    }
                }

                viewModel.setTankList(tankList)
                adapter.notifyDataSetChanged()
            }
            override fun onCancelled(error: DatabaseError) {
                Log.w("TankListFragment", "Failed to read value.", error.toException())
            }
        })
        binding.addTankButton.setOnClickListener {
            viewModel.addTank()
            binding.addTankButton.findNavController().navigate(TankListFragmentDirections.actionTankListFragmentToTankInfoUserInputFragment(viewModel.tankNumber))
        }
        binding.removeTankButton.setOnClickListener {
            dbRef.removeValue()
        }
        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.options_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return NavigationUI.onNavDestinationSelected(item, requireView().findNavController())
                || super.onOptionsItemSelected(item)
    }
}