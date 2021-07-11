package dev.lucasvillaverde.recipeapp.feature_recipe.presenter.recipe_details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import dev.lucasvillaverde.recipeapp.R
import dev.lucasvillaverde.recipeapp.databinding.FragmentIngredientsBinding
import dev.lucasvillaverde.recipeapp.feature_recipe.data.local.entities.RecipeEntity

class IngredientsFragment : Fragment(R.layout.fragment_ingredients) {
    private lateinit var binding: FragmentIngredientsBinding
    private var recipeId: Int? = null
    private val recipeDetailsViewModel: RecipeDetailsViewModel by viewModels(
        ownerProducer = { requireParentFragment() }
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentIngredientsBinding.inflate(layoutInflater)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let { arguments ->
            recipeId = arguments.getInt(INGREDIENTS_FRAGMENT_RECIPE_ID)
            recipeDetailsViewModel.getMeal(recipeId!!).observe(viewLifecycleOwner, { updateUI(it) })
        }
    }

    private fun updateUI(recipe: RecipeEntity?) {
        recipe?.let {
            val ingredientsListText = getIngredientList(recipe.getIngredients())
            val ingredientsMeasureListText = getMeasuresList(recipe.getMeasures())
            binding.txtIngredients.text = ingredientsListText
            binding.txtMeasures.text = ingredientsMeasureListText
            binding.ingredientsRoot.visibility = View.VISIBLE
        }

    }

    private fun getIngredientList(list: ArrayList<String?>) =
        list.filter { !it.isNullOrBlank() }.joinToString(prefix = "• ", separator = "\n\n• ")

    private fun getMeasuresList(list: ArrayList<String?>) =
        list.filter { !it.isNullOrBlank() }.joinToString(separator = "\n\n")

    companion object {
        private const val INGREDIENTS_FRAGMENT_RECIPE_ID = "ingredients_fragment_recipe_id"

        fun newInstance(recipeId: Int) = IngredientsFragment().apply {
            arguments = bundleOf(INGREDIENTS_FRAGMENT_RECIPE_ID to recipeId)
        }
    }
}