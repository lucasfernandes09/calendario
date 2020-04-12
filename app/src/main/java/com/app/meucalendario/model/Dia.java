package com.app.meucalendario.model;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.io.Serializable;
import java.time.LocalDate;

public class Dia {

    private String diaDaSemana;
    private int diaDoMes;
    private String mes;
    private int numeroDoMes;
    private int ano;
    private String feriado;

    @RequiresApi(api = Build.VERSION_CODES.O)
    public Dia(LocalDate localDate) {
        this.diaDaSemana = localDate.getDayOfWeek().name();
        this.diaDoMes = localDate.getDayOfMonth();
        this.mes = localDate.getMonth().name();
        this.numeroDoMes = localDate.getMonthValue();
        this.ano = localDate.getYear();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public Dia(LocalDate localDate, String feriado) {
        this.diaDaSemana = localDate.getDayOfWeek().name();
        this.diaDoMes = localDate.getDayOfMonth();
        this.mes = localDate.getMonth().name();
        this.numeroDoMes = localDate.getMonthValue();
        this.ano = localDate.getYear();
        this.feriado = feriado;
    }

    public String getDiaDaSemana() {
        return diaDaSemana;
    }

    public void setDiaDaSemana(String diaDaSemana) {
        this.diaDaSemana = diaDaSemana;
    }

    public int getDiaDoMes() {
        return diaDoMes;
    }

    public void setDiaDoMes(int diaDoMes) {
        this.diaDoMes = diaDoMes;
    }

    public String getMes() {
        return mes;
    }

    public void setMes(String mes) {
        this.mes = mes;
    }

    public int getNumeroDoMes() {
        return numeroDoMes;
    }

    public void setNumeroDoMes(int numeroDoMes) {
        this.numeroDoMes = numeroDoMes;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public String getFeriado() {
        return feriado;
    }

    public void setFeriado(String feriado) {
        this.feriado = feriado;
    }

}
