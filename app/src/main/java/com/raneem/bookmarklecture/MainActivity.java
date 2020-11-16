package com.raneem.bookmarklecture;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ListView listview;
    StorageReference storageRef;
    DatabaseReference mDatabase;
    FirebaseDatabase firebaseDatabase;
    List<Slides> list=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listview = (ListView)findViewById(R.id.listview);

        firebaseDatabase = FirebaseDatabase.getInstance();
        storageRef= FirebaseStorage.getInstance().getReference();

        //chekbook();
   //   ReadFairebaseFailes();
        ViewAllFailes();

    }


    public void ViewAllFailes () {
        mDatabase = FirebaseDatabase.getInstance().getReference().child("JavaOneSlides");
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot ds : snapshot.getChildren()) {
                    Slides ss = ds.getValue(Slides.class);
                    ss.setId(ds.getKey());
                    list.add(ss);



                }


                MyAdapter adapter = new MyAdapter(getApplicationContext(), list);
                listview.setAdapter(adapter);

            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }});
    }
    private void ReadFairebaseFailes() {
        Slides s1=new Slides("JavaBasics","https://firebasestorage.googleapis.com/v0/b/bookmarklecture.appspot.com/o/files%2FJavaBasics%20(I).pdf?alt=media&token=a936873d-098a-4cff-b9df-28c53333fc1b"
                ,R.drawable.notbookkmark);
        Slides s2=new Slides("methods ","https://firebasestorage.googleapis.com/v0/b/bookmarklecture.appspot.com/o/files%2FLec_5%266%20-%20Loops-converted.pdf?alt=media&token=1ebeb38b-9422-478e-bbcd-18e89d0b0b9c",
                R.drawable.notbookkmark);
        firebaseDatabase.getReference("JavaOneSlides").push().setValue(s1);
        firebaseDatabase.getReference("JavaOneSlides").push().setValue(s2);

    }


    }
