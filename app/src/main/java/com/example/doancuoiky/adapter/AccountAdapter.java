package com.example.doancuoiky.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.doancuoiky.R;
import com.example.doancuoiky.model.NewAccount;
import java.util.List;

public class AccountAdapter extends BaseAdapter {
    private Context context;
    private List<NewAccount> list;

    public AccountAdapter(Context context, List<NewAccount> list) {
        this.context = context;
        this.list = list;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view==null){
            view = LayoutInflater.from(context).inflate(R.layout.layout_account,null);


        }
        TextView tvAccountID = view.findViewById(R.id.tvAccountID);
        TextView tvUsername = view.findViewById(R.id.tvUsername);
        TextView tvPassword = view.findViewById(R.id.tvPassword);
        TextView tvNameAcc = view.findViewById(R.id.tvNameAcc);
        TextView tvRole = view.findViewById(R.id.tvRole);

        NewAccount nac = list.get(i);

        tvAccountID.setText(""+ nac.getId());
        tvUsername.setText(nac.getUsername());
        tvPassword.setText(nac.getPassword());
        tvNameAcc.setText(nac.getName());
        tvRole.setText(nac.getRole());
        return view;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }
}
