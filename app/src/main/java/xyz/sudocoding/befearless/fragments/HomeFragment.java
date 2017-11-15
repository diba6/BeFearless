package xyz.sudocoding.befearless.fragments;

import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import xyz.sudocoding.befearless.R;

public class HomeFragment extends Fragment {

    private Button shareLocationBTN, shareLocDefaultBtn, shareLocSocialBtn, sendMsgBtn, callBtn;
    private ConstraintLayout subBTNLayout;
    private ImageView profileImgView;
    private TextView userNameTV;

    private View root;

    //------------------------------ DEFAULT
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
            }
        });

        sendMsgBtn = (Button) root.findViewById(R.id.send_msg_btn);
        sendMsgBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO: send message method
            }
        });

        callBtn = (Button) root.findViewById(R.id.make_call_btn);
        callBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO: make call method
            }
        });

        profileImgView = (ImageView) root.findViewById(R.id.profileImage);
        // TODO: load user image from uri
        // profileImgView.setImageBitmap();

        userNameTV = (TextView) root.findViewById(R.id.user_name_tv);
        // TODO: set user name text
        // userNameTV.setText();
    }
}
