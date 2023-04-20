package com.example.mealtalk

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.mealtalk.databinding.FragmentSecondBinding


class SecondFragment : Fragment() {
   private lateinit var binding :FragmentSecondBinding
    private lateinit var smvvm:SecondViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root


    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

/*        binding.toolbar.apply {
            binding.back.setOnClickListener {
                Navigation.findNavController(view).popBackStack()
            }
        }*/
        smvvm = ViewModelProvider(this)[SecondViewModel::class.java]
        val idIngredient = arguments?.getString("idIngredient")
        val strDescription = arguments?.getString("strDescription")
        binding.recipe.text = idIngredient.toString()
        binding.recipe.text = strDescription.toString()
        smvvm.recipeDescription.observe(viewLifecycleOwner){
                description-> binding.recipe.text =strDescription.toString()
        }
        smvvm.receiveData()
        smvvm.observeLiveData()
}}