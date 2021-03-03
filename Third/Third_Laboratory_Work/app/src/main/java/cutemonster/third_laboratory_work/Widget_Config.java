package cutemonster.third_laboratory_work;

import androidx.appcompat.app.AppCompatActivity;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RemoteViews;

public class Widget_Config extends AppCompatActivity {

    public static final String SHARED_PREFS = "prefs";
    public static final String KEY_BUTTON_TEXT = "keyButtonText";
    private int appWidgetId = AppWidgetManager.INVALID_APPWIDGET_ID;
    private EditText editTextButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_widget__config);

        Intent intent = getIntent();
        /// Getting extras from current intent
        Bundle extras = intent.getExtras();
        if (extras != null) {
            /// Getting widget_id from extra
            this.appWidgetId = extras.getInt(AppWidgetManager.EXTRA_APPWIDGET_ID,
                    AppWidgetManager.INVALID_APPWIDGET_ID);
        }

        Intent resultValue = new Intent();
        ///Filling intent with current widget id
        resultValue.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, this.appWidgetId);
        setResult(RESULT_CANCELED, resultValue);

        if (appWidgetId == AppWidgetManager.INVALID_APPWIDGET_ID) {
            /// Finish widget configuration activity if widget id belongs to invalid widget
            finish();
        }

        /// Getting view button instance
        this.editTextButton = findViewById(R.id.edit_text_button);
    }

    public void confirmConfiguration(View v) {

        /// Get instance of WidgetManager to make changes on widget
        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(this);

        // Setting intent which will be called by widget
        Intent intent = new Intent(this, MainActivity.class);
        Intent resultValue = new Intent();
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);
        String buttonText = this.editTextButton.getText().toString();
        RemoteViews views = new RemoteViews(this.getPackageName(), R.layout.activity_widget);

        /// Creating of shared preferences and editor to save configuration between calling new widget
        SharedPreferences prefs = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();

        /// Configuring widget view and updating widget
        views.setOnClickPendingIntent(R.id.widgetButton, pendingIntent);
        views.setCharSequence(R.id.widgetButton, "setText", buttonText);
        appWidgetManager.updateAppWidget(appWidgetId, views);
        editor.putString(KEY_BUTTON_TEXT + appWidgetId, buttonText);
        editor.apply();

        /// Saving result with RESULT_OK code and finishing widget config activity
        resultValue.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId);
        setResult(RESULT_OK, resultValue);
        finish();
    }
}