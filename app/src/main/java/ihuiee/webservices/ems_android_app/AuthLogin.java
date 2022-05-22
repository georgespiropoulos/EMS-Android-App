package ihuiee.webservices.ems_android_app;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;


public class AuthLogin extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_auth_login, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Button loginBtn = view.findViewById(R.id.login);

        FragmentManager manager = getParentFragmentManager();
        Fragment startingPage = new StartingPage();

        loginBtn.setOnClickListener(v -> manager.beginTransaction()
                .replace(R.id.fragment_container, startingPage)
                .commit());
    }
}