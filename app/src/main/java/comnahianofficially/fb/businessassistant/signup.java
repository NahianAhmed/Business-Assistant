package comnahianofficially.fb.businessassistant;

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

public class signup extends AppCompatActivity {
    SQLiteDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        db=openOrCreateDatabase("Account", Context.MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS user(name VARCHAR,password VARCHAR,email VARCHAR,recover VARCHAR);");
    }


    public void Singup(View view){
        EditText e1 = findViewById(R.id.editText);
        EditText e2 = findViewById(R.id.editText2);
        String passWord=e2.getText().toString();
        int PassWord=passWord.hashCode();
        EditText e3 = findViewById(R.id.editText3);
        EditText e4 = findViewById(R.id.editText4);
        if (e1.getText().toString().trim().length() == 0 ||
                e2.getText().toString().trim().length() == 0 ||
                e3.getText().toString().trim().length() == 0 ||
                e4.getText().toString().trim().length() == 0) {
            showMessage("Error", "Please enter all values");
            return;
        }
        db.execSQL("INSERT INTO user VALUES('" + e1.getText() + "','" + PassWord +
                "','" + e3.getText() + "','" + e4.getText() +
                "');");
        showMessage("Success", "Record added");
        clearText();

        Intent i = new Intent(this,MainActivity.class);
        startActivity(i);



    }
    public void showMessage(String title,String message)
    {
        Builder builder=new Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }
    public void clearText()
    {
        EditText e1=findViewById(R.id.editText);
        EditText e2=findViewById(R.id.editText2);
        EditText e3=findViewById(R.id.editText3);
        EditText e4 = findViewById(R.id.editText4);
        e1.setText("");
        e2.setText("");
        e3.setText("");
        e4.setText("");
        e4.requestFocus();
    }
    public void show(View view){
        Cursor c=db.rawQuery("SELECT * FROM user", null);
        if(c.getCount()==0)
        {
            showMessage("Error", "No records found");
            return;
        }

        StringBuffer buffer=new StringBuffer();
        while(c.moveToNext())
        {
            buffer.append("Name: "+c.getString(0)+"\n");
            buffer.append("Password: "+c.getString(1)+"\n");
            buffer.append("email: "+c.getString(2)+"\n");
            buffer.append("Recover: "+c.getString(3)+"\n\n");


        }


        showMessage("User info", buffer.toString());
    }


}















