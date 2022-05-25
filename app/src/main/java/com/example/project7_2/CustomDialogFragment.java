package com.example.project7_2;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CustomDialogFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CustomDialogFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public CustomDialogFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CustomDialogFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CustomDialogFragment newInstance(String param1, String param2) {
        CustomDialogFragment fragment = new CustomDialogFragment();
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

    TextView tvName, tvEmail;
    Button btn_custom_dialog;
    EditText dlgName, dlgEmail;
    View dialogView, toastView; //다이얼로그 자체를 뷰로


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        ViewGroup rootview = (ViewGroup) inflater.inflate(R.layout.fragment_custom_dialog, container, false);
        tvName = rootview.findViewById(R.id.tvName);
        tvEmail = rootview.findViewById(R.id.tvEmail);


        btn_custom_dialog = rootview.findViewById(R.id.btnCustomDialog);

        //사용자가 만든 다이얼 로고를 붙여준다.
        btn_custom_dialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dialogView = View.inflate(getActivity(), R.layout.dialog1, null);
                //화면이 이동이 되는게 아니라 현재 화면에서 대화상자를 띄워야 한다.
                AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity());
                dialog.setTitle("사용자정보입력");
                dialog.setIcon(R.drawable.icon_custom_dialog);
                //setVi
                dialog.setView(dialogView); //대화상자에 들어갈 내용을 붙인다. 1.view dialogView를 만든다. 2. dialogView = View.inflate(getActivity(), R.layout.dialog1, null);를 한다 이 구문을 적는다.
                dialog.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //view rootview가 아니라 dialogview에 있다.
                        dlgName = dialogView.findViewById(R.id.dlgName);
                        dlgEmail = dialogView.findViewById(R.id.dlgEmail);
                        //사용자가 입력한 내용을 뿌려준다.
                        tvName.setText(dlgName.getText().toString());
                        tvEmail.setText(dlgEmail.getText().toString());

                    }
                }); //실행 아무것도 안하려면 null을 준다.
                dialog.setNegativeButton("취소", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast toast = new Toast(getActivity());
                        toastView = (View) View.inflate(getActivity(), R.layout.toast1,null);
                        toast.setView(toastView);
                        toast.show();

                    }
                });
                dialog.show();
            }
        });

        return rootview;
    }
}