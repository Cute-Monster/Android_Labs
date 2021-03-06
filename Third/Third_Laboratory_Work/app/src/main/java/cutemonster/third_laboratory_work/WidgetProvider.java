package cutemonster.third_laboratory_work;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.RemoteViews;

import static cutemonster.third_laboratory_work.Widget_Config.KEY_BUTTON_TEXT;
import static cutemonster.third_laboratory_work.Widget_Config.SHARED_PREFS;

/**
 * Создание Android приложения, которое будет иметь виджет.
 * Данный виджет должен иметь какой-нибудь функционал.
 * */
public class WidgetProvider extends AppWidgetProvider {
    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        for (int appWidgetId : appWidgetIds) {
            /// Creating intent which will be called when listener is triggered
            Intent intent = new Intent(context, MainActivity.class);
            PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);

            /// Creating to save preferences between invokes
            SharedPreferences prefs = context.getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);
            String buttonText = prefs.getString(KEY_BUTTON_TEXT + appWidgetId, "Press me");

            /*
                Creating RemoteViews object by which we can access and make changes on activities
              and their items. We can't do this like usual? cause here we access them through stream
            */
            RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.activity_widget);
            views.setOnClickPendingIntent(R.id.widgetButton, pendingIntent);
            views.setCharSequence(R.id.widgetButton, "setText", buttonText);

            /// Calling updateAppWidget method from AppWidgetManager to update specified widget
            // by passed view settings
            appWidgetManager.updateAppWidget(appWidgetId, views);
        }
    }
}
