package cutemonster.second_laboratory_work;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;


public class EmoticonSelection extends AppCompatActivity {
    ImageButton[] buttons;
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emoticon_selection);
        this.initButtons();
        this.initListeners();
    }

    private void initButtons(){
        buttons = new ImageButton[]{
                findViewById(R.id.firstImage),
                findViewById(R.id.secondImage),
                findViewById(R.id.thirdImage),
                findViewById(R.id.fourthImage),
                findViewById(R.id.fiveImage),
                findViewById(R.id.sixhImage),
                findViewById(R.id.sevenImage),
                findViewById(R.id.eightImage),
                findViewById(R.id.nineImage)
        };
    }

    private void initListeners(){
        for(ImageButton btn : this.buttons){
            btn.setOnClickListener(item -> {
                this.intent = new Intent(EmoticonSelection.this, MainActivity.class);
                intent.putExtra("ImageChosen", btn.getId());
                setResult(1, intent);
                finish();
            });
        }
    }
}