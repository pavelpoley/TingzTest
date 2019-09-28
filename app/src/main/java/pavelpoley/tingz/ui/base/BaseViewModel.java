package pavelpoley.tingz.ui.base;

import androidx.lifecycle.ViewModel;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public class BaseViewModel extends ViewModel {

    //holds RxJava subscriptions
    private final CompositeDisposable disposables = new CompositeDisposable();

    @Override
    protected void onCleared() {
        super.onCleared();
        clearDisposables();
    }

    private void clearDisposables() {
        if (!disposables.isDisposed()) {
            disposables.dispose();
        }
    }

    protected void addSubscription(Disposable disposable) {
        disposables.add(disposable);
    }
}
