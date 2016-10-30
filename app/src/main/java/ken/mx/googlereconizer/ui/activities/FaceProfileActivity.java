package ken.mx.googlereconizer.ui.activities;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import devlight.io.library.ArcProgressStackView;
import ken.mx.googlereconizer.R;

public class FaceProfileActivity extends AppCompatActivity {

    ArcProgressStackView arcProgressStackView;
    public final static int MODEL_COUNT = 4;
    private int[]
            mStartColors = new int[MODEL_COUNT];
    private int[] mEndColors = new int[MODEL_COUNT];
    private Bundle bundle;
    private String id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_face_profile);
        arcProgressStackView=(ArcProgressStackView)findViewById(R.id.apsv_presentation);
        setArcProgressStackViewValues();
        bundle = getIntent().getExtras();
        id=bundle.getString("Algo");
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(this,
                MainActivity.class);
        startActivity(intent);
        finish();
    }

    public void setArcProgressStackViewValues(){

        // Get colors
        final String[] startColors = getResources().getStringArray(R.array.devlight);
        final String[] endColors = getResources().getStringArray(R.array.default_preview);
        final String[] bgColors = getResources().getStringArray(R.array.medical_express);
        for (int i = 0; i < MODEL_COUNT; i++) {
            mStartColors[i] = Color.parseColor(startColors[i]);
            mEndColors[i] = Color.parseColor(endColors[i]);
        }
        float minX = 0.0f;
        float maxX = 100.0f;

        Random rand = new Random();
        float final1 = rand.nextFloat() * (maxX - minX) + minX;
        float final2 = rand.nextFloat() * (maxX - minX) + minX;
        float final3 = rand.nextFloat() * (maxX - minX) + minX;
        float final4 = rand.nextFloat() * (maxX - minX) + minX;
        arcProgressStackView.setIsRounded(true);
        List<ArcProgressStackView.Model> models = new ArrayList<>();
        models.add(new ArcProgressStackView.Model("Enojo",final1, Color.parseColor(bgColors[0]), mStartColors[3]));
        models.add(new ArcProgressStackView.Model("Desprecio", final2, Color.parseColor(bgColors[1]), mStartColors[2]));
        models.add(new ArcProgressStackView.Model("Neutral", final3, Color.parseColor(bgColors[2]), mStartColors[1]));
        models.add(new ArcProgressStackView.Model("Felicidad", final4, Color.parseColor(bgColors[3]), mStartColors[0]));
        arcProgressStackView.setModels(models);
        for (ArcProgressStackView.Model model : arcProgressStackView.getModels());
        arcProgressStackView.animateProgress();






    }
}
