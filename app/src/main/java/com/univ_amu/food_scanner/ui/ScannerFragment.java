package com.univ_amu.food_scanner.ui;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Application;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.Result;
import com.univ_amu.food_scanner.R;
import com.univ_amu.food_scanner.data.Repository;
import com.univ_amu.food_scanner.databinding.ActivityMainBinding;
import com.univ_amu.food_scanner.databinding.FragmentFoodBinding;
import com.univ_amu.food_scanner.databinding.FragmentScannerBinding;

import java.util.Collections;

public class ScannerFragment extends Fragment {

    private FragmentScannerBinding binding;

    private Repository repository;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentScannerBinding.inflate(inflater, container, false);

        return binding.getRoot();
    }

    private void requestPermission() {
        requestPermissions(new String[]{android.Manifest.permission.CAMERA}, 0);
    }

    private boolean hasNoPermissions() {
        return ContextCompat.checkSelfPermission(this.getContext(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED;
    }

    private void navigateUp() {

        Navigation.findNavController(binding.getRoot()).navigateUp();
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.repository = new Repository(getContext());
    }

    @Override
    public void onStart() {
        super.onStart();
        if (hasNoPermissions()) {
            requestPermission();
        }
        if (hasNoPermissions()) {
            navigateUp();
        }
    }

    @Override
    public void onResume() {

        super.onResume();
        binding.scanner.startCamera();
        binding.scanner.setFormats(Collections.singletonList(BarcodeFormat.EAN_13));
        binding.scanner.setResultHandler(this::handleResult);
    }

    @Override
    public void onPause() {
        super.onPause();
        binding.scanner.stopCamera();
    }

    private void handleResult(Result result) {

        Log.i("FOOD_SCANNER", result.getText());
        String code = result.getText();
        repository.downloadFood(code).observe(this, success -> {
            if (success) {
                onFoodDetected(code);
            }
            else{
                onUnknownBarcode();
            }
        });
    }


    private void onFoodDetected(String code) {
        /* TODO : naviguer vers le fragment FoodFragment */
        NavDirections action = ScannerFragmentDirections.actionScannerFragmentToFoodFragment(code);
        Navigation.findNavController(binding.getRoot()).navigate(action);
    }

    private void onUnknownBarcode() {
        new AlertDialog.Builder(getContext())
                .setTitle(getString(R.string.unknown_barcode))
                .setPositiveButton(android.R.string.ok, (dialog, which) -> navigateUp())
                .setIcon(android.R.drawable.ic_dialog_info)
                .show();
    }


}
