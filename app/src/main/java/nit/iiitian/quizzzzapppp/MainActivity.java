package nit.iiitian.quizzzzapppp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public static ArrayList<ModelClass> listOfQ;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listOfQ=new ArrayList<>();

        databaseReference = FirebaseDatabase.getInstance().getReference("Question");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot:snapshot.getChildren())
                {
                    ModelClass modelClass = dataSnapshot.getValue(ModelClass.class);
                    listOfQ.add(modelClass);
                }
                Intent intent=new Intent(MainActivity.this,DashboardActivity.class);
                startActivity(intent);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


//        listOfQ.add(new ModelClass("Which state produces maximum soybean?","Madhya Pradesh","Uttar Pradesh","Bihar","Rajasthan","Madhya Pradesh"));
//        listOfQ.add(new ModelClass("Which country operationalized world’s largest radio telescope?","USA","China","Russia","India","China"));
//        listOfQ.add(new ModelClass("Which of the following is the capital of Arunachal Pradesh?","Itanagar","Dispur","Imphal","Panaji","Itanagar"));
//        listOfQ.add(new ModelClass("Katerina Sakellaropoulou was elected the first woman President of","Greece","Spain","Finland","Netherland","Greece"));
//        listOfQ.add(new ModelClass("Which one among the following radiations carries maximum energy?","Ultraviolet rays","Gamma rays","X- rays","Infra-red rays","Gamma rays"));
//        listOfQ.add(new ModelClass("What is India’s rank on EIU’s Global Democracy Index 2019?","48th Rank","50th Rank","53rd Rank","51st Rank","51st Rank"));
//        listOfQ.add(new ModelClass("Which of the following states is not located in the North?","Jharkhand","Jammu and Kashmir","Himachal Pradesh","Haryana","Jharkhand"));
//        listOfQ.add(new ModelClass("What is India’s rank on the WEF’s Global Social Mobility Index 2020?","75th Rank","77th Rank","76th Rank","78th Rank","76th Rank"));
//        listOfQ.add(new ModelClass("Bokaro Steel Limited was established with the assistance of","Germany","Soviet Union","UK","USA","Soviet Union"));
//        listOfQ.add(new ModelClass("India’s first satellite of 2020 GSAT-30 was successfully launched is a","Remote Sensing Satellite","Communication Satelite","Spy Satellite","Metrological Satellite","Communication Satelite"));


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
//                Intent intent=new Intent(MainActivity.this,DashboardActivity.class);
//                startActivity(intent);
            }
        },3500);

    }
    public void exit_click(View view){

        System.exit(0);
    }
}