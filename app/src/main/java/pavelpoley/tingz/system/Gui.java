package pavelpoley.tingz.system;

import android.widget.Toast;

import pavelpoley.tingz.App;

public class Gui {
    public static void showToast(String msg) {
        Toast.makeText(App.get().getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
    }
}
