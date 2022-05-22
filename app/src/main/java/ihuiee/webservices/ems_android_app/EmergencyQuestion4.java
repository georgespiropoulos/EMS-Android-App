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
import java.util.HashMap;

public class EmergencyQuestion4 extends Fragment {

    Button A1;
    Button A2;
    ImageButton backBtn;
    TextView tvAnswers;
    String anw1;
    Bundle bundle = new Bundle();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_emergency_question4, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        assert getArguments() != null;
        //ArrayList<String> call = getArguments().getStringArrayList("call");
        HashMap<String, String> call = (HashMap<String, String>) getArguments().getSerializable("call");
        FragmentManager manager = getParentFragmentManager();

        A1 = view.findViewById(R.id.answer_1);
        A2 = view.findViewById(R.id.answer_2);
        backBtn = view.findViewById(R.id.back_btn);

        A1.setOnClickListener(v -> {
            tvAnswers = view.findViewById(R.id.q1a1);
            anw1 = tvAnswers.getText().toString();

            //call.add(anw1);
            call.put("Αισθήσεις", anw1);
            //bundle.putSerializable("call", call);
            bundle.putSerializable("call", call);

            Fragment question5 = new EmergencyQuestion5();
            question5.setArguments(bundle);
            manager.beginTransaction()
                    .replace(R.id.fragment_container, question5)
                    .commit();
        });
        A2.setOnClickListener(v -> {
            tvAnswers = view.findViewById(R.id.q2a2);
            anw1 = tvAnswers.getText().toString();

            //call.add(anw1);
            call.put("Αισθήσεις", anw1);
            //bundle.putSerializable("call", call);
            bundle.putSerializable("call", call);

            Fragment question5 = new EmergencyQuestion5();
            question5.setArguments(bundle);
            manager.beginTransaction()
                    .replace(R.id.fragment_container, question5)
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