package com.app.meucalendario.adapter;

import android.graphics.Paint;
import android.graphics.Typeface;
import android.text.SpannableString;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.app.meucalendario.model.Dia;
import com.app.meucalendario.R;

import java.time.LocalDate;
import java.util.List;

public class AdapterDiasDoCalendario extends RecyclerView.Adapter<AdapterDiasDoCalendario.MyViewHolder>{
    private List<Dia> listaDeDiasDoMes;
    private List<Dia> listaDeFeriados;
    private LocalDate ldNow = LocalDate.now();
    private SpannableString diaDoMesSS;

    public AdapterDiasDoCalendario(List<Dia> listaDeDiasDoMes, List<Dia> listaDeFeriados) {
        this.listaDeDiasDoMes = listaDeDiasDoMes;
        this.listaDeFeriados = listaDeFeriados;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View item = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_dias_do_calendario, parent, false);
        return new MyViewHolder(item);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, int position) {
        Dia dia = listaDeDiasDoMes.get(position);

        configDia(dia, holder);
        diaAtual(dia, holder);
        if(listaDeFeriados != null) {
            configFeriados(dia, holder);
        }
    }

    @Override
    public int getItemCount() {
        return listaDeDiasDoMes.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tvDia;
        ConstraintLayout lDia;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvDia = itemView.findViewById(R.id.tvDia);
            lDia = itemView.findViewById(R.id.lDia);
        }
    }

    public void configDia(Dia dia, MyViewHolder holder) {
        if(dia.getDiaDoMes()==0) {
            holder.tvDia.setText("");
        }else {
            holder.tvDia.setText(String.valueOf(dia.getDiaDoMes()));
            //cor
            switch (dia.getDiaDaSemana()) {
                case "SUNDAY":
                    holder.lDia.setBackgroundResource(R.drawable.cor_1);
                    break;
                case "MONDAY":
                    holder.lDia.setBackgroundResource(R.drawable.cor_2);
                    break;
                case "TUESDAY":
                    holder.lDia.setBackgroundResource(R.drawable.cor_3);
                    break;
                case "WEDNESDAY":
                    holder.lDia.setBackgroundResource(R.drawable.cor_4);
                    break;
                case "THURSDAY":
                    holder.lDia.setBackgroundResource(R.drawable.cor_5);
                    break;
                case "FRIDAY":
                    holder.lDia.setBackgroundResource(R.drawable.cor_6);
                    break;
                case "SATURDAY":
                    holder.lDia.setBackgroundResource(R.drawable.cor_7);
            }
        }
    }

    public void diaAtual(Dia dia, MyViewHolder holder) {
        if(dia.getDiaDoMes()==ldNow.getDayOfMonth() && dia.getMes().equals(ldNow.getMonth().name()) && dia.getAno()==ldNow.getYear()) {
            //holder.tvDia.setBackgroundResource(R.drawable.bordas);
            holder.tvDia.setTypeface(null, Typeface.BOLD_ITALIC);
        }
    }

    public void configFeriados(Dia dia, MyViewHolder holder) {
        for(Dia feriado : listaDeFeriados) {
            if(feriado.getDiaDoMes() == dia.getDiaDoMes()) {
                /*diaDoMesSS = new SpannableString(String.valueOf(dia.getDiaDoMes()));
                diaDoMesSS.setSpan(new UnderlineSpan(), 0, String.valueOf(dia.getDiaDoMes()).length(), 0);
                holder.tvDia.setText(diaDoMesSS);*/
                holder.tvDia.setPaintFlags(holder.tvDia.getPaintFlags()|Paint.UNDERLINE_TEXT_FLAG);
            }
        }
    }


}
