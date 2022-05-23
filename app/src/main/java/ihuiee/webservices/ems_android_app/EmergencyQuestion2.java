package ihuiee.webservices.ems_android_app;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import java.util.HashMap;

public class EmergencyQuestion2 extends Fragment {

    Button A1;
    Button A2;
    Button A3;
    ImageButton backBtn;
    TextView tvAnswers;
    String anw2;
    Bundle bundle = new Bundle();
    //ArrayList<String> call;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_emergency_question2, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        assert getArguments() != null;
        HashMap<String, String> call = (HashMap<String, String>) getArguments().getSerializable("call");

        FragmentManager manager = getParentFragmentManager();

        A1 = view.findViewById(R.id.answer_1);
        A2 = view.findViewById(R.id.answer_2);
        A3 = view.findViewById(R.id.answer_3);
        backBtn = view.findViewById(R.id.back_btn);

        A1.setOnClickListener(v -> {
            tvAnswers = view.findViewById(R.id.q1a1);
            anw2 = tvAnswers.getText().toString();

            //call.add(anw2);
            call.put("Μήνας Εγκυμοσύνης", anw2);
            //bundle.putStringArrayList("call", call);
            bundle.putSerializable("call", call);

            Fragment question4 = new EmergencyQuestion4();
            question4.setArguments(bundle);
            manager.beginTransaction()
                    .replace(R.id.fragment_container, question4)
                    .commit();
        });

        A2.setOnClickListener(v -> {
            tvAnswers = view.findViewById(R.id.q1a1);
            anw2 = tvAnswers.getText().toString();

            //call.add(anw2);
            call.put("Μήνας Εγκυμοσύνης", anw2);
            //bundle.putStringArrayList("call", call);
            bundle.putSerializable("call", call);

            Fragment question4 = new EmergencyQuestion4();
            question4.setArguments(bundle);
            manager.beginTransaction()
                    .replace(R.id.fragment_container, question4)
                    .commit();
        });

        A3.setOnClickListener(v -> {
            tvAnswers = view.findViewById(R.id.q1a1);
            anw2 = tvAnswers.getText().toString();

            //call.add(anw2);
            call.put("Μήνας Εγκυμοσύνης", anw2);
            //bundle.putStringArrayList("call", call);
            bundle.putSerializable("call", call);

            Fragment question4 = new EmergencyQuestion4();
            question4.setArguments(bundle);
            manager.beginTransaction()
                    .replace(R.id.fragment_container, question4)
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