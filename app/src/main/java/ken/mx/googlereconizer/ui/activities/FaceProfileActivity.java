package ken.mx.googlereconizer.ui.activities;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.nio.FloatBuffer;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import devlight.io.library.ArcProgressStackView;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmQuery;
import io.realm.RealmResults;
import ken.mx.googlereconizer.R;
import ken.mx.googlereconizer.model.DatosFace;

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
        arcProgressStackView = (ArcProgressStackView) findViewById(R.id.apsv_presentation);
        setArcProgressStackViewValues();
        bundle = getIntent().getExtras();
        id = bundle.getString("Algo");
        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder(this)
                .name(Realm.DEFAULT_REALM_NAME)
                .schemaVersion(0)
                .deleteRealmIfMigrationNeeded()
                .build();
        Realm.setDefaultConfiguration(realmConfiguration);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(this,
                MainActivity.class);
        startActivity(intent);
        finish();
    }

    public void setArcProgressStackViewValues() {

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

        Realm realm = Realm.getDefaultInstance();
        //DatosFace resultado = realm.where(DatosFace.class).equalTo("idQuery", id).findFirst();
        RealmResults<DatosFace> resultado = realm.where(DatosFace.class).equalTo("idQuery", id).findAll();
        double anger=0, disgust=0, neutral=0, hapiness=0, sadness=0;
        for (int i = 0; i < resultado.size(); i++)
        {
            anger=resultado.get(i).getAnger();
            disgust=resultado.get(i).getDisgust();
            neutral=resultado.get(i).getNeutral();
            hapiness=resultado.get(i).getHappiness();
            sadness=resultado.get(i).getSadness();
        }

        final float final1 = (float) anger * (maxX - minX) + minX; //Enojo
        final float final2 = (float) disgust * (maxX - minX) + minX; //Desprecio
        final float final3 = (float) neutral * (maxX - minX) + minX; //Neutral
        final float final4 = (float) hapiness * (maxX - minX) + minX; //Felicidad
        //final float final5 = (float) sadness * (maxX - minX) + minX; //Tristeza
        arcProgressStackView.post(new Runnable() {
            @Override
            public void run() {
                arcProgressStackView.setIsRounded(true);
                List<ArcProgressStackView.Model> models = new ArrayList<>();
                models.add(new ArcProgressStackView.Model("Enojo", final1, Color.parseColor(bgColors[0]), mStartColors[3]));
                models.add(new ArcProgressStackView.Model("Desprecio", final2, Color.parseColor(bgColors[1]), mStartColors[2]));
                models.add(new ArcProgressStackView.Model("Neutral", final3, Color.parseColor(bgColors[2]), mStartColors[1]));
                models.add(new ArcProgressStackView.Model("Felicidad", final4, Color.parseColor(bgColors[3]), mStartColors[0]));
                //models.add(new ArcProgressStackView.Model("Tristeza", final5, Color.parseColor(bgColors[4]), mStartColors[0]));
                arcProgressStackView.setModels(models);
                for (ArcProgressStackView.Model model : arcProgressStackView.getModels()) ;
                arcProgressStackView.animateProgress();
            }
        });


    }
}
