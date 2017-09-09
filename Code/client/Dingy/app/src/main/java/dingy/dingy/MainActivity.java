package dingy.dingy;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
public class MainActivity extends AppCompatActivity {

    private EditText test_tf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        test_tf = (EditText) findViewById(R.id.test_tf);

        Button test_btn = (Button) findViewById(R.id.test_btn);

        test_btn.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {

                new RestClient().execute(test_tf.getText().toString());
            }
        });
    }

    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    public native String stringFromJNI();

    // Used to load the 'native-lib' library on application startup.
    static {
        System.loadLibrary("native-lib");
    }
}
