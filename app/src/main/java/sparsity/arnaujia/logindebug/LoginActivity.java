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

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;


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
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);

        if (this.containsNoFragments()) {
            LoginFragment loginf = this.getLoginFragment();
            this.launchContentFragment(R.id.login_activity_fragment_container, loginf);
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
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

        @Override
    @Subscribe
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
    @Subscribe
    public void onErrorLoginEvent(LoginErrorEvent loginErrorEvent) {
        Toast.makeText(getApplicationContext(), "Error Code " + loginErrorEvent.getCode() + "  "
                                                + loginErrorEvent.getDescrip(), Toast.LENGTH_SHORT).show();
    }
}
