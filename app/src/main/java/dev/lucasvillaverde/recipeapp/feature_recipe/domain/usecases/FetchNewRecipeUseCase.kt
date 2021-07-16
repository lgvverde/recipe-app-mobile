package dev.lucasvillaverde.recipeapp.feature_recipe.domain.usecases

import dev.lucasvillaverde.recipeapp.base.data.model.BaseResource
import dev.lucasvillaverde.recipeapp.base.domain.BaseUseCase
import dev.lucasvillaverde.recipeapp.feature_recipe.domain.repositories.RecipeRepository

class FetchNewRecipeUseCase(private val recipeRepository: RecipeRepository) :
    BaseUseCase<Nothing, Nothing>() {
    override suspend fun execute(params: Nothing): BaseResource<Nothing> =
        try {
            recipeRepository.fetchNewRecipe()
            BaseResource.Success()
        } catch (ex: Exception) {
            BaseResource.Error(ex.message ?: "Oops, something wrong happened.")
        }
}