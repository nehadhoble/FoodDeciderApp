package com.example.dinnerdeciderapp

import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import com.example.fooddecider.R
import com.example.fooddecider.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater,container,false)
        val clickAnimation = AnimationUtils.loadAnimation(requireContext(), R.anim.button_click_animation)

        binding.txtWhoIsHungry.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                // No action needed before text changes
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                // Update the text of tvName with the entered text
                binding.tvName.text = "welcome "+s.toString()
            }

            override fun afterTextChanged(s: Editable?) {
                // No action needed after text changes
            }
        })

        binding.btnDiveInToDecider.setOnClickListener {
            binding.btnDiveInToDecider.startAnimation(clickAnimation)
            if(!TextUtils.isEmpty(binding.txtWhoIsHungry.text.toString())){
                val bundle = bundleOf("user_name" to binding.txtWhoIsHungry.text.toString())
                it.findNavController().navigate(R.id.action_homeFragment_to_decideFoodFragment, bundle)
            }else{
                Toast.makeText(requireContext(), "Please enter your good name...", Toast.LENGTH_LONG).show()
            }

        }
        return binding.root
    }

}