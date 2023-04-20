package com.example.mealtalk


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mealtalk.databinding.FragmentHomeFragmentBinding


class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeFragmentBinding
    private lateinit var navController: NavController
    private lateinit var recyclerView: RecyclerView
    private lateinit var items: ArrayList<Meal>
    private lateinit var homeMvvM:HomeViewMode
    private lateinit var myadapter: MyAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =FragmentHomeFragmentBinding.inflate(inflater,container,false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        homeMvvM=ViewModelProvider(this)[HomeViewMode::class.java]
        items = arrayListOf()
        recyclerView = binding.recycler
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(context)
        myadapter = MyAdapter(items)
        recyclerView.adapter = myadapter
        navController = findNavController()
        homeMvvM.getMyData()
        homeMvvM.observeLiveData()
        observeItemData()
        prepareREcyclerView()

        myadapter.setOnItemClickListener(object : MyAdapter.OnItemClickListener {
        override fun onItemClick(position: Int, currentItem: Meal) {
            val bundle = Bundle().apply {
                putString("idIngredient", currentItem.idIngredient)
                putString("strDescription", currentItem.strDescription)
            }
            navController.navigate(R.id.action_homeFrgment_to_secondFragment,bundle)
   }})

    }
    private fun prepareREcyclerView() {
        binding.recycler.apply {
            layoutManager= LinearLayoutManager(activity,LinearLayoutManager.VERTICAL,false)
            adapter = myadapter
        }
    }

    private fun observeItemData() {
        homeMvvM.listMeal.observe(requireActivity()){
            myadapter.setMeals(it as ArrayList<Meal>)
        }
    }

}


