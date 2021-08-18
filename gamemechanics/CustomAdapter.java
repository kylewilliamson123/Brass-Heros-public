package com.example.brassheroes.gamemechanics;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.brassheroes.R;
import com.example.brassheroes.items.Armor;
import com.example.brassheroes.items.Equipment;
import com.example.brassheroes.items.Weapon;

import java.util.ArrayList;

public class CustomAdapter extends ArrayAdapter<Equipment> implements View.OnClickListener {

    private ArrayList<Equipment> inventory;
    Context context;
    PlayerManager playerManager;

    // View lookup cache
    private static class ViewHolder {
        TextView txtEquipmentInfo;
        ImageView eqIcon;
        Button btnEquip;
    }

    public CustomAdapter(ArrayList<Equipment> inventory, Context context) {
        super(context, R.layout.list_layout, inventory);
        this.inventory = inventory;
        this.context = context;
        playerManager = new PlayerManager(context);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        Equipment equipment = getItem(position);

        ViewHolder viewHolder; // view lookup cache stored in tag

        final View result;

        if (convertView == null) {

            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.list_layout, parent, false);
            viewHolder.txtEquipmentInfo = (TextView) convertView.findViewById(R.id.inventoryListDescription);
            viewHolder.eqIcon = (ImageView) convertView.findViewById(R.id.inventoryListIcon);
            viewHolder.btnEquip = (Button) convertView.findViewById(R.id.inventoryEquipPiece);
            result = convertView;

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
            result = convertView;
        }

        viewHolder.txtEquipmentInfo.setText(equipment.toString());
        viewHolder.eqIcon.setImageResource(equipment.getIcon());
        viewHolder.btnEquip.setOnClickListener(this);
        viewHolder.btnEquip.setTag(position);
        return convertView;
    }

    @Override
    public void onClick(View v) {
        int position = (Integer) v.getTag();
        Object object = getItem(position);
        Equipment equipment = (Equipment) object;
        switch (v.getId()) {
            case R.id.inventoryEquipPiece:
               playerManager.equip(equipment);
                //System.out.println(object.getClass().toString());
                Toast.makeText(context, equipment.getType()+" equipped", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}

