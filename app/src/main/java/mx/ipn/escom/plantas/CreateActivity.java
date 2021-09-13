package mx.ipn.escom.plantas;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.auth.api.signin.internal.Storage;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

public class CreateActivity  extends AppCompatActivity {

    private EditText name,type,age,seasson;
    private ImageView image;
    private Spinner spinner;
    private Button imagePicker,submit;
    private ActivityResultLauncher<Intent> launchSomeActivity;

    private StorageReference mStorageRef;
    private DatabaseReference mDbRef;
    private Uri imageUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);
        name = findViewById(R.id.name);
        type = findViewById(R.id.type);
        age = findViewById(R.id.age);
        seasson = findViewById(R.id.seasson);
        spinner = findViewById(R.id.options);
        imagePicker = findViewById(R.id.imagePicker);
        submit = findViewById(R.id.submit);
        image = findViewById(R.id.image);
        mStorageRef = FirebaseStorage.getInstance().getReference("plantas");
        mDbRef = FirebaseDatabase.getInstance().getReference("plantas");
        launchSomeActivity = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        System.out.println("Image response ");
                        if (result.getResultCode() == Activity.RESULT_OK) {
                            Intent data = result.getData();
                            Picasso.get()
                                    .load(data.getData())
                                    .into(image);
                            imageUri = data.getData();
                        }
                    }
                });
    }

    @Override
    protected void onResume() {
        super.onResume();


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = spinner.getSelectedItem().toString();
                StorageReference fileRef = mStorageRef.child(name.getText().toString()+fileExtension(imageUri));
                fileRef.putFile(imageUri)
                        .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                            @Override
                            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                Toast.makeText(getApplicationContext(),"Guardado correctamente",Toast.LENGTH_SHORT).show();
                                Task<Uri> result = taskSnapshot.getStorage().getDownloadUrl();
                                result.addOnSuccessListener(new OnSuccessListener<Uri>() {
                                    @Override
                                    public void onSuccess(Uri uri) {
                                        UploadFile uploadFile = new UploadFile(name.getText().toString(),
                                                uri.toString(),name.getText().toString(),
                                                type.getText().toString(),age.getText().toString(),seasson.getText().toString(),text);
                                        String uploadId = mDbRef.push().getKey();
                                        mDbRef.child(uploadId).setValue(uploadFile);
                                    }
                                });

                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });

        imagePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFileManager();
            }
        });
    }

    private void openFileManager(){
        Intent i = new Intent();
        i.setType("image/*");
        i.setAction(Intent.ACTION_GET_CONTENT);
        launchSomeActivity.launch(i);
    }

    private String fileExtension(Uri uri){
        ContentResolver resolver = getContentResolver();
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
        return mimeTypeMap.getExtensionFromMimeType(resolver.getType(uri));
    }

    private void savePlant(String imageUrl){

    }
}
