package com.univ_amu.food_scanner.ui;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.univ_amu.food_scanner.data.Food;
import com.univ_amu.food_scanner.data.Quantity;
import com.univ_amu.food_scanner.databinding.FoodItemBinding;
import com.univ_amu.food_scanner.databinding.QuantityItemBinding;

import java.util.Objects;


public class QuantityListAdapter extends ListAdapter<Quantity, QuantityListAdapter.ViewHolder> {

        QuantityListAdapter() {
            super(diffUtilCallback);
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int type) {

            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            QuantityItemBinding binding =
                    QuantityItemBinding.inflate(inflater, parent, false);
            return new ViewHolder(binding);

        }


        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            Quantity quantity = getItem(position);
            holder.bind(quantity);
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            private final QuantityItemBinding binding;
            public Quantity quantity;
            public ViewHolder(QuantityItemBinding quantityItemBinding) {
                super(quantityItemBinding.getRoot());
                this.binding = quantityItemBinding;
                this.binding.setViewHolder(this);
            }
            void bind(Quantity quantity) {
                this.quantity=quantity;
                this.binding.invalidateAll();
            }


        }

        private static final DiffUtil.ItemCallback<Quantity> diffUtilCallback =
                new DiffUtil.ItemCallback<Quantity>() {
                    @Override
                    public boolean areItemsTheSame(Quantity oldQuantity, Quantity newQuantity) {
                        return oldQuantity.foodCode.equals(newQuantity.foodCode);
                        //return Objects.equals(oldQuantity.level, newQuantity.level);
                    }
                    @Override
                    public boolean areContentsTheSame(Quantity oldQuantity, Quantity newQuantity) {
                        return this.areItemsTheSame(oldQuantity, newQuantity);
                    }
                };

    }

