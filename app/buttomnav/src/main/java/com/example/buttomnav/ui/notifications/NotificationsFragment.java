package com.example.buttomnav.ui.notifications;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.buttomnav.databinding.FragmentNotificationsBinding;

public class NotificationsFragment extends Fragment {

    private FragmentNotificationsBinding binding;

    IntentFilter intentFilter;

    int deviceHealth;

    String currentBatteryHealth="Batter Health";

    int batterLevel;

    TextView textView;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        NotificationsViewModel notificationsViewModel =
                new ViewModelProvider(this).get(NotificationsViewModel.class);

        binding = FragmentNotificationsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        textView = binding.textNotifications;
        final Button checkStatus = binding.buttonCheck;
        notificationsViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);

        intentFilter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);

        checkStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().registerReceiver(broadcastReceiver,intentFilter);
            }
        });
        return root;
    }

    private BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {

            deviceHealth = intent.getIntExtra(BatteryManager.EXTRA_HEALTH, 0);

            if(deviceHealth == BatteryManager.BATTERY_HEALTH_COLD){
                textView.setText(currentBatteryHealth+" = Cold");
            }

            if(deviceHealth == BatteryManager.BATTERY_HEALTH_DEAD){
                textView.setText(currentBatteryHealth+" = Dead");
            }

            if(deviceHealth == BatteryManager.BATTERY_HEALTH_GOOD){
                textView.setText(currentBatteryHealth+" = Good");
            }

            if(deviceHealth == BatteryManager.BATTERY_HEALTH_OVERHEAT){
                textView.setText(currentBatteryHealth+" = OverHeat");
            }

            if(deviceHealth == BatteryManager.BATTERY_HEALTH_OVER_VOLTAGE){
                textView.setText(currentBatteryHealth+" = Over voltage");
            }

            if(deviceHealth == BatteryManager.BATTERY_HEALTH_UNKNOWN){
                textView.setText(currentBatteryHealth+" = Unknown");
            }

            if(deviceHealth == BatteryManager.BATTERY_HEALTH_UNSPECIFIED_FAILURE){
                textView.setText(currentBatteryHealth+" = Unspecified Failure");
            }
        }
    };

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}