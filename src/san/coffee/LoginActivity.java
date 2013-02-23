package san.coffee;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

public class LoginActivity extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		Log.d("LoginActivity", "onCreate");
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
	}

}
