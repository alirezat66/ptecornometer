package ir.greencode.cornometer.ui.Main.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.OnClick;
import ir.greencode.cornometer.R;

public class FragmentAbout extends Fragment {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_about, container, false);
        ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

    }

    @OnClick({R.id.btn_mail, R.id.btn_linked_in, R.id.btn_git , R.id.btn_denote})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_mail:
                sendMailForMe();
                break;
            case R.id.btn_linked_in:
                openLinkedIn();
                break;
            case R.id.btn_git:
                openGit();
                break;

            case R.id.btn_denote:
                break;
        }
    }

    private void openGit() {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/alirezat66/"));
        startActivity(browserIntent);
    }
    private void openGitHossein() {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/alirezat66/"));
        startActivity(browserIntent);
    }

    private void openLinkedIn() {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.linkedin.com/in/reza-taghizadeh-2738816a/"));
        startActivity(browserIntent);
    }
    private void openLinkedInForHossein() {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.linkedin.com/in/hptavakoli"));
        startActivity(browserIntent);
    }

    private void sendMailForMe() {
        Intent email = new Intent(Intent.ACTION_SEND);
        email.putExtra(Intent.EXTRA_EMAIL, new String[]{ "alirezataghizadeh66@gmail.com"});
        email.putExtra(Intent.EXTRA_SUBJECT, "from cornometer app");
        email.putExtra(Intent.EXTRA_TEXT, "Hi dear Reza,");
        //need this to prompts email client only
        email.setType("message/rfc822");

        startActivity(Intent.createChooser(email, "Choose an Email client :"));
    }
    private void sendMailForHossein() {
        Intent email = new Intent(Intent.ACTION_SEND);
        email.putExtra(Intent.EXTRA_EMAIL, new String[]{ "h.ptavakoli@gmail.com"});
        email.putExtra(Intent.EXTRA_SUBJECT, "from cornometer app");
        email.putExtra(Intent.EXTRA_TEXT, "Hi dear Hossein,");
        //need this to prompts email client only
        email.setType("message/rfc822");

        startActivity(Intent.createChooser(email, "Choose an Email client :"));
    }
}
