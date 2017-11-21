package com.example.asus.myapplication;

import android.content.Context;
import android.content.res.Resources;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by ASUS on 18/11/2017.
 */

public class AdaptadorP extends RecyclerView.Adapter<AdaptadorP.PacientesViewHolder> {

    private OnPacientesClickListener clickListener;
    private ArrayList<Pacientes> pacientes;
    private Resources res;
    private Context contexto;


    public AdaptadorP(Context contexto, ArrayList<Pacientes> pacientes,
                            OnPacientesClickListener clickListener){
        this.pacientes=pacientes;
        this.res=contexto.getResources();
        this.contexto=contexto;
        this.clickListener=clickListener;
    }

    @Override
    public PacientesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_pac,parent,false);
        return new PacientesViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final PacientesViewHolder holder, int position) {
        final Pacientes p = pacientes.get(position);

        StorageReference storageReference = FirebaseStorage.getInstance().getReference();
        storageReference.child(p.getFoto()).getDownloadUrl()
                .addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        Picasso.with(contexto).load(uri).into(holder.foto);
                    }
                });
        holder.cedula.setText(p.getCedula());
        holder.nombre.setText(p.getNombre());
        holder.edad.setText(p.getEdad());
        holder.valoracion_medica.setText(p.getValoracion_medica());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickListener.onPacientesClick(p);
            }
        });

    }
    @Override
    public int getItemCount() {
        return pacientes.size();
    }

    public static class PacientesViewHolder extends RecyclerView.ViewHolder{

        private ImageView foto;
        private TextView cedula;
        private TextView nombre;
        private TextView edad;
        private TextView valoracion_medica;


        public PacientesViewHolder(View item){
            super(item);

            foto = (ImageView)item.findViewById(R.id.imgFoto3);
            cedula = (TextView) item.findViewById(R.id.lblCedulap);
            nombre = (TextView) item.findViewById(R.id.lblNombrep);
            edad = (TextView) item.findViewById(R.id.lblEdadp);
            valoracion_medica = (TextView) item.findViewById(R.id.lblTratamientop);
        }

    }

    public interface OnPacientesClickListener{
        void onPacientesClick(Pacientes p);
    }
}
