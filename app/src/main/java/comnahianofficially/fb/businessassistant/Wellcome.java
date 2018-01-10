package comnahianofficially.fb.businessassistant;

import android.app.AlertDialog;
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

public class Wellcome extends Activity implements OnClickListener {

    SQLiteDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wellcome);
        String name=getIntent().getExtras().getString("name");
        TextView tv =findViewById(R.id.user);
        tv.setText("Welcome "+name);
        db=openOrCreateDatabase("Accounts", Context.MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS accountset(asset VARCHAR,liablity VARCHAR,ownercapital VARCHAR,drowing VARCHAR,revenue VARCHAR,expense VARCHAR);");


    }


    @Override
    public void onClick(View view) {
        Button b1 = findViewById(R.id.transaction);
        Button b2 = findViewById(R.id.showall);
        Button b3 = findViewById(R.id.balance);
        Button b4 = findViewById(R.id.income);
        Button b5 = findViewById(R.id.b5);
        Button b6 = findViewById(R.id.b6);

        if (view == b1) {

            Intent i = new Intent(this, entryTransaction.class);
            startActivity(i);

        }


        if (view == b2) {

            Cursor c = db.rawQuery("SELECT * FROM accountset", null);
            if (c.getCount() == 0) {
                showMessage("Error", "No records found");
                return;
            }

            StringBuffer buffer = new StringBuffer();
            while (c.moveToNext()) {
                buffer.append("Asset: " + c.getString(0) + "\n");
                buffer.append("Liablity: " + c.getString(1) + "\n");
                buffer.append("Owner Capital : " + c.getString(2) + "\n");
                buffer.append("Drowing: " + c.getString(3) + "\n");
                buffer.append("Revenue : " + c.getString(4) + "\n");
                buffer.append("Expense: " + c.getString(5) + "\n\n");


            }


            showMessage("Transction info", buffer.toString());
        }


        if (view == b3) {
            Intent intent = new Intent(this,BalanceSheet.class);
            startActivity(intent);
        }
        if (view == b4) {
            Intent i = new Intent(this,Income.class);
            startActivity(i);

        }
        if (view == b5) {
            db.execSQL("DELETE  FROM accountset;");
            showMessage("Delete", "No records Now");

        }
        if(view==b6){

         Intent a = new Intent(this,OwnerEquity.class);
         startActivity(a);

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
