package ken.mx.googlereconizer.ui.activities;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.drawable.ColorDrawable;
import android.os.CountDownTimer;
import android.support.annotation.NonNull;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.WindowManager;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.akexorcist.roundcornerprogressbar.RoundCornerProgressBar;
import com.bumptech.glide.Glide;
import com.flaviofaria.kenburnsview.KenBurnsView;
import com.flaviofaria.kenburnsview.RandomTransitionGenerator;
import com.google.firebase.iid.FirebaseInstanceId;
import com.mikhaellopez.circularimageview.CircularImageView;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import ken.mx.googlereconizer.ApiService;
import ken.mx.googlereconizer.R;
import ken.mx.googlereconizer.model.BodyImage;
import ken.mx.googlereconizer.model.Face;
import ken.mx.googlereconizer.model.ResponseReconizing;
import ken.mx.googlereconizer.ui.adapter.FacesAdapter;
import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    public static final String NEW_PHOTO = "NEW_PHOTO";
    private RecyclerView recyclerViewFaces;
    private FacesAdapter facesAdapter;
    private List<Face> faceAdapterList = new ArrayList<>();
    private LocalBroadcastManager bManager;
    Face face = new Face();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        initViews();

        bManager = LocalBroadcastManager.getInstance(this);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(NEW_PHOTO);
        bManager.registerReceiver(bReceiver, intentFilter);
    }

    @Override
    protected void onStart() {
        super.onStart();

    }

    private void initViews() {
//        KenBurnsView kbv = (KenBurnsView) findViewById(R.id.image);
//        RandomTransitionGenerator generator = new RandomTransitionGenerator(10000, new AccelerateDecelerateInterpolator());
//        kbv.setTransitionGenerator(generator);
//        ImageView imageView = (ImageView) findViewById(R.id.image);

//        Glide.with(this).load("https://i.vimeocdn.com/portrait/4900311_300x300").into(imageView);
        String deviceToken = FirebaseInstanceId.getInstance().getToken();
        setupRecycler();
        log("token: " + deviceToken);
    }

    public void doRequest(final String url) {


        final HttpLoggingInterceptor logging = new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder()
                .addInterceptor(logging);


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.projectoxford.ai")
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient.build())
                .build();

        ApiService apiService = retrofit.create(ApiService.class);

