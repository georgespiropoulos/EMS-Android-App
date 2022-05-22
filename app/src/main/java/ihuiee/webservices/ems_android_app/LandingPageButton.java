package ihuiee.webservices.ems_android_app;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

public class LandingPageButton extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.landing_page_button, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        FragmentManager manager = getParentFragmentManager();
        Fragment authLogin = new AuthLogin();
        ImageButton loginButton = view.findViewById(R.id.imageButton);
        loginButton.setOnClickListener(v -> manager.beginTransaction()
                .replace(R.id.fragment_container, authLogin)
                .commit());

    }
}