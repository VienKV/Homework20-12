package vn.hust.edu.homeworkw13;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.widget.ImageView;
import android.widget.TextView;

import static vn.hust.edu.homeworkw13.MainActivity.EXTRA_CREATOR;
import static vn.hust.edu.homeworkw13.MainActivity.EXTRA_EMAIL;
import static vn.hust.edu.homeworkw13.MainActivity.EXTRA_URL;

import com.squareup.picasso.Picasso;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent intent = getIntent();
        String imageUrl = intent.getStringExtra(EXTRA_URL);
        String creatorName = intent.getStringExtra(EXTRA_CREATOR);
        String email = intent.getStringExtra(EXTRA_EMAIL);

        ImageView imageView = findViewById(R.id.image_view_detail);
        TextView textViewCreator = findViewById(R.id.text_view_creator_detail);
        TextView textViewEmail = findViewById(R.id.text_view_email_detail);

        Picasso.get().load(imageUrl).fit().centerInside().into(imageView);
        textViewCreator.setText(creatorName);
        textViewEmail.setText("email: " + email);
    }
}