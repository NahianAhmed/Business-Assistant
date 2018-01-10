package comnahianofficially.fb.businessassistant;

import android.app.AlertDialog;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class BalanceSheet extends AppCompatActivity {
    SQLiteDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_balance_sheet);
        db = openOrCreateDatabase("Accounts", Context.MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS accountset(asset VARCHAR,liablity VARCHAR,ownercapital VARCHAR,drowing VARCHAR,revenue VARCHAR,expense VARCHAR);");
        Cursor c = db.rawQuery("SELECT * FROM accountset", null);
        if (c.getCount() == 0) {
            showMessage("Error", "No records found");
            return;
        }
        String reve="",exp="",asset="",li="",cap="",dr="";
        double a=0,l=0, r=0,e=0,ans,cp=0,d=0;
        StringBuffer buffer = new StringBuffer();
        while (c.moveToNext()) {
            buffer.append("Asset: " + c.getString(0) + "\n");
            buffer.append("Liablity: " + c.getString(1) + "\n");
            buffer.append("Owner Capital : " + c.getString(2) + "\n");
            buffer.append("Drowing: " + c.getString(3) + "\n");
            buffer.append("Revenue : " + c.getString(4) + "\n");
            buffer.append("Expense: " + c.getString(5) + "\n\n");
            asset=c.getString(0);
            li=c.getString(1);
            cap=c.getString(2);
            dr=c.getString(3);

            reve=c.getString(4);
            exp=c.getString(5);

            a+=Double.parseDouble(asset);
            l+=Double.parseDouble(li);
            cp+=Double.parseDouble(cap);
            d+=Double.parseDouble(dr);
            r+=Double.parseDouble(reve);
            e+=Double.parseDouble(exp);




        }
        Double Asset=a,Liability=l,ownerEeuity=cp-d+r-e;
        TextView t1=findViewById(R.id.aa);
        t1.setText(Double.toString(Asset));
        TextView t2=findViewById(R.id.bb);
        t2.setText(Double.toString(Liability));
        TextView t3=findViewById(R.id.cc);
        t3.setText(Double.toString(ownerEeuity));
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
