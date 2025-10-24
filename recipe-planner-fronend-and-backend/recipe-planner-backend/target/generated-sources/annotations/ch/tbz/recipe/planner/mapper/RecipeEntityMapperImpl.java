package ch.tbz.recipe.planner.mapper;

import ch.tbz.recipe.planner.domain.Ingredient;
import ch.tbz.recipe.planner.domain.Recipe;
import ch.tbz.recipe.planner.entities.IngredientEntity;
import ch.tbz.recipe.planner.entities.RecipeEntity;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-10-24T09:58:19+0200",
    comments = "version: 1.5.5.Final, compiler: Eclipse JDT (IDE) 3.44.0.v20251001-1143, environment: Java 21.0.8 (Eclipse Adoptium)"
)
@Component
public class RecipeEntityMapperImpl implements RecipeEntityMapper {

    @Override
    public Recipe entityToDomain(RecipeEntity recipeEntity) {
        if ( recipeEntity == null ) {
            return null;
        }

        Recipe recipe = new Recipe();

        recipe.setDescription( recipeEntity.getDescription() );
        recipe.setId( recipeEntity.getId() );
        recipe.setImageUrl( recipeEntity.getImageUrl() );
        recipe.setIngredients( ingredientEntityListToIngredientList( recipeEntity.getIngredients() ) );
        recipe.setName( recipeEntity.getName() );

        return recipe;
    }

    @Override
    public RecipeEntity domainToEntity(Recipe recipe) {
        if ( recipe == null ) {
            return null;
        }

        RecipeEntity recipeEntity = new RecipeEntity();

        recipeEntity.setDescription( recipe.getDescription() );
        recipeEntity.setId( recipe.getId() );
        recipeEntity.setImageUrl( recipe.getImageUrl() );
        recipeEntity.setIngredients( ingredientListToIngredientEntityList( recipe.getIngredients() ) );
        recipeEntity.setName( recipe.getName() );

        return recipeEntity;
    }

    protected Ingredient ingredientEntityToIngredient(IngredientEntity ingredientEntity) {
        if ( ingredientEntity == null ) {
            return null;
        }

        Ingredient ingredient = new Ingredient();

        ingredient.setAmount( ingredientEntity.getAmount() );
        ingredient.setComment( ingredientEntity.getComment() );
        ingredient.setId( ingredientEntity.getId() );
        ingredient.setName( ingredientEntity.getName() );
        ingredient.setUnit( ingredientEntity.getUnit() );

        return ingredient;
    }

    protected List<Ingredient> ingredientEntityListToIngredientList(List<IngredientEntity> list) {
        if ( list == null ) {
            return null;
        }

        List<Ingredient> list1 = new ArrayList<Ingredient>( list.size() );
        for ( IngredientEntity ingredientEntity : list ) {
            list1.add( ingredientEntityToIngredient( ingredientEntity ) );
        }

        return list1;
    }

    protected IngredientEntity ingredientToIngredientEntity(Ingredient ingredient) {
        if ( ingredient == null ) {
            return null;
        }

        IngredientEntity ingredientEntity = new IngredientEntity();

        ingredientEntity.setAmount( ingredient.getAmount() );
        ingredientEntity.setComment( ingredient.getComment() );
        ingredientEntity.setId( ingredient.getId() );
        ingredientEntity.setName( ingredient.getName() );
        ingredientEntity.setUnit( ingredient.getUnit() );

        return ingredientEntity;
    }

    protected List<IngredientEntity> ingredientListToIngredientEntityList(List<Ingredient> list) {
        if ( list == null ) {
            return null;
        }

        List<IngredientEntity> list1 = new ArrayList<IngredientEntity>( list.size() );
        for ( Ingredient ingredient : list ) {
            list1.add( ingredientToIngredientEntity( ingredient ) );
        }

        return list1;
    }
}
