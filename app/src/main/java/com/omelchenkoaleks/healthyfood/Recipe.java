package com.omelchenkoaleks.healthyfood;

public class Recipe {
    /**
     * ссылка на картинку рецепта
     * название рецепта
     * ссылка на страницу рецепта
     */

    private String linkImage;
    private String nameRecipe;
    private String linkPageRecipe;

    public Recipe(String linkImage, String nameRecipe, String linkPageRecipe) {
        this.linkImage = linkImage;
        this.nameRecipe = nameRecipe;
        this.linkPageRecipe = linkPageRecipe;
    }
}
