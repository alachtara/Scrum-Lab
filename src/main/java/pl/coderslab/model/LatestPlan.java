//package pl.coderslab.model;
//
//import java.util.List;
//
//public class LatestPlan {
//    List<String> dayName;
//    List<String> mealName;
//    List<String> recipeName;
//    List<String> recipeDescription;
//    List<Integer> recipeId;
//
//    public LatestPlan(List<String> dayName, List<String> mealName, List<String> recipeName, List<String> recipeDescription, List<Integer> recipeId) {
//        this.dayName = dayName;
//        this.mealName = mealName;
//        this.recipeName = recipeName;
//        this.recipeDescription = recipeDescription;
//        this.recipeId = recipeId;
//    }
//
//    public LatestPlan() {
//    }
//
//    public List<String> getDayName() {
//        return dayName;
//    }
//
//    public void setDayName(List<String> dayName) {
//        this.dayName = dayName;
//    }
//
//    public List<String> getMealName() {
//        return mealName;
//    }
//
//    public void setMealName(List<String> mealName) {
//        this.mealName = mealName;
//    }
//
//    public List<String> getRecipeName() {
//        return recipeName;
//    }
//
//    public void setRecipeName(List<String> recipeName) {
//        this.recipeName = recipeName;
//    }
//
//    public List<String> getRecipeDescription() {
//        return recipeDescription;
//    }
//
//    public void setRecipeDescription(List<String> recipeDescription) {
//        this.recipeDescription = recipeDescription;
//    }
//
//    public List<Integer> getRecipeId() {
//        return recipeId;
//    }
//
//    public void setRecipeId(List<Integer> recipeId) {
//        this.recipeId = recipeId;
//    }
//
//    @Override
//    public String toString(){
//        return "Last plan" + "\n" +
//                "Day: " + dayName + "\n" +
//                "Meal: " + mealName + "\n" +
//                "Recipe: " + recipeName + "\n" +
//                "Recipe description: " + recipeDescription + "\n" +
//                "Recipe id: " + recipeId;
//    }
//}

package pl.coderslab.model;

import java.util.List;

public class LatestPlan {
    String dayName;
    String mealName;
    String recipeName;
    String recipeDescription;
    Integer recipeId;

    public LatestPlan() {
    }

    public LatestPlan(String dayName, String mealName, String recipeName, String recipeDescription, Integer recipeId) {
        this.dayName = dayName;
        this.mealName = mealName;
        this.recipeName = recipeName;
        this.recipeDescription = recipeDescription;
        this.recipeId = recipeId;
    }

    public LatestPlan(String mealName, String recipeName, String recipeDescription) {
        this.mealName = mealName;
        this.recipeName = recipeName;
        this.recipeDescription = recipeDescription;
    }

    public LatestPlan(String mealName, String recipeName, String recipeDescription, Integer recipeId) {
        this.mealName = mealName;
        this.recipeName = recipeName;
        this.recipeDescription = recipeDescription;
        this.recipeId = recipeId;
    }

    public String getDayName() {
        return dayName;
    }

    public void setDayName(String dayName) {
        this.dayName = dayName;
    }

    public String getMealName() {
        return mealName;
    }

    public void setMealName(String mealName) {
        this.mealName = mealName;
    }

    public String getRecipeName() {
        return recipeName;
    }

    public void setRecipeName(String recipeName) {
        this.recipeName = recipeName;
    }

    public String getRecipeDescription() {
        return recipeDescription;
    }

    public void setRecipeDescription(String recipeDescription) {
        this.recipeDescription = recipeDescription;
    }

    public Integer getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(Integer recipeId) {
        this.recipeId = recipeId;
    }

    @Override
    public String toString(){
        return
                "Meal: " + mealName + "\n" +
                "Recipe: " + recipeName + "\n" +
                "Recipe description: " + recipeDescription + "\n" +
                "Recipe id: " + recipeId + "\n";
    }
}
