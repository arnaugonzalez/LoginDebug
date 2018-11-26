package sparsity.arnaujia.logindebug;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import dama.upc.edu.login.event.LoginErrorEvent;
import dama.upc.edu.login.event.LoginSuccessfulEvent;
import dama.upc.edu.login.integration.ILoginOperationClient;
import dama.upc.edu.login.presentation.model.LoginVisualConfig;
import dama.upc.edu.login.presentation.ui.fragments.LoginFragment;
import dama.upc.edu.login.presentation.ui.utils.LoginFragmentExtrasMgr;

public class LoginActivity extends AppCompatActivity implements ILoginOperationClient {

    private UserView userView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_login);
        super.onCreate(savedInstanceState);

        if (this.containsNoFragments()) {
            this.launchContentFragment(R.id.login_activity_fragment_container, this.getLoginFragment());
        }

    }
    protected boolean containsNoFragments() {
        return this.getSupportFragmentManager() != null && this.getSupportFragmentManager().getBackStackEntryCount() <= 0;
    }

    protected void launchContentFragment(int container, Fragment fragment) {
            FragmentManager mgr = this.getSupportFragmentManager();
            FragmentTransaction trans = mgr.beginTransaction();
            trans.replace(container, fragment);
            trans.commit();
            mgr.executePendingTransactions();
    }

    private LoginFragment getLoginFragment(){
        LoginFragment loginFragment =
                LoginFragment.newInstance(
                        LoginFragmentExtrasMgr.getLoginExtras(

                                //XXX: test customization
                                new LoginVisualConfig()
                                //new LoginVisualConfig(false, "http://ajuntament.barcelona.cat/economia-social-solidaria/sites/default/files/logo-ajuntament-banca.png", Color.BLUE, "Test", Color.RED, Color.YELLOW)
                        )
                );

        return loginFragment;
    }

    @Override
    public void onSuccessfulLoginEvent(LoginSuccessfulEvent loginSuccessfulEvent) {
        this.userView =
                new UserView(
                        loginSuccessfulEvent.getId(),
                        loginSuccessfulEvent.getName(),
                        loginSuccessfulEvent.getLastname(),
                        loginSuccessfulEvent.getUsername() != null? loginSuccessfulEvent.getUsername() : "",
                        loginSuccessfulEvent.getEmail(),
                        loginSuccessfulEvent.getToken(),
                        loginSuccessfulEvent.isUpdatePass(),
                        loginSuccessfulEvent.getPhotoUri()
                );
        Intent i = new Intent(this, PostLogin.class);
        i.putExtra("user", userView);
        startActivity(i);
    }

    @Override
    public void onErrorLoginEvent(LoginErrorEvent loginErrorEvent) {
        Toast.makeText(getApplicationContext(), "Code " + loginErrorEvent.getCode() + ": "
                                                + loginErrorEvent.getMsg(), Toast.LENGTH_SHORT).show();
    }
}
