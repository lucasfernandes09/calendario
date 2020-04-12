package com.app.meucalendario;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import com.app.meucalendario.adapter.AdapterCalendario;
import com.app.meucalendario.model.Dia;
import com.app.meucalendario.model.Feriados;
import com.app.meucalendario.model.Mes;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    private List<Mes> listaDeMeses = new ArrayList<>();
    private List<Dia> listaDeDiasDoMes, listaDeFeriados;
    private Mes mes;
    private Dia dia;
    private RecyclerView rvCalendario;
    private int mesAtual = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //remover ActionBar
        Objects.requireNonNull(getSupportActionBar()).hide();

        //período do calendário
        LocalDate ldInicial = LocalDate.of(2000, 1, 1);
        LocalDate ldFinal = LocalDate.of(2040, 12, 31);
        Period periodo = Period.between(ldInicial, ldFinal);

        criarMeses(periodo, ldInicial);

        criarDias();

        mesAtual();

        exibirCalendario();

    }

    public void criarMeses(Period periodo, LocalDate ldInicial) {
        LocalDate ld;
        for(int i=0; i<periodo.toTotalMonths(); i++) {
            ld = ldInicial.plusMonths(i);
            mes = new Mes(Mes.setNomeDoMes(ld.getMonth().name()), ld.getMonthValue(), ld.getYear(), ld.lengthOfMonth());
            listaDeMeses.add(mes);
        }
    }

    public void criarDias() {
        for(int i=0; i<listaDeMeses.size(); i++) {
            mes = listaDeMeses.get(i);
            listaDeDiasDoMes = new ArrayList<>();
            for(int j=0; j<mes.getNumeroDeDias(); j++) {
                LocalDate ld = LocalDate.of(mes.getAno(), mes.getNumero(), j+1);
                dia = new Dia(ld);
                if(dia.getDiaDoMes()==1) {
                    posicionarDias();
                }else {
                    listaDeDiasDoMes.add(dia);
                }
            }
            //completar dias do calendário
            adicionarDiasVazios((42 - listaDeDiasDoMes.size()), new Dia(LocalDate.of(mes.getAno(), mes.getNumero(), dia.getDiaDoMes())));

            mes.setListaDeDiasDoMes(listaDeDiasDoMes);

            adicionarFeriados(mes);
        }
    }

    public void mesAtual() {
        LocalDate ldNow = LocalDate.now();
        for(int i=0; i<listaDeMeses.size(); i++) {
            mes = listaDeMeses.get(i);
            if(mes.getNumero()==ldNow.getMonthValue() && mes.getAno()==ldNow.getYear()) {
                mesAtual = listaDeMeses.indexOf(mes);
            }
        }
    }

    public void exibirCalendario() {
        rvCalendario = findViewById(R.id.rvCalendario);
        AdapterCalendario adapterCalendario = new AdapterCalendario(listaDeMeses);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext(), RecyclerView.HORIZONTAL, false);
        rvCalendario.setLayoutManager(layoutManager);
        layoutManager.scrollToPosition(mesAtual);
        SnapHelper snapHelper = new PagerSnapHelper();
        snapHelper.attachToRecyclerView(rvCalendario);
        rvCalendario.setHasFixedSize(true);
        rvCalendario.setAdapter(adapterCalendario);
    }

    public void posicionarDias() {
        Dia diaVazio = new Dia(LocalDate.of(mes.getAno(), mes.getNumero(), dia.getDiaDoMes()));

        switch (dia.getDiaDaSemana()) {
            case "SUNDAY":
                listaDeDiasDoMes.add(dia);
                break;
            case "MONDAY":
                adicionarDiasVazios(1, diaVazio);
                listaDeDiasDoMes.add(dia);
                break;
            case "TUESDAY":
                adicionarDiasVazios(2, diaVazio);
                listaDeDiasDoMes.add(dia);
                break;
            case "WEDNESDAY":
                adicionarDiasVazios(3, diaVazio);
                listaDeDiasDoMes.add(dia);
                break;
            case "THURSDAY":
                adicionarDiasVazios(4, diaVazio);
                listaDeDiasDoMes.add(dia);
                break;
            case "FRIDAY":
                adicionarDiasVazios(5, diaVazio);
                listaDeDiasDoMes.add(dia);
                break;
            case "SATURDAY":
                adicionarDiasVazios(6, diaVazio);
                listaDeDiasDoMes.add(dia);
                break;
        }
    }

    public void adicionarDiasVazios(int qtd, Dia diaVazio) {
        diaVazio.setDiaDoMes(0);
        for(int i=0; i<qtd; i++) {
            listaDeDiasDoMes.add(diaVazio);
        }
    }

    public void adicionarFeriados(Mes mes) {
        List<Dia> listaDeTodosFeriados = new ArrayList<>();
        listaDeTodosFeriados.addAll(Feriados.feriadosFixos(mes.getAno()));
        listaDeTodosFeriados.addAll(Feriados.feriadosMoveis(mes.getAno()));

        listaDeFeriados = new ArrayList<>();
        for(int i=0; i<listaDeTodosFeriados.size(); i++) {
            if(listaDeTodosFeriados.get(i).getNumeroDoMes() == mes.getNumero()) {
                listaDeFeriados.add(listaDeTodosFeriados.get(i));
            }
        }
        mes.setListaDeFeriados(listaDeFeriados);
    }

    public void scrollToPosition(View view) {
        rvCalendario.scrollToPosition(mesAtual);
        //para problemas de travamento no scroll(função lambda)
        new Handler().postDelayed(() -> rvCalendario.smoothScrollToPosition(mesAtual), 50);
    }


}
