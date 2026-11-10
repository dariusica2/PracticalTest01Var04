package ro.pub.cs.systems.eim.practicaltest01var04;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class PracticalTest01Var04MainActivity extends AppCompatActivity {

    private Button navigateToSecondaryActivityButton, displayInformationButton;
    private CheckBox selectStudentCheckbox, selectGroupCheckbox;
    private EditText studentEditText, groupEditText;
    private TextView studentInformationTextView;
    private Toast toast;
    private ButtonClickListener buttonClickListener = new ButtonClickListener();

    private class ButtonClickListener implements View.OnClickListener {
        public void onClick(View view) {
            String studentText = studentEditText.getText().toString();
            String groupText = groupEditText.getText().toString();
            String newText = "";

            int id = view.getId();
            if (id == R.id.display_information_button) {
                boolean ok = true;
                if (selectStudentCheckbox.isChecked()) {
                    if (!studentText.isEmpty()) {
                        newText += studentText + " ";
                    } else {
                        ok = false;
                    }
                }

                if (selectGroupCheckbox.isChecked()) {
                    if (!groupText.isEmpty()) {
                        newText += groupText;
                    } else {
                        ok = false;
                    }
                }

                if (ok) {
                    studentInformationTextView.setText(newText);
                } else {
                    toast.show();
                    studentInformationTextView.setText("");
                }
            } else if (id == R.id.navigate_to_secondary_activity_button) {
                Intent intent = new Intent(getApplicationContext(), PracticalTest01Var04SecondaryActivity.class);
                intent.putExtra("STUDENT_NAME", studentEditText.getText().toString());
                intent.putExtra("GROUP_NAME", groupEditText.getText().toString());
                startActivityForResult(intent, 69);
            } else {
                throw new IllegalStateException("Unexpected value: " + view.getId());
            }
        }
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putString("STUDENT_NAME", studentEditText.getText().toString());
        savedInstanceState.putString("GROUP_NAME", groupEditText.getText().toString());
    }

    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        if (savedInstanceState.containsKey("STUDENT_NAME")) {
            studentEditText.setText(savedInstanceState.getString("STUDENT_NAME"));
        } else {
            studentEditText.setText("");
        }

        if (savedInstanceState.containsKey("GROUP_NAME")) {
            groupEditText.setText(savedInstanceState.getString("GROUP_NAME"));
        } else {
            groupEditText.setText("");
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        if (requestCode == 69) {
            Toast.makeText(this, "The activity returned with result " + resultCode, Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        setContentView(R.layout.activity_practical_test01_var04_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        navigateToSecondaryActivityButton = (Button)findViewById(R.id.navigate_to_secondary_activity_button);
        navigateToSecondaryActivityButton.setOnClickListener(buttonClickListener);

        selectStudentCheckbox = (CheckBox)findViewById(R.id.select_student_checkbox);
        selectGroupCheckbox = (CheckBox)findViewById(R.id.select_group_checkbox);

        studentEditText = (EditText)findViewById(R.id.student_edit_text);
        groupEditText = (EditText)findViewById(R.id.group_edit_text);

        displayInformationButton = (Button)findViewById(R.id.display_information_button);
        displayInformationButton.setOnClickListener(buttonClickListener);

        studentInformationTextView = (TextView)findViewById(R.id.student_information_text_view);
        toast = Toast.makeText(this, "Oops, forgot something...", Toast.LENGTH_LONG);
    }
}