package com.app.meucalendario.model;

import java.util.List;

public class Mes {

    private String nome;
    private int numero;
    private int ano;
    private int numeroDeDias;
    private List<Dia> listaDeDiasDoMes;
    private List<Dia> listaDeFeriados;

    public Mes(String nome, int numero, int ano, int numeroDeDias) {
        this.nome = nome;
        this.numero = numero;
        this.ano = ano;
        this.numeroDeDias = numeroDeDias;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public List<Dia> getListaDeDiasDoMes() {
        return listaDeDiasDoMes;
    }

    public void setListaDeDiasDoMes(List<Dia> listaDeDiasDoMes) {
        this.listaDeDiasDoMes = listaDeDiasDoMes;
    }

    public int getNumeroDeDias() {
        return numeroDeDias;
    }

    public void setNumeroDeDias(int numeroDeDias) {
        this.numeroDeDias = numeroDeDias;
    }

    public List<Dia> getListaDeFeriados() {
        return listaDeFeriados;
    }

    public void setListaDeFeriados(List<Dia> listaDeFeriados) {
        this.listaDeFeriados = listaDeFeriados;
    }

    public static String setNomeDoMes(String nome) {
        String nomeTraduzido = "";

        switch (nome) {
            case "JANUARY":
                nomeTraduzido = "JAN";
                break;
            case "FEBRUARY":
                nomeTraduzido = "FEV";
                break;
            case "MARCH":
                nomeTraduzido = "MAR";
                break;
            case "APRIL":
                nomeTraduzido = "ABR";
                break;
            case "MAY":
                nomeTraduzido = "MAI";
                break;
            case "JUNE":
                nomeTraduzido = "JUN";
                break;
            case "JULY":
                nomeTraduzido = "JUL";
                break;
            case "AUGUST":
                nomeTraduzido = "AGO";
                break;
            case "SEPTEMBER":
                nomeTraduzido = "SET";
                break;
            case "OCTOBER":
                nomeTraduzido = "OUT";
                break;
            case "NOVEMBER":
                nomeTraduzido = "NOV";
                break;
            case "DECEMBER":
                nomeTraduzido = "DEZ";
        }
        return nomeTraduzido;
    }
}
