package com.alparp.thirstydrinkfinder.ui.details

import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.alparp.thirstydrinkfinder.R
import com.alparp.thirstydrinkfinder.ui.IngredientAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class DrinkDetailsFragment : Fragment() {

    private lateinit var drinkDetailsViewModel: DrinkDetailsViewModel
    private lateinit var recyclerAdapter: IngredientAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        drinkDetailsViewModel =
            ViewModelProvider(requireActivity()).get(DrinkDetailsViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_drink_details, container, false)
        val textView: TextView = root.findViewById(R.id.name)
        val icon: ImageView = root.findViewById(R.id.icon)
        val instructions: TextView = root.findViewById(R.id.instructions)

        val pieChartContainer: ConstraintLayout = root.findViewById(R.id.pie_chart_container)
        val recyclerView: RecyclerView = root.findViewById(R.id.ingredients_list)
        recyclerAdapter = IngredientAdapter(requireContext())
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = recyclerAdapter

        drinkDetailsViewModel.item?.let { drink ->
            textView.text = drink.name
            instructions.text = drink.instructions
            Glide.with(requireContext()).load(drink.thumbImageUrl)
                .apply(RequestOptions().centerCrop())
                .into(icon)
            val ingredients = arrayListOf<String>()
            val ingredientsMeasures = arrayListOf<String>()
            drink.ingredient1?.let {
                ingredients.add(it)
                drink.measure1?.let {
                    ingredientsMeasures.add(it)
                }
            }
            drink.ingredient2?.let {
                ingredients.add(it)
                drink.measure2?.let {
                    ingredientsMeasures.add(it)
                }
            }
            drink.ingredient3?.let {
                ingredients.add(it)
                drink.measure3?.let {
                    ingredientsMeasures.add(it)
                }
            }
            drink.ingredient4?.let {
                ingredients.add(it)
                drink.measure4?.let {
                    ingredientsMeasures.add(it)
                }
            }
            drink.ingredient5?.let {
                ingredients.add(it)
                drink.measure5?.let {
                    ingredientsMeasures.add(it)
                }
            }
            drink.ingredient6?.let {
                ingredients.add(it)
                drink.measure6?.let {
                    ingredientsMeasures.add(it)
                }
            }
            drink.ingredient7?.let {
                ingredients.add(it)
                drink.measure7?.let {
                    ingredientsMeasures.add(it)
                }
            }
            drink.ingredient8?.let {
                ingredients.add(it)
                drink.measure8?.let {
                    ingredientsMeasures.add(it)
                }
            }
            drink.ingredient9?.let {
                ingredients.add(it)
                drink.measure9?.let {
                    ingredientsMeasures.add(it)
                }
            }
            drink.ingredient10?.let {
                ingredients.add(it)
                drink.measure10?.let {
                    ingredientsMeasures.add(it)
                }
            }
            drink.ingredient11?.let {
                ingredients.add(it)
                drink.measure11?.let {
                    ingredientsMeasures.add(it)
                }
            }
            drink.ingredient12?.let {
                ingredients.add(it)
                drink.measure12?.let {
                    ingredientsMeasures.add(it)
                }
            }
            drink.ingredient13?.let {
                ingredients.add(it)
                drink.measure13?.let {
                    ingredientsMeasures.add(it)
                }
            }
            drink.ingredient14?.let {
                ingredients.add(it)
                drink.measure14?.let {
                    ingredientsMeasures.add(it)
                }
            }
            drink.ingredient15?.let {
                ingredients.add(it)
                drink.measure15?.let {
                    ingredientsMeasures.add(it)
                }
            }
            recyclerAdapter.setListItems(ingredients)
            val measures = arrayListOf<Int>()
            var total = 0
            ingredientsMeasures.forEach { measure ->
                var m = 0
                val splits = measure.split(" ")
                try {
                    if (splits.size == 3) {
                        splits[0]?.toInt()?.let {
                            m += it * 4
                        }
                        if (splits[1]?.equals("1\\/2")) {
                            m += 2
                        } else if (splits[1]?.equals("1\\/4")) {
                            m += 1
                        } else if (splits[1]?.equals("3\\/4")) {
                            m += 3
                        }
                    }
                    if (splits.size == 2) {
                        if (splits[1]?.equals("1\\/2")) {
                            m += 2
                        } else if (splits[1]?.equals("1\\/4")) {
                            m += 1
                        } else if (splits[1]?.equals("3\\/4")) {
                            m += 3
                        } else {
                            splits[0]?.toInt()?.let {
                                m += it * 4
                            }
                        }
                    }
                }catch (e : Exception) {
                    Log.e("DrinkDetailsFragment", e.toString())
                }
                measures.add(m)
                total += m
            }

            var index = 0
            var currrentTotal = total
            measures.forEach { measure ->
                val pieSlice = inflater.inflate(R.layout.progress_bar, container, false)
                    .findViewById<ProgressBar>(R.id.stats_progressbar)
                val drawable = pieSlice.progressDrawable
                if (drawable != null) {
                    drawable.colorFilter = PorterDuffColorFilter(
                        ContextCompat.getColor(
                            requireContext(),
                            IngredientAdapter.colors[index++]
                        ), PorterDuff.Mode.SRC_IN
                    )
                }
                currrentTotal -= measure
                pieSlice.progress = currrentTotal*100/total
                pieChartContainer.addView(pieSlice)
            }
        }
        return root
    }
}