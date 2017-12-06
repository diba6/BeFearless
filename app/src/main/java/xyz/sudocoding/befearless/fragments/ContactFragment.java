package xyz.sudocoding.befearless.fragments;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import xyz.sudocoding.befearless.R;

public class ContactFragment extends Fragment{

    private View root;
    private Vector<Map<String, String>> contacts;

    //------------------------------- DEFAULTS
    /*
    * Default constructor required by android system
    * */
    public ContactFragment(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_contact, container, false);
        //__init__();
    }

    //------------------------------- PRIVATE
    /*
    * Method to init all the components of the fragment
    * */
    private void __init__(){
        //contacts = getContacts();
        //showContactList();

        contacts = new Vector<Map<String, String>>();

        String data = "Anamika: 9609772962, Anju: 9436133183, Ankita: 746793347, Bijoy: 8906108197, Swastik: 9706719169";
        String contacts[] = data.split(",");

        for(String item : contacts){
            String subs[] = item.split(":");
            Map<String, String> contactItem = new HashMap<String, String>();
            contactItem.put(subs[0], subs[1]);
            this.contacts.add(contactItem);
        }
        showContactList();

//        FloatingActionButton newContactFAB = root.findViewById(R.id.add_new_contact_fab);
//        newContactFAB.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                showContactList();
//            }
//        });
    }

    /*
    * Method to get the list of contacts
    * */
    private Vector<Map<String, String>> getContacts(){
        Vector<Map<String, String>> contacts = new Vector<Map<String, String>>();

        ContentResolver cr = getActivity().getContentResolver();
        Cursor cur = cr.query(ContactsContract.Contacts.CONTENT_URI, null, null, null, null);

        if((cur!=null ? cur.getCount() : 0) > 0){
            while(cur!=null && cur.moveToNext()){
                String id = cur.getString(
                        cur.getColumnIndex(ContactsContract.Contacts._ID));
                String name = cur.getString(
                        cur.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));

                String phoneNo = "";
                if(cur.getInt(cur.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER)) > 0){
                    Cursor pcur = cr.query(
                            ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                            null,
                            ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ? ",
                            new String[]{id}, null);
                    int flag=0;
                    while(pcur.moveToNext()){
                        String pn = pcur.getString(pcur.getColumnIndex(
                                ContactsContract.CommonDataKinds.Phone.NUMBER));
                        if(flag>0) phoneNo += ", ";
                        phoneNo += pn;
                    }
                    pcur.close();
                }

                Map<String, String> data = new HashMap<String, String>();
                data.put("id",id);
                data.put("name",name);
                data.put("phoneNo",phoneNo);
                contacts.add(data);
            }
        } if (cur!=null) {
            cur.close();
        }

        return contacts;
    }

    /*
    * Method to show contact lists and allow contact selection
    * */
    private void showContactList(){
        LinearLayout parent = root.findViewById(R.id.sub_contact_layout);
        LayoutInflater inflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        for(final Map<String, String> dat : contacts){
            View child = inflater.inflate(R.layout.contact_template_1, null);

            ((TextView)child.findViewById(R.id.contact_name_tv)).setText(dat.get("name"));
            ((TextView)child.findViewById(R.id.contact_number_tv)).setText(dat.get("phoneNo"));
            (child.findViewById(R.id.add_contact_btn)).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // TODO: send data set to Firebase to be stored
                    String dataSet[] = {dat.get("id"), dat.get("name"), dat.get("phoneNo")};
                }
            });

            parent.addView(child);
        }
    }
}
