package com.shub.ehrs.Common.PatientScreens;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.ImageView;


import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.shub.ehrs.R;

import static android.widget.Toast.*;


public class uploadimage extends AppCompatActivity {

    private Button uploadBtn, showAllBtn;
    private ImageView imageView;
    String ref, phoneNo, symptomName;
    DatabaseReference root;
    StorageReference reference;
    Uri imageUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uploadimage);

        uploadBtn = findViewById(R.id.uploadbtn);
        showAllBtn = findViewById(R.id.showbtn);
        imageView = findViewById(R.id.addimg);

        symptomName = getIntent().getStringExtra("symptomName");
        phoneNo = getIntent().getStringExtra("phoneNo");


    root = FirebaseDatabase.getInstance("https://ehrs-83560-default-rtdb.firebaseio.com/").getReference().child("users").child(phoneNo).child("Symptoms").child(symptomName).child("Report");

         reference = FirebaseStorage.getInstance().getReference();



        showAllBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ShowActivity.class);
                intent.putExtra("symptomName",symptomName);
                intent.putExtra("phoneNo",phoneNo);

                startActivity(intent);
            }
        });

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent galleryIntent= new Intent();
                galleryIntent.setAction(Intent.ACTION_GET_CONTENT);
                galleryIntent.setType("image/*");
                startActivityForResult(galleryIntent, 2);


            }
        });

        uploadBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (imageUri != null){
                    uploadToFirebase(imageUri);

                }else{
                    makeText(uploadimage.this, "Please Select Image", LENGTH_SHORT).show();
                }

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode==2 && resultCode == RESULT_OK &&  data != null){
            imageUri= data.getData();
            imageView.setImageURI(imageUri);


        }
    }

    private void uploadToFirebase(Uri uri){

        final StorageReference fileRef = reference.child(System.currentTimeMillis() + "." + getFileExtension(uri));
        fileRef.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                fileRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {


                        Model model= new Model(uri.toString());
                        String modelId= root.push().getKey();
                        root.child(modelId).setValue(model);
//                        progressBar.setVisibility(View.INVISIBLE);
                        makeText(uploadimage.this, "Upload Suucessful", LENGTH_SHORT).show();
                        imageView.setImageResource(R.drawable.add_btn_vector);
                    }
                });

            }
        }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {
//                progressBar.setVisibility(View.VISIBLE);

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
//                progressBar.setVisibility(View.INVISIBLE);
                makeText(uploadimage.this, "uploading fialed", LENGTH_SHORT).show();
            }
        });


    }

    private String getFileExtension(Uri mUri){
        ContentResolver cr = getContentResolver();
        MimeTypeMap mime= MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cr.getType(mUri));

    }
}
