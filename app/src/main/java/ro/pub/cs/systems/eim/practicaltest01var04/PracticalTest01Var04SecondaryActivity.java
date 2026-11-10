package ro.pub.cs.systems.eim.practicaltest01var04;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class PracticalTest01Var04SecondaryActivity extends AppCompatActivity {

    private TextView studentNameTextView, groupNameTextView;
    private Button okButton, cancelButton;
    ButtonClickListener buttonClickListener = new ButtonClickListener();

    private class ButtonClickListener implements View.OnClickListener {
        public void onClick(View view) {
            int viewId = view.getId();
            if (viewId == R.id.ok_button) {
                setResult(RESULT_OK, null);
            } else if (viewId == R.id.cancel_button) {
                setResult(RESULT_CANCELED, null);
            } else {
                throw new IllegalStateException("Unexpected value: " + view.getId());
            }

            finish();
        }
    }

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        setContentView(R.layout.activity_practical_test01_var04_secondary);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        studentNameTextView = (TextView)findViewById(R.id.student_name_text_view);
        groupNameTextView = (TextView)findViewById(R.id.group_name_text_view);

        Intent intent = getIntent();
        if (intent != null
                && intent.getExtras().containsKey("STUDENT_NAME")) {
            String studentName = intent.getStringExtra("STUDENT_NAME");
            studentNameTextView.setText(String.valueOf(studentName));
        }

        if (intent != null
                && intent.getExtras().containsKey("GROUP_NAME")) {
            String groupName = intent.getStringExtra("GROUP_NAME");
            groupNameTextView.setText(String.valueOf(groupName));
        }

        okButton = (Button)findViewById(R.id.ok_button);
        cancelButton = (Button)findViewById(R.id.cancel_button);

        okButton.setOnClickListener(buttonClickListener);
        cancelButton.setOnClickListener(buttonClickListener);
    }
}