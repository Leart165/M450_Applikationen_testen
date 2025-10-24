package ch.tbz.recipe.planner.mapper;

import ch.tbz.recipe.planner.domain.Ingredient;
import ch.tbz.recipe.planner.entities.IngredientEntity;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-10-24T09:43:07+0200",
    comments = "version: 1.5.5.Final, compiler: Eclipse JDT (IDE) 3.44.0.v20251001-1143, environment: Java 21.0.8 (Eclipse Adoptium)"
)
@Component
public class IngredientEntityMapperImpl implements IngredientEntityMapper {

    @Override
    public Ingredient entityToDomain(IngredientEntity ingredientEntity) {
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

    @Override
    public IngredientEntity domainToEntity(Ingredient ingredient) {
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

    @Override
    public List<Ingredient> entitiesToDomains(List<IngredientEntity> ingredientEntities) {
        if ( ingredientEntities == null ) {
            return null;
        }

        List<Ingredient> list = new ArrayList<Ingredient>( ingredientEntities.size() );
        for ( IngredientEntity ingredientEntity : ingredientEntities ) {
            list.add( entityToDomain( ingredientEntity ) );
        }

        return list;
    }

    @Override
    public List<IngredientEntity> domainsToEntities(List<Ingredient> ingredients) {
        if ( ingredients == null ) {
            return null;
        }

        List<IngredientEntity> list = new ArrayList<IngredientEntity>( ingredients.size() );
        for ( Ingredient ingredient : ingredients ) {
            list.add( domainToEntity( ingredient ) );
        }

        return list;
    }
}
