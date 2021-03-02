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
public class WidgetProvider extends AppWidgetProvider {
    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        for (int appWidgetId : appWidgetIds) {
            Intent intent = new Intent(context, MainActivity.class);
            PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);
            SharedPreferences prefs = context.getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);
            String buttonText = prefs.getString(KEY_BUTTON_TEXT + appWidgetId, "Press me");
            RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.activity_widget);
            views.setOnClickPendingIntent(R.id.widgetButton, pendingIntent);
            views.setCharSequence(R.id.widgetButton, "setText", buttonText);
            appWidgetManager.updateAppWidget(appWidgetId, views);
        }
    }
}
