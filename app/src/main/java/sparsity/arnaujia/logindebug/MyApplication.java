package sparsity.arnaujia.logindebug;

import android.app.Application;
import android.content.res.AssetManager;

import dama.upc.edu.login.config.worker.LoginModuleConfigLauncher;
import dama.upc.edu.login.integration.ILoginModuleAppClient;
import dama.upc.edu.login.storage.database.LoginDatabase;

public class MyApplication extends Application implements ILoginModuleAppClient {

    @Override
    public void onCreate() {
        super.onCreate();

        // initRoom
        LoginDatabase.getDatabase(this);

        // launchModuleConfigurations
        this.launchLoginModuleConfig(this.getAssets());
    }

    @Override
    public LoginModuleConfigLauncher launchLoginModuleConfig(AssetManager assetManager) {
        return new LoginModuleConfigLauncher(assetManager);
    }
}
