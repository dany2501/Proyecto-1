package mx.ipn.escom.plantas;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;

public class PlantActivity extends AppCompatActivity {
    private String name,imageUrl,age,seasson,plantar,type;
    private TextView tvName,tvAge,tvSeasson,tvPlantar,tvType;
    private ImageView image;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plant);
        Intent i = getIntent();
        tvName = findViewById(R.id.name);
        tvAge = findViewById(R.id.age);
        tvSeasson = findViewById(R.id.seasson);
        tvPlantar = findViewById(R.id.plantar);
        tvType = findViewById(R.id.type);
        image = findViewById(R.id.image);


        name = i.getStringExtra("name");
        imageUrl = i.getStringExtra("imageUrl");
        age = i.getStringExtra("age");
        seasson = i.getStringExtra("seasson");
        plantar = i.getStringExtra("plantar");
        type = i.getStringExtra("type");
    }


    @Override
    protected void onResume() {
        super.onResume();

        Picasso.get()
                .load(imageUrl)
                .fit()
                .centerCrop()
                .into(image);

        tvName.setText(name);
        tvType.setText(tvType.getText().toString()+": "+type);
        tvPlantar.setText(tvPlantar.getText().toString()+": "+plantar);
        tvSeasson.setText(tvSeasson.getText().toString()+": "+seasson);
        tvAge.setText(tvAge.getText().toString()+": "+age);


    }
}
