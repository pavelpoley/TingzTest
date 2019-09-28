package pavelpoley.tingz.system;

import android.app.Activity;
import android.content.Intent;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import pavelpoley.tingz.R;


public class Navigation {

    public static void replaceFragment(FragmentManager fm, Fragment fragment, boolean backStack) {

        FragmentTransaction ft = fm.beginTransaction();

        if (backStack) {
            ft.addToBackStack(null);
        }

        ft.replace(R.id.container, fragment, fragment.getClass().getSimpleName())
                .commit();

        fm.executePendingTransactions();
    }

    public static void startActivityAndFinish(Activity activity, Class targetClass) {
        activity.startActivity(new Intent(activity, targetClass));
        activity.finish();
    }
}
