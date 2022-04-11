package com.example.pickers2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.pickers2.databinding.FragmentCallingBinding

class CallingFragment : Fragment() {

    private lateinit var binding:FragmentCallingBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentCallingBinding.inflate(inflater,container,false)
        return binding.root
    }


}