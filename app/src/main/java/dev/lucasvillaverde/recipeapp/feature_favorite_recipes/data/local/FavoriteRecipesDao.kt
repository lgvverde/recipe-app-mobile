package dev.lucasvillaverde.recipeapp.feature_favorite_recipes.data.local

import androidx.room.Dao
import androidx.room.Query
import dev.lucasvillaverde.recipeapp.core.data.local.model.RecipeEntity

@Dao
interface FavoriteRecipesDao {
    @Query("SELECT * FROM recipes WHERE isFavorite = 1 ORDER BY name ASC")
    suspend fun getFavoriteRecipes(): List<RecipeEntity>

    @Query("UPDATE recipes SET isFavorite = 0 WHERE id = :id")
    fun removeRecipeFromFavorite(id: Int)
}