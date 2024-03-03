package com.example.fooddecider

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.fooddecider.databinding.FragmentDecideFoodBinding
import com.example.fooddecider.viewmodel.FoodViewModel

class DecideFoodFragment : Fragment() {
    private lateinit var binding: FragmentDecideFoodBinding
    private lateinit var foodViewModel: FoodViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentDecideFoodBinding.inflate(inflater, container, false)
        var input = requireArguments().getString("user_name")
        val clickAnimation = AnimationUtils.loadAnimation(requireContext(), R.anim.button_click_animation)

        binding.tvName.text = "Welcome "+ input
        foodViewModel = ViewModelProvider(this).get(FoodViewModel::class.java)

        // Observe changes in selectedFood and update UI accordingly
        foodViewModel.selectedFood.observe(viewLifecycleOwner) { selectedFood ->
            binding.selectedFoodTxt.text = selectedFood
        }

        binding.decideBtn.setOnClickListener {
//            foodViewModel.decideOnFood()
            if (foodViewModel.foodList.isEmpty()) {
                // Show a toast message indicating that the food list is empty
                Toast.makeText(requireContext(), "Add some food first!", Toast.LENGTH_SHORT).show()
            } else {
                // Food list is not empty, proceed with deciding on food
                binding.decideBtn.startAnimation(clickAnimation)
                foodViewModel.decideOnFood()
            }
        }

        binding.addFoodBtn.setOnClickListener {
            binding.addFoodBtn.startAnimation(clickAnimation)
            val newFood = binding.addFoodTxt.text.toString()
            foodViewModel.addFood(newFood)
            binding.addFoodTxt.text.clear()
        }

        binding.clearFoodBtn.setOnClickListener {
            binding.clearFoodBtn.startAnimation(clickAnimation)
            binding.selectedFoodTxt.text = "....."
            foodViewModel.clearFoodList()
        }

        return binding.root
    }

}