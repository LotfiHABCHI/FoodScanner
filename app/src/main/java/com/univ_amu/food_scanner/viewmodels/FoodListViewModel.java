package com.univ_amu.food_scanner.viewmodels;

import android.app.Application;
import android.util.Log;
import android.view.View;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import com.univ_amu.food_scanner.data.Food;
import com.univ_amu.food_scanner.data.Repository;
import com.univ_amu.food_scanner.ui.FoodListFragmentDirections;

import java.util.Date;
import java.util.List;
import java.util.Random;

public class FoodListViewModel extends AndroidViewModel {
    private final Repository repository;
    private final LiveData<List<Food>> foods;

    public FoodListViewModel(Application application) {

        super(application);
        this.repository = new Repository(application);
        this.foods = repository.getFoods();
    }

    public LiveData<List<Food>> foods() {
        return foods;
    }



    public void onSearch(View view) {
        NavDirections action = FoodListFragmentDirections.actionFoodListFragmentToScannerFragment();
        Navigation.findNavController(view).navigate(action);
    }

    /*public void onSearch(View view) {
        String code = new String();
        code="9565610235101";
        Log.i("FOOD SCANNER", "onSearch");
        //this.repository.addRandomFood(new Date());
        repository.downloadFood(code);

    }*/
}

    

    