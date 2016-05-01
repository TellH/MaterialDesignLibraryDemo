package com.tellh.materialdesignlibrarydemo.BottomSheet;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.tellh.materialdesignlibrarydemo.R;

import net.soulwolf.widget.dialogbuilder.DialogBuilder;
import net.soulwolf.widget.dialogbuilder.MasterDialog;
import net.soulwolf.widget.dialogbuilder.OnItemClickListener;
import net.soulwolf.widget.dialogbuilder.adapter.GridDialogAdapter;
import net.soulwolf.widget.dialogbuilder.adapter.TextDialogAdapter;
import net.soulwolf.widget.dialogbuilder.dialog.GridMasterDialog;
import net.soulwolf.widget.dialogbuilder.dialog.ListMasterDialog;
import net.soulwolf.widget.dialogbuilder.model.GridModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tlh on 2016/4/12.
 */
public class BottomDialogActivity extends AppCompatActivity implements View.OnClickListener, OnItemClickListener, ListPopWindow.OnBottomTextviewClickListener, ListPopWindow.OnPopItemClickListener {
    private Button button;
    private Dialog mBottomSheetDialog;
    private Button anotherBotton;
    private ListMasterDialog dialog;
    private Button gridDialogButton;
    private GridMasterDialog gridDialog;
    private Button btnBottomSheetDlg;
    private BottomSheetDialog bottomSheetDlg;
    private Button btnBottomSheetActivity;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_dialog);
        initView();
    }

    private void initView() {
        button = (Button) findViewById(R.id.button);
        gridDialogButton = (Button) findViewById(R.id.button3);
        anotherBotton = (Button) findViewById(R.id.button2);
        btnBottomSheetDlg = (Button) findViewById(R.id.btn_bottom_sheet_dlg);
        btnBottomSheetActivity = (Button) findViewById(R.id.button4);
        btnBottomSheetActivity.setOnClickListener(this);
        button.setOnClickListener(this);
        anotherBotton.setOnClickListener(this);
        gridDialogButton.setOnClickListener(this);
        btnBottomSheetDlg.setOnClickListener(this);
        View view = getLayoutInflater().inflate(R.layout.bottom_dialog, null);
        TextView tv_account = (TextView) view.findViewById(R.id.tv_account);
        TextView tv_compare = (TextView) view.findViewById(R.id.tv_compare);
        TextView tv_desktop = (TextView) view.findViewById(R.id.tv_desktop);
        TextView tv_location = (TextView) view.findViewById(R.id.tv_location);
        mBottomSheetDialog = new Dialog(BottomDialogActivity.this, R.style.MaterialDialogSheet);
        mBottomSheetDialog.setContentView(view);
        mBottomSheetDialog.setCancelable(true);
        mBottomSheetDialog.getWindow().setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        mBottomSheetDialog.getWindow().setGravity(Gravity.BOTTOM);
        mBottomSheetDialog.dismiss();
        tv_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(BottomDialogActivity.this, "account", Toast.LENGTH_SHORT).show();
                mBottomSheetDialog.dismiss();
            }
        });

        tv_compare.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Toast.makeText(BottomDialogActivity.this, "compare", Toast.LENGTH_SHORT).show();
                mBottomSheetDialog.dismiss();
            }
        });

        tv_desktop.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Toast.makeText(BottomDialogActivity.this, "desktop", Toast.LENGTH_SHORT).show();
                mBottomSheetDialog.dismiss();
            }
        });

        tv_location.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Toast.makeText(BottomDialogActivity.this, "location", Toast.LENGTH_SHORT).show();
                mBottomSheetDialog.dismiss();
            }
        });


        DialogBuilder builder = new DialogBuilder(this)
                .setAnimation(R.anim.da_slide_in_bottom, R.anim.da_slide_out_bottom)
                .setOnItemClickListener(this)
                .setGravity(net.soulwolf.widget.dialogbuilder.Gravity.BOTTOM);
        dialog = new ListMasterDialog(builder);
        dialog.setAdapter(new TextDialogAdapter(this, getSimpleStringData(4)));


        gridDialog = new GridMasterDialog(builder);
        gridDialog.setAdapter(new GridDialogAdapter(this, getSimpleGridModelData()));
        gridDialog.setNumColumns(4);


        bottomSheetDlg = new BottomSheetDialog(this);
        View contentView = getLayoutInflater().inflate(R.layout.bottom_dialog, null);
        bottomSheetDlg.setContentView(contentView);
    }

    private GridModel[] getSimpleGridModelData() {
        return new GridModel[]{
                new GridModel(R.drawable.share_layout_qq_icon, "QQ"),
                new GridModel(R.drawable.share_layout_wx_icon, "微信"),
                new GridModel(R.drawable.share_layout_friends_icon, "朋友圈"),
                new GridModel(R.drawable.share_layout_qq_icon, "QQ"),
                new GridModel(R.drawable.share_layout_wx_icon, "微信"),
                new GridModel(R.drawable.share_layout_friends_icon, "朋友圈")
        };
    }

    private String[] getSimpleStringData(int size) {
        String[] array = new String[size];
        for (int i = 0; i < size; i++) {
            array[i] = "SIMPLE-DATA" + i;
        }
        return array;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button:
                mBottomSheetDialog.show();
                break;
            case R.id.button2:
                dialog.show();
                break;
            case R.id.button3:
                gridDialog.show();
                break;
            case R.id.btn_bottom_sheet_dlg:
                View contentView = getLayoutInflater().inflate(R.layout.bottom_dialog, null);
                bottomSheetDlg.setContentView(contentView);
                bottomSheetDlg.show();
                break;
            case R.id.button4:
                startActivity(new Intent(this, BottomSheetActivity.class));
                break;
            case R.id.button5:
                List<PopBean> pops = new ArrayList<>();
                for (int i = 0; i < 5; i++) {
                    PopBean pop = new PopBean("item" + i, 0);
                    pops.add(pop);
                }
                ListPopWindow popWindow = new ListPopWindow(this, this, this, v, pops, "cancel", "title");
                popWindow.showAtLocation(v, Gravity.CENTER | Gravity.BOTTOM, 0, 0);
                break;
        }
    }

    @Override
    public void onItemClick(MasterDialog dialog, View view, int position) {
        Toast.makeText(this, "Item click:" + position, Toast.LENGTH_SHORT).show();
        dialog.dismiss();
    }

    @Override
    public void onBottomClick() {
        Toast.makeText(BottomDialogActivity.this, "bottom click", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPopItemClick(View view, int position) {
        Toast.makeText(BottomDialogActivity.this, "pos:" + position, Toast.LENGTH_SHORT).show();
    }
}
