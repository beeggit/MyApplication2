package as.myapplication;

import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.app.Activity;
import android.widget.Toast;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;


public class MainActivity extends AppCompatActivity {
    //String great="";
    private static final String TAG = "MINE";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //ohneNTLM();
        //WithNTLM();
        new Task().execute();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        //if (id == R.id.action_settings) {
        //    return true;
        //}

        return super.onOptionsItemSelected(item);
    }


    public void WithNTLM()
    {
        String namespace = "urn:microsoft-dynamics-schemas/page/salesorder";
        String url = "http://10.0.9.120:7047/DynamicsNAV/WS/EGI/Page/SalesOrder";
        String soap_action = "urn:microsoft-dynamics-schemas/page/salesorder:Read";
        String method_name = "Read";
        String great;
        try
        {
            SoapObject request = new SoapObject(namespace, method_name);

            String custID = "527652";
            PropertyInfo custProp = new PropertyInfo();
            custProp.setName("No");
            custProp.setValue(custID);
            custProp.setType(String.class);
            request.addProperty(custProp);

            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);

            envelope.dotNet = true;
            envelope.setOutputSoapObject(request);
            // HttpTransportSE transport = new HttpTransportSE(url);

            Log.i(TAG, "WithNTLM-1");

            NtlmTransport ntlm = new NtlmTransport();
            ntlm.setCredentials(url, "brad", "larry.12", "LOGAN","");
            ntlm.call(soap_action, envelope); // Receive Error here!
            Log.i(TAG, "WithNTLM-2");
            SoapObject result = (SoapObject) envelope.getResponse();
            great = result.toString();
            Log.i(TAG, great);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            great = e.toString();
            System.out.println(great);
            Toast.makeText(this, great, Toast.LENGTH_LONG).show();
        }
    }

    public class Task extends AsyncTask<Void,Void,Void>
    {

        @Override
        protected Void doInBackground(Void... params) {
            // TODO Auto-generated method stub

            //Log.i(TAG, "doInBackground-1");
            //great=
                    WithNTLM();
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            //Log.i(TAG, "doInBackground");
            //txt.setText(great);
        }



    }


}