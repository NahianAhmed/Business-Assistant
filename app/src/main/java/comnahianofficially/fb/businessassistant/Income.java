package comnahianofficially.fb.businessassistant;

import android.app.AlertDialog;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Income extends AppCompatActivity {


    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_income);

        db = openOrCreateDatabase("Accounts", Context.MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS accountset(asset VARCHAR,liablity VARCHAR,ownercapital VARCHAR,drowing VARCHAR,revenue VARCHAR,expense VARCHAR);");
        Cursor c = db.rawQuery("SELECT * FROM accountset", null);
        if (c.getCount() == 0) {
            showMessage("Error", "No records found");
            return;
        }
        String reve="",exp="";
        double r=0,e=0,ans;
        StringBuffer buffer = new StringBuffer();
        while (c.moveToNext()) {
            buffer.append("Asset: " + c.getString(0) + "\n");
            buffer.append("Liablity: " + c.getString(1) + "\n");
            buffer.append("Owner Capital : " + c.getString(2) + "\n");
            buffer.append("Drowing: " + c.getString(3) + "\n");
            buffer.append("Revenue : " + c.getString(4) + "\n");
            buffer.append("Expense: " + c.getString(5) + "\n\n");
            reve=c.getString(4);
            exp=c.getString(5);
            r+=Double.parseDouble(reve);
            e+=Double.parseDouble(exp);




        }
        ans=r-e;
        TextView t1=findViewById(R.id.reAns);
        t1.setText(Double.toString(r));
        TextView t2=findViewById(R.id.exAns);
        t2.setText(Double.toString(e));
        TextView t3=findViewById(R.id.net);
        t3.setText(Double.toString(ans));
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
