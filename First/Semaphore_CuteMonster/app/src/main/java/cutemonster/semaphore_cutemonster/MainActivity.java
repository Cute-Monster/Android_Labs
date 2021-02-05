package cutemonster.semaphore_cutemonster;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    View view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Find Main_View by id
        this.view = findViewById(R.id.Main_View);

        // Initialize button listeners on click
        this.init_btn_click_listeners();
    }

    /**
     * Method which initializes button listeners
     * */
    public void init_btn_click_listeners(){

        // Changing button background color
        findViewById(R.id.Red_Button).setOnClickListener(
                view -> view.setBackgroundColor(Color.RED)
        );

        // Changing Main_View background color when button is pressed
        findViewById(R.id.Yellow_Button).setOnClickListener(
                view -> this.view.setBackgroundColor(Color.YELLOW)
        );

        findViewById(R.id.Green_Button).setOnClickListener(
                view -> this.view.setBackgroundColor(Color.GREEN)
        );

        findViewById(R.id.Clear_Background_Color_Button).setOnClickListener(
                view -> this.view.setBackgroundColor(Color.WHITE)
        );
    }
}