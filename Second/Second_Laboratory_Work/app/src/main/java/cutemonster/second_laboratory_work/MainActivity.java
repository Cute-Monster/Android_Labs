package cutemonster.second_laboratory_work;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    int choseCounter = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /// Initialize button listener
        this.init_listeners();
        /// Setting TextView counter
        this.setChoseCounterText();
    }

    /**
     * Method which initializes button listener
     * */
    private void init_listeners(){
        findViewById(R.id.ImageChooseBtn).setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, EmoticonSelection.class);
            startActivityForResult(intent, 1);
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == 1) {
            this.choseCounter++ ;
            this.init_image(data);
            this.setChoseCounterText();
        }
    }

    private void init_image(Intent savedState){
        if (savedState == null) { return;}
        ImageView imageView = findViewById(R.id.resultImageView);
        imageView.setImageResource(
                this.drawable_Choose(
                        savedState.getIntExtra("ImageChosen", R.id.resultImageView)
                )
        );
    }

    private int drawable_Choose(int btn){
        switch(btn){
            case R.id.firstImage:
                return R.drawable.airplay;
            case R.id.secondImage:
                return R.drawable.anchor;
            case R.id.thirdImage:
                return R.drawable.bike_scooter;
            case R.id.fourthImage:
                return R.drawable.biotech;
            case R.id.fiveImage:
                return R.drawable.backpack;
            case R.id.sixhImage:
                return R.drawable.money;
            case R.id.sevenImage:
                return R.drawable.backup;
            case R.id.eightImage:
                return R.drawable.bedtime;
            case R.id.nineImage:
                return R.drawable.elderly;
            default:
                return R.drawable.ic_launcher_foreground;
        }
    }

    private void setChoseCounterText(){
        TextView chooseCounterShow = findViewById(R.id.imageNameTextView);
        chooseCounterShow.setText(
                String.format(
                        "%s %s",
                        getString(R.string.imageNameTextView),
                        (this.choseCounter == 0) ? "First Launch" : Integer.toString(this.choseCounter)
                )
        );
    }


}