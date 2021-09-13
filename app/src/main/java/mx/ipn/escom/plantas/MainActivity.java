package mx.ipn.escom.plantas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

public class MainActivity extends AppCompatActivity {

    private ImageView bottomImage;
    private Button create,show;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bottomImage = findViewById(R.id.bottom);
        create = findViewById(R.id.create);
        show = findViewById(R.id.show);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Picasso.get()
                .load("https://images-wixmp-ed30a86b8c4ca887773594c2.wixmp.com/f/3746f055-2f9b-4bf8-9833-883f5441d4ef/d65hkkv-92fcc0db-5235-4160-99e5-5be7aa0cb097.png?token=eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1cm46YXBwOjdlMGQxODg5ODIyNjQzNzNhNWYwZDQxNWVhMGQyNmUwIiwiaXNzIjoidXJuOmFwcDo3ZTBkMTg4OTgyMjY0MzczYTVmMGQ0MTVlYTBkMjZlMCIsIm9iaiI6W1t7InBhdGgiOiJcL2ZcLzM3NDZmMDU1LTJmOWItNGJmOC05ODMzLTg4M2Y1NDQxZDRlZlwvZDY1aGtrdi05MmZjYzBkYi01MjM1LTQxNjAtOTllNS01YmU3YWEwY2IwOTcucG5nIn1dXSwiYXVkIjpbInVybjpzZXJ2aWNlOmZpbGUuZG93bmxvYWQiXX0.7Qrc2nHiLZ37tPVA_Eme5yZl1tHljoziuCmkYeiW0oM")
                .into(bottomImage);

        show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),ShowActivity.class);
                startActivity(i);
            }
        });

        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),CreateActivity.class);
                startActivity(i);
            }
        });

    }
}