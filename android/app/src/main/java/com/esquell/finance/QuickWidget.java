package com.esquell.finance;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

// Виджет на рабочий стол: кнопки сразу открывают форму нужной операции
public class QuickWidget extends AppWidgetProvider {
    @Override
    public void onUpdate(Context ctx, AppWidgetManager mgr, int[] ids) {
        RemoteViews v = new RemoteViews(ctx.getPackageName(), R.layout.widget_quick);
        v.setOnClickPendingIntent(R.id.open, open(ctx, "", 0));
        v.setOnClickPendingIntent(R.id.btn_expense, open(ctx, "expense", 1));
        v.setOnClickPendingIntent(R.id.btn_income, open(ctx, "income", 2));
        v.setOnClickPendingIntent(R.id.btn_transfer, open(ctx, "transfer", 3));
        for (int id : ids) mgr.updateAppWidget(id, v);
    }

    private PendingIntent open(Context ctx, String type, int code) {
        Intent i = new Intent(ctx, MainActivity.class).putExtra("quick", type)
                .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        return PendingIntent.getActivity(ctx, code, i, PendingIntent.FLAG_IMMUTABLE | PendingIntent.FLAG_UPDATE_CURRENT);
    }
}
