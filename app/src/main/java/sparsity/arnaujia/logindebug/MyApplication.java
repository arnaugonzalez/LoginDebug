package sparsity.arnaujia.logindebug;

import android.app.Application;
import android.content.res.AssetManager;

import dama.upc.edu.login.config.worker.LoginModuleConfigLauncher;
import dama.upc.edu.login.event.LoginErrorEvent;
import dama.upc.edu.login.event.LoginSuccessfulEvent;
import dama.upc.edu.login.integration.ILoginModuleAppClient;
import dama.upc.edu.login.integration.ILoginOperationClient;

public class MyApplication extends Application implements ILoginModuleAppClient, ILoginOperationClient {

    @Override
    public LoginModuleConfigLauncher launchLoginModuleConfig(AssetManager assetManager) {
        return null;
    }

    @Override
    public void onSuccessfulLoginEvent(LoginSuccessfulEvent loginSuccessfulEvent) {

    }

    @Override
    public void onErrorLoginEvent(LoginErrorEvent loginErrorEvent) {

    }
}
