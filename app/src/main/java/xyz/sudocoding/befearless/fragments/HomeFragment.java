package xyz.sudocoding.befearless.fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.telephony.SmsManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import xyz.sudocoding.befearless.R;

public class HomeFragment extends Fragment {

    private Button shareLocationBTN, shareLocDefaultBtn, shareLocSocialBtn, sendMsgBtn, callBtn;
    private ConstraintLayout subBTNLayout;
    private ImageView profileImgView;
    private TextView userNameTV;

    private View root;

    //------------------------------ DEFAULT
    public HomeFragment(){}

    @Override
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup container, Bundle savedInstances){
        root = layoutInflater.inflate(R.layout.fragment_home, container, false);
        __init__();
        return root;
    }

    //------------------------------ PUBLIC

    //------------------------------ PRIVATE
    /*
    * Method to init fragment components
    * */
    private void __init__(){
        shareLocationBTN = (Button) root.findViewById(R.id.share_location_btn);

        subBTNLayout = (ConstraintLayout) root.findViewById(R.id.location_sub_btn_layout);
        shareLocDefaultBtn = (Button) root.findViewById(R.id.share_loc_default_btn);
        shareLocSocialBtn = (Button) root.findViewById(R.id.share_loc_social_btn);

        subBTNLayout.setVisibility(View.GONE);
        shareLocationBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean isOn = subBTNLayout.getVisibility()==View.VISIBLE;
                subBTNLayout.setVisibility(isOn ? View.GONE : View.VISIBLE);
            }
        });

        shareLocDefaultBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO: send location default method
            }
        });

        shareLocSocialBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO: send location social method
                shareinWhatsapp("I am in danger. Need help. I'm at https://www.google.co.in/maps/@27.1832436,88.501825,17z?hl=en");
            }
        });

        sendMsgBtn = (Button) root.findViewById(R.id.send_msg_btn);
        sendMsgBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO: send message method
                try {
                    SmsManager sms = SmsManager.getDefault();
                    sms.sendTextMessage("+919435276379", null, "I am in danger. Need help. I'm at https://www.google.co.in/maps/@27.1832436,88.501825,17z?hl=en", null, null);
                    Toast.makeText(getActivity(), "Message sent to your trusted contacts", Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    Toast.makeText(getActivity(), "No service found", Toast.LENGTH_SHORT).show();
                }
            }
        });

        callBtn = (Button) root.findViewById(R.id.make_call_btn);
        callBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO: make call method
                Intent phoneIntent = new Intent(Intent.ACTION_CALL);
                phoneIntent.setData(Uri.parse("tel:+919435276379"));
                startActivity(phoneIntent);
            }
        });

        profileImgView = (ImageView) root.findViewById(R.id.profileImage);
        // TODO: load user image from uri
        // profileImgView.setImageBitmap();

        userNameTV = (TextView) root.findViewById(R.id.user_name_tv);
        // TODO: set user name text
        // userNameTV.setText();
    }

    /*
    * Method to share in what's app
    * */
    void shareinWhatsapp(String shareURL) {
        Intent waIntent = new Intent(Intent.ACTION_SEND);
        waIntent.setType("text/plain");
        waIntent.setPackage("com.whatsapp");
        if (waIntent != null) {
            waIntent.putExtra(
                    Intent.EXTRA_TEXT,
                    shareURL);
            startActivity(Intent.createChooser(waIntent, "Share with"));
        } else
            Toast.makeText(getActivity(), "WhatsApp not Installed", Toast.LENGTH_SHORT).show();
    }
}
