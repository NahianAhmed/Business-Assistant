package comnahianofficially.fb.businessassistant;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity implements View.OnClickListener {
    SQLiteDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db=openOrCreateDatabase("Account", Context.MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS user(name VARCHAR,password VARCHAR,email VARCHAR,recover VARCHAR);");
    }

    @Override
    public void onClick(View view) {
        EditText e1=findViewById(R.id.name);
        EditText e2=findViewById(R.id.password);
        Button Login=findViewById(R.id.login);
        Button forgot=findViewById(R.id.forgot);
        Button signup=findViewById(R.id.Signup);
        String uname=e1.getText().toString();
        String upass=e2.getText().toString();
        int PassWord=upass.hashCode();
        String P1=String.valueOf(PassWord);
        if(view==Login){
            String name="",pass="";




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
                    name=c.getString(0);
                    pass=c.getString(1);

                }
            //showMessage("User info", buffer.toString());
                if(name.equals(uname) && pass.equals(P1)) {
                    Intent i =new Intent(this,Wellcome.class);
                    i.putExtra("name",name);
                    e1.setText("");
                    e2.setText("");


                    startActivity(i);


                }
                else {
                    showMessage("Error", "User Name or PassWord Mismatch");
                }



                //TextView tv = findViewById(R.id.textView3);
                //tv.setText(x);
                //showMessage("User info", buffer.toString());





        }
        if(view==forgot){
            Intent i = new Intent(this,forgot.class);
            startActivity(i);



        }
        if(view==signup){
            Intent i =new Intent(this,signup.class);

             startActivity(i);


        }





    }
    public void showMessage(String title,String message)
    {
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }
}