//
        apiService.getFace(new BodyImage(url)).enqueue(new Callback<List<ResponseReconizing>>() {
            @Override
            public void onResponse(Call<List<ResponseReconizing>> call, Response<List<ResponseReconizing>> response) {
                if (response.body() != null) {
                    log(response.body().toString());
                    if (response.body().size() > 0) {
                        showDialog(response.body().get(0), url);
                    } else {
                        Toast.makeText(MainActivity.this, "No se encontraron caracteristicas", Toast.LENGTH_LONG).show();
                    }

                } else {
                    log("no entro");
                }
            }

            @Override
            public void onFailure(Call<List<ResponseReconizing>> call, Throwable t) {
                log(t.getMessage());
            }
        });


    }

    public void showDialog(ResponseReconizing responseReconizing, final String url) {

        final View dialogView = View.inflate(MainActivity.this, R.layout.dialog_reveal, null);


        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setView(dialogView)
                .setCancelable(false);


        final AlertDialog dialog = builder.create();


        dialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialog) {
                revealShow(dialogView, true, null);
            }
        });
        dialogView.findViewById(R.id.btn_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                revealShow(dialogView, false, dialog);
            }
        });
        dialogView.findViewById(R.id.btn_save).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                revealShow(dialogView, false, dialog);
                //TODO guardar datos de la persona y actualizar el recyclerview
                face.setPotencial(0);
                face.setScore("asdfg");
                face.setId("asdfg");
                face.setUrl(url);

                faceAdapterList.add(face);
                facesAdapter.notifyDataSetChanged();
            }
        });


        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));


        dialog.show();

        CircularImageView circular = (CircularImageView) dialog.findViewById(R.id.circle_img);
        Glide.with(this).load(url).into(circular);


        animateLoadingData(dialog, responseReconizing);


    }

    public void animateLoadingData(AlertDialog dialog, ResponseReconizing responseReconizing) {
        final RoundCornerProgressBar progressBarFelicidad = (RoundCornerProgressBar) dialog.findViewById(R.id.progress_felicidad);
        final RoundCornerProgressBar progressBarTristeza = (RoundCornerProgressBar) dialog.findViewById(R.id.progress_tristeza);
        final RoundCornerProgressBar progressBarDesprecio = (RoundCornerProgressBar) dialog.findViewById(R.id.progress_desprecio);
        final RoundCornerProgressBar progressBarDisgusto = (RoundCornerProgressBar) dialog.findViewById(R.id.progress_disgusto);
        final RoundCornerProgressBar progressBarNeutral = (RoundCornerProgressBar) dialog.findViewById(R.id.progress_neutral);


        TextView tvFelicidad = (TextView) dialog.findViewById(R.id.tv_felicidad);
        TextView tvTristeza = (TextView) dialog.findViewById(R.id.tv_tristeza);
        TextView tvDesprecio = (TextView) dialog.findViewById(R.id.tv_desprecio);
        TextView tvDisgusto = (TextView) dialog.findViewById(R.id.tv_disgusto);
        TextView tvNeutral = (TextView) dialog.findViewById(R.id.tv_neutral);


        final double happiness = responseReconizing.getScores().getHappiness();
        final double sadness = responseReconizing.getScores().getSadness();
        final double anger = responseReconizing.getScores().getAnger();
        final double disgust = responseReconizing.getScores().getDisgust();
        final double neutral = responseReconizing.getScores().getNeutral();


        final int totalCount = 1000;
        final int descontar = 1;

        final double hapinessPercent = happiness * 1000;
        final double sadnessPercent = sadness * 1000;
        final double angerPercent = anger * 1000;
        final double disgustPercent = disgust * 1000;
        final double neutralpercent = neutral * 1000;
        DecimalFormat precision = new DecimalFormat("0.0000");

        tvFelicidad.setText(precision.format(hapinessPercent / 10) + "%");
        tvTristeza.setText(precision.format(sadnessPercent / 10) + "%");
        tvDesprecio.setText(precision.format(angerPercent / 10) + "%");
        tvDisgusto.setText(precision.format(disgustPercent / 10) + "%");
        tvNeutral.setText(precision.format(neutralpercent / 10) + "%");


        progressBarFelicidad.postDelayed(new Runnable() {
            @Override
            public void run() {
                new CountDownTimer(totalCount, descontar) {
                    @Override
                    public void onTick(long downTimer) {

                        double total = (totalCount - downTimer) * happiness;
                        progressBarFelicidad.setProgress((float) total);

                        total = (totalCount - downTimer) * sadness;
                        progressBarTristeza.setProgress((float) total);

                        total = (totalCount - downTimer) * anger;
                        progressBarDesprecio.setProgress((float) total);

                        total = (totalCount - downTimer) * disgust;
                        progressBarDisgusto.setProgress((float) total);

                        total = (totalCount - downTimer) * neutral;
                        progressBarNeutral.setProgress((float) total);


                    }

                    @Override
                    public void onFinish() {
                        progressBarFelicidad.setProgress((float) hapinessPercent);
                        progressBarTristeza.setProgress((float) sadnessPercent);
                        progressBarDesprecio.setProgress((float) angerPercent);
                        progressBarDisgusto.setProgress((float) disgustPercent);
                        progressBarNeutral.setProgress((float) neutralpercent);
                    }
                }.start();
            }
        }, 1000);

    }


    private void revealShow(View rootView, boolean reveal, final AlertDialog dialog) {
        final View view = rootView.findViewById(R.id.reveal_view);
        int w = view.getWidth();
        int h = view.getHeight();
        float maxRadius = (float) Math.sqrt(w * w / 4 + h * h / 4);

        if (reveal) {

            Animator revealAnimator = ViewAnimationUtils.createCircularReveal(view,
                    w / 2, h / 2, 0, maxRadius);

            view.setVisibility(View.VISIBLE);
            revealAnimator.start();

        } else {

            Animator anim =
                    ViewAnimationUtils.createCircularReveal(view, w / 2, h / 2, maxRadius, 0);

            anim.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    super.onAnimationEnd(animation);
                    dialog.dismiss();
                    view.setVisibility(View.INVISIBLE);

                }
            });

            anim.start();
        }
    }


    public void setupRecycler() {
        faceAdapterList = new ArrayList<>();
        recyclerViewFaces = (RecyclerView) findViewById(R.id.recycler_faces);
        recyclerViewFaces.setLayoutManager(new GridLayoutManager(this, 3));
        recyclerViewFaces.setHasFixedSize(true);
        facesAdapter = new FacesAdapter(faceAdapterList);
        recyclerViewFaces.setAdapter(facesAdapter);

        /*Face face1 = new Face("12", "sdfds", "sdfdsf", 1);
        Face face2 = new Face("12", "sdfds", "sdfdsf", 0);
        Face face3 = new Face("12", "sdfds", "sdfdsf", 1);
        Face face4 = new Face("12", "sdfds", "sdfdsf", 2);
        Face face5 = new Face("12", "sdfds", "sdfdsf", 2);
        Face face6 = new Face("12", "sdfds", "sdfdsf", 1);*/

        facesAdapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("Perfil activity", "Pulsado el elemento " + recyclerViewFaces.getChildAdapterPosition(v));
                Face face = faceAdapterList.get(recyclerViewFaces.getChildAdapterPosition(v));
                Intent intent = new Intent(MainActivity.this, FaceProfileActivity.class);
                intent.putExtra("Algo", face.getId());
                startActivity(intent);
                finish();
            }
        });


    }


    public void log(String content) {
        Log.i("myLog", content);
    }

    private BroadcastReceiver bReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals(NEW_PHOTO)) {
//                String serviceJsonString = intent.getStringExtra("stati");
                String url = intent.getStringExtra("url");
                //Do something with the string
                //finish
                log(url);
                doRequest(url);
            }
        }
    };


}
