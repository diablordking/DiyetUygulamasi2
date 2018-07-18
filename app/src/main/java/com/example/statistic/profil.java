package com.example.statistic;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.R;
import com.example.model.Dater;
import com.example.model.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.jjoe64.graphview.series.DataPoint;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link profil.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link profil#newInstance} factory method to
 * create an instance of this fragment.
 */
public class profil extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private DatabaseReference databaseReferenceCustomers;
    FirebaseAuth mAuth = FirebaseAuth.getInstance();
    TextView sabahtext,ogletext,aksamtext,durum,ideal;
    User a;
    int boy=180;
    int yas = 23;
    int kilo =50;
    double idealKilo;
    int Mevcutkalori; // Mevcut kiloyu korumak için gerekli kalori miktarı

    int idealKiloAraligi1;
    int idealKiloAraligi2;
    double kitleEndeks;


    private OnFragmentInteractionListener mListener;

    public profil() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static profil newInstance(String param1, String param2) {
        profil fragment = new profil();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    public void kalori() {
        databaseReferenceCustomers = FirebaseDatabase.getInstance().getReference("users");

        databaseReferenceCustomers.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot myDatasnaphot : dataSnapshot.getChildren()) {
                    a = myDatasnaphot.getValue(User.class);

                    String ab = a.getId();
                    String b = mAuth.getUid();
                    if (ab.equals(b)) {
                        kilo = Integer.parseInt(a.getWeight());
                        boy = Integer.parseInt(a.getHeight());
                        yas = Integer.parseInt(a.getAge());


                        idealKilo= (50 + (2.3 / 2.54) * (boy - 152.4));
                        idealKilo=(double)Math.round(idealKilo * 10d) / 10d;
                        Mevcutkalori= (int)(1.35*(float)((10*kilo)+(int)(6.25*(float)boy)-(5*yas)+5));

                        ideal.setText("İdeal Kilonuz:"+idealKilo);

                        idealKiloAraligi1=(int)idealKilo-11;
                        idealKiloAraligi2=(int)idealKilo+11;
                        kitleEndeks=kilo/(((float)boy/100)*((float)boy/100));
                        kitleEndeks=(double)Math.round(kitleEndeks * 10d) / 10d;
                        if(kitleEndeks>16 && kitleEndeks <18.5) {
                            durum.setText("Zayıfsın kilo alman gerek");

                            sabahtext.setText(R.string.dSabah );
                            ogletext.setText(R.string.dOgle );
                            aksamtext.setText(R.string.dAksam );


                        }
                        else if(kitleEndeks>=18.5 && kitleEndeks <30) {
                            durum.setText("İdeal Kilodasın.");
                            sabahtext.setText(R.string.nSabah );
                            ogletext.setText(R.string.nOgle );
                            aksamtext.setText(R.string.nAksam );
                        }
                        else if(kitleEndeks>=30) {
                            durum.setText("Acillen Yemeği Bırakman lazım.");
                            sabahtext.setText(R.string.sSabah );
                            ogletext.setText(R.string.sOgle );
                            aksamtext.setText(R.string.sAksam );
                        }


                    }

                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View RootView = inflater.inflate(R.layout.fragment_profil, container, false);


        sabahtext = RootView.findViewById(R.id.idSabah);

        ogletext = RootView.findViewById(R.id.idOgle);

        aksamtext = RootView.findViewById(R.id.idAksam);

        durum = RootView.findViewById(R.id.durum);

        ideal = RootView.findViewById(R.id.ideal);

        kalori();

        // Inflate the layout for this fragment
        return RootView;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
