package com.raneem.bookmarklecture;

import android.content.Context;
import android.content.Intent;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import com.sackcentury.shinebuttonlib.ShineButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import android.content.SharedPreferences;

import java.util.List;
import java.util.prefs.PreferenceChangeEvent;

public class MyAdapter  extends BaseAdapter {

    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String shinebtn = "shine";
    public static final String name = "shine";
    private boolean isshine;

    private Context context; //context
    DatabaseReference mDatabase;

    FirebaseDatabase firebaseDatabase;

    List<Slides> list;
    List<String>bookmarklist;
    final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

public MyAdapter( List<String> bookmarklist){

this.bookmarklist=bookmarklist;
}
    public MyAdapter(Context context,  List<Slides> list) {
        this.context = context;
        this.list = list;


    }


    @Override
    public int getCount() {
        return list.size(); //returns total of items in the list;
    }

    @Override
    public Object getItem(int position) {

        return list.get(position); //returns list item at the specified position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }



    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // inflate the layout for each list row

        final String lectuer=list.get(position).getTitle();









        if (convertView == null) {
            convertView = LayoutInflater.from(context).
                    inflate(R.layout.customlist, parent, false); }
        // get current item to be displayed
        //PDFs ss = (PDFs) getItem(position);

        // get the TextView for item name and item description
        TextView textViewItemName = (TextView)
                convertView.findViewById(R.id.titleview);

     /*   Button bookmark_button=(Button)
                convertView.findViewById(R.id.bookmark_button);*/

        final ImageView imageView = (ImageView)
                convertView.findViewById(R.id.imgview12);

        textViewItemName.setText(list.get(position).getTitle());
        imageView.setImageResource(list.get(position).getImg());
        textViewItemName.setText(list.get(position).getTitle());


        imageView.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        if (user != null) {
            final String userID = user.getUid();

            mDatabase = FirebaseDatabase.getInstance().getReference("users").child(userID);
            mDatabase.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    PDF s=new PDF(lectuer);
                   // mDatabase = FirebaseDatabase.getInstance().getReference("users").child(userID);
                    mDatabase.child("users").child(userID).setValue(lectuer);

                    mDatabase.child("booked").push().setValue(s);
                        Toast.makeText(context.getApplicationContext(), "You've booked in successfully", Toast.LENGTH_SHORT).show();
                    }


                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });




        }
    }
});


        return convertView;
    }

}