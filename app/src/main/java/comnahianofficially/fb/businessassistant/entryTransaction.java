package comnahianofficially.fb.businessassistant;

import android.app.AlertDialog;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;

public class entryTransaction extends AppCompatActivity {
    SQLiteDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entry_transaction);
        db=openOrCreateDatabase("Accounts", Context.MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS accountset(asset VARCHAR,liablity VARCHAR,ownercapital VARCHAR,drowing VARCHAR,revenue VARCHAR,expense VARCHAR);");
    }
    public void save(View v){

        EditText e1=findViewById(R.id.asset);
        EditText e2=findViewById(R.id.liablity);
        EditText e3=findViewById(R.id.ownereq);
        RadioGroup RG=findViewById(R.id.rg1);
        int rgid = RG.getCheckedRadioButtonId();
        String ownercapital="0",drowing="0",revenue="0",expense="0";

        if (e1.getText().toString().trim().length() == 0 ||
                e2.getText().toString().trim().length() == 0 ||
                e3.getText().toString().trim().length() == 0) {
            showMessage("Error", "Please enter values if Empty then 0");
            return;
        }
        if(rgid==R.id.rb1){
            ownercapital=e3.getText().toString();
            db.execSQL("INSERT INTO accountset VALUES('" + e1.getText() + "','" + e2.getText() + "','" + ownercapital + "','" +
                    drowing + "','" + revenue + "','" + expense + "');");
            showMessage("Success", "Record added");
            clearText();

        }
        else if(rgid==R.id.rb2){


            drowing=e3.getText().toString();
            db.execSQL("INSERT INTO accountset VALUES('" + e1.getText() + "','" + e2.getText() + "','" + ownercapital + "','" +
                    drowing + "','" + revenue + "','" + expense + "');");
            showMessage("Success", "Record added");
            clearText();

        }
        else if(rgid==R.id.rb3){


            revenue=e3.getText().toString();
            db.execSQL("INSERT INTO accountset VALUES('" + e1.getText() + "','" + e2.getText() + "','" + ownercapital + "','" +
                    drowing + "','" + revenue + "','" + expense + "');");
            showMessage("Success", "Record added");
            clearText();

        }
        else if(rgid==R.id.rb4){


            expense=e3.getText().toString();
            db.execSQL("INSERT INTO accountset VALUES('" + e1.getText() + "','" + e2.getText() + "','" + ownercapital + "','" +
                    drowing + "','" + revenue + "','" + expense + "');");
            showMessage("Success", "Record added");
            clearText();

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
    public void clearText()
    {
        EditText e1=findViewById(R.id.asset);
        EditText e2=findViewById(R.id.liablity);
        EditText e3=findViewById(R.id.ownereq);

        e1.setText("");
        e2.setText("");
        e3.setText("");

    }

}
