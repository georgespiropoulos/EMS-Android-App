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
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import java.util.HashMap;

public class EmergencyQuestions extends Fragment {

    Button A1;
    Button A2;
    Button A3;
    Button A4;
    Button A5;
    TextView A5text;
    TextView A4text;
    ImageButton backBtn;
    ImageButton confirmSituation;
    EditText anotherSituation;
    TextView tvAnswers;
    String anw1;
    Bundle bundle = new Bundle();
    //ArrayList<String> call = new ArrayList<>();
    HashMap<String, String> call = new HashMap<>();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_emergency_questions, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        FragmentManager manager = getParentFragmentManager();

        A1 = view.findViewById(R.id.answer_1);
        A2 = view.findViewById(R.id.answer_2);
        A3 = view.findViewById(R.id.answer_3);
        A4 = view.findViewById(R.id.answer_4);
        A5 = view.findViewById(R.id.answer_5);
        A5text = view.findViewById(R.id.q5a5);
        A4text = view.findViewById(R.id.q4a4);
        anotherSituation = view.findViewById(R.id.another_situation);
        backBtn = view.findViewById(R.id.back_btn);
        confirmSituation = view.findViewById(R.id.confirm_situation);

        A1.setOnClickListener(v -> {
            tvAnswers = view.findViewById(R.id.q1a1);
            anw1 = tvAnswers.getText().toString();

            //call.add(anw1);
            call.put("Περιστατικό", anw1);
            //bundle.putStringArrayList("call", call);
            bundle.putSerializable("call", call);

            Fragment question3 = new EmergencyQuestion3();
            question3.setArguments(bundle);
            manager.beginTransaction()
                    .replace(R.id.fragment_container, question3)
                    .commit();
        });
        A2.setOnClickListener(v -> {
            tvAnswers = view.findViewById(R.id.q2a2);
            anw1 = tvAnswers.getText().toString();

            //call.add(anw1);
            call.put("Περιστατικό", anw1);
            //bundle.putStringArrayList("call", call);
            bundle.putSerializable("call", call);

            Fragment question3 = new EmergencyQuestion3();
            question3.setArguments(bundle);
            manager.beginTransaction()
                    .replace(R.id.fragment_container, question3)
                    .commit();
        });
        A3.setOnClickListener(v -> {
            tvAnswers = view.findViewById(R.id.q3a3);
            anw1 = tvAnswers.getText().toString();

            //call.add(anw1);
            call.put("Περιστατικό", anw1);
            //bundle.putStringArrayList("call", call);
            bundle.putSerializable("call", call);

            Fragment question2 = new EmergencyQuestion2();
            question2.setArguments(bundle);
            manager.beginTransaction()
                    .replace(R.id.fragment_container, question2)
                    .commit();
        });
        A4.setOnClickListener(v -> {
            tvAnswers = view.findViewById(R.id.q4a4);
            anw1 = tvAnswers.getText().toString();

            //call.add(anw1);
            call.put("Περιστατικό", anw1);
            //bundle.putStringArrayList("call", call);
            bundle.putSerializable("call", call);

            Fragment question3 = new EmergencyQuestion3();
            question3.setArguments(bundle);
            manager.beginTransaction()
                    .replace(R.id.fragment_container, question3)
                    .commit();
        });
        A5.setOnClickListener(v -> {
            A1.setEnabled(false);
            A2.setEnabled(false);
            A3.setEnabled(false);
            A4.setVisibility(View.INVISIBLE);
            A4text.setVisibility(View.INVISIBLE);
            A5.setVisibility(View.INVISIBLE);
            A5text.setVisibility(View.INVISIBLE);
            anotherSituation.setVisibility(View.VISIBLE);
            confirmSituation.setVisibility(View.VISIBLE);
        });

        confirmSituation.setOnClickListener(v -> {
            //call.add(anw1);
            call.put("Περιστατικό", anw1);
            //bundle.putStringArrayList("call", call);
            bundle.putSerializable("call", call);

            Fragment question3 = new EmergencyQuestion3();
            question3.setArguments(bundle);
            manager.beginTransaction()
                    .replace(R.id.fragment_container, question3)
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