package com.app.meucalendario.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.app.meucalendario.model.Dia;
import com.app.meucalendario.model.Mes;
import com.app.meucalendario.R;

import java.util.Collections;
import java.util.List;

public class AdapterCalendario extends RecyclerView.Adapter<AdapterCalendario.MyViewHolder>{

    private List<Mes> listaDeMeses;
    private Mes mes;

    public AdapterCalendario(List<Mes> listaDeMeses) {
        this.listaDeMeses = listaDeMeses;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View item = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_calendario, parent, false);
        return new MyViewHolder(item);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        mes = listaDeMeses.get(position);

        holder.tvMes.setText(mes.getNome());
        holder.tvAno.setText(String.valueOf(mes.getAno()));

        AdapterDiasDoCalendario adapter = new AdapterDiasDoCalendario(mes.getListaDeDiasDoMes(), mes.getListaDeFeriados());
        GridLayoutManager layoutManager = new GridLayoutManager(holder.rvDiasDoCalendario.getContext(), 7);
        holder.rvDiasDoCalendario.setLayoutManager(layoutManager);
        holder.rvDiasDoCalendario.setHasFixedSize(true);
        holder.rvDiasDoCalendario.setAdapter(adapter);

        if(!mes.getListaDeFeriados().isEmpty()) {
            setarFeriados(holder, mes.getListaDeFeriados());
        }
    }

    @Override
    public int getItemCount() {
        return listaDeMeses.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        RecyclerView rvDiasDoCalendario;
        TextView tvMes, tvAno, tvFeriadoA, tvFeriadoB, tvFeriadoC;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            rvDiasDoCalendario = itemView.findViewById(R.id.rvDiasDoCalendario);
            tvMes = itemView.findViewById(R.id.tvMes);
            tvAno = itemView.findViewById(R.id.tvAno);
            tvFeriadoA = itemView.findViewById(R.id.tvFeriadoA);
            tvFeriadoB = itemView.findViewById(R.id.tvFeriadoB);
            tvFeriadoC = itemView.findViewById(R.id.tvFeriadoC);
        }
    }

    public void setarFeriados(MyViewHolder holder, List<Dia> listaDeFeriados) {
        //ordenar lista
        Collections.sort(listaDeFeriados, (d1, d2) -> d1.getDiaDoMes() - d2.getDiaDoMes());

        Dia dia = listaDeFeriados.get(0);
        String feriado = dia.getDiaDoMes() + " - " + dia.getFeriado();
        holder.tvFeriadoA.setText(feriado);
        holder.tvFeriadoA.setCompoundDrawablesRelativeWithIntrinsicBounds(setarCorDoDia(dia.getDiaDaSemana()), 0, 0, 0);
        holder.tvFeriadoA.setCompoundDrawablePadding(10);

        if(listaDeFeriados.size()  > 1) {
            dia = listaDeFeriados.get(1);
            feriado = dia.getDiaDoMes() + " - " + dia.getFeriado();
            holder.tvFeriadoB.setText(feriado);
            holder.tvFeriadoB.setCompoundDrawablesRelativeWithIntrinsicBounds(setarCorDoDia(dia.getDiaDaSemana()), 0, 0, 0);
            holder.tvFeriadoB.setCompoundDrawablePadding(10);
        }else {
            holder.tvFeriadoB.setVisibility(View.INVISIBLE);
        }

        if(listaDeFeriados.size() > 2) {
            dia = listaDeFeriados.get(2);
            feriado = dia.getDiaDoMes() + " - " + dia.getFeriado();
            holder.tvFeriadoC.setText(feriado);
            holder.tvFeriadoC.setCompoundDrawablesRelativeWithIntrinsicBounds(setarCorDoDia(dia.getDiaDaSemana()), 0, 0, 0);
            holder.tvFeriadoC.setCompoundDrawablePadding(10);
        }else {
            holder.tvFeriadoC.setVisibility(View.INVISIBLE);
        }
    }

    public int setarCorDoDia(String diaDaSemana) {
        int ponto = 0;

        switch (diaDaSemana) {
            case "SUNDAY":
                ponto = R.drawable.ponto_1;
                break;
            case "MONDAY":
                ponto = R.drawable.ponto_2;
                break;
            case "TUESDAY":
                ponto = R.drawable.ponto_3;
                break;
            case "WEDNESDAY":
                ponto = R.drawable.ponto_4;
                break;
            case "THURSDAY":
                ponto = R.drawable.ponto_5;
                break;
            case "FRIDAY":
                ponto = R.drawable.ponto_6;
                break;
            case "SATURDAY":
                ponto = R.drawable.ponto_7;
                break;
        }
        return ponto;
    }

}
