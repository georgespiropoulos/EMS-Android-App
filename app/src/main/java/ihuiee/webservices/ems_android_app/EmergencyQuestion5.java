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
import android.widget.TextView;

import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class EmergencyQuestion5 extends Fragment {

    Button A1;
    Button A2;
    ImageButton backBtn;
    TextView tvAnswers;
    String anw1;
    FirebaseFirestore fb = FirebaseFirestore.getInstance();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_emergency_question5, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        assert getArguments() != null;
        ArrayList<String> call = getArguments().getStringArrayList("call");
        System.out.println(call.toString());
        FragmentManager manager = getParentFragmentManager();

        A1 = view.findViewById(R.id.answer_1);
        A2 = view.findViewById(R.id.answer_2);
        backBtn = view.findViewById(R.id.back_btn);

        A1.setOnClickListener(v -> {
            tvAnswers = view.findViewById(R.id.q1a1);
            anw1 = tvAnswers.getText().toString();

            call.add(anw1);
            fb.collection("Calls").add(call);

            Fragment thanksPage = new ThankYouPage();
            manager.beginTransaction()
                    .replace(R.id.fragment_container, thanksPage)
                    .commit();

        });
        A2.setOnClickListener(v -> {
            tvAnswers = view.findViewById(R.id.q2a2);
            anw1 = tvAnswers.getText().toString();

            call.add(anw1);
            fb.collection("Calls").add(call);

            Fragment thanksPage = new ThankYouPage();
            manager.beginTransaction()
                    .replace(R.id.fragment_container, thanksPage)
                    .commit();
        });

        backBtn.setOnClickListener(v -> {
            Fragment startingPage = new StartingPage();
            manager.beginTransaction()
                    .replace(R.id.fragment_container, startingPage)
                    .commit();
        });
    }
}