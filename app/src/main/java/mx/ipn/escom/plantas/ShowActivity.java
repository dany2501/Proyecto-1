package mx.ipn.escom.plantas;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ShowActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private ImageAdapter imageAdapter;
    private Button back;

    private DatabaseReference dbRef;
    private List<UploadFile> uploads;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);
        mRecyclerView = findViewById(R.id.recycler_view);
        back = findViewById(R.id.back);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        uploads = new ArrayList<>();

        dbRef = FirebaseDatabase.getInstance().getReference("plantas");


    }

    @Override
    protected void onResume() {
        super.onResume();

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {

                if (uploads.size() == 0 ){
                    for (DataSnapshot element : snapshot.getChildren()){
                        UploadFile uploadFile = element.getValue(UploadFile.class);
                        uploads.add(uploadFile);
                    }

                    imageAdapter = new ImageAdapter(ShowActivity.this, uploads, new ImageAdapter.OnItemClickListener() {
                        @Override
                        public void onItemClick(UploadFile item) {
                            Intent i = new Intent(getApplicationContext(),PlantActivity.class);
                            i.putExtra("name",item.getName());
                            i.putExtra("imageUrl",item.getImageUrl());
                            i.putExtra("age",item.getAge());
                            i.putExtra("seasson",item.getSeasson());
                            i.putExtra("plantar",item.getPlantar());
                            i.putExtra("type",item.getType());
                            startActivity(i);
                        }
                    });

                    mRecyclerView.setAdapter(imageAdapter);
                }


            }

            @Override
            public void onCancelled(DatabaseError error) {
                Toast.makeText(getApplicationContext(),"Ocurrió un error",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
