package com.anilax.customdesign.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.widget.TextView;
import androidx.annotation.NonNull;
import com.anilax.textart.R;

public class DiscardDialog extends Dialog {
    TextView tvDiscard;
    TextView tvKeep;
    Activity ac;

    public DiscardDialog(@NonNull Activity activity) {
        super(activity);
        this.ac = activity;
        setContentView(R.layout.discard_dialog);
        addControls();
        addEvents();
    }

    private void addEvents() {
        this.tvDiscard.setOnClickListener(view -> DiscardDialog.this.ac.finish());
        this.tvKeep.setOnClickListener(view -> DiscardDialog.this.dismiss());
    }

    private void addControls() {
        this.tvDiscard = findViewById(R.id.tv_discard);
        this.tvKeep = findViewById(R.id.tv_keep);
        setCanceledOnTouchOutside(true);
    }
}
