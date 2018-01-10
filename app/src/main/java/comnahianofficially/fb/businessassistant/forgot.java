package comnahianofficially.fb.businessassistant;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class forgot extends AppCompatActivity {
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot);
        db=openOrCreateDatabase("Account", Context.MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS user(name VARCHAR,password VARCHAR,email VARCHAR,recover VARCHAR);");
    }
    public void check(View view){
        EditText e1=findViewById(R.id.name);
        EditText e2=findViewById(R.id.pet);
        String uname=e1.getText().toString();
        String upet=e2.getText().toString();
        if(e1.getText().toString().trim().length()==0||e2.getText().toString().trim().length()==0)
        {
            showMessage("Error", "Please enter All Info");
            return;
        }
        Cursor c=db.rawQuery("SELECT * FROM user", null);
        String name="",pet="";
        while(c.moveToNext()) {

            name = c.getString(0);
            pet = c.getString(3);
        }
            if (uname.equals(name) && upet.equals(pet)) {
                Intent i = new Intent(this, signup.class);
                db.execSQL("DELETE  FROM user;");
                startActivity(i);



            } else {
                showMessage("Error", "Invalid Info");
                clearText();
            }




    }
    public void showMessage(String title,String message)
    {
        Builder builder=new Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }
    public void clearText() {
        EditText e1 = findViewById(R.id.name);
        EditText e2 = findViewById(R.id.pet);


        e1.setText("");
        e2.setText("");
    }
}
